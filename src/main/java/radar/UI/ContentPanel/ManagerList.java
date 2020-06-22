package radar.UI.ContentPanel;

import radar.UI.Components.Button;
import radar.UI.Components.ContentPanel;
import radar.UI.Components.Table;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

/**
 * 部队列表内容面板
 * @author Administrator
 *
 */
public class ManagerList extends ContentPanel{
	

	/**
	 * 部队管理-部队详情-部队列表页面
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	public ManagerList() {
		contentFoot.setBackground(Color.WHITE);
		ContentBody.setBackground(Color.WHITE);
		contentTop.setBackground(Color.WHITE);
		contentTop.setLayout(new MigLayout("", "[grow]", "[grow][4px]"));
		
		JLabel lblNewLabel = new JLabel("部队列表详情");
		lblNewLabel.setFont(new Font("仿宋", Font.BOLD, 24));
		contentTop.add(lblNewLabel, "cell 0 0,grow");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		contentTop.add(separator, "cell 0 1,growx,aligny bottom");
		ContentBody.setLayout(new BorderLayout(0, 0));
		
		String[] header = { "序号", "部队编号", "所在位置","部队状态","下属故障雷达数量"};
		table = new Table("TestServiceImpl", "getManagers",header);
		JScrollPane scrollPane = new JScrollPane(table);
		ContentBody.add(scrollPane, BorderLayout.CENTER);
		contentFoot.setLayout(new MigLayout("", "[15%][10%][grow][10%][grow][10%][grow][10%][15%]", "[grow]"));
		
		JButton firstPage = new Button("首 页");
		firstPage.setFont(new Font("仿宋", Font.BOLD, 14));
		contentFoot.add(firstPage, "cell 1 0,grow");
		
		JButton previousPage = new Button("上 一 页");
		previousPage.setFont(new Font("仿宋", Font.BOLD, 14));
		contentFoot.add(previousPage, "cell 3 0,grow");
		
		JButton nextPage = new Button("下 一 页");
		nextPage.setFont(new Font("仿宋", Font.BOLD, 14));
		contentFoot.add(nextPage, "cell 5 0,grow");
		
		JButton lastPage = new Button("尾 页");
		lastPage.setFont(new Font("仿宋", Font.BOLD, 14));
		contentFoot.add(lastPage, "cell 7 0,grow");
		
	}

}
