package radar.Dao;

import java.util.List;

import radar.Entity.Fault;


public interface FaultRecordDao {
	List<Fault>getAllFaultRecords();

	List<Fault> selectFaultRecordByManagerId(Integer managerId,String startTimeDate,String endTimeDate);

	List<Fault> selectFaultRecordByTime(String startTimeDate, String endTimeDate);

	List<Fault> selectFaultRecordByRadarId(Integer radarId, String startTimeDate, String endTimeDate);
}
