package radar.UI.ContentPanel;

import radar.UI.Components.Button;
import radar.UI.Components.Chooser;
import radar.UI.Components.ComboBox;
import radar.UI.Components.ContentPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;


public class ManagerExportExcel extends ContentPanel implements InterfaceForContentPanel{
	public ManagerExportExcel() {
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentTop.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[]", "[grow]"));
		
		JLabel title = new JLabel("部队数据/报表导出");
		title.setFont(new Font("仿宋", Font.BOLD, 24));
		panel.add(title, "cell 0 0,grow");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentTop.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		panel_1.add(separator, BorderLayout.NORTH);
		ContentBody.setLayout(new MigLayout("", "[][][][40%]", "[40px][40px][30px][40px][30px][40px][30px][40px][30px]"));
		
		JLabel label = new JLabel("部队编号：");
		label.setFont(new Font("仿宋", Font.PLAIN, 16));
		ContentBody.add(label, "cell 1 1,grow");
		
		JComboBox comboBox = new ComboBox("ManagerServiceImpl", "getDataForManagerComboBox");
		ContentBody.add(comboBox, "cell 3 1,growx,aligny center");
		
		JLabel label_1 = new JLabel("数据类型：");
		label_1.setFont(new Font("仿宋", Font.PLAIN, 16));
		ContentBody.add(label_1, "cell 1 3,grow");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		ContentBody.add(panel_2, "cell 3 3,grow");
		panel_2.setLayout(new MigLayout("", "[][][]", "[grow]"));
		
		JCheckBox check1 = new JCheckBox("故障数据");
		check1.setFont(new Font("仿宋", Font.PLAIN, 13));
		check1.setBackground(Color.WHITE);
		panel_2.add(check1, "cell 0 0");
		
		JCheckBox check2 = new JCheckBox("健康数据");
		check2.setFont(new Font("仿宋", Font.PLAIN, 13));
		check2.setBackground(Color.WHITE);
		panel_2.add(check2, "cell 1 0");
		
		JCheckBox check3 = new JCheckBox("备件数据");
		check3.setFont(new Font("仿宋", Font.PLAIN, 13));
		check3.setBackground(Color.WHITE);
		panel_2.add(check3, "cell 2 0");
		
		JLabel label_2 = new JLabel("数据起始时间：");
		label_2.setFont(new Font("仿宋", Font.PLAIN, 16));
		ContentBody.add(label_2, "cell 1 5,grow");
		
		Chooser chooser1 = Chooser.getInstance();
		textField = new JTextField();
		chooser1.register(textField);
		ContentBody.add(textField, "cell 3 5,growx,aligny center");
		textField.setColumns(10);
		
		
		JLabel label_3 = new JLabel("数据截止时间：");
		label_3.setFont(new Font("仿宋", Font.PLAIN, 16));
		ContentBody.add(label_3, "cell 1 7,grow");
		
		Chooser chooser2 = Chooser.getInstance();
		textField_1 = new JTextField();
		chooser2.register(textField_1);
		ContentBody.add(textField_1, "cell 3 7,growx,aligny center");
		textField_1.setColumns(10);
		
		contentFoot.setLayout(new MigLayout("", "[35%][grow][35%]", "[grow]"));
		JButton export = new Button("报表导出");
		contentFoot.add(export,"cell 1 0,grow");
	}

	/**
	 * 部队管理-数据统计-报表导出页面
	 */
	private static final long serialVersionUID = 9143528372340271758L;
	private JTextField textField;
	private JTextField textField_1;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initContentTop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initContentBody() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initContentFoot() {
		// TODO Auto-generated method stub
		
	}

}
