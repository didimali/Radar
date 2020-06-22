package radar.UI.LeftPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;
import radar.UI.TopPanel.TopPanel;

public class LRadar extends JPanel{
	
	/**
	 * 雷达管理左侧菜单
	 */
	private static final long serialVersionUID = -7626868893196210106L;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;

	public LRadar() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new MigLayout("", "[150px]", "[40px][40px][40px][40px][40][40]"));
		
		btnNewButton = new Button("部队管理");
		btnNewButton.setIcon(TopPanel.getIcon("list3",this));
		btnNewButton.setFont(new Font("仿宋", Font.BOLD, 20));
		
		add(btnNewButton, "cell 0 0,grow");
		
		btnNewButton_1 = new Button("雷达管理");
		btnNewButton_1.setIcon(TopPanel.getIcon("list4",this));
		btnNewButton_1.setFont(new Font("仿宋", Font.BOLD, 20));
		btnNewButton_1.setForeground(new Color(72,171,247));
		
		add(btnNewButton_1, "cell 0 1,grow");
		
		btnNewButton_2 = new Button("大数据分析");
		btnNewButton_2.setIcon(new ImageIcon(LManager.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaTimeThumb.png")));
		add(btnNewButton_2, "cell 0 2,grow");
		
		btnNewButton_3 = new Button("雷达详情");
		btnNewButton_3.setIcon(new ImageIcon(LManager.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaTimeThumb.png")));
		add(btnNewButton_3, "cell 0 3,grow");
		
		btnNewButton_4 = new Button("数据管理");
		btnNewButton_4.setIcon(new ImageIcon(LManager.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaTimeThumb.png")));
		add(btnNewButton_4, "cell 0 4,grow");
		
		btnNewButton_5 = new Button("型号信息");
		btnNewButton_5.setIcon(new ImageIcon(LManager.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaTimeThumb.png")));
		add(btnNewButton_5, "cell 0 5,grow");
		
	}
	/**
	 * 点亮大数据分析
	 */
	public void b1() {
		btnNewButton_2.setBackground(Color.GRAY);
		btnNewButton_2.setForeground(Color.BLACK);
	}
	
	/**
	 * 点亮雷达详情
	 */
	public void b2() {
		btnNewButton_3.setBackground(Color.GRAY);
		btnNewButton_3.setForeground(Color.BLACK);
	}
	/**
	 * 点亮数据管理
	 */
	public void b3() {
		btnNewButton_4.setBackground(Color.GRAY);
		btnNewButton_4.setForeground(Color.BLACK);
	}
	/**
	 * 点亮型号信息
	 */
	public void b4() {
		btnNewButton_5.setBackground(Color.GRAY);
		btnNewButton_5.setForeground(Color.BLACK);
	}
}