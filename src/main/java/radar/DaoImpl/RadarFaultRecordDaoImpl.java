package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.RadarFaultRecordDao;
import radar.Entity.Fault;

@Repository("RadarFaultRecordDaoImpl")
@SuppressWarnings("unchecked")
public class RadarFaultRecordDaoImpl implements RadarFaultRecordDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<Fault> getFaultRecord() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from fault ";
		Query query = em.createNativeQuery(sql,Fault.class);
		List<Fault> list = query.getResultList();
		em.close();
		return list;
	}

}
