package radar.UI.TopPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

import java.awt.Color;

import javax.swing.JButton;

/**
 * 雷达管理-雷达型号信息顶部栏
 *
 */
public class TRadarType extends TopPanel{
	
	private static final long serialVersionUID = 1L;

	private JButton BasicInfo;
	
	public TRadarType() {
		topTools.setLayout(new MigLayout("", "[140px]", "[5px][40px][5px]"));
		
		BasicInfo = new Button("信息查看");
		BasicInfo.setIcon(TopPanel.getIcon("issue",this));
		BasicInfo.setBackground(new Color(72,171,247));
		BasicInfo.setForeground(Color.WHITE);
		topTools.add(BasicInfo, "cell 0 1,grow");
	}
	
	

}
