package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.BasicInfo;
import radar.Entity.DynamicData;



public interface DynamicDataRepository extends Repository<BasicInfo,Integer>{

	void save(DynamicData dynamicData);

}
