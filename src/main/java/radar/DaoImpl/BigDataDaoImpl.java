package radar.DaoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.BigDataDao;
import radar.Entity.Equipment;
import radar.Entity.Radar;
import radar.Entity.healthResult;

@Repository("BigDataDaoImpl")
@SuppressWarnings("unchecked")
public class BigDataDaoImpl implements BigDataDao {
	
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<Object> getManagerName() {
		EntityManager em = emf.createEntityManager();
		String sql = "select manager_name from manager where manager_status = '0'";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<Object> getRadarName(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String sql = "select radar_name from radar right join manager "
				+ "on radar.manager_id=manager.manager_id where manager_name='" + searchKey + "'and radar_status=0";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}
	
	
	@Override
	public List<Radar> getRadarIdByName(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from radar where radar_name='" + searchKey + "'and radar_status=0";
		Query query = em.createNativeQuery(sql,Radar.class);
		List<Radar> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<Object> getEquipName(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String sql = "select equip_name from equipment right join radar "
				+ "on equipment.radar_id=radar.radar_id where radar_name='" + searchKey + "'and radar_status=0";
		Query query = em.createNativeQuery(sql);
		List<Object> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<Equipment> getEquipIdByName(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from equipment where equip_name='" + searchKey + "'and equip_status=0";
		Query query = em.createNativeQuery(sql,Equipment.class);
		List<Equipment> list = query.getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<healthResult> getPreviousHI(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from health_result where radar_id='" + searchKey + "'order by health_result_id desc limit 6";
		Query query = em.createNativeQuery(sql,healthResult.class);
		List<healthResult> list = query.getResultList();
		em.close();
		return list;
	}
	
	public void savehealthResult(double HI, int ID) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(new Date());
		try {
			String selectSql ="insert into health_result(assess_date,health_scores,radar_id) "
					+ "values('"+currentTime+"','"+HI+"','"+ID+"')";
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	
}

