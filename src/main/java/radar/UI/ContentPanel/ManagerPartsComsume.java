package radar.UI.ContentPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;
import radar.Tools.PartsPlan;
import radar.UI.Components.Button;
import radar.UI.Components.ContentPanel;
import radar.UI.Components.LineChart;
import radar.UI.Components.PieChart;
import radar.UI.Components.Table;

/**
 * 部队管理-数据统计-备件消耗（备件采购计划）
 */
public class ManagerPartsComsume extends ContentPanel implements InterfaceForContentPanel{

	
	private static final long serialVersionUID = -6261645479950453487L;
	
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
	private JPanel plan;
	
	private JButton firstPage;
	private JButton previousPage;
	private JButton nextPage;
	private JButton lastPage;
	
	//是否打开子菜单
	private boolean mark = false;


	public ManagerPartsComsume() {
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
					showManagerDetails(null,null);
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
					String date = (String) table.getValueAt(index,3);
					mark = true;
					showSubTitle(managerName+"详情");//展开子标题		
					showManagerDetails(managerName,date);//切换到某部队详情
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
		panel.setLayout(new MigLayout("", "[][][]", "[grow]"));
		title = new JLabel("部队备件数据");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0");
		
		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);		
		panel1.setLayout(new BorderLayout(0, 0));		
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		panel1.add(separator);
		
		contentTop.add(panel, "cell 0 0,grow");
		contentTop.add(panel1, "cell 0 1,grow");
	}

	@Override
	public void initContentBody() {
		ContentBody.setLayout(new BorderLayout(0, 0));

		String[] header = {"序号","部队编号","备件消耗（件）","建议采购时间","备件存储充盈程度"};
		table = new Table("ManagerServiceImpl", "getDataForPartsConsumeTable", header);
		scrollPane = new JScrollPane(table);
		
		ContentBody.add(scrollPane);
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
	 * @param date 
	 */
	private void showManagerDetails(String managerName, String date) {
		if(mark) {	
			
			plan = new PartsPlan();
			((PartsPlan) plan).setParams(managerName,date);
			pie = new PieChart("部队1"+"备件消耗状况统计", "ManagerServiceImpl", "getDataForManagerHealthPie");
			pie.init();			

			managerDetails = new JPanel();
			managerDetails.setBackground(Color.WHITE);
			
			managerDetails.setLayout(new MigLayout("", "[60%][grow]", "[grow]"));
			managerDetails.add(plan,"cell 0 0,grow");			
			
			managerDetails.add(pie,"cell 1 0,grow");
			
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
