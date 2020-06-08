package radar.UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import radar.Tools.InitUIAndAction;
import radar.UI.Components.BarChart;
import radar.UI.Components.Button;
import radar.UI.Components.ComboBox;
import radar.UI.Components.LineChart;
import radar.UI.Components.PieChart;
import radar.UI.Components.Table;
import radar.UI.ContentPanel.Example;
import radar.UI.ContentPanel.ManagerList;
import radar.UI.LeftPanel.TestLeftPanel;
import radar.UI.TopPanel.TestTopPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


import javax.swing.JTable;
import javax.swing.JComboBox;

public class Home extends JPanel implements InitUIAndAction {
	
	public Home() {
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(new BorderLayout(0, 0));		
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
		
		Example e = new Example();
		add(e, BorderLayout.CENTER);
		
		//页面组件事件初始化
		Action();
	}

	@Override
	public void Action() {
	}

}
