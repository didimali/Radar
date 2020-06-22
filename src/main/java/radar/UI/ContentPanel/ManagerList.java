package radar.UI.ContentPanel;

import radar.UI.Components.Button;
import radar.UI.Components.ContentPanel;
import radar.UI.Components.LineChart;
import radar.UI.Components.PieChart;
import radar.UI.Components.Table;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 部队列表内容面板
 * @author Administrator
 *
 */
public class ManagerList extends ContentPanel{
	

	/**
	 * 部队管理-部队详情-部队列表页面
	 */
	private static final long serialVersionUID = 1L;
	private Table table;
	private JLabel title;
	private JSeparator separator;
	
	private JScrollPane scrollPane;
	
	private JPanel managerDetails;
	
	private JButton firstPage;
	private JButton previousPage;
	private JButton nextPage;
	private JButton lastPage;
	private JPanel panel;
	private JLabel Delimiter;
	private JLabel subTitle;
	
	private PieChart pie1;
	private LineChart line1;
	private PieChart pie2;
	private LineChart line2;
	
	
	//是否打开子菜单
	private boolean mark = false;
	
	
	public ManagerList() {
		
		initContentTop();		
		
		
		contentTop.add(panel, "cell 0 0,grow");		
		contentTop.add(separator, "cell 0 1,growx,aligny bottom");
		

		String[] header = { "序号", "部队编号", "所在位置","部队状态","下属故障雷达数量"};
		table = new Table("TestServiceImpl", "getManagers",header);
		JScrollPane scrollPane = new JScrollPane(table);
    
		initContentBody();	
		ContentBody.add(scrollPane, BorderLayout.CENTER);
		
		initContentFoot();		
		
		
		Aciton();
		
	}


	private void Aciton() {
		//底部按钮事件
		//首页
		firstPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table.getFirstPage();
				getCurrentPage();
			}
		});
		//底部按钮事件
			//上一页
		previousPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//页数-1
				table.getPreviousPage();
				getCurrentPage();
			}
		});
		//底部按钮事件
			//下一页
		nextPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//页数+1
				table.getNextPage();
				getCurrentPage();
			}
		});
		//底部按钮事件
			//末页
		lastPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table.getLastPage();
				getCurrentPage();
			}
		});
		//主标题点击事件
		title.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(mark) {
					mark = false;
					showSubTitle("");
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
					showSubTitle(managerName+"部队详情");//展开子标题		
					showManagerDetails(managerName);//切换到某部队详情
					showButtons();//隐藏底部按钮
				}
				
				
			}
		});
	}
	
	/**
	 * 表格返回当前页
	 */
	private void  getCurrentPage(){
		DefaultTableModel model = new DefaultTableModel(table.getPageData(),table.header);
        table.setModel(model);
        table.setStyle();
	}


	private void initContentFoot() {
		contentFoot.setBackground(Color.WHITE);
		contentFoot.setLayout(new MigLayout("", "[15%][10%][grow][10%][grow][10%][grow][10%][15%]", "[grow]"));		
		showButtons();
	}


	private void initContentBody() {
		ContentBody.setBackground(Color.WHITE);
		ContentBody.setLayout(new BorderLayout(0, 0));
		
		String[] header = { "序号", "部队编号", "所在位置","故障雷达数量","亚健康雷达数量","健康雷达数量",};
		table = new Table("ManagerServiceImpl", "getDataForManagerList",header);
		scrollPane = new JScrollPane(table);
	}


	private void initContentTop() {
		contentTop.setBackground(Color.WHITE);
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		title = new JLabel("部队列表详情");
		title.setFont(new Font("仿宋", Font.BOLD, 24));			
		
		panel.setLayout(new MigLayout("", "[][][]", "[grow]"));
		panel.add(title, "cell 0 0,grow");	
		
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
	}
	
	/**
	 * 是否打开子菜单
	 */
	private void showSubTitle(String subTitleText) {
		if(mark) {
			Delimiter = new JLabel(">>");
			Delimiter.setFont(new Font("宋体", Font.PLAIN, 24));
			subTitle = new JLabel(subTitleText);
			subTitle.setFont(new Font("仿宋", Font.BOLD, 24));
			
			panel.add(Delimiter, "cell 1 0");		
			panel.add(subTitle, "cell 2 0");
		}
		else {
			panel.remove(Delimiter);		
			panel.remove(subTitle);
		}
		validate();
		repaint();
	}
	
	/**
	 * 是否打开某部队详情页面
	 */
	private void showManagerDetails(String managerName) {
		if(mark) {	
			
			pie1 = new PieChart(managerName+"雷达状态统计", "ManagerServiceImpl", "getDataForManagerDetailsHealthStatus");
			pie1.init();
			pie2 = new PieChart(managerName+"备件消耗统计", "ManagerServiceImpl", "getDataForManagerDetailsPartsComsume");
			pie2.init();
			line1 = new LineChart(managerName+"雷达状态历史数据统计","时间","数量/台", "ManagerServiceImpl", "getDataForManagerDetailsHistoryHealthStatus");
			line1.init();
			line2 = new LineChart(managerName+"备件消耗历史数据统计","时间","数量/件", "ManagerServiceImpl", "getDataForManagerDetailsHistoryPartsComsume");
			line2.init();
			
			managerDetails = new JPanel();
			managerDetails.setBackground(Color.WHITE);
			
			managerDetails.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
			managerDetails.add(pie1,"cell 0 0,grow");
			managerDetails.add(pie2,"cell 1 0,grow");
			managerDetails.add(line1,"cell 0 1,grow");
			managerDetails.add(line2,"cell 1 1,grow");
			
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
