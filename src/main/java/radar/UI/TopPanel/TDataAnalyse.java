package radar.UI.TopPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

import java.awt.Color;

import javax.swing.JButton;

/**
 * 雷达管理-大数据分析顶部栏
 */
public class TDataAnalyse extends TopPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	
	public TDataAnalyse() {
		topTools.setLayout(new MigLayout("", "[140px][140px][140px]", "[5px][40px][5px]"));
		
		btnNewButton = new Button("故障预测");
		btnNewButton.setIcon(TopPanel.getIcon("activity",this));
		topTools.add(btnNewButton, "cell 0 1,grow");
		
		btnNewButton_1 = new Button("健康评估");
		btnNewButton_1.setIcon(TopPanel.getIcon("forecast",this));
		topTools.add(btnNewButton_1, "cell 1 1,grow");
		
		btnNewButton_2 = new Button("维修资源定制");
		btnNewButton_2.setIcon(TopPanel.getIcon("task",this));
		topTools.add(btnNewButton_2, "cell 2 1,grow");
	}
	/**
	 * 点亮故障预测
	 */
	public void b1() {
		btnNewButton.setBackground(new Color(72,171,247));
		btnNewButton.setForeground(Color.WHITE);
		validate();
		repaint();
		
	}
	
	/**
	 * 点亮健康评估
	 */
	public void b2() {
		btnNewButton_1.setBackground(new Color(72,171,247));
		btnNewButton_1.setForeground(Color.WHITE);
		validate();
		repaint();
	}
	
	/**
	 * 点亮维修资源个性化定制
	 */
	public void b3() {
		btnNewButton_2.setBackground(new Color(72,171,247));
		btnNewButton_2.setForeground(Color.WHITE);
		validate();
		repaint();
	}

	
	

}
