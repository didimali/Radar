package radar.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.BigDataDao;
import radar.Entity.Equipment;
import radar.Entity.Radar;
import radar.Entity.healthResult;
import radar.Service.BigDataService;

@Service("BigDataServiceImpl")

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BigDataServiceImpl implements BigDataService{
	
	@Autowired
	BigDataDao BigDataDao;
	
	@Override
	public String[] getManagerName() {
		List<Object> list = BigDataDao.getManagerName();	
		String[] result = new String[list.size()+1];
		result[0]="——未选择——";
		for(int i=1;i<list.size()+1;i++) {
			result[i] = (String) list.get(i-1);
		}
		return result;
	}

	@Override
	public String[] getRadarName(String searchKey) {
		List<Object> list = new ArrayList();
		list = BigDataDao.getRadarName(searchKey);	
		String[] result =  new String[list.size()];		
		for(int i=0;i<list.size();i++) {
			result[i] = (String) list.get(i);
		}
		return result;
	}
	
	@Override
	public String getRadarIdByName(String searchKey) {
		List<Radar> list = BigDataDao.getRadarIdByName(searchKey);
		String radarID=list.get(0).toString();
		return radarID;
	}
	
	@Override
	public String[] getEquipName(String searchKey) {
		List<Object> list = new ArrayList();
		list = BigDataDao.getEquipName(searchKey);	
		String[] result = new String[list.size()];		
		for(int i=0;i<list.size();i++) {
			result[i] = (String) list.get(i);
		}
		return result;
	}
	
	@Override
	public List<Equipment> getEquipIdByName(String searchKey) {
		List<Equipment> list = BigDataDao.getEquipIdByName(searchKey);
		return list;
	}
	
	@Override
	public Object[][] getPreviousHI(String searchKey) {
		List<healthResult> list = BigDataDao.getPreviousHI(searchKey);
		Object[][] data  = new Object[list.size()][];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<list.size();i++) {
			healthResult r = list.get(i);
			double HI=Double.valueOf(r.getHealthScores());
			String state; 
			if(HI>85) {
				state="健康";
			}else if(HI>60) {
				state="良好";
			}else {
				state="故障";
			}
			
			Object[] o = {i+1,sdf.format(r.getAssessDate()),r.getRadarId().getRadarName(),state};
			data[i] = o;
		}
		return data;
	}
	
	@Override
	public void savehealthResult(double HI,int ID){		
		BigDataDao.savehealthResult(HI,ID);
	}
}
