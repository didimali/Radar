package radar.Service;

import java.text.ParseException;
import java.util.List;

import radar.Entity.Equipment;

public interface BigDataService {
	
	//获取部队名称
	String[] getManagerName();
    //根据部队名称获取雷达名称
	String[] getRadarName(String searchKey);
    //根据雷达名称获取雷达id
	String getRadarIdByName(String searchKey);
    //根据雷达名称获取部件名称
	String[] getEquipName(String searchKey);
    //根据部件名称获取部件id
	List<Equipment> getEquipIdByName(String searchKey);
	//获取历史健康信息
	Object[][] getPreviousHI(String searchKey);
	//存储健康评估结果
	void savehealthResult(double HI, int ID);

	
	
	
	

}
