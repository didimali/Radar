package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.Fault;


public interface FaultRepository extends Repository<Fault,Integer>{

	void save(Fault fault);

}
