package radar.Tools;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Table;

import javax.swing.JLabel;
import java.awt.Font;

public class PartsPlan extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private JLabel title;
	private JLabel dateValue;
	
	public PartsPlan() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[40px][grow]"));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		add(titlePanel, "cell 0 0,grow");
		titlePanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		title = new JLabel("xxx部队备件采购计划");
		title.setFont(new Font("仿宋", Font.BOLD, 16));
		titlePanel.add(title, "cell 0 0,grow");
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		add(contentPanel, "cell 0 1,grow");
		contentPanel.setLayout(new MigLayout("", "[grow]", "[30px][grow]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPanel.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][]", "[]"));
		
		JLabel date = new JLabel("建议采购时间：");
		date.setFont(new Font("仿宋", Font.PLAIN, 14));
		panel.add(date, "cell 0 0");
		
		dateValue = new JLabel("2020-06-29");
		dateValue.setFont(new Font("仿宋", Font.PLAIN, 14));
		panel.add(dateValue, "cell 1 0");
		
		String[] header = {"备件","采购数量","备件","采购数量"};
		JTable table = new Table("ManagerServiceImpl", "getDataForPartsPlan", header);
		JScrollPane panel_1 = new JScrollPane(table);
		panel_1.setBackground(Color.WHITE);
		contentPanel.add(panel_1, "cell 0 1,grow");
	}

	/**
	 * 设置备件采购计划的标题和采购时间
	 * @param text ：标题
	 * @param date : 采购时间
	 */
	public void setParams(String text,String date) {
		title.setText(text);
		dateValue.setText(date);
		
		validate();
		repaint();
	}
	
}
