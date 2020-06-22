package radar.Tools;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Table;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class struct1 extends JPanel{
	private JTable table;
	public struct1() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[60px][grow]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[]", "[grow]"));
		
		JLabel structInfo = new JLabel("雷达结构信息");
		structInfo.setFont(new Font("仿宋", Font.BOLD, 20));
		panel.add(structInfo, "cell 0 0,grow");
		
		
		
		String[] header = {"序号","结构名称","级别"};
		table = new Table("ManagerServiceImpl", "getDataForRadarType", header);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, "cell 0 1,grow");
	}

}
