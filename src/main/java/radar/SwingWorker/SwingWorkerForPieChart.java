package radar.SwingWorker;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import radar.SpringUtil;
import radar.UI.Components.PieChart;

public class SwingWorkerForPieChart extends SwingWorker<DefaultPieDataset,Void>{

	/**
	 * 获取饼图数据的SwingWorker类
	 * @author madi
	 */
	private PieChart panel;
	private String className;
	private String methodName;
	
	/**
	 * @param panel:饼图对象实例
	 * @param className:获取数据所需方法所在ServiceImpl的名字
	 * @param methodName:获取数据所需方法的名字
	 */
	public SwingWorkerForPieChart(PieChart panel,String className,String methodName) {
		this.panel = panel;
		this.className = className;
		this.methodName = methodName;
	}
	
	@Override
	protected DefaultPieDataset doInBackground() throws Exception {
		Object o = SpringUtil.getBean(className);
		Method method = o.getClass().getMethod(methodName);
		DefaultPieDataset data = (DefaultPieDataset) method.invoke(o);
		return data;
	}
	
	@Override
	protected void done() {		
		try {
			DefaultPieDataset data;
			data = get();
			PiePlot p = (PiePlot) panel.getJFreeChart().getPlot();
			p.setDataset(data);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
	}

}
