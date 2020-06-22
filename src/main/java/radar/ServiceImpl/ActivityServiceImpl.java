package radar.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import radar.Dao.ActivityDao;
import radar.Entity.Activity;
import radar.Service.ActivityService;


@Service("ActivityServiceImpl")
public class ActivityServiceImpl implements ActivityService{
	@Autowired
	ActivityDao  activityDao;
	
	public List<Activity> getAllActivity(){
		return activityDao.getAllActivity();
	}

}
