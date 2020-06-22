package radar.UI.ContentPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.Entity.Activity;
import radar.Entity.BasicInfo;
import radar.Entity.DynamicData;
import radar.Entity.Fault;
import radar.Entity.Manager;
import radar.Entity.Radar;
import radar.Entity.Record;
import radar.Entity.faultType;
import radar.ServiceImpl.ActivityServiceImpl;
import radar.ServiceImpl.BasicInfoServiceImpl;
import radar.ServiceImpl.DynamicDataServiceImpl;
import radar.ServiceImpl.FaultRecordServiceImpl;
import radar.ServiceImpl.FaultTypeServiceImpl;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.ServiceImpl.RecordServiceImpl;
import radar.Tools.ExportExcel;
import radar.UI.Components.Button;
import radar.UI.Components.Chooser;
import radar.UI.Components.ContentPanel;
import radar.UI.Components.ManagerComBox;
import radar.UI.Components.RadarComBox;

public class RadarInAndOut extends ContentPanel implements InterfaceForContentPanel{

	/**
	 * 雷达管理-数据管理-导入导出
	 */
	private static final long serialVersionUID = -9063602874808041474L;
		//标题
		private JLabel LabelForTitle;
		//分割线
		private JSeparator separator;
		//雷达所属部队标签及下拉框
		private JLabel RadarManager;
		@SuppressWarnings("rawtypes")
		private JComboBox ManagerName;
		//雷达编号及下拉框
		private JLabel RadarNumber;
		@SuppressWarnings("rawtypes")
		private JComboBox RadarName;
		
