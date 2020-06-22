package radar.UI.ContentPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.ServiceImpl.BigDataServiceImpl;
import radar.UI.Components.BigDataPanel;
import radar.UI.Components.BigDataPanel2;
import radar.UI.Components.ContentPanel;


public class RadarPartsRequirements extends BigDataPanel2 implements InterfaceForContentPanel{

	/**
	 *雷达管理-大数据分析-备件需求（个性化维修资源定制）
	 */
	private static final long serialVersionUID = -4392998078746436727L;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel Mmode;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel Rmode;
	private String[] result= {"——未选择——"};
	private int[] RadarHI;
	private JLabel Manager;
	@SuppressWarnings("rawtypes")
	private JComboBox managerName;
	private JLabel Radar;
	@SuppressWarnings("rawtypes")
	private JComboBox radarName;
	private JButton startButton;
	BigDataServiceImpl bigDataServiceImpl = (BigDataServiceImpl) SpringUtil.getBean("BigDataServiceImpl");
	private JFreeChart jFreeChart;
	private JPanel faultPanel;
	private JTable table;
	private JScrollPane JSP;
	public RadarPartsRequirements() {
		init();
	}
	
	
	
	

	@Override
	public void init() {
		// TODO Auto-generated method stub
		initContentTop();
		initContentBody();
		initContentFoot();
		Action();
	}

	@Override
	public void Action() {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			managerName.addActionListener(new ActionListener() {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				public void actionPerformed(ActionEvent e) {				
					String Mname=managerName.getSelectedItem().toString();
					String [] Rname=bigDataServiceImpl.getRadarName(Mname);
					Rmode = new DefaultComboBoxModel(Rname);
					radarName.setModel(Rmode);				
					if(Rname.length>0) {
					radarName.setSelectedIndex(0); //切换时默认选中
					}
				}
			});	
		
	}

	@Override
	public void initContentTop() {
		// TODO Auto-generated method stub
		contentTop.setLayout(new MigLayout("", "[10%][15%][10%][15%][grow]", "[]"));

		Manager = new JLabel("请选择部队:");
		Manager.setFont(new Font("宋体", Font.PLAIN, 14));		
		Radar = new JLabel("请选择雷达:");
		Radar.setFont(new Font("宋体", Font.PLAIN, 14));
				
		managerName = new JComboBox();
		String[] mresult=bigDataServiceImpl.getManagerName();
		Mmode = new DefaultComboBoxModel(mresult);
		managerName.setModel(Mmode);
		
		radarName = new JComboBox();				
		Rmode = new DefaultComboBoxModel(result);
		radarName.setModel(Rmode);
		
		startButton = new JButton("开始分析");
				
		contentTop.add(Manager, "cell 0 0,alignx trailing");
		contentTop.add(managerName, "cell 1 0,growx");
		contentTop.add(Radar, "cell 2 0,alignx trailing");
		contentTop.add(radarName, "cell 3 0,growx");
		contentTop.add(startButton, "cell 4 0,alignx center");
		
		
		
		
	}

	@Override
	public void initContentBody() {
		// TODO Auto-generated method stub
        ContentBody.setLayout(new MigLayout("", "[grow]", "[grow][30%]"));	
		
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1","采样处理板异常","编码板：3,功放模块2,主波采样板：1"},
				{"2","收发模块7异常","功放模块：2,主波采样板：1"},
				{"3","定时接口板异常","继电器：1"},
				{"4","热像仪校正异常","主波采样板：2，跟踪球：2"},
				{"5","预燃故障","继电器：3"},
				{"6","串口板异常","跟踪球：3"},
				{"7","处理任务异常","询问机备件：4"},
				{"8","电视信息板I异常","询问机备件：3"}				
			},
			new String[] {
				"编号", "故障名称","所需备件及数量"
			}
		));
		table.setFont(new Font("宋体", Font.PLAIN, 14));	
		JSP= new JScrollPane(table);
		JSP.setEnabled(false);
		ContentBody.add(JSP, "cell 0 1,growx");
		
		jFreeChart = createChart(createDataset());
		faultPanel = new ChartPanel(jFreeChart);		
		ContentBody.add(faultPanel,"cell 0 0,alignx center");
	}

	@Override
	public void initContentFoot() {
		// TODO Auto-generated method stub
		
	}

	 public static JFreeChart createChart(CategoryDataset paramArrayOfCategoryDataset) {
		    JFreeChart jFreeChart = ChartFactory.createBarChart(null, null,null, paramArrayOfCategoryDataset,PlotOrientation.VERTICAL, false,true, false);
		    jFreeChart.addSubtitle((Title)new TextTitle("预测故障所需备件数量统计", new Font("黑体", Font.BOLD, 15)));
			CategoryPlot categoryPlot = (CategoryPlot)jFreeChart.getPlot(); 
			categoryPlot.getDomainAxis().setTickLabelFont(new Font("宋体",Font.BOLD,14));//设置x轴坐标上的字体
			categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT); //坐标在下方		
			categoryPlot.getRangeAxis().setRange(0, 10); //x轴区间
			categoryPlot.setBackgroundAlpha(0.0f); //背景透明
			CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
			categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			BarRenderer barRenderer = (BarRenderer)categoryPlot.getRenderer();
		    barRenderer.setItemLabelAnchorOffset(9.0D);    //上下间距
		    barRenderer.setMaximumBarWidth(0.05);
		    barRenderer.setBaseItemLabelsVisible(true);    //显示字 
		    barRenderer.setBaseItemLabelGenerator((CategoryItemLabelGenerator)new StandardCategoryItemLabelGenerator());   //显示字
		    return jFreeChart;
		  }
	
	 
	 
		private static CategoryDataset  createDataset() {
		    DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		    String str = "qwer";
		    defaultCategoryDataset.addValue(3, str, "编码板");
		    defaultCategoryDataset.addValue(2, str, "主波采样板");
		    defaultCategoryDataset.addValue(4, str, "继电器");
		    defaultCategoryDataset.addValue(5, str, "跟踪球");
		    defaultCategoryDataset.addValue(7, str, "询问机备件");
		    defaultCategoryDataset.addValue(4, str, "功放模块");
		    return (CategoryDataset)defaultCategoryDataset;
		  }
	
}
