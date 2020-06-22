package radar.DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import radar.Dao.ManagerDao;
import radar.Entity.Manager;
@Repository("ManagerDaoImpl")
@SuppressWarnings("unchecked")
public class ManagerDaoImpl implements ManagerDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	@Override
	public List<Manager> getManagers() {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from manager where manager_status = '0'";
		Query query = em.createNativeQuery(sql,Manager.class);
		List<Manager> list = query.getResultList();
		em.close();
		return list;
	}
	//查询所有未删除的部队信息
	@Override
	public List<Manager> getAllManager() {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from manager where manager_status = '0'";
		Query query = em.createNativeQuery(selectSql,Manager.class);
		List<Manager> list = query.getResultList();
		em.close();
		return list;
	}
}
