package radar.UI.ContentPanel;

import radar.UI.Components.BarChart;
import radar.UI.Components.Button;
import radar.UI.Components.ContentPanel;
import radar.UI.Components.LineChart;
import radar.UI.Components.PieChart;
import radar.UI.Components.Table;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 部队管理-数据统计-故障信息页面
 */
public class ManagerFault extends ContentPanel implements InterfaceForContentPanel{
	
	private static final long serialVersionUID = -8434882661438742343L;
	
	private JPanel panel;
	private JLabel title;
	private JPanel panel1;
	private JSeparator separator;
	
	private JLabel Delimiter;
	private JLabel subTitle;
	
	private JScrollPane scrollPane;		
	private JTable table;
	private JPanel managerDetails;
	private PieChart pie;
	private LineChart line;
	private BarChart bar;
	
	private JButton firstPage;
	private JButton previousPage;
	private JButton nextPage;
	private JButton lastPage;
	
	//是否打开子菜单
	private boolean mark = false;
	
	public ManagerFault() {
		
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

	@Override
	public void initContentTop() {
		contentTop.setBackground(Color.WHITE);
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		panel = new JPanel();
		contentTop.add(panel, "cell 0 0,grow");
		panel.setBackground(Color.WHITE);
		panel.setLayout(new MigLayout("", "[][][]", "[grow]"));
		
		
		title = new JLabel("部队故障数据");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,grow");	
		
		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		contentTop.add(panel1, "cell 0 1,grow");
		panel1.setLayout(new BorderLayout(0, 0));
		
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		panel1.add(separator);
				
	}

	@Override
	public void initContentBody() {
		ContentBody.setBackground(Color.WHITE);
		ContentBody.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		ContentBody.add(scrollPane);
		String[] header = {"序号","部队编号","下辖雷达总数（台）","故障雷达数量（台）"};
		table = new Table("ManagerServiceImpl", "getDataForManagerFaultTable", header);
		scrollPane.setViewportView(table);
		
	}

	@Override
	public void initContentFoot() {
		contentFoot.setBackground(Color.WHITE);
		contentFoot.setLayout(new MigLayout("", "[15%][10%][grow][10%][grow][10%][grow][10%][15%]", "[grow]"));		
		showButtons();
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
			
			pie = new PieChart(managerName+"故障雷达数量统计", "ManagerServiceImpl", "getDataForManagerFaultPieChart");
			pie.init();			
			line = new LineChart(managerName+"故障雷达历史数据统计","时间","数量/台", "ManagerServiceImpl", "getDataForManagerFaultLineChart");
			line.init();
			bar = new BarChart(managerName+"雷达数量统计", "", "数量/台", "ManagerServiceImpl", "getDataForManagerFaultBarChart");
			bar.init();
			
			managerDetails = new JPanel();
			managerDetails.setBackground(Color.WHITE);
			
			managerDetails.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
			managerDetails.add(pie,"cell 0 0,grow");
			managerDetails.add(line,"cell 0 1,grow");
			managerDetails.add(bar,"cell 1 0,grow");
			
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

}
