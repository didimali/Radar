package radar.Dao;

import java.util.List;

import radar.Entity.Radar;

public interface RadarDao {

	List<Radar> getAllRadars();

	List<Radar> getRadarsByManagerId(Integer managerIdInRadar);


}
