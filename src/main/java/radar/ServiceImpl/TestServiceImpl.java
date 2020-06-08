package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.TestDao;
import radar.Entity.DynamicData;
import radar.Entity.Manager;
import radar.Entity.Radar;
import radar.Service.TestService;

@Service("TestServiceImpl")

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestServiceImpl implements TestService{
	
	@Autowired
	TestDao testDao;

	//表格数据获取
	@Override
	public Object[][] getManagers() {
		List<Manager> list = new ArrayList();
		list = testDao.getManagers();
		Object[][] data  = new Object[list.size()][];
		for(int i=0;i<list.size();i++) {
			Manager m = list.get(i);
			Object[] o = {m.getManagerId(),m.getManagerName(),m.getManagerLocation(),m.getManagerStatus()};
			data[i] = o;
		}
		return data;
	}

	//下拉框数据获取
	@Override
	public Object[] getRadars() {
		List<Radar> list = new ArrayList();
		list = testDao.getRadars();
		Object[] result = new Object[list.size()];
		for(int i=0;i<list.size();i++) {
			Radar r = list.get(i);
			result[i] = r.getRadarName();
		}
		return result;
	}

	//获取饼图数据
	@Override
	public DefaultPieDataset getPieData() {
		List<Object> list = new ArrayList();
		list = testDao.getPieData();
		DefaultPieDataset data = new DefaultPieDataset();
		Iterator it  = list.iterator();
		while(it.hasNext()) {
			Object[] o = (Object[]) it.next();
			data.setValue(String.valueOf(o[0]),Integer.parseInt(o[1].toString()));
		}
		return data;
	}

	//获取折线图数据
	@Override
	public DefaultCategoryDataset getLineData() {
		List<DynamicData> list = new ArrayList();
		list = testDao.getLineData();
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		for(int i=0;i<list.size();i++) {
			DynamicData data = list.get(i);
			result.setValue(Float.valueOf(data.getDataVaule()),data.getParamId().getParamName(),data.getCollectDate());
		}
		return result;
	}

}
