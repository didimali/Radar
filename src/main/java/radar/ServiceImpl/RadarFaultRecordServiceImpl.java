package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.RadarFaultRecordDao;
import radar.Entity.Fault;
import radar.Service.RadarFaultRecordService;
@Service("RadarFaultRecordServiceImpl")

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RadarFaultRecordServiceImpl implements RadarFaultRecordService{
	@Autowired
	RadarFaultRecordDao radarFaultRecordDao;
//获取雷达故障信息
	public Object[][] getFaultRecord(){
		List<Fault> list = new ArrayList();
		list = radarFaultRecordDao.getFaultRecord();
		Object[][] data  = new Object[list.size()][];
		for(int i=0;i<list.size();i++) {
			Fault f = list.get(i);
			String radarNumber="";
			if(f.getRecordId()!=null&&f.getRecordId().getRadarId()!=null) {
				radarNumber=f.getRecordId().getRadarId().getRadarName();
			};
			String faultName="";
			if(f.getFaultTypeId()!=null) {
				faultName=f.getFaultTypeId().getFaultName();
			};
			Object[] o = {f.getFaultId(),radarNumber,faultName,f.getFaultDate(),f.getFaultLocation(),f.getFaultReason()};
			data[i] = o;
		}
		return data;
	}
}
