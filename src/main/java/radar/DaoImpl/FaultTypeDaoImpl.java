package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.FaultTypeDao;
import radar.Entity.faultType;

@Repository("FaultTypeDaoImpl")
@SuppressWarnings("unchecked")
public class FaultTypeDaoImpl implements FaultTypeDao {
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	public List<faultType> getFaultType(){
		EntityManager em = emf.createEntityManager();
		String sql = "select * from fault_type";
		Query query = em.createNativeQuery(sql,faultType.class);
		List<faultType> list = query.getResultList();
		em.close();
		return list;
	}
	//查询所有未删除的部队信息
	@Override
	public 	List<faultType> getAllFaultType() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from fault_Type";
		Query query = em.createNativeQuery(selectSql,faultType.class);
		List<faultType> list = query.getResultList();
		em.close();
		return list;
	}
}
