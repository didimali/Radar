package radar.UI.TopPanel;
import java.awt.Color;

import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

/**
 * 部队详情-顶部栏
 * @author Administrator
 *
 */
public class TManagerDetails extends TopPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton managerList;
	private JButton managerSet;
	
	public TManagerDetails() {
		topTools.setLayout(new MigLayout("", "[140px][140]", "[5][40][5px]"));
		
		managerList = new Button("部队列表");
		managerList.setIcon(TopPanel.getIcon("form",this));
		
		topTools.add(managerList, "cell 0 1,grow");
		
		managerSet = new Button("部队设置");
		managerSet.setIcon(TopPanel.getIcon("edit2",this));
		topTools.add(managerSet, "cell 1 1,grow");
		
	}
	
	/**
	 * 点亮部队列表按钮
	 */
	public void b1() {
		managerList.setBackground(new Color(72,171,247));
		managerList.setForeground(Color.WHITE);
		validate();
		repaint();
	}
	
	/**
	 * 点亮部队设置按钮
	 */
	public void b2() {
		managerSet.setBackground(new Color(72,171,247));
		managerSet.setForeground(Color.WHITE);
		validate();
		repaint();
	}
	
}
