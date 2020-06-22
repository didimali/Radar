package radar.UI.ContentPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import net.miginfocom.swing.MigLayout;
import radar.SpringUtil;
import radar.ServiceImpl.BigDataServiceImpl;
import radar.UI.Components.BigDataPanel;
import radar.UI.Components.BigDataPanel2;
import radar.UI.Components.ContentPanel;

public class RadarFaultForecast extends BigDataPanel2 implements InterfaceForContentPanel {

	/**
	 * 雷达管理-大数据分析-故障预测
	 */
	private static final long serialVersionUID = 1273972143025493001L;
	
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel Mmode;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel Rmode;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel Emode;
	private String[] result= {"——未选择——"};
	private JLabel Manager;
	@SuppressWarnings("rawtypes")
	private JComboBox managerName;
	private JLabel Radar;
	@SuppressWarnings("rawtypes")
	private JComboBox radarName;
	private JLabel Equip;
	@SuppressWarnings("rawtypes")
	private JComboBox equipName;
	private JButton startButton;
	BigDataServiceImpl bigDataServiceImpl = (BigDataServiceImpl) SpringUtil.getBean("BigDataServiceImpl");
	private JFreeChart jFreeChart;
	private JPanel faultPanel;
	private JTable table;
	private JScrollPane JSP;
	private JLabel title;
	private JTextPane textPane;
	private String radarID=null;
	public RadarFaultForecast() {
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
		
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Mname=managerName.getSelectedItem().toString();
				String [] Rname=bigDataServiceImpl.getRadarName(Mname);
				if(Rname.length>0) {     //非空判断				
				String name=radarName.getSelectedItem().toString();
				radarID=bigDataServiceImpl.getRadarIdByName(name);  //获取雷达id，传入R函数，取出值存入String[]
				
				
				
				
				
				bigDataServiceImpl.savehealthResult(80.5,1);    //暂时先这么写，主要将String[0]以及radarID结果存入
				
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
		
		jFreeChart = createPieChart(createDataset());
		faultPanel = new ChartPanel(jFreeChart);
		faultPanel.setBounds(75, 10, 460, 254);
		ContentBody.add(faultPanel, "cell 0 0,alignx center");
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1","采样处理板异常","控制系统","信号处理机", "板卡松动", "3"},
				{"2","收发模块7异常","控制系统","收发模块", "电路老化", "1"},
				{"3","定时接口板异常","控制系统","信号处理机", "定时接口板故障", "3"},
				{"4","热像仪校正异常","光电系统","红外跟踪器", "电路老化","3"},
				{"5","预燃故障","光电系统","激光测距机", "保险丝熔断","2"},
				{"6","串口板异常","火控计算机系统","火控计算机", "连接电缆故障","1"},
				{"7","处理任务异常","信息处理系统","跟踪控制计算机", "网络故障","1"},
				{"8","电视信息板I异常","信息处理系统","搜索空情处理计算机", "接口损坏","1"}				
			},
			new String[] {
				"编号", "故障名称", "所属分系统", "故障部位/板卡","故障原因", "故障级别"
			}
		));
		table.setFont(new Font("宋体", Font.PLAIN, 14));		
		JSP= new JScrollPane(table);
		JSP.setEnabled(false);
		ContentBody.add(JSP, "cell 0 1,growx");
		
		
		
		
	}

	@Override
	public void initContentFoot() {
		// TODO Auto-generated method stub
		contentFoot.setLayout(new MigLayout(""));
//		contentFoot.setLayout(new MigLayout("", "[grow]", "[25%][grow]"));	
//		
//		title = new JLabel("维修建议");
//		title.setFont(new Font("宋体", Font.BOLD, 15));
//		contentFoot.add(title, "cell 0 0,alignx center");
//		
//		textPane = new JTextPane();
//		textPane.setEditable(false);
//		textPane.setText("下一周内易发生预燃故障，超温故障，请及时通知维护人员进行更换维修！");
//		textPane.setFont(new Font("宋体", Font.PLAIN, 14));
//		contentFoot.add(textPane, "cell 0 1,grow");
		
		
		
	}

	
	private static PieDataset createDataset() {
	    DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
	    defaultPieDataset.setValue("控制系统", 3);
	    defaultPieDataset.setValue("光电系统", 2);
	    defaultPieDataset.setValue("火控计算机系统", 1);
	    defaultPieDataset.setValue("信息处理系统", 2);
	    return (PieDataset)defaultPieDataset;
	  }
	  
	  private static JFreeChart createPieChart(PieDataset paramPieDataset) {
	    JFreeChart jFreeChart = ChartFactory.createPieChart(null, paramPieDataset, true, true, false);
	    jFreeChart.addSubtitle((Title)new TextTitle("未来一个月内分系统预计故障数目统计", new Font("宋体", Font.BOLD, 15)));
	    jFreeChart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,14));
	    PiePlot piePlot = (PiePlot)jFreeChart.getPlot();				  
	    piePlot.setBackgroundAlpha(0.0f);
	    piePlot.setCircular(false);
	    piePlot.setLabelGap(0.02D);
	    piePlot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
	    piePlot.setLabelFont(new Font("宋体",Font.PLAIN,10));
	    piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}"));
	    return jFreeChart;
	  }
	
	
	
}
