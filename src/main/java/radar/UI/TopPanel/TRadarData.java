package radar.UI.TopPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

import java.awt.Color;

import javax.swing.JButton;

/**
 * 雷达管理-雷达数据顶部栏
 */
public class TRadarData extends TopPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton activityRecord;
	private JButton FaultRecord;
	private JButton ActivityData;
	private JButton InAndOut;
	
	public TRadarData() {
		topTools.setLayout(new MigLayout("", "[140px][140px][140px][140px]", "[5px][40px][5px]"));
		
		activityRecord = new Button("活动记录");
		activityRecord.setIcon(TopPanel.getIcon("list3", this));
		topTools.add(activityRecord, "cell 0 1,grow");
		
		FaultRecord = new Button("故障记录");
		FaultRecord.setIcon(TopPanel.getIcon("list4", this));
		topTools.add(FaultRecord, "cell 1 1,grow");
		
		ActivityData = new Button("活动数据");
		ActivityData.setIcon(TopPanel.getIcon("monitoring", this));
		topTools.add(ActivityData, "cell 2 1,grow");
		
		InAndOut = new Button("导入导出");
		InAndOut.setIcon(TopPanel.getIcon("communication", this));
		topTools.add(InAndOut, "cell 3 1,grow");
	}
	
	/**
	 * 点亮活动记录按钮
	 */
	public void b1() {
		activityRecord.setBackground(new Color(72,171,247));
		activityRecord.setForeground(Color.WHITE);
		validate();
		repaint();
	}
	/**
	 * 点亮故障记录按钮
	 */
	public void b2() {
		FaultRecord.setBackground(new Color(72,171,247));
		FaultRecord.setForeground(Color.WHITE);
		validate();
		repaint();
	}
	/**
	 * 点亮活动数据按钮
	 */
	public void b3() {
		ActivityData.setBackground(new Color(72,171,247));
		ActivityData.setForeground(Color.WHITE);
		validate();
		repaint();
	}
	/**
	 * 点亮导入导出按钮
	 */
	public void b4() {
		InAndOut.setBackground(new Color(72,171,247));
		InAndOut.setForeground(Color.WHITE);
		validate();
		repaint();
	}
}
