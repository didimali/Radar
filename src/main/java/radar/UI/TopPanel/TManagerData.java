package radar.UI.TopPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

import java.awt.Color;

import javax.swing.JButton;

/**
 * 部队管理-数据管理顶部栏
 * @author madi
 *
 */
public class TManagerData extends TopPanel{

	private static final long serialVersionUID = 1L;
	
	private JButton faultData;
	private JButton healthData;
	private JButton partsComsume;
	private JButton excelExport;

	public TManagerData() {
		
		topTools.setLayout(new MigLayout("", "[140px][140px][140px][140px]", "[5px][40px][5px]"));
		
		faultData = new Button("故障信息");
		faultData.setIcon(TopPanel.getIcon("list1",this));
		topTools.add(faultData, "cell 0 1,grow");
		
		healthData = new Button("健康信息");
		healthData.setIcon(TopPanel.getIcon("list2",this));
		topTools.add(healthData, "cell 1 1,grow");
		
		partsComsume = new Button("备件信息");
		partsComsume.setIcon(TopPanel.getIcon("administrative",this));
		topTools.add(partsComsume, "cell 2 1,grow");
		
		excelExport = new Button("报表导出");
		excelExport.setIcon(TopPanel.getIcon("download6",this));
		topTools.add(excelExport, "cell 3 1,grow");
	}
	
	/**
	 * 点亮故障信息按钮
	 */

	public void b1() {
		faultData.setBackground(new Color(72,171,247));
		faultData.setForeground(Color.WHITE);
	}
	
	/**
	 * 点亮健康信息按钮
	 */

	public void b2() {
		healthData.setBackground(new Color(72,171,247));
		healthData.setForeground(Color.WHITE);
	}
	
	/**
	 * 点亮备件信息按钮
	 */

	public void b3() {
		partsComsume.setBackground(new Color(72,171,247));
		partsComsume.setForeground(Color.WHITE);
	}
	
	/**
	 * 点亮报表导出按钮
	 */

	public void b4() {
		excelExport.setBackground(new Color(72,171,247));
		excelExport.setForeground(Color.WHITE);
	}
}
