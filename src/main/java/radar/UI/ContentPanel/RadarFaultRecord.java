package radar.UI.ContentPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;
import radar.UI.Components.Chooser;
import radar.UI.Components.ComboBox;
import radar.UI.Components.ContentPanel;
import radar.UI.Components.Table;

public class RadarFaultRecord extends ContentPanel implements InterfaceForContentPanel{
		//雷达编号
		private JLabel RNumber;
		private JTextField RadarNumber;
		//故障类型
		private JLabel FaultType;
		@SuppressWarnings("rawtypes")
		private JComboBox comboBox;
		//故障发生时刻
		private JLabel FaultDate;
		private JTextField FaultDateText;
		//搜搜按钮
		private JButton LookUpFault;
		
		private Table table;
		private JScrollPane panel_2;
		private JButton firstPage;
		private JButton previousPage;
		private JButton nextPage;
		private JButton lastPage;
		public RadarFaultRecord() {
			init();
		}
	/**
	 * 雷达管理-数据管理-故障记录
	 */
	private static final long serialVersionUID = -6029898662509471669L;
	
	@Override
	public void init() {
		initContentTop();	
		//雷达编号标签以及输入框
		contentTop.add(RNumber,"cell 0 1,grow");
		contentTop.add(RadarNumber,"cell 1 1,grow");
		contentTop.add(FaultType,"cell 2 1,grow");
		contentTop.add(comboBox,"cell 3 1,grow");
		Chooser chooser = Chooser.getInstance();
		FaultDateText = new JTextField();
		FaultDateText.setToolTipText("故障时间");
		FaultDateText.setHorizontalAlignment(SwingConstants.CENTER);
	    chooser.register(FaultDateText);
		
		contentTop.add(FaultDate,"cell 4 1,grow");
		contentTop.add(FaultDateText,"cell 5 1,grow");
		contentTop.add(LookUpFault,"cell 6 1,grow");
		//添加开机记录列表
		initContentBody();
		ContentBody.add(panel_2, "cell 0 0,grow");	
		//添加底部按钮
		initContentFoot();
		contentFoot.add(firstPage, "cell 1 1,grow");			
		contentFoot.add(previousPage, "cell 3 1,grow");			
		contentFoot.add(nextPage, "cell 5 1,grow");			
		contentFoot.add(lastPage, "cell 7 1,grow");	
		
		Action();
	}

	@Override
	public void Action() {
		LookUpFault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String	radarNumber =	RadarNumber.getText();
				String	faultDateText	= FaultDateText.getText();
				try{				
					table.getFaultRecordByRadarAndTime(radarNumber,comboBox.getSelectedItem().toString(),faultDateText);
						}
				catch(Exception execption) {
					execption.printStackTrace();
				}

			}
		});
		
	}

	@Override
	public void initContentTop() {
		contentTop.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow]", "[25%][grow][25%]"));
		//雷达编号标签以及输入框
		RNumber = new JLabel("编号:");
		RNumber.setFont(new Font("仿宋", Font.PLAIN, 14));
		RNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		RadarNumber = new JTextField();
		RadarNumber.setHorizontalAlignment(SwingConstants.CENTER);
		RadarNumber.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		//故障类型
		FaultType = new JLabel("故障类型:");
		FaultType.setFont(new Font("宋体", Font.PLAIN, 14));
		FaultType.setHorizontalAlignment(SwingConstants.RIGHT);
		comboBox = new ComboBox("FaultTypeServiceImpl", "getFaultType");
		//故障发生时刻
		FaultDate = new JLabel("发生时刻：");
		FaultDate.setFont(new Font("宋体", Font.PLAIN, 14));
		FaultDate.setHorizontalAlignment(SwingConstants.RIGHT);

		//搜索按钮
		LookUpFault = new JButton("搜索");
		LookUpFault.setForeground(Color.BLACK);
		LookUpFault.setOpaque(true);
		LookUpFault.setBackground(new Color(255, 255, 255));
		LookUpFault.setFont(new Font("仿宋", Font.PLAIN, 14));
		LookUpFault.setBackground(Color.WHITE);
	

	}

	@Override
	public void initContentBody() {
		// TODO Auto-generated method stub
		ContentBody.setLayout(new MigLayout("", "[grow]", "[grow]"));
		String[] header = { "序号", "雷达编号", "故障类型","发生时刻","故障部位","原因"};
		table = new Table("RadarFaultRecordServiceImpl", "getFaultRecord",header);
		panel_2 = new JScrollPane(table);		
		panel_2.setBackground(Color.WHITE);
		panel_2.setOpaque(true);
	}

	@Override
	public void initContentFoot() {
		contentFoot.setLayout(new MigLayout("", "[10%][grow][10][grow][10][grow][10][grow][10%]", "[10%][80%][10%]"));
		firstPage = new Button("首 页");
		previousPage = new Button("上 一 页");
		nextPage = new Button("下 一 页");
		lastPage = new Button("尾 页");		
	}

}
