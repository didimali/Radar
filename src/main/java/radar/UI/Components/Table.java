package radar.UI.Components;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import radar.SpringUtil;
import radar.Entity.Fault;
import radar.Entity.Record;
import radar.ServiceImpl.FaultRecordServiceImpl;
import radar.ServiceImpl.RecordServiceImpl;
import radar.SwingWorker.SwingWorkerForTable;
import radar.Tools.TableStyleUI;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Table extends JTable {
	
	private static final long serialVersionUID = 1L;
	// JTable表分页信息相关变量
    public int currentPage = 1;
    private int pageCount = 10;
    private int totalPage = 0;
    private int totalRowCount = 0;
    private int column = 0;
    private int restCount;
    //表格加载的数据
    private Object[][] resultData;
    private DefaultTableModel model = null;
    
    //表格将要调用的ServiceImpl类的名字
    private String ServiceImplName;
    private String methodName;
    public  String[] header;
	
    /**
     * @param ServiceImplName:表格将要调用的ServiceImpl类的名字
     * @param methodName:表格将要调用的ServiceImpl类中方法的名字
     * @param header:表格表头
     */
	public Table(String ServiceImplName,String methodName,String[] header) {
    	setBackground(Color.WHITE);
		this.ServiceImplName = ServiceImplName;
		this.methodName = methodName;
		this.header = header;
		initTable();
	}

	private void initTable() {
//		getDataForTable g = new getDataForTable(this, "TestServiceImpl","getManagers");
		SwingWorkerForTable swt = new SwingWorkerForTable(this, ServiceImplName,methodName);
		swt.execute();
//		Object[][] data = new Object[1][2];
//        if (data != null) {
//            initResultData(data);
//            model = new DefaultTableModel(getPageData(), columnNames);
//        } else {
//            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
            Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {},{}, {}, {}, {}, {}, {}};
            model = new DefaultTableModel(nothing, header);
            totalRowCount = 0;
//        }
        this.setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        setStyle();
	}
	
	public void init(Object[][] data) {
		if (data != null) {
            initResultData(data);
            model = new DefaultTableModel(getPageData(), header);
        } else {
            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
            Object[][] nothing = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {},{}, {}, {}, {}, {}, {}};
            model = new DefaultTableModel(nothing, header);
            totalRowCount = 0;
        }
        setModel(model);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, r);
        setStyle();
	}
	
	public void setStyle() {
		//设置表格样式
        TableStyleUI ui = new TableStyleUI();
        ui.makeFace(this);
	}
	
	/**
     * 获取下一页
     */
    public int getNextPage() {
        if (this.currentPage != this.totalPage) {
            return ++currentPage;
        }
        return -1;
    }

    /**
     * 获取上一页
     */
    public int getPreviousPage() {
        if (this.currentPage != 1) {
            return --currentPage;
        }
        return -1;
    }

    /**
     * 获取最后一页
     */
    public int getLastPage() {
    	currentPage = totalPage;
        return this.totalPage;
    }

    /**
     * 获取第一页
     */
    public int getFirstPage() {
    	currentPage = 1;
        return 1;
    }

    /**
     * 获取总页数
     */
    public int getTotolPage() {
        return this.totalPage;
    }

    /**
     * 获取当前页
     */
    public int getCurrentPage() {
        return this.currentPage;
    }
    /**
     * 获取分页数据
     * 
     * @return
     */
    public Object[][] getPageData() {
        Object[][] currentPageData = new Object[pageCount][column];// 构造每页数据集
        if (this.getCurrentPage() < this.totalPage) {// 如果当前页数小于总页数，那么每页数目应该是规定的数pageCount
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + pageCount; i++) {
                for (int j = 0; j < column; j++) {
                    // 把结果集中对应每页的每一行数据全部赋值给当前页的每一行的每一列
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        } else {
            // 在动态改变数据结果集的时候，如果当前页没有数据了，则回到前一页（一般针对最后一页而言）
            if (pageCount * (this.getCurrentPage() - 1) >= totalRowCount)
                this.currentPage--;
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + restCount; i++) {
                for (int j = 0; j < column; j++) {
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        }
        return currentPageData;
    }
    public void initResultData(Object[][] data) {
        if (data != null) {
            resultData = data;// 总的结果集
            column = data[0].length;// 表的列数
            totalRowCount = data.length;// 表的长度
            currentPage = 1;
            totalPage = totalRowCount % pageCount == 0 ? totalRowCount
                    / pageCount : totalRowCount / pageCount + 1;// 结果集的总页数
            restCount = totalRowCount % pageCount == 0 ? 10 : totalRowCount
                    % pageCount;// 最后一页的数据数
        }
    }
//根据条件筛选开机活动记录表
    public void getRecordByRadarAndTime(String radarNumber, String startTime, String endTime) {
		// TODO Auto-generated method stub
    	 SpringUtil s = new SpringUtil();
    	 RecordServiceImpl RecordServiceImpl = (RecordServiceImpl) s.getBean("RecordServiceImpl"); 
     	List<Record> records =RecordServiceImpl.getAllRecords();
    	 Object[][] data = getDataByConditions(records,radarNumber,startTime,endTime);
    	if (data != null&&data.length>0) {
			  initResultData(data);
			  model = new DefaultTableModel(getPageData(), header);
		  } 
		  else { 
			  //如果结果集中没有数据，那么就用空来代替数据集中的每一行 
			  Object[][] nothing = { {},{}, {}, {}, {}, {},{} };
			  model = new DefaultTableModel(nothing, header);
			  totalRowCount = 0;
		  }
		  setModel(model);
		  DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	        r.setHorizontalAlignment(JLabel.CENTER);
	        setDefaultRenderer(Object.class, r);
	        setStyle();
	}

	private Object[][] getDataByConditions(List<Record> record, String radarNumber, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		if(record.size()>0){
			  Object[][] data = new Object[record.size()][5];
	            //data的first size;
	            int dataCounts = 0;
	            for(int i=0;i<record.size();i++){
	            	Record r = record.get(i);
	            	Boolean addItems = false;
	            	String IsDefault;
	            	String HRadarNumber;
	            	if(r.getRadarId()!=null) {
	            		HRadarNumber= r.getRadarId().getRadarName();
	            	}else {
	            		HRadarNumber="";
	            	}
	            	Date BRadarStartTime = r.getRecordStartDate();
	            	Date BRadrEndTime = r.getRecordEndDate();
            	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                  String HRadarStartTime = sdf.format(BRadarStartTime); // 把带时分秒的 date 转为 yyyy-MM-dd 格式的字符串
                  String HRadarEndTime = sdf.format(BRadrEndTime); // 把带时分秒的 date 转为 yyyy-MM-dd 格式的字符串


	            	if(r.getWithFault()==0){
	            		IsDefault="否";
	            	}else{
	            		IsDefault="是";

	            	}
	            	String activityName;
	            	if(r.getActivityId()!=null) {
	            		activityName=r.getActivityId().getActivityName();
	            	}else {
	            		activityName="";
	            	}
	            	
	            	//只有雷达编号
	            	if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
        			   &&(startTime==null||startTime.equals(""))
            		   &&(endTime==null||endTime.equals(""))){
	            		addItems=true;
	            	}
	            	//只有开机时间
	            
	            	else if((startTime==HRadarStartTime||startTime.equals(HRadarStartTime))
	            			   &&(radarNumber==null||radarNumber.equals(""))
		            		   &&(endTime==null||endTime.equals(""))){
	            		addItems=true;
	            	}
	            	//只有关机时间
	            	else if((startTime==null||startTime.equals(""))
	            			   &&(radarNumber==null||radarNumber.equals(""))
		            		   &&(endTime==HRadarEndTime||endTime.equals(HRadarEndTime))){
	            		addItems=true;

	            	}
	            	//雷达编号、开机时间、关机时间都有
	            	else if((startTime==HRadarStartTime||startTime.equals(HRadarStartTime))
	            			   &&(radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
		            		   &&(endTime==HRadarEndTime||endTime.equals(HRadarEndTime))){
	            		addItems=true;

	            	}
	            	//只有雷达编号、开机时间
	            	else if((startTime==HRadarStartTime||startTime.equals(HRadarStartTime))
	            			   &&(radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
		            		   &&(endTime==null||endTime.equals(""))){
	            		addItems=true;
	            	}
	            	//只有雷达编号、关机时间
	            	else if((startTime==null||startTime.equals(""))
	            			   &&(radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
		            		   &&(endTime==HRadarEndTime||endTime.equals(HRadarEndTime))){
	            		addItems=true;

	            	}
	            	//只有开机时间、关机时间
	            	else if((startTime==HRadarStartTime||startTime.equals(HRadarStartTime))
	            			&&(endTime==HRadarEndTime||endTime.equals(HRadarEndTime))
	            			&&(radarNumber==null||radarNumber.equals(""))){
	            		addItems=true;
	            	}
	            	else if((startTime==null||startTime.equals(""))
	            			&&(endTime==null||endTime.equals(""))
	            			&&(radarNumber==null||radarNumber.equals(""))){
	            		addItems=true;

	            	}
	            
	            	if(addItems) {
	    				Object[] a = {r.getRecordId(),dataCounts+1,HRadarNumber,r.getRecordStartDate(),r.getRecordEndDate(),activityName,IsDefault};
	                    data[dataCounts] = a;// 把数组的值赋给二维数组的一行
	                    dataCounts++;
	            	}
	            }
	            Object[][] result = new Object[dataCounts][5];
	            for(int i=0;i<dataCounts;i++) {
	            	result[i] = data[i];
	            }
	            return result;
		}
		return null;
	}
	//根据条件筛选故障记录表
	public void getFaultRecordByRadarAndTime(String radarNumber, String faultType, String faultDateText) {
		 SpringUtil s = new SpringUtil();
		  FaultRecordServiceImpl faultRecordServiceImpl = (FaultRecordServiceImpl) s.getBean("FaultRecordServiceImpl"); 
		  List<Fault> faults = faultRecordServiceImpl.getAllFaultRecords();
		Object[][] data = getFaultDataByConditions(faults,radarNumber,faultType,faultDateText);

		if (data != null&&data.length>0) {
			  initResultData(data);
			  model = new DefaultTableModel(getPageData(), header);
		  } 
		  else { 
			  //如果结果集中没有数据，那么就用空来代替数据集中的每一行 
			  Object[][] nothing = { {},{}, {}, {}, {}, {},{} };
			  model = new DefaultTableModel(nothing, header);
			  totalRowCount = 0;
		  }
		  setModel(model);
		  DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	        r.setHorizontalAlignment(JLabel.CENTER);
	        setDefaultRenderer(Object.class, r);
	        setStyle();
	}
	private Object[][] getFaultDataByConditions(List<Fault> fault, String radarNumber, String faultType,
			String faultDateText) {
		// TODO Auto-generated method stub
		if(fault.size()>0){
			  Object[][] data = new Object[fault.size()][5];
	            //data的first size;
	            int dataCounts = 0;
	            for(int i=0;i<fault.size();i++){
	            	Fault f = fault.get(i);
	            	Boolean addItems = false;
	            	//查询结果中的雷达编号
	            	String HRadarNumber;
	            	if(f.getRecordId()!=null&&f.getRecordId().getRadarId()!=null) {
	            		HRadarNumber=f.getRecordId().getRadarId().getRadarName();
	            	}else {
	            		HRadarNumber="";
	            	}
	            	//查询结果中的故障类型
	            	String HFaultName;
	            	if(f.getFaultTypeId()!=null) {
	            		HFaultName=f.getFaultTypeId().getFaultName();
	            	}else {
	            		HFaultName="";
	            	}
	            	Date BFaultDate = f.getFaultDate();
	            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            	//查询结果中的故障发生时刻
	            	String HFaultDate = sdf.format(BFaultDate); // 把带时分秒的 date 转为 yyyy-MM-dd 格式的字符串
	            	String radarName;
	            	if(f.getRecordId()!=null&&f.getRecordId().getRadarId()!=null) {
	            		radarName =f.getRecordId().getRadarId().getRadarName();
	            	}else {
	            		radarName="";
	            	}
	            	String faultName;
	            	if(f.getFaultTypeId()!=null) {
	            		faultName=f.getFaultTypeId().getFaultName();
	            	}else {
	            		faultName="";
	            	}
	            	//只有雷达编号
	            	if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
      			   &&(faultType==null||faultType.equals(""))
          		   &&(faultDateText==null||faultDateText.equals(""))){
	            		addItems=true;

	            	}
	            	//只有故障类型
	            
	            	else if((faultType==HFaultName||faultType.equals(HFaultName))
	            			   &&(radarNumber==null||radarNumber.equals(""))
		            		   &&(faultDateText==null||faultDateText.equals(""))){
	            		addItems=true;
	            	}
	            	//只有故障发生时刻
	            	else if((faultDateText==HFaultDate||faultDateText.equals(HFaultDate))
	            			   &&(radarNumber==null||radarNumber.equals(""))
		            		   &&(faultType==null||faultType.equals(""))){
	            		addItems=true;

	            	}
	            	//雷达编号、故障类型、时刻
	            	else if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
	            			   &&(faultType==HFaultName||faultType.equals(HFaultName))
		            		   &&(faultDateText==HFaultDate||faultDateText.equals(HFaultDate))){
	            		addItems=true;

	            	}
	            	//只有雷达编号、故障时刻
	            	else if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
	            			   &&(faultDateText==HFaultDate||faultDateText.equals(HFaultDate))
		            		   &&(faultType==null||faultType.equals(""))){
	            		addItems=true;
	            	}
	            	//只有雷达编号、故障类型
	            	else if((radarNumber==HRadarNumber||radarNumber.equals(HRadarNumber))
	            			   &&(faultType==HFaultName||faultType.equals(HFaultName))
		            		   &&(faultDateText==null||faultDateText.equals(""))){
	            		addItems=true;

	            	}
	            	//只有故障类型、故障时刻
	            	else if((faultType==HFaultName||faultType.equals(HFaultName))
	            			&&(faultDateText==HFaultDate||faultDateText.equals(HFaultDate))
	            			&&(radarNumber==null||radarNumber.equals(""))){
	            		addItems=true;
	            	}
	            	else if((radarNumber==null||radarNumber.equals(""))
	            			&&(faultType==null||faultType.equals(""))
	            			&&(faultDateText==null||faultDateText.equals(""))){
	            		addItems=true;

	            	}
	            
	            	if(addItems) {
	    				Object[] a = {f.getFaultId(),dataCounts+1,radarName,faultName,f.getFaultDate(),f.getFaultLocation(),f.getFaultReason()};
	                    data[dataCounts] = a;// 把数组的值赋给二维数组的一行
	                    dataCounts++;
	            	}
	            }
	            Object[][] result = new Object[dataCounts][5];
	            for(int i=0;i<dataCounts;i++) {
	            	result[i] = data[i];
	            }
	            return result;
		}
		return null;
	}

}
