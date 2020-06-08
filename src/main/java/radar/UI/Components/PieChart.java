package radar.UI.Components;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import radar.SwingWorker.SwingWorkerForPieChart;

import java.awt.BorderLayout;

public class PieChart extends JPanel{
	
	/**
	 * 饼图控件
	 * @author madi
	 */
	private static final long serialVersionUID = 1898284017545564731L;
	
	private String title;
	private String className;
	private String methodName;
	
	private ChartPanel chartPanel;
	private JFreeChart pieChart;
	private DefaultPieDataset data;
	
	public JFreeChart getJFreeChart() {
		return this.pieChart;
	}
	
	public PieChart(String title,String className,String methodName) {
		this.title = title;
		this.className = className;
		this.methodName = methodName;
		
		
		
	}

	public void init() {
		//容器样式初始化
		setBackground(Color.WHITE);
		setBorder(null);
		setOpaque(true);
		
		//图表初始化
		setPieChart();
		setChartPanel();
		setLayout(new BorderLayout(0, 0));
		add(chartPanel);
		
		//查询加载数据
		SwingWorkerForPieChart swp = new SwingWorkerForPieChart(this, className, methodName);
		swp.execute();
	}
	
	private void setPieChart() {
		//创建数据集对象
		data = new DefaultPieDataset();
		
		//创建JFreeChart对象
		pieChart = ChartFactory.createPieChart(title, data, true, true, true);
		pieChart.setBorderVisible(false);
		pieChart.setBackgroundPaint(null);
		pieChart.setBackgroundImageAlpha(0.0f);
		
		pieChart.setTitle(new TextTitle(title,new Font("宋体", Font.BOLD, 24)));		

        LegendTitle legend = pieChart.getLegend(0);
        legend.setFrame(new BlockBorder(Color.white));
        legend.setItemFont(new Font("宋体",Font.PLAIN,14));//修改图例的字体
        PiePlot p = (PiePlot) pieChart.getPlot();
        
        p.setLabelFont(new Font("宋体", Font.PLAIN,14));     //水平底部标题
        p.setNoDataMessage("数据加载中，请稍候......"); // 没有数据的时候显示的内容 
        p.setLabelGenerator(new StandardPieSectionLabelGenerator(("{0}:{1}"), NumberFormat.getNumberInstance(),new DecimalFormat("0.00"))); 
        
        p.setOutlinePaint(Color.WHITE); // 设置绘图面板外边的填充颜色
        p.setShadowPaint(Color.WHITE); // 设置绘图面板阴影的填充颜色
		// 饼图的透明度
		p.setForegroundAlpha(0.5f);
		// 饼图的背景全透明
		p.setBackgroundAlpha(0.0f);
		
		// 设置标题颜色
		pieChart.getTitle().setPaint(ChartColor.blue);
	}
	
	private void setChartPanel() {
		chartPanel = new ChartPanel(pieChart);
		chartPanel.setBorder(null);
		chartPanel.setOpaque(true);
		chartPanel.setBackground(Color.WHITE);
	}

}
