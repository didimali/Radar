package radar.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.FaultTypeDao;
import radar.Entity.faultType;
import radar.Service.FaultTypeService;

@Service("FaultTypeServiceImpl")

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FaultTypeServiceImpl implements FaultTypeService{
@Autowired
FaultTypeDao faultTypeDao;
	//下拉框数据获取
		@Override
		public Object[] getFaultType() {
			List<faultType> list = new ArrayList();
			list = faultTypeDao.getFaultType();
			Object[] result = new Object[list.size()];
			for(int i=0;i<list.size();i++) {
				faultType ft = list.get(i);
				result[i] = ft.getFaultName();
			}
			return result;
		}
		@Override
		public List<faultType> getAllFaultType(){
			return faultTypeDao.getAllFaultType();
		}
}
