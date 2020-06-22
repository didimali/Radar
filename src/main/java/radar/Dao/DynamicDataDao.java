package radar.Dao;

import java.util.List;

import radar.Entity.DynamicData;


public interface DynamicDataDao {

	List<DynamicData> selectDynamicDataByRadarId(Integer dynamicDataRadarId,String startTimeDate,String endTimeDate);
  
	List<DynamicData> getAllDynamicDataByCollectDate(String startDate, String endDate);

	List<Object> getRadarCountsGroupByManager();

	List<DynamicData> selectDynamicDataByTime(String startTimeDate, String endTimeDate);

	List<DynamicData> selectDynamicDataByManagerId(Integer managerId, String startTimeDate, String endTimeDate);

}
