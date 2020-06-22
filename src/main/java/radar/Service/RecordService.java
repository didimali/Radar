package radar.Service;

import java.util.List;

import radar.Entity.Record;


public interface RecordService {
	List<Record> getAllRecords();
	boolean add(Record record);
	List<Record> selectRecordByRadar(Integer RecordRadarId,String startTimeDate,String endTimeDate);
	List<Record> selectRecordByRadarId(Integer recordRadarId);
	Object[][] getDataForRadarFaultRecord(Integer radarId, String startTimeDate, String endTimeDate);
	Object[][] selectRecordByTime(String startTimeDate, String endTimeDate);
}
