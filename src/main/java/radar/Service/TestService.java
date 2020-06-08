package radar.Service;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public interface TestService {
	
	//获取表格数据方法示例
	Object[][] getManagers();
	//获取下拉框数据方法示例
	Object[] getRadars();	
	//获取饼图数据方法示例
	DefaultPieDataset getPieData();
	//获取折线图数据方法示例
	DefaultCategoryDataset getLineData();
	

}
