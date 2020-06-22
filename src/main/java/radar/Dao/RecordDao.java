package radar.Dao;

import java.util.List;

import radar.Entity.Record;


public interface RecordDao {
	List<Record> getAllRecords();

	List<Record> selectRecordByRadar(Integer RecordRadarId,String startTimeDate,String endTimeDate);

	List<Record> selectRecordByRadarId(Integer recordRadarId);

	List<Record> slectRecordByManager(Integer managerId,String startTimeDate,String endTimeDate);

	List<Record> selectRecordByTime(String startTimeDate, String endTimeDate);


}
