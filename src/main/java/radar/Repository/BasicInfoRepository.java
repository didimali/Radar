package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.BasicInfo;
import radar.Entity.Manager;

public interface BasicInfoRepository extends Repository<BasicInfo,Integer> {
	
	Manager findByParamId(Integer id);

	void save(BasicInfo r);

}
