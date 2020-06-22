package radar.ServiceImpl;


import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radar.Dao.DynamicDataDao;
import radar.Entity.DynamicData;
import radar.Repository.DynamicDataRepository;
import radar.Service.DynamicDataService;



@Service("DynamicDataServiceImpl")
public class DynamicDataServiceImpl implements DynamicDataService{
  
	@Autowired
	DynamicDataRepository dynamicDataRepository;
	@Autowired
	DynamicDataDao dynamicDataDao;
  
	public boolean add(DynamicData dynamicData) {
		try {
			dynamicDataRepository.save(dynamicData);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Object[][] selectDynamicDataByRadarId(Integer RadarId,
			String startTimeDate,String endTimeDate) {		
		 Object[][] result = null;
		  List<DynamicData> list = dynamicDataDao.selectDynamicDataByRadarId(RadarId,startTimeDate, endTimeDate);
		  result = new Object[list.size()][5];
		  for(int i=0;i<result.length;i++) {
		   Object[] o = new Object[4];
		   DynamicData d = list.get(i);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   String ParaName= "";
		   if(d.getParamId()!=null) {
			   ParaName=d.getParamId().getParamName();
		   }
		   String paraValue="";
		   if(!d.getDataVaule().equals("")) {
			   paraValue=d.getDataVaule();
		   }
		   o[0] =i+1;
		   o[1] = ParaName;
		   o[2] = paraValue;
		   o[3] =sdf1.format(d.getCollectDate());
		   result[i] = o; 
		  }
		  return result;
	}


	//根据时间段获取雷达监测数据
	public List<DynamicData> getAllDynamicDataByCollectDate(String startDate, String endDate) {
		return dynamicDataDao.getAllDynamicDataByCollectDate(startDate,endDate);
	}

	//获取由部队分组的雷达数量
	@Override
	public DefaultPieDataset getRadarCountsGroupByManager() {
		List<Object> list = dynamicDataDao.getRadarCountsGroupByManager();
		DefaultPieDataset result = new DefaultPieDataset();
		Iterator<Object> it = list.iterator();
		Object[] obj = null;
		while (it.hasNext()) {
			obj = (Object[]) it.next();
			result.setValue(String.valueOf(obj[1]),Integer.parseInt(obj[0].toString()));
		}
		return result;
	}
	//导出所有雷达监测数据
	public Object[][] selectDynamicDataByTime(String startTimeDate, String endTimeDate) {
		 Object[][] result = null;
		  List<DynamicData> list = dynamicDataDao.selectDynamicDataByTime(startTimeDate, endTimeDate);
		  result = new Object[list.size()][5];
		  for(int i=0;i<result.length;i++) {
		   Object[] o = new Object[4];
		   DynamicData d = list.get(i);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   String radarName = "";
		   if(d.getRadarId()!=null) {
			   radarName=d.getRadarId().getRadarName();
		   }
		   String ParaName= "";
		   if(d.getParamId()!=null) {
			   ParaName=d.getParamId().getParamName();
		   }
		   String paraValue="";
		   if(!d.getDataVaule().equals("")) {
			   paraValue=d.getDataVaule();
		   }
		   o[0] =radarName;
		   o[1] = ParaName;
		   o[2] = paraValue;
		   o[3] =sdf1.format(d.getCollectDate());
		   result[i] = o; 
		  }
		  return result;
	}
	//导出某个部队的雷达
	public Object[][] selectDynamicDataByManagerId(Integer managerId, String startTimeDate, String endTimeDate) {
		 Object[][] result = null;
		  List<DynamicData> list = dynamicDataDao.selectDynamicDataByManagerId(managerId,startTimeDate, endTimeDate);
		  result = new Object[list.size()][5];
		  for(int i=0;i<result.length;i++) {
		   Object[] o = new Object[4];
		   DynamicData d = list.get(i);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   String radarName = "";
		   if(d.getRadarId()!=null) {
			   radarName=d.getRadarId().getRadarName();
		   }
		   String ParaName= "";
		   if(d.getParamId()!=null) {
			   ParaName=d.getParamId().getParamName();
		   }
		   String paraValue="";
		   if(!d.getDataVaule().equals("")) {
			   paraValue=d.getDataVaule();
		   }
		   o[0] =radarName;
		   o[1] = ParaName;
		   o[2] = paraValue;
		   o[3] =sdf1.format(d.getCollectDate());
		   result[i] = o; 
		  }
		  return result;
	}	
}
