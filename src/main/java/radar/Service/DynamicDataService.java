package radar.Service;

import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

import radar.Entity.DynamicData;

public interface DynamicDataService {
  
  boolean add(DynamicData dynamicData );
  
	List<DynamicData> getAllDynamicDataByCollectDate(String startDate, String endDate);
	
	DefaultPieDataset getRadarCountsGroupByManager();
}
