package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.RadarDao;
import radar.Entity.Radar;
import radar.Service.RadarService;
@Service("RadarServiceImpl")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RadarServiceImpl implements RadarService{
	@Autowired
	RadarDao radarDao;
	@Override
	public List<Radar> getAllRadars() {
		return radarDao.getAllRadars();
	}
	//下拉框数据获取
	@Override
	public Object[] getRadars() {
		List<Radar> list = new ArrayList();
		list = radarDao.getAllRadars();
		Object[] result = new Object[list.size()];
		for(int i=0;i<list.size();i++) {
			Radar r = list.get(i);
			result[i] = r.getRadarName();
		}
		return result;
	}
	public List<Radar> getRadarsByManagerId(Integer managerIdInRadar) {
		return radarDao.getRadarsByManagerId(managerIdInRadar);
	}
}
