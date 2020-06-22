package radar.UI.ContentPanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.ContentPanel;

public class NewManager extends ContentPanel implements InterfaceForContentPanel {
	/**
	 * 新建部队内容面板
	 * @author lhy
	 */
	
	private JLabel labelForTitle;
	private JTextField managerLocation;
	private JSeparator separator;
	private JLabel labelForManagerName;
	private JLabel labelForManagerLocation;
	private JButton submit;
	private JButton cancle;
	private JTextField managerName;
	/**
	 * 部队管理-部队详情-部队新建页面
	 */
	private static final long serialVersionUID = 5356274909163143896L;
	public NewManager() {		
		init();
		Action();
	}
	@Override
	public void init() {
		
	}

	@Override
	public void Action() {
		
	}

	@Override
	public void initContentTop() {
	contentTop.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));

	}

	@Override
	public void initContentBody() {
		
	}

	@Override
	public void initContentFoot() {
		
	}

}
