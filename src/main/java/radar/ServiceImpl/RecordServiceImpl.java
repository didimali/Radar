package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.RecordDao;
import radar.Entity.Record;
import radar.Repository.RecordRepository;
import radar.Service.RecordService;

@Service("RecordServiceImpl")
public class RecordServiceImpl	implements RecordService {
	@Autowired
	RecordDao recordDao;
	@Autowired
	RecordRepository recordRepository;
	public List<Record> getAllRecords() {
		return recordDao.getAllRecords();
	}


	public boolean add(Record record) {
		try {
			recordRepository.save(record);
			return true;

		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public List<Record> selectRecordByRadar(Integer RecordRadarId,String startTimeDate,String endTimeDate) {
		return recordDao.selectRecordByRadar(RecordRadarId,startTimeDate,endTimeDate);
	}


	public List<Record> selectRecordByRadarId(Integer recordRadarId) {
		return recordDao.selectRecordByRadarId(recordRadarId);
	}


	public Object[][] slectRecordByManager(Integer managerId,String startTimeDate,String endTimeDate) {
		 Object[][] result = null;
		  List<Record> list = recordDao.slectRecordByManager(managerId,startTimeDate, endTimeDate);
		  result = new Object[list.size()][5];
		  for(int i=0;i<result.length;i++) {
		   Object[] o = new Object[5];
		   Record r = list.get(i);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String radarName="";
		   if(r.getRadarId()!=null) {
			   radarName=r.getRadarId().getRadarName();
		   }
		   String activityName="";
		   if(r.getActivityId()!=null) {
			   activityName=r.getActivityId().getActivityName();
		   }
		   o[0] = radarName;
		   o[1] = sdf1.format(r.getRecordStartDate());
		   o[2] = sdf1.format(r.getRecordEndDate());
		   o[3] = activityName;
		   if(r.getWithFault() == 0)
		    o[4] = "否";
		   else
		    o[4] = "是";   
		   result[i] = o; 
		  }
		  return result;
		  }


	public Object[][] selectRecordByTime(String startTimeDate, String endTimeDate) {
		  Object[][] result = null;
		  List<Record> list = recordDao.selectRecordByTime(startTimeDate, endTimeDate);
		  result = new Object[list.size()][5];
		  for(int i=0;i<result.length;i++) {
		   Object[] o = new Object[5];
		   Record r = list.get(i);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   String radarName="";
		   if(r.getRadarId()!=null) {
			   radarName=r.getRadarId().getRadarName();
		   }
		   String activityName="";
		   if(r.getActivityId()!=null) {
			   activityName=r.getActivityId().getActivityName();
		   }
		   o[0] = radarName;
		   o[1] = sdf1.format(r.getRecordStartDate());
		   o[2] = sdf1.format(r.getRecordEndDate());
		   o[3] = activityName;
		   if(r.getWithFault() == 0)
		    o[4] = "否";
		   else
		    o[4] = "是";   
		   result[i] = o; 
		  }
		  return result;
	}

	@Override
	 public Object[][] getDataForRadarFaultRecord(Integer radarId, String startTimeDate, String endTimeDate) {
	  
	  Object[][] result = null;
	  List<Record> list = recordDao.selectRecordByRadar(radarId, startTimeDate, endTimeDate);
	  result = new Object[list.size()][5];
	  for(int i=0;i<result.length;i++) {
	   Object[] o = new Object[5];
	   Record r = list.get(i);
	   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	   String activityName="";
	   if(r.getActivityId()!=null) {
		   activityName=r.getActivityId().getActivityName();
	   }
	   o[0] = i+1;
	   o[1] = sdf1.format(r.getRecordStartDate());
	   o[2] = sdf1.format(r.getRecordEndDate());
	   o[3] = activityName;
	   if(r.getWithFault() == 0)
	    o[4] = "否";
	   else
	    o[4] = "是";   
	   result[i] = o; 
	  }
	  return result;
	 }

}
