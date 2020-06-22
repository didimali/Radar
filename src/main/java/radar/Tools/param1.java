package radar.Tools;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Table;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;

public class param1 extends JPanel{
	private JTable table;
	public param1() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[60px][grow]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JLabel title = new JLabel("检测参数");
		title.setFont(new Font("仿宋", Font.BOLD, 20));
		panel.add(title, "cell 0 0,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");
		
		String[] header = {"序号","检测状态","波动范围"};
		table = new Table("ManagerServiceImpl","getParamInfo",header);
		scrollPane.setViewportView(table);
	}

}
