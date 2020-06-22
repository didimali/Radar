package radar.UI;

import javax.swing.JPanel;
import radar.Tools.InitUIAndAction;
import radar.UI.ContentPanel.RadarFaultRecord;
import radar.UI.LeftPanel.TestLeftPanel;
import radar.UI.TopPanel.TestTopPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class Home extends JPanel implements InitUIAndAction {
	
	public Home() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout(0, 0));	
		initUI();
	}

	private static final long serialVersionUID = -5219016543328905524L;

	/**
	 * 页面组件及其事件初始化
	 */
	@Override
	public void initUI() {
		//页面组件初始化
		JPanel leftPanel = new TestLeftPanel();
		add(leftPanel, BorderLayout.WEST);
		JPanel topPanel = new TestTopPanel();
		add(topPanel, BorderLayout.NORTH);
		RadarFaultRecord ri= new RadarFaultRecord();
		add(ri,BorderLayout.CENTER);
		//页面组件事件初始化
		Action();
	}

	@Override
	public void Action() {
	}

}
