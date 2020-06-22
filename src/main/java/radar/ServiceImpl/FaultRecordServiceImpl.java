package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.FaultRecordDao;
import radar.Entity.Fault;
import radar.Repository.FaultRepository;
import radar.Service.FaultRecordService;


@Service("FaultRecordServiceImpl")
public class FaultRecordServiceImpl implements FaultRecordService{
	@Autowired
	FaultRecordDao faultRecordDao;
	@Autowired
	FaultRepository faultRepository;
	@Override
	public List<Fault> getAllFaultRecords(){
		return faultRecordDao.getAllFaultRecords();
	}
	public  Boolean add(Fault fault) {
		try {
			faultRepository.save(fault);
			return true;

		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Object[][] selectFaultRecordByManagerId(Integer managerId,String startTimeDate,String endTimeDate) {
		 Object[][] result = null;
		  List<Fault> list = faultRecordDao.selectFaultRecordByManagerId(managerId,startTimeDate, endTimeDate);
		  result = new Object[list.size()][5];
		  for(int i=0;i<result.length;i++) {
		   Object[] o = new Object[5];
		   Fault f = list.get(i);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   String radarName="";
		   if(f.getRecordId()!=null&&f.getRecordId().getRadarId()!=null) {
			   radarName=f.getRecordId().getRadarId().getRadarName();
		   }
		   
		   String faultType= "";
		   if(f.getFaultTypeId()!=null) {
			   faultType=f.getFaultTypeId().getFaultName();
		   }
		   o[0] =radarName;
		   o[1] = faultType;
		   o[2] = sdf1.format(f.getFaultDate());
		   o[3] = f.getFaultLocation();
   	   o[4] = f.getFaultReason();
		   result[i] = o; 
		  }
		  return result;	}
	public Object[][] selectFaultRecordByTime(String startTimeDate, String endTimeDate) {
		 Object[][] result = null;
		  List<Fault> list = faultRecordDao.selectFaultRecordByTime(startTimeDate, endTimeDate);
		  result = new Object[list.size()][5];
		  for(int i=0;i<result.length;i++) {
		   Object[] o = new Object[5];
		   Fault f = list.get(i);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   String radarName="";
		   if(f.getRecordId()!=null&&f.getRecordId().getRadarId()!=null) {
			   radarName=f.getRecordId().getRadarId().getRadarName();
		   }
		   
		   String faultType= "";
		   if(f.getFaultTypeId()!=null) {
			   faultType=f.getFaultTypeId().getFaultName();
		   }
		   o[0] =radarName;
		   o[1] = faultType;
		   o[2] = sdf1.format(f.getFaultDate());
		   o[3] = f.getFaultLocation();
    	   o[4] = f.getFaultReason();
		   result[i] = o; 
		  }
		  return result;
	}
	public Object[][] selectFaultRecordByRadarId(Integer radarId, String startTimeDate, String endTimeDate) {
		 Object[][] result = null;
		  List<Fault> list = faultRecordDao.selectFaultRecordByRadarId(radarId,startTimeDate, endTimeDate);
		  result = new Object[list.size()][5];
		  for(int i=0;i<result.length;i++) {
		   Object[] o = new Object[5];
		   Fault f = list.get(i);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   String faultType= "";
		   if(f.getFaultTypeId()!=null) {
			   faultType=f.getFaultTypeId().getFaultName();
		   }
		   o[0] =i+1;
		   o[1] = faultType;
		   o[2] = sdf1.format(f.getFaultDate());
		   o[3] = f.getFaultLocation();
   	   o[4] = f.getFaultReason();
		   result[i] = o; 
		  }
		  return result;
	}
}
