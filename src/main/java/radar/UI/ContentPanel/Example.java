package radar.UI.ContentPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.BarChart;
import radar.UI.Components.Button;
import radar.UI.Components.ComboBox;
import radar.UI.Components.ContentPanel;
import radar.UI.Components.LineChart;
import radar.UI.Components.PieChart;
import radar.UI.Components.Table;

public class Example extends ContentPanel implements InterfaceForContentPanel{
	

	/**
	 * 内容面板示例
	 */
	private static final long serialVersionUID = 1476420283823701696L;
	
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	
	private PieChart pie;
	private LineChart line;
	private BarChart bar;
	private Table table;
	private JScrollPane panel_2;
	
	private JButton firstPage;
	private JButton previousPage;
	private JButton nextPage;
	private JButton lastPage;
	
	public Example() {		
		init();
	}	
	
	/**
	 * 页面初始化
	 */
	public void init() {

		//添加内容面板头部		
		initContentTop();
		contentTop.add(comboBox, "cell 0 0,grow");
		
		//添加内容面板内容部分
		initContentBody();		
		ContentBody.add(panel_2, "cell 0 0,grow");		
		ContentBody.add(bar, "cell 1 0,grow");		
		ContentBody.add(line, "cell 1 1,grow");		
		ContentBody.add(pie, "cell 0 1,grow");		
		
		//添加内容面板底部栏部分
		initContentFoot();
		
		contentFoot.add(firstPage, "cell 1 1,grow");			
		contentFoot.add(previousPage, "cell 3 1,grow");			
		contentFoot.add(nextPage, "cell 5 1,grow");			
		contentFoot.add(lastPage, "cell 7 1,grow");		
		
		 Action();
	}


	
	/**
	 * 添加内容面板头部
	 */
	public void initContentTop() {
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow]"));		
		String[] s = {"asd","ajd","asjsw"};
		comboBox = new ComboBox("TestServiceImpl", "getRadars");
	}

	/**
	 * 添加内容面板躯干
	 */
	public void initContentBody() {	
		//表示一行有两个单元格，一列有两个单元格，	grow: x 方向按上一级的宽度进行延伸。注意如果此处不添加grow，那么在添加组件的时候使用growx会没有效果。
		ContentBody.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		
		pie = new PieChart("饼图标题","TestServiceImpl", "getPieData");
		pie.init();		
		line = new LineChart("折线图标题","x轴","y轴","TestServiceImpl", "getLineData");
		line.init();		
		bar = new BarChart("柱状图标题","x轴","y轴","TestServiceImpl", "getLineData");
		bar.init();	
		String[] header = { "序号", "部队编号", "所在位置"};
		table = new Table("TestServiceImpl", "getManagers",header);
		panel_2 = new JScrollPane(table);		
		panel_2.setBackground(Color.WHITE);
		panel_2.setOpaque(true);
	}
	
	/**
	 * 添加内容面板底部
	 */
	public void initContentFoot() {
		contentFoot.setLayout(new MigLayout("", "[10%][grow][10][grow][10][grow][10][grow][10%]", "[10%][80%][10%]"));
		firstPage = new Button("首 页");
		previousPage = new Button("上 一 页");
		nextPage = new Button("下 一 页");
		lastPage = new Button("尾 页");
		
	}
	/**
	 * 添加页面组件事件
	 */
	public void Action() {
		
		//底部按钮事件
		//首页
		firstPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table.getFirstPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(table.getPageData(),
		                table.header);
		        table.setModel(model);
		        table.setStyle();
			}
		});
		//底部按钮事件
			//上一页
		previousPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//页数-1
				table.getPreviousPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(table.getPageData(),
		                table.header);
		        table.setModel(model);
		        table.setStyle();
			}
		});
		//底部按钮事件
			//下一页
		nextPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//页数+1
				table.getNextPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(table.getPageData(),
		                table.header);
		        table.setModel(model);
		        table.setStyle();
			}
		});
		//底部按钮事件
			//末页
		lastPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table.getLastPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(table.getPageData(),
		                table.header);
		        table.setModel(model);
		        table.setStyle();
			}
		});
		}
}
