package radar.Dao;

import java.util.List;

import radar.Entity.BasicInfo;


public interface BasicInfoDao {
	
	List<BasicInfo> getAllBasicInfo();

	List<BasicInfo> getAllParams();
}
