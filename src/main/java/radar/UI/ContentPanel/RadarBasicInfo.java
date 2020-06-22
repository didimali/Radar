package radar.UI.ContentPanel;

import radar.Tools.param1;
import radar.Tools.struct1;
import radar.UI.Components.ContentPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;

/**
 * 雷达管理-型号信息-信息查看
 */
public class RadarBasicInfo extends ContentPanel implements InterfaceForContentPanel{
	public RadarBasicInfo() {
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentTop.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JLabel title = new JLabel("型号信息");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,grow");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentTop.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		panel_1.add(separator);
		ContentBody.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		struct1 panel_3 = new struct1();
		panel_2.add(panel_3, "cell 0 0,grow");
		
		param1 panel_5 = new param1();
		panel_2.add(panel_5, "cell 1 0,grow");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		ContentBody.add(tabbedPane);
		
		JTabbedPane type1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("I型雷达", null, panel_2, null);
		
		JTabbedPane type2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("II型雷达", null, type2, null);
	}

	
	private static final long serialVersionUID = -6210924771931179683L;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initContentTop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initContentBody() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initContentFoot() {
		// TODO Auto-generated method stub
		
	}

}
