package radar.UI.LeftPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;
import radar.UI.TopPanel.TopPanel;
import javax.swing.ImageIcon;

public class LManager extends JPanel{
	
	/**
	 * 部队管理左侧菜单
	 */
	private static final long serialVersionUID = -7626868893196210106L;
	private JButton btnNewButton;
	private JButton btnNewButton_6;
	private JButton btnNewButton_4;
	private JButton btnNewButton_2;

	public LManager() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new MigLayout("", "[150px]", "[40px][40px][40px][40px]"));
		
		btnNewButton = new Button("部队管理");
		btnNewButton.setFont(new Font("仿宋", Font.BOLD, 20));
		btnNewButton.setForeground(new Color(72,171,247));
		btnNewButton.setIcon(TopPanel.getIcon("list3",this));
		add(btnNewButton, "cell 0 0,grow");
		
		btnNewButton_6 = new Button("部队详情");
		btnNewButton_6.setIcon(new ImageIcon(LManager.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaTimeThumb.png")));
		add(btnNewButton_6, "cell 0 1,grow");
		
		btnNewButton_4 = new Button("数据管理");
		btnNewButton_4.setIcon(new ImageIcon(LManager.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaTimeThumb.png")));
		add(btnNewButton_4, "cell 0 2,grow");
		
		btnNewButton_2 = new Button("雷达管理");
		btnNewButton_2.setFont(new Font("仿宋", Font.BOLD, 20));
		btnNewButton_2.setIcon(TopPanel.getIcon("list4",this));
		add(btnNewButton_2, "cell 0 3,grow");
		
	}
	/**
	 * 点亮部队详情
	 */
	public void b1() {
		btnNewButton_6.setBackground(Color.GRAY);
		btnNewButton_6.setForeground(Color.BLACK);
	}
	
	/**
	 * 点亮数据管理
	 */
	public void b2() {
		btnNewButton_4.setBackground(Color.GRAY);
		btnNewButton_4.setForeground(Color.BLACK);
	}
	

}
