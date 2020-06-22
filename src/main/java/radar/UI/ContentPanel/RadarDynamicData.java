package radar.UI.ContentPanel;

import radar.UI.Components.Button;
import radar.UI.Components.Chooser;
import radar.UI.Components.ComboBox;
import radar.UI.Components.ContentPanel;
import radar.UI.Components.LineChart;
import radar.UI.Components.PieChart;
import radar.UI.Components.Table;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;

/**
 * 雷达管理-数据管理-监测数据
 */
@SuppressWarnings("rawtypes")
public class RadarDynamicData extends ContentPanel implements InterfaceForContentPanel{
	
	private static final long serialVersionUID = -2529510207657169185L;
	
	private JPanel panel;
	private JLabel title;
	private JPanel panel_1;
	private JLabel managerName;
	private JTextField managerValue;
	private JLabel startDate;
	private JTextField sDate;
	private JLabel endDate;
	private JTextField eDate;
	private JLabel paramName;
	private JButton select;	
	private JComboBox params;
	private JSeparator separator;
	
	private JScrollPane scrollPane;	
	private JTable table;
	
	private JPanel managerDetails;
	private LineChart line;
	
	private JLabel Delimiter;
	private JLabel subTitle;
	
	private JButton firstPage;
	private JButton previousPage;
	private JButton nextPage;
	private JButton lastPage;
	
	//是否打开子菜单
	private boolean mark = false;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	
	public RadarDynamicData() {
	
		init();		
		Action();
	}

	

	@Override
	public void init() {
		initContentTop();
		initContentBody();
		initContentFoot();
	}

	@Override
	public void Action() {
		//主标题点击事件
		title.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(mark) {
					mark = false;
					showSearch(null);
					showManagerDetails(null);
					showButtons();
				}				
			}
		});
		//表格行点击事件
			//具体部队详情
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!mark) {
					int index = table.getSelectedRow();
					String managerName = (String) table.getValueAt(index,1);
					mark = true;
					showSearch(managerName+"详情");	
					showManagerDetails(managerName);//切换到某部队详情
					showButtons();//隐藏底部按钮
				}				
			}
		});
	}

	@Override
	public void initContentTop() {
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentTop.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][grow]", "[grow]"));
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));
		title = new JLabel("活动数据");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel_2.add(title,"cell 0 0,grow");	
		
		showSearch(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentTop.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		panel_1.add(separator);
	}

	@Override
	public void initContentBody() {
		ContentBody.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		ContentBody.add(scrollPane, BorderLayout.CENTER);
		
		String[] header = {"序号","雷达编号","参数","参数值","采集时间"};
		table = new Table("ManagerServiceImpl", "getDataForRadarDynamicData", header);
		scrollPane.setViewportView(table);
	}

	@Override
	public void initContentFoot() {
		contentFoot.setBackground(Color.WHITE);
		contentFoot.setLayout(new MigLayout("", "[15%][10%][grow][10%][grow][10%][grow][10%][15%]", "[grow]"));		
		showButtons();
	}
	
	private void showSearch(String text) {
		if(mark) {
			panel.remove(panel_3);
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setLayout(new MigLayout("", "[][]", "[]"));			
			Delimiter = new JLabel(">>");
			Delimiter.setFont(new Font("宋体", Font.PLAIN, 24));
			subTitle = new JLabel(text);
			subTitle.setFont(new Font("仿宋", Font.BOLD, 24));			
			panel_4.add(Delimiter, "cell 0 0");		
			panel_4.add(subTitle, "cell 1 0");
			
			panel.add(panel_4, "cell 1 0,grow");
		}
		else {		
			if(panel_4 != null)
				panel.remove(panel_4);
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setLayout(new MigLayout("", "[10%][][][][][][][][][][][][][]", "[grow]"));		
			managerName = new JLabel("编号：");
			managerName.setFont(new Font("仿宋", Font.PLAIN, 14));
			panel_3.add(managerName,"cell 1 0,growx,aligny center");
			managerValue = new JTextField();
			panel_3.add(managerValue,"cell 2 0,growx,aligny center");
			managerValue.setColumns(10);
			startDate = new JLabel("起始时间：");
			startDate.setFont(new Font("仿宋", Font.PLAIN, 14));
			panel_3.add(startDate,"cell 4 0,growx,aligny center");		
			sDate = new JTextField();
			Chooser chooser1 = Chooser.getInstance();
			chooser1.register(sDate);
			panel_3.add(sDate,"cell 5 0,growx,aligny center");
			sDate.setColumns(10);		
			endDate = new JLabel("截止时间：");
			endDate.setFont(new Font("仿宋", Font.PLAIN, 14));
			panel_3.add(endDate,"cell 7 0,growx,aligny center");		
			eDate = new JTextField();
			Chooser chooser2 = Chooser.getInstance();
			chooser2.register(eDate);
			eDate.setColumns(10);
			panel_3.add(eDate,"cell 8 0,growx,aligny center");
			eDate.setColumns(10);		
			paramName = new JLabel("参数：");
			paramName.setFont(new Font("仿宋", Font.PLAIN, 14));
			panel_3.add(paramName,"cell 10 0,growx,aligny center");		
			params = new ComboBox("ManagerServiceImpl", "getParamForComboBox");
			params.setPreferredSize(new Dimension(80, 21));
			panel_3.add(params,"cell 11 0,growx,aligny center");		
			select = new Button("搜索");
			select.setPreferredSize(new Dimension(70, 23));
			panel_3.add(select,"cell 13 0,growx,aligny center");		
			panel.add(panel_3, "cell 1 0,grow");
		}
		validate();
		repaint();
	}
	
	
	/**
	 * 是否打开某部队详情页面
	 */
	private void showManagerDetails(String managerName) {
		if(mark) {	

			line = new LineChart(managerName+"雷达状态历史数据统计","时间","数量/台", "ManagerServiceImpl", "getDataForRadarDynamicDataLine");
			line.init();
			
			managerDetails = new JPanel();
			managerDetails.setBackground(Color.WHITE);
			
			managerDetails.setLayout(new MigLayout("", "[grow]", "[grow]"));
			managerDetails.add(line,"cell 0 0,grow");
			
			ContentBody.remove(scrollPane);	
			ContentBody.add(managerDetails, BorderLayout.CENTER);
		}
		else {
			ContentBody.remove(managerDetails);	
			ContentBody.add(scrollPane, BorderLayout.CENTER);
		}
		validate();
		repaint();
	}
	
	/**
	 * 是否显示底部表格按钮
	 */
	private void showButtons() {
		if(mark) {
			contentFoot.remove(firstPage);
			contentFoot.remove(previousPage);
			contentFoot.remove(nextPage);
			contentFoot.remove(lastPage);
		}
		else {
			firstPage = new Button("首 页");
			firstPage.setFont(new Font("仿宋", Font.BOLD, 14));		
			previousPage = new Button("上 一 页");
			previousPage.setFont(new Font("仿宋", Font.BOLD, 14));		
			nextPage = new Button("下 一 页");
			nextPage.setFont(new Font("仿宋", Font.BOLD, 14));		
			lastPage = new Button("尾 页");
			lastPage.setFont(new Font("仿宋", Font.BOLD, 14));
			
			contentFoot.add(firstPage, "cell 1 0,grow");		
			contentFoot.add(previousPage, "cell 3 0,grow");		
			contentFoot.add(nextPage, "cell 5 0,grow");		
			contentFoot.add(lastPage, "cell 7 0,grow");
		}
		validate();
		repaint();
	}

}
