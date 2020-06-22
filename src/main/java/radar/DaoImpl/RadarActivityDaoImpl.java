package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.RadarActivityDao;
import radar.Entity.Record;

@Repository("RadarActivityDaoImpl")
@SuppressWarnings("unchecked")
public class RadarActivityDaoImpl implements RadarActivityDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<Record> getRadarActivity() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from record";
		Query query = em.createNativeQuery(sql,Record.class);
		List<Record> list = query.getResultList();
		em.close();
		return list;
	}
}
