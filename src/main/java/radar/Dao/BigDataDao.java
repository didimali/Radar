package radar.Dao;

import java.util.List;

import radar.Entity.Equipment;
import radar.Entity.Radar;
import radar.Entity.healthResult;

public interface BigDataDao {

	List<Object> getManagerName();

	List<Object> getRadarName(String searchKey);

	List<Radar> getRadarIdByName(String searchKey);

	List<Object> getEquipName(String searchKey);

	List<Equipment> getEquipIdByName(String searchKey);

	List<healthResult> getPreviousHI(String searchKey);

	void savehealthResult(double hI, int iD);

}
