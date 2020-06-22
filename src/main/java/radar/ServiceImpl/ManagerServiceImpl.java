package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.ManagerDao;
import radar.Entity.Manager;
import radar.Service.ManagerService;
@Service("ManagerServiceImpl")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	ManagerDao managerDao;
	//下拉框数据获取
		@Override
		public Object[] getManagers() {
			List<Manager> list = new ArrayList();
			list = managerDao.getManagers();
			Object[] result = new Object[list.size()];
			for(int i=0;i<list.size();i++) {
				Manager m = list.get(i);
				result[i] = m.getManagerName();
			}
			return result;
		}
		@Override
		public List<Manager> getAllManager() {
			return managerDao.getAllManager();
		}
}
