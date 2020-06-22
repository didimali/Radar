package radar.Service;

import java.util.List;

import radar.Entity.Fault;


public interface FaultRecordService {
	List<Fault> getAllFaultRecords();
	Boolean add(Fault fault);
	Object[][] selectFaultRecordByManagerId(Integer managerId,String startTimeDate,String endTimeDate);
}
