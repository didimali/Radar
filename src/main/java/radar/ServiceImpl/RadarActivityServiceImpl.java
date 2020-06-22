package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.RadarActivityDao;
import radar.Entity.Record;
import radar.Service.RadarActivityService;
@Service("RadarActivityServiceImpl")

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RadarActivityServiceImpl implements RadarActivityService{
	@Autowired
	RadarActivityDao radarActivityDao;
	//表格数据获取
		@Override
		public Object[][] getRadarActivity() {
			List<Record> list = new ArrayList();
			list = radarActivityDao.getRadarActivity();
			Object[][] data  = new Object[list.size()][];
			for(int i=0;i<list.size();i++) {
				Record r = list.get(i);
				String radarName="";
				if(r.getRadarId()!=null) {
					radarName=r.getRadarId().getRadarName();
				}
				String activityName="";
				if(r.getActivityId()!=null) {
					activityName=r.getActivityId().getActivityName();
				}
				String isDefault="";
				if(r.getWithFault()==0) {
					isDefault="否";
				}else if(r.getWithFault()==1){
					isDefault="是";

				}
				Object[] o = {r.getRecordId(),radarName,r.getRecordStartDate(),r.getRecordEndDate(),activityName,isDefault};
				data[i] = o;
			}
			return data;
		}

}
