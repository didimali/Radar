package radar.UI.TopPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

import java.awt.Color;

import javax.swing.JButton;

/**
 * 雷达管理-雷达详情顶部栏
 */
public class TRadarDetails extends TopPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton radarList;
	private JButton radarSet;
	public TRadarDetails() {
		topTools.setLayout(new MigLayout("", "[140px][140px]", "[5px][40px][5px]"));
		
		radarList = new Button("雷达列表");
		radarList.setIcon(TopPanel.getIcon("list3", this));
		topTools.add(radarList, "cell 0 1,grow");
		
		radarSet = new Button("雷达设置");
		radarSet.setIcon(TopPanel.getIcon("settings", this));
		topTools.add(radarSet, "cell 1 1,grow");
	}
	
	/**
	 * 点亮雷达列表按钮
	 */
	public void b1() {
		radarList.setBackground(new Color(72,171,247));
		radarList.setForeground(Color.WHITE);
		validate();
		repaint();
	}
	
	/**
	 * 点亮雷达设置按钮
	 */
	public void b2() {
		radarSet.setBackground(new Color(72,171,247));
		radarSet.setForeground(Color.WHITE);
		validate();
		repaint();
	}


}
