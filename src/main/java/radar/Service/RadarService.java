package radar.Service;

import java.util.List;

import radar.Entity.Radar;


public interface RadarService {
	//获取所有未删除的雷达数据
		List<Radar> getAllRadars();
		//获取下拉框数据方法示例
		Object[] getRadars();
}