		//数据类型及下拉框
		private JLabel DataType;
		@SuppressWarnings("rawtypes")
		private JComboBox  TypeName;
		//起始时间
		private JLabel StartTime;
		private	JTextField startDate;
		//终止时间
		private JLabel EndTime;
		private	JTextField endDate;
		//导入、导出
		private JButton InButton;
		private JButton OutButton;
		public static File chooseFile;  
	     private static JFileChooser fileChooser;
		public RadarInAndOut() {
			init();
			Action();
		}
	@Override
	public void init() {
		initContentTop();	
		contentTop.add(LabelForTitle,"cell 0 0,grow");
		contentTop.add(separator,"cell 0 1,grow");

		initContentBody();
		//部队
		ContentBody.add(RadarManager,"cell 1 1,grow");
		ContentBody.add(ManagerName,"cell 2 1,grow");
		//雷达
		ContentBody.add(RadarNumber,"cell 1 4,grow");
		ContentBody.add(RadarName,"cell 2 4,grow");
		//数据类型
		ContentBody.add(DataType,"cell 1 7,grow");
		ContentBody.add(TypeName,"cell 2 7,grow");
		//起始时间
		Chooser chooser1 = Chooser.getInstance();
		startDate = new JTextField();
		startDate.setToolTipText("起始时间");
		startDate.setHorizontalAlignment(SwingConstants.CENTER);
	    chooser1.register(startDate);
		ContentBody.add(StartTime,"cell 1 10,grow");
		ContentBody.add(startDate,"cell 2 10,grow");
		//终止时间
		Chooser chooser2 = Chooser.getInstance();
		endDate = new JTextField();
		endDate.setToolTipText("终止时间");
		endDate.setHorizontalAlignment(SwingConstants.CENTER);
		endDate.setBounds(372, 20, 80, 21);
	    chooser2.register(endDate);
		
	    ContentBody.add(EndTime,"cell 1 13,grow");
	    ContentBody.add(endDate,"cell 2 13,grow");
	    //导入、导出
	    initContentFoot();
	    contentFoot.add(InButton,"cell 3 1,grow");
	    contentFoot.add(OutButton,"cell 6 1,grow");
	}
	//弹出文件选择框
	Integer returnValue =null;
	private int importExcel() {
		 fileChooser = new JFileChooser();             
	     //过滤Excel文件，只寻找以xls结尾的Excel文件，如果想过滤word文档也可以写上doc
	     FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "xls");
	     fileChooser.setFileFilter(filter);      
	     returnValue = fileChooser.showOpenDialog(null);    
	     return returnValue;
	}
	private String getFilePath() {
		//这里为导出文件存放的路径	
//		filePath ="e:\\Users\\USER" + UUID.randomUUID() + "\\";	
		  String	filePath="";
	      JFileChooser jfc=new JFileChooser();
	      jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		  int chooseFlag  =  jfc.showSaveDialog(null);
		  if(chooseFlag==JFileChooser.APPROVE_OPTION) {
		  File f=jfc.getSelectedFile();
		  filePath =f.getAbsolutePath()+"\\";
			//每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件	
			File file = new File(filePath);	
			if (!file.exists()) {		
				file.mkdirs();	
			} 
	  }
	return filePath;
	}
	@Override
	public void Action() {
		//部队下拉框事件（更新雷达下拉框数据）
		ManagerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((RadarComBox) RadarName).initRadar(ManagerName.getSelectedItem().toString());
				}
				catch(Exception execption) {
					execption.printStackTrace();
				}
			}
		});
		//雷达下拉框事件（更新部队下拉框数据）
		RadarName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					((ManagerComBox) ManagerName).initData(RadarName.getSelectedItem().toString());
				}
				catch(Exception execption) {
					execption.printStackTrace();
				}
			}
		});
		InButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "static-access", "null" })
			@Override
			public void mouseClicked(MouseEvent e) {
			String dateType=(String)TypeName.getSelectedItem();
			//导入开机记录
			boolean flagk =false;
			boolean flagg =false;
			boolean flagj = false;
			if(dateType.equals("")) {
				JOptionPane.showMessageDialog(null, "请选择导入的数据类型", "标题",JOptionPane.WARNING_MESSAGE);  
			}else if(dateType.equals("开机记录")) {//kaiji
					importExcel();
			        //弹出一个文件选择提示框
			        if (returnValue == fileChooser.APPROVE_OPTION) {
			        //当用户选择文件后获取文件路径
			        chooseFile = fileChooser.getSelectedFile();
			        //根据文件路径初始化Excel工作簿
			        Workbook workBook=null;
			         try {
			                 workBook = Workbook.getWorkbook(chooseFile);
			         } catch (Exception event) {	
			        	 event.printStackTrace();
			         } 
			          //获取该工作表中的第一个工作表   
			          Sheet sheet=workBook.getSheet(0);  
			          //获取该工作表的行数，以供下面循环使用   
			          int rowSize=sheet.getRows();  
			          SpringUtil s = new SpringUtil();
		    		  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
		    		  List<Radar> r = radarServiceImpl.getAllRadars();
		    		  ActivityServiceImpl activityServiceImpl =(ActivityServiceImpl)s.getBean("ActivityServiceImpl");
		    		 List<Activity> a =activityServiceImpl.getAllActivity();
			        if(rowSize>1) {
			   		 for(int i=1;i<rowSize;i++) {
			    		  Record record = new Record();
			        	  String RName = sheet.getCell(0,i).getContents();
			        	  Cell startTime = sheet.getCell(1, i);
			        	  if(startTime.getType()==CellType.DATE) {
			        		     DateCell dcs = (DateCell) startTime;
			                     java.util.Date startDate = dcs.getDate();
			                     TimeZone gmt = TimeZone.getTimeZone("GMT");
			                     SimpleDateFormat sdf = new SimpleDateFormat(
			                             "yyyy-MM-dd HH:mm:ss",Locale.getDefault());
			                     sdf.setTimeZone(gmt);
				                String sDate = sdf.format(startDate);
				                TimeZone local = TimeZone.getDefault();
				                sdf.setTimeZone(local);

					        	  java.util.Date sd = null;

					        	  try {
									sd = sdf.parse(sDate);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					        	  record.setRecordStartDate(sd);
			        	  }else {
				        	  String strStartTime = sheet.getCell(1,i).getContents().toString();
				        	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        	  java.util.Date startDate = null;
			        		  try {
								startDate = sdf.parse(strStartTime);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        	  record.setRecordStartDate(startDate);
			        	  }
			        	  Cell endTime = sheet.getCell(2, i);
			        	  if(endTime.getType()==CellType.DATE) {
			        		  DateCell dcs = (DateCell) endTime;
			                     java.util.Date endDate = dcs.getDate();
			                     TimeZone gmt = TimeZone.getTimeZone("GMT");

			                     SimpleDateFormat sdf = new SimpleDateFormat(
			                             "yyyy-MM-dd HH:mm:ss",Locale.getDefault());
			                     sdf.setTimeZone(gmt);
				                String eDate = sdf.format(endDate);
				                TimeZone local = TimeZone.getDefault();
				                sdf.setTimeZone(local);
					        	  java.util.Date ed = null;
					        	  try {
									ed = sdf.parse(eDate);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					        	  record.setRecordEndDate(ed);
			        	  }else {
				        	  String strEndTime = sheet.getCell(1,i).getContents().toString();
				        	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        	  java.util.Date endDate = null;
				        	  try {
								endDate = sdf.parse(strEndTime);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        	  record.setRecordEndDate(endDate);
			        	  }
			        	  String activityTarget = sheet.getCell(3,i).getContents();
			        	  String isDefault = sheet.getCell(4,i).getContents();
			        	  if(r!=null||r.size()>0) {
			        		  for(int j=0;j<r.size();j++) {
			    			  System.out.println(r.get(j).getRadarName().toString());
			    			  if(r.get(j).getRadarName().toString().equals(RName)||r.get(j).getRadarName().toString()==RName) {
			    				  record.setRadarId(r.get(j)); 
			    			  }
			    		  }
			        	  }
			    		  for(int k =0;k<a.size();k++) {
			    			  if(a.get(k).getActivityName().toString().equals(activityTarget)||a.get(k).getActivityName().toString()==activityTarget) {
			    				  record.setActivityId(a.get(k));
			    			  }
			    		  }
			    		 if(isDefault.equals("是")) {
			    			 record.setWithFault(1);
			    			 
			    		 }else if(isDefault.equals("否")){
			    			 record.setWithFault(0);

			    		 }
			        	 //添加record 
			    		 RecordServiceImpl recordServiceImpl =(RecordServiceImpl)s.getBean("RecordServiceImpl");
			    		 flagk = recordServiceImpl.add(record);
			    		 
			          }
			        }
			          if(flagk) {
				            JOptionPane.showMessageDialog(null, "开机记录成功导入");
				            flagk = true;
			    			 System.out.println("成功存储开机记录");
			    		 }
			        }
			  //导入故障记录      
			}else if(dateType.equals("故障记录")) {
				importExcel();
		        //弹出一个文件选择提示框
		        if (returnValue == fileChooser.APPROVE_OPTION) {
		        //当用户选择文件后获取文件路径
		        chooseFile = fileChooser.getSelectedFile();
		        
		        //根据文件路径初始化Excel工作簿
		        Workbook workBook=null;
		         try {
		                 workBook = Workbook.getWorkbook(chooseFile);
		         } catch (Exception event) {	
		        	 event.printStackTrace();
		         } 
		          //获取该工作表中的第一个工作表   
		          Sheet sheet=workBook.getSheet(0);  
		          //获取该工作表的行数，以供下面循环使用   
		          int rowSize=sheet.getRows();  
		          SpringUtil s = new SpringUtil();
		          RecordServiceImpl recordServiceImpl =(RecordServiceImpl)s.getBean("RecordServiceImpl");
		          FaultRecordServiceImpl faultRecordServiceImpl =(FaultRecordServiceImpl)s.getBean("FaultRecordServiceImpl");
		          List<Record> records =recordServiceImpl.getAllRecords();
		          FaultTypeServiceImpl faultTypeServiceImpl =(FaultTypeServiceImpl)s.getBean("FaultTypeServiceImpl");
		          List<faultType> faultTypes = faultTypeServiceImpl.getAllFaultType();
		          for(int i=1;i<rowSize;i++) {
		        	  Fault fault = new Fault();
		        	  String RName = sheet.getCell(0,i).getContents().toString();
		        	  String faultType = sheet.getCell(1,i).getContents().toString();
		        	  Cell faultDate = sheet.getCell(2, i);
		        	  String faultLocation = sheet.getCell(3,i).getContents().toString();
		        	  String faultReason = sheet.getCell(4,i).getContents().toString();
		        	  java.util.Date fd = null;
		        	  if(faultDate.getType()==CellType.DATE) {
		        		  DateCell dcs = (DateCell) faultDate;
		                     java.util.Date fDate = dcs.getDate();
		                     TimeZone gmt = TimeZone.getTimeZone("GMT");

		                     SimpleDateFormat sdf = new SimpleDateFormat(
		                             "yyyy-MM-dd HH:mm:ss",Locale.getDefault());
		                     sdf.setTimeZone(gmt);
			                String faultTime = sdf.format(fDate);
			                TimeZone local = TimeZone.getDefault();
			                sdf.setTimeZone(local);
				        	  try {
				        		  fd = sdf.parse(faultTime);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        	  	fault.setFaultDate(fd);
		        	  }
		        	  if(records!=null||records.size()>0) {
		        		  for(int j =0;j<records.size();j++) {
			        		  //发生故障的时刻应该位于开机与关机记录时间之间
				        	  boolean before = records.get(j).getRecordStartDate().before(fd);
				        	  boolean after = records.get(j).getRecordEndDate().after(fd);
				        	  String radarName;
				        	  if(records.get(j).getRadarId()!=null) {
				        		  radarName=records.get(j).getRadarId().getRadarName();
				        	  }else {
				        		  radarName="";
				        	  }
				        	  if(radarName.equals(RName)&&records.get(j).getWithFault()==1
				        			&&before&&after	  	) {
				        		  fault.setRecordId(records.get(j));
				        	  }
				          }
		        	  }
		        	if(faultTypes!=null||faultTypes.size()>0) {
		        		 for(int k =0;k<faultTypes.size();k++) {
		 		        	if(faultType.equals(faultTypes.get(k).getFaultName())) {
		 		        		fault.setFaultTypeId(faultTypes.get(k));;
		 		        	}
		 		        }
		        	}
		        fault.setFaultLocation(faultLocation);
		        fault.setFaultReason(faultReason);
		        flagg = faultRecordServiceImpl.add(fault);
		          }
		          if(flagg) {
		                JOptionPane.showMessageDialog(null, "故障记录成功导入");
			        	System.out.println("故障记录成功导入");
			        }
		        }
			}else if(dateType.equals("监测数据")) {
				importExcel();
		        //弹出一个文件选择提示框
		        if (returnValue == fileChooser.APPROVE_OPTION) {
		        //当用户选择文件后获取文件路径
		        chooseFile = fileChooser.getSelectedFile();
		        
		        //根据文件路径初始化Excel工作簿
		        Workbook workBook=null;
		         try {
		                 workBook = Workbook.getWorkbook(chooseFile);
		         } catch (Exception event) {	
		        	 event.printStackTrace();
		         } 
		          //获取该工作表中的第一个工作表   
		          Sheet sheet=workBook.getSheet(0);  
		          //获取该工作表的行数，以供下面循环使用   
		          int rowSize=sheet.getRows();  
		          SpringUtil s = new SpringUtil();
	    		  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
	    		  List<Radar> r = radarServiceImpl.getAllRadars();
	    		  BasicInfoServiceImpl basicInfoServiceImpl = (BasicInfoServiceImpl) s.getBean("BasicInfoServiceImpl"); 
		          List<BasicInfo> basicInfo = basicInfoServiceImpl.getAllBasicInfo();
		          DynamicDataServiceImpl dynamicDataServiceImpl = (DynamicDataServiceImpl) s.getBean("DynamicDataServiceImpl"); 

		          for(int i=1;i<rowSize;i++) {
	    			  DynamicData dynamicData = new DynamicData();
		        	  String RName = sheet.getCell(0,i).getContents().toString();
		        	  String paraName = sheet.getCell(1,i).getContents().toString();
		        	  String paraValue = sheet.getCell(2,i).getContents().toString();
		        	  Cell collectDate = sheet.getCell(3, i);
		        	  java.util.Date cd = null;
		        	  if(collectDate.getType()==CellType.DATE) {
		        		  DateCell dcs = (DateCell) collectDate;
		                     java.util.Date cDate = dcs.getDate();
		                     TimeZone gmt = TimeZone.getTimeZone("GMT");
		                     SimpleDateFormat sdf = new SimpleDateFormat(
		                             "yyyy-MM-dd HH:mm:ss",Locale.getDefault());
		                     sdf.setTimeZone(gmt);
			                String cTime = sdf.format(cDate);
			                TimeZone local = TimeZone.getDefault();
			                sdf.setTimeZone(local);
				        	  try {
				        		  cd = sdf.parse(cTime);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        	  dynamicData.setCollectDate(cd);
		        	  }
		        	  if(basicInfo!=null||basicInfo.size()>0) {
		        		  for(int j=0;j<basicInfo.size();j++) {
				        	  if(paraName.equals(basicInfo.get(j).getParamName())) {
				        		  dynamicData.setParamId(basicInfo.get(j));
				        	  }
			        	  }
		        	  }
		        	  if(r!=null||r.size()>0) {
		        		  for(int k= 0;k<r.size();k++) {
			        		  if(RName.equals(r.get(k).getRadarName())) {
			        			  dynamicData.setRadarId(r.get(k));
			        		  }
			        	  }
		        	  }
		        	  dynamicData.setDataVaule(paraValue);;
		        	  flagj = dynamicDataServiceImpl.add(dynamicData);
		          } 
		          if(flagj) {
		                JOptionPane.showMessageDialog(null, "监测数据成功导入");

			        	System.out.println("监测数据成功导入");
			        }
		        }
			}
			}
		});
		//按选择的雷达编号导出开机记录、
			OutButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "static-access", "null" })
			@Override
			public void mouseClicked(MouseEvent e) {
				//部队和始末时间都为空，雷达编号和数据类型不为空
				String dateType=(String)TypeName.getSelectedItem();
				String radarNumber=(String)RadarName.getSelectedItem();
				String managerNumber=(String)ManagerName.getSelectedItem();
				String startTimeString = startDate.getText();
				String endTimeString = endDate.getText();
				SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTimeDate1 =null;
				String startTimeDate="";
				if(!startTimeString.equals("")) {
				try {
					startTimeDate1=new Date(sdf5.parse(startTimeString).getTime());
					startTimeDate =sdf6.format(startTimeDate1);
				} catch (ParseException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
				}
				Date endTimeDate1=null;
				String endTimeDate="";
				if(!endTimeString.equals("")) {
				try {
					endTimeDate1=new Date(sdf5.parse(endTimeString).getTime());
					endTimeDate =sdf5.format(endTimeDate1);
				} catch (ParseException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}
				}
				//没有选择导出数据的类型，起始时间时，提示框；部队类型默认为all，雷达编号默认为all
				if(((dateType.equals("")||dateType.equals(null))&&(radarNumber.equals("All"))
						&&startTimeString.equals("")&&endTimeString.equals(""))&&managerNumber.equals("All")){
					JOptionPane.showMessageDialog(null, "请选择数据类型和起始时间", "标题",JOptionPane.WARNING_MESSAGE);  
					//没有选择起始时间
				}else if(((!dateType.equals("")||!dateType.equals(null))&&(radarNumber.equals("All")||!radarNumber.equals("")||radarNumber!=null)
						&&startTimeString.equals("")&&endTimeString.equals(""))&&(managerNumber.equals("All")||!managerNumber.equals("")||managerNumber!=null)) {
					JOptionPane.showMessageDialog(null, "请选择起始时间", "标题",JOptionPane.WARNING_MESSAGE);  
				}
				//导出所有部队下面的所有雷达开机记录
				else if(dateType.equals("开机记录")&&(radarNumber.equals("All"))
						&&managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
				String filePath =	getFilePath();
					
					if(!filePath.equals("")) {
						SpringUtil s1 = new SpringUtil();
					     RecordServiceImpl recordServiceImpl =(RecordServiceImpl) s1.getBean("RecordServiceImpl");
					        Object[][] record =recordServiceImpl.selectRecordByTime(startTimeDate,endTimeDate);
					     String[] header = { "雷达编号","开机时间", "关机时间","活动目的", "是否故障"};
					     ExportExcel ep = new ExportExcel();
					     boolean result = ep.exportExcel("所有雷达开机记录", filePath, header, record);
					     if(result)
					      JOptionPane.showMessageDialog(null, "导出成功");
					     else
					      JOptionPane.showMessageDialog(null, "导出失败");
					}
				}
				//导出某台雷达在某段时间内的开机记录
				else if(dateType.equals("开机记录")&&(!radarNumber.equals("All"))
				&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals(""))
				{
				String filePath =	getFilePath();
				if(!filePath.equals("")) {
					SpringUtil s1 = new SpringUtil();
				     RecordServiceImpl recordServiceImpl =(RecordServiceImpl) s1.getBean("RecordServiceImpl");
				     RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s1.getBean("RadarServiceImpl"); 
		    		 List<Radar> r = radarServiceImpl.getAllRadars();
		    		 Integer	RadarId = null;
		    		 if(r!=null||r.size()>0) {
		    			 for(int i=0;i<r.size();i++) {
			    			 if(radarNumber.equals(r.get(i).getRadarName().toString())) {
			    				 RadarId = r.get(i).getRadarId();
			    			 }
			    		 }
		    		 } 
				     Object[][] record =recordServiceImpl.getDataForRadarFaultRecord(
				    		 RadarId,startTimeDate,endTimeDate);
				     String[] header = { "雷达编号","开机时间", "关机时间","活动目的", "是否故障"};
				     ExportExcel ep = new ExportExcel();
				     boolean result = ep.exportExcel(radarNumber+"开机记录", filePath, header, record);
				     if(result)
				      JOptionPane.showMessageDialog(null, "导出成功");
				     else
				      JOptionPane.showMessageDialog(null, "导出失败");
				}
				//导出某个部队开机记录
				}else if(dateType.equals("开机记录")&&(radarNumber.equals("All"))
						&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					String	filePath=getFilePath();
					if(!filePath.equals("")) {
						SpringUtil s = new SpringUtil();
						 ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl"); 
						 List<Manager> managers =managerServiceImpl.getAllManager();
			    		 Integer	managerId = null ;
						 if(managers!=null||managers.size()>0) {

							 for(int i=0;i<managers.size();i++) {
								 if(managers.get(i).getManagerName().equals(managerNumber)) {
									 managerId=managers.get(i).getManagerId();
								 }
							 }
						 }
					     RecordServiceImpl recordServiceImpl =(RecordServiceImpl) s.getBean("RecordServiceImpl");
					     Object[][] record =recordServiceImpl.slectRecordByManager(managerId,startTimeDate,endTimeDate);
					     String[] header = { "雷达编号","开机时间", "关机时间","活动目的", "是否故障"};
					     ExportExcel ep = new ExportExcel();
					     boolean result = ep.exportExcel(managerNumber+"开机记录", filePath, header, record);
					     if(result)
					      JOptionPane.showMessageDialog(null, "导出成功");
					     else
					      JOptionPane.showMessageDialog(null, "导出失败");
					}
				}
				//导出所有部队下面所有雷达的故障记录
				else if(dateType.equals("故障记录")&&(radarNumber.equals("All"))
						&&managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
						String filePath=getFilePath();
				if(!filePath.equals("")) {
					 SpringUtil s = new SpringUtil();
		    		 FaultRecordServiceImpl faultRecordServiceImpl =(FaultRecordServiceImpl)s.getBean("FaultRecordServiceImpl");
				     Object[][] fault =faultRecordServiceImpl.selectFaultRecordByTime(startTimeDate,endTimeDate);
				     String[] header = { "雷达编号","故障类型", "发生时刻","故障部位", "原因"};
				     ExportExcel ep = new ExportExcel();
				     boolean result = ep.exportExcel("所有雷达故障记录", filePath, header, fault);
				     if(result)
				      JOptionPane.showMessageDialog(null, "导出成功");
				     else
				      JOptionPane.showMessageDialog(null, "导出失败");
				}
				}
				//导出某个部队的故障记录
				else if(dateType.equals("故障记录")&&(radarNumber.equals("All"))
						&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					String filePath =getFilePath();
				if(!filePath.equals("")) {
					 SpringUtil s = new SpringUtil();
					 ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl"); 
		    		 List<Manager> m = managerServiceImpl.getAllManager();
		    		 Integer managerId = null;
		    		 if(m!=null||m.size()>0) {
		    			 for(int i=0;i<m.size();i++) {
		    				 if(m.get(i).getManagerName().equals(managerNumber)) {
		    					managerId = m.get(i).getManagerId();
		    				 }
		    			 }
		    		 }
		    		 FaultRecordServiceImpl faultRecordServiceImpl =(FaultRecordServiceImpl)s.getBean("FaultRecordServiceImpl");
				     Object[][] fault =faultRecordServiceImpl.selectFaultRecordByManagerId(managerId,startTimeDate,endTimeDate);
				     String[] header = { "雷达编号","故障类型", "发生时刻","故障部位", "原因"};
				     ExportExcel ep = new ExportExcel();
				     boolean result = ep.exportExcel(managerNumber+"故障记录", filePath, header, fault);
				     if(result)
				      JOptionPane.showMessageDialog(null, "导出成功");
				     else
				      JOptionPane.showMessageDialog(null, "导出失败");
				}
				}
				//导出某台雷达的故障记录
				else if(dateType.equals("故障记录")&&(!radarNumber.equals("All"))
						&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					String filePath=getFilePath();
				if(!filePath.equals("")) {
					 SpringUtil s = new SpringUtil();
					 RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
		    		 List<Radar> r = radarServiceImpl.getAllRadars();
		    		 Integer	RadarId = null;
		    		 if(r!=null||r.size()>0) {
		    			 for(int i=0;i<r.size();i++) {
			    			 if(radarNumber.equals(r.get(i).getRadarName().toString())) {
			    			 RadarId = r.get(i).getRadarId();
			    			 }
			    		 }
		    		 }
		    		 FaultRecordServiceImpl faultRecordServiceImpl =(FaultRecordServiceImpl)s.getBean("FaultRecordServiceImpl");
				     Object[][] fault =faultRecordServiceImpl.selectFaultRecordByRadarId(RadarId,startTimeDate,endTimeDate);
				     String[] header = { "雷达编号","故障类型", "发生时刻","故障部位", "原因"};
				     ExportExcel ep = new ExportExcel();
				     boolean result = ep.exportExcel(radarNumber+"故障记录", filePath, header, fault);
				     if(result)
				      JOptionPane.showMessageDialog(null, "导出成功");
				     else
				      JOptionPane.showMessageDialog(null, "导出失败");
				}
				}
				//导出所有部队下所有雷达的监测数据
				else if(dateType.equals("监测数据")&&(radarNumber.equals("All"))
						&&managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					String filePath=getFilePath();
				if(!filePath.equals("")) {
					 SpringUtil s = new SpringUtil();
    				 DynamicDataServiceImpl dynamicDataServiceImpl =(DynamicDataServiceImpl)s.getBean("DynamicDataServiceImpl");
				     Object[][] dynamicData = dynamicDataServiceImpl.selectDynamicDataByTime(startTimeDate,endTimeDate);
				     String[] header = { "雷达编号","参数", "参数值","采集时间"};
				     ExportExcel ep = new ExportExcel();
				     boolean result = ep.exportExcel("所有雷达监测数据", filePath, header, dynamicData);
				     if(result)
				      JOptionPane.showMessageDialog(null, "导出成功");
				     else
				      JOptionPane.showMessageDialog(null, "导出失败");
				}
				}
					//导出某个部队的监测数据
					else if(dateType.equals("监测数据")&&(radarNumber.equals("All"))
							&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
						String filePath=getFilePath();
					if(!filePath.equals("")) {
						 SpringUtil s = new SpringUtil();
						 ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl"); 
			    		 List<Manager> m = managerServiceImpl.getAllManager();
			    		 Integer managerId=null;
			    		 if(m!=null||m.size()>0) {
			    			 for(int i=0;i<m.size();i++) {
			    				 if(m.get(i).getManagerName().equals(managerNumber)) {
			    					 managerId=m.get(i).getManagerId();
			    				 }
			    			 }
			    		 }
	    				 DynamicDataServiceImpl dynamicDataServiceImpl =(DynamicDataServiceImpl)s.getBean("DynamicDataServiceImpl");
					     Object[][] dynamicData = dynamicDataServiceImpl.selectDynamicDataByManagerId(managerId,startTimeDate,endTimeDate);
					     String[] header = { "雷达编号","参数", "参数值","采集时间"};
					     ExportExcel ep = new ExportExcel();
					     boolean result = ep.exportExcel(managerNumber+"监测数据", filePath, header, dynamicData);
					     if(result)
					      JOptionPane.showMessageDialog(null, "导出成功");
					     else
					      JOptionPane.showMessageDialog(null, "导出失败");
					}
					}
					//导出某台雷达的监测数据
				else if(dateType.equals("监测数据")&&(!radarNumber.equals("All"))
						&&!managerNumber.equals("All")&&!startTimeString.equals("")&&!endTimeString.equals("")) {
					String filePath=getFilePath();
				if(!filePath.equals("")) {
					 SpringUtil s = new SpringUtil();
					 RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl"); 
		    		 List<Radar> r = radarServiceImpl.getAllRadars();
		    		 Integer	RadarId = null;
		    		 if(r!=null||r.size()>0) {
		    			 for(int i=0;i<r.size();i++) {
			    			 if(radarNumber.equals(r.get(i).getRadarName().toString())) {
			    				 RadarId = r.get(i).getRadarId();
			    			 }
			    		 }
		    		 }
		    		 DynamicDataServiceImpl dynamicDataServiceImpl =(DynamicDataServiceImpl)s.getBean("DynamicDataServiceImpl");
				     Object[][] dynamicData = dynamicDataServiceImpl.selectDynamicDataByRadarId(RadarId,startTimeDate,endTimeDate);
				     String[] header = { "雷达编号","参数", "参数值","采集时间"};
				     ExportExcel ep = new ExportExcel();
				     boolean result = ep.exportExcel(radarNumber+"监测数据", filePath, header, dynamicData);
				     if(result)
				      JOptionPane.showMessageDialog(null, "导出成功");
				     else
				      JOptionPane.showMessageDialog(null, "导出失败");
				}
				}
			}
		});
	}
	@Override
	public void initContentTop() {
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		LabelForTitle =  new JLabel("数据导入/导出");
		LabelForTitle.setFont(new Font("微软雅黑", Font.PLAIN, 20));	
		LabelForTitle.setHorizontalAlignment(SwingConstants.LEFT);
		separator = new JSeparator();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initContentBody() {
		//表示一行有两个单元格，一列有两个单元格，	grow: x 方向按上一级的宽度进行延伸。注意如果此处不添加grow，那么在添加组件的时候使用growx会没有效果。
		ContentBody.setLayout(new MigLayout("", "[25%][10%][40%][25%]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		//部队
		RadarManager = new JLabel("雷达所属部队：");
		RadarManager.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		RadarManager.setHorizontalAlignment(SwingConstants.LEFT);
		ManagerName = new ManagerComBox("ManagerServiceImpl", "getManagers");
		//雷达
		RadarNumber = new JLabel("雷达编号：");
		RadarNumber.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		RadarNumber.setHorizontalAlignment(SwingConstants.LEFT);
		RadarName = new	RadarComBox("RadarServiceImpl", "getRadars");
		//数据类型
		DataType = new JLabel("数据类型:");
		DataType.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		DataType.setHorizontalAlignment(SwingConstants.LEFT);
		TypeName = new JComboBox();
		TypeName.addItem("");
		TypeName.addItem("开机记录");
		TypeName.addItem("故障记录");
		TypeName.addItem("监测数据");
		//起始时间
		StartTime = new JLabel("起始时间：");
		StartTime.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		StartTime.setHorizontalAlignment(SwingConstants.LEFT);
		//终止时间
		EndTime = new JLabel("终止时间：");
		EndTime.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		EndTime.setHorizontalAlignment(SwingConstants.LEFT);
	}
	@Override
	public void initContentFoot() {
	    contentFoot.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow]"));
	    InButton = new Button("导入");
	    OutButton = new Button("导出");
	}

}
