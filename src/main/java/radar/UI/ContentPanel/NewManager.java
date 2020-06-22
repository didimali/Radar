package radar.UI.ContentPanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;
import radar.UI.Components.ComboBox;
import radar.UI.Components.ContentPanel;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewManager extends ContentPanel implements InterfaceForContentPanel {

	/**
	 * 部队管理-部队详情-部队新建、修改、删除页面
	 */
	private static final long serialVersionUID = 5356274909163143896L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private JPanel panel;
	private JLabel title;
	private JPanel panel1;
	private JSeparator separator;
	
	private JLabel managerName;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JComboBox mNames;
	private JLabel managerLocation;
	private JComboBox mLocations;
	private JLabel managerProperty1;
	private JLabel managerProperty2;
	private JLabel managerProperty3;
	
	JButton add;
	JButton change;
	JButton delete;
	
	
	public NewManager() {
		
		initTop();
		
		ContentBody.setLayout(new MigLayout("", "[][][grow][10%]", "[][50px][][50px][][50px][][50px][][50px][]"));
		
		managerName = new JLabel("部队名称");
		managerName.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(managerName, "cell 0 1,grow");
		
		label1 = new JLabel(":");
		label1.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(label1, "cell 1 1,grow");
		
		mNames = new ComboBox("ManagerServiceImpl", "getDataForManagerComboBox");
		mNames.setEditable(true);
		ContentBody.add(mNames, "cell 2 1,grow");
		
		managerLocation = new JLabel("部队位置");
		managerLocation.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(managerLocation, "cell 0 3,grow");
		
		label2 = new JLabel(":");
		label2.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(label2, "cell 1 3,grow");
		
		mLocations = new ComboBox("ManagerServiceImpl", "getDataForManagerLocationComboBox");
		mLocations.setEditable(true);
		ContentBody.add(mLocations, "cell 2 3,grow");
		
		managerProperty1 = new JLabel("部队信息1");
		managerProperty1.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(managerProperty1, "cell 0 5,grow");
		
		label3 = new JLabel(":");
		label3.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(label3, "cell 1 5,grow");
		
		textField = new JTextField();
		ContentBody.add(textField, "cell 2 5,grow");
		textField.setColumns(10);
		
		managerProperty2 = new JLabel("部队信息2");
		managerProperty2.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(managerProperty2, "cell 0 7,grow");
		
		label4 = new JLabel(":");
		label4.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(label4, "cell 1 7,grow");
		
		textField_1 = new JTextField();
		ContentBody.add(textField_1, "cell 2 7,grow");
		textField_1.setColumns(10);
		
		managerProperty3 = new JLabel("部队信息3");
		managerProperty3.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(managerProperty3, "cell 0 9,grow");
		
		label5 = new JLabel(":");
		label5.setFont(new Font("仿宋", Font.BOLD, 20));
		ContentBody.add(label5, "cell 1 9,grow");
		
		textField_2 = new JTextField();
		ContentBody.add(textField_2, "cell 2 9,grow");
		textField_2.setColumns(10);
		contentFoot.setLayout(new MigLayout("", "[10%][grow][10%][grow][10%][grow][10%]\r\n", "[10%][grow][10%]"));
		
		add = new Button("新 建");
		
		contentFoot.add(add, "cell 1 1,grow");
		
		change = new Button("修 改");
		contentFoot.add(change, "cell 3 1,grow");
		
		delete = new Button("删 除");
		
		contentFoot.add(delete, "cell 5 1,grow");
	}
	
	private void initTop() {
		
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));	
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);		
		panel.setLayout(new BorderLayout(0, 0));
		
		title = new JLabel("部队信息");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, BorderLayout.CENTER);
		
		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);		
		panel1.setLayout(new BorderLayout(0, 0));
		
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		panel1.add(separator, BorderLayout.CENTER);
		
		contentTop.add(panel, "cell 0 0,grow");
		contentTop.add(panel1, "cell 0 1,grow");
	}

	@Override
	public void init() {
		
	}

	@Override
	public void Action() {
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		change.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
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
