package radar.SwingWorker;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import radar.SpringUtil;
import radar.UI.Components.LineChart;

public class SwingWorkerForLineChart extends SwingWorker<DefaultCategoryDataset,Void>{

	/**
	 * 折线图的数据查询类
	 */
	private LineChart lineChart;
	private String className;
	private String methodName;
	
	public SwingWorkerForLineChart(LineChart lineChart,String className,String methodName) {
		this.lineChart = lineChart;
		this.className = className;
		this.methodName = methodName;		
	}
	
	@Override
	protected DefaultCategoryDataset doInBackground() throws Exception {
		Object o = SpringUtil.getBean(className);
		Method method = o.getClass().getMethod(methodName);
		DefaultCategoryDataset result = (DefaultCategoryDataset) method.invoke(o);
		return result;
	}
	
	@Override
	protected void done() {
		try {
			DefaultCategoryDataset data = get();
			CategoryPlot plot = lineChart.getJFreeChart().getCategoryPlot();
			plot.setDataset(data);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
