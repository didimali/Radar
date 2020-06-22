package radar.Repository;

import org.springframework.data.repository.Repository;

import radar.Entity.Record;


public interface RecordRepository extends Repository<Record,Integer>{

	void save(Record record);

}
