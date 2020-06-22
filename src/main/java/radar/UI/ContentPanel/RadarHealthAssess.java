package radar.UI.ContentPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import radar.SpringUtil;
import radar.ServiceImpl.BigDataServiceImpl;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.BigDataPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;

public class RadarHealthAssess extends BigDataPanel implements InterfaceForContentPanel{
	/**
	 * 雷达管理-大数据分析-健康评估
	 */
	private static final long serialVersionUID = 2288694751645396402L;
	
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel Mmode;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel Rmode;
	private String[] result= {"——未选择——"};
	private int[] RadarHI;
	private JLabel Manager;
	@SuppressWarnings("rawtypes")
	private JComboBox managerName;
	private JLabel Radar;
	@SuppressWarnings("rawtypes")
	private JComboBox radarName;
	private JButton startButton;
	BigDataServiceImpl bigDataServiceImpl = (BigDataServiceImpl) SpringUtil.getBean("BigDataServiceImpl");
	private JLabel system;
	private JLabel system1;
	private JLabel system2;
	private JLabel system3;
	private JLabel system4;
	private JTable table;
	private JScrollPane JSP;
	private JLabel system_result;
	private JLabel system_result1;
	private JLabel system_result2;
	private JLabel system_result3;
	private JLabel system_result4;
	private JLabel suggestion;
	private JTextPane textPane;
	private JLabel title;
	private JLabel healthTitle;
	private String radarID=null;
	public RadarHealthAssess() {
		init();
		
	}
	@Override
	public void init() {
		initContentTop();
		initContentBody();
		initContentFoot();
		Action();
	}

	@Override
	public void Action() {
		managerName.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent e) {				
				String Mname=managerName.getSelectedItem().toString();
				String [] Rname=bigDataServiceImpl.getRadarName(Mname);
				Rmode = new DefaultComboBoxModel(Rname);
				radarName.setModel(Rmode);				
				if(Rname.length>0) {
				radarName.setSelectedIndex(0); //切换时默认选中
				}
			}
		});	
		
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Mname=managerName.getSelectedItem().toString();
				String [] Rname=bigDataServiceImpl.getRadarName(Mname);
				if(Rname.length>0) {     //非空判断				
				String name=radarName.getSelectedItem().toString();
				radarID=bigDataServiceImpl.getRadarIdByName(name);  
				//雷达id，传入R函数，R返回各取出值存入String[]			
				bigDataServiceImpl.savehealthResult(80.5,1);    //暂时先这么写，主要将String[0]以及radarID结果存入
				
				}
			}
		});
			
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initContentTop() {
		// TODO Auto-generated method stub
		contentTop.setLayout(new MigLayout("", "[10%][15%][10%][15%][grow]", "[]"));

		Manager = new JLabel("请选择部队:");
		Manager.setFont(new Font("宋体", Font.PLAIN, 14));		
		Radar = new JLabel("请选择雷达:");
		Radar.setFont(new Font("宋体", Font.PLAIN, 14));
				
		managerName = new JComboBox();
		String[] mresult=bigDataServiceImpl.getManagerName();
		Mmode = new DefaultComboBoxModel(mresult);
		managerName.setModel(Mmode);
		
		radarName = new JComboBox();				
		Rmode = new DefaultComboBoxModel(result);
		radarName.setModel(Rmode);
		
		startButton = new JButton("开始分析");
				
		contentTop.add(Manager, "cell 0 0,alignx trailing");
		contentTop.add(managerName, "cell 1 0,growx");
		contentTop.add(Radar, "cell 2 0,alignx trailing");
		contentTop.add(radarName, "cell 3 0,growx");
		contentTop.add(startButton, "cell 4 0,alignx center");
		
		
	}

	@Override
	public void initContentBody() {
		// TODO Auto-generated method stub
		ContentBody.setLayout(new MigLayout("", "[40%][20%][40%]", "[25%][15%][15%][15%][15%][15%]"));			
		system = new JLabel("雷达总系统");
		system.setFont(new Font("宋体", Font.PLAIN, 14));
		system1 = new JLabel("控制系统");
		system1.setFont(new Font("宋体", Font.PLAIN, 14));
		system2 = new JLabel("光电系统");
		system2.setFont(new Font("宋体", Font.PLAIN, 14));
		system3 = new JLabel("火控计算机系统");
		system3.setFont(new Font("宋体", Font.PLAIN, 14));
		system4 = new JLabel("信息处理系统");
		system4.setFont(new Font("宋体", Font.PLAIN, 14));
	
		
		RadarHI= new int[]{80,80,95,30,65};         //暂时先这么写
		String[] state=ChangeState(RadarHI);
		system_result = new JLabel(state[0]);
		system_result.setFont(new Font("宋体", Font.PLAIN, 14));
		system_result1 = new JLabel(state[1]);
		system_result1.setFont(new Font("宋体", Font.PLAIN, 14));
		system_result2 = new JLabel(state[2]);
		system_result2.setFont(new Font("宋体", Font.PLAIN, 14));
		system_result3 = new JLabel(state[3]);
		system_result3.setFont(new Font("宋体", Font.PLAIN, 14));
		system_result4 = new JLabel(state[4]);
		system_result4.setFont(new Font("宋体", Font.PLAIN, 14));
		String text =suggest(RadarHI[0]);		
		suggestion = new JLabel("维保建议");
		suggestion.setFont(new Font("宋体", Font.BOLD, 15));
        textPane = new JTextPane();
        textPane.setEditable(false);
		textPane.setText(text);
		textPane.setFont(new Font("宋体", Font.PLAIN, 14));
		healthTitle = new JLabel("当前雷达健康状态等级");
		healthTitle.setFont(new Font("宋体", Font.BOLD, 15));
		
		ContentBody.add(system, "cell 0 1,alignx center,growy");
		ContentBody.add(system1, "cell 0 2,alignx center,growy");
		ContentBody.add(system2, "cell 0 3,alignx center,growy");
		ContentBody.add(system3, "cell 0 4,alignx center,growy");
		ContentBody.add(system4, "cell 0 5,alignx center,aligny center");
		ContentBody.add(system_result, "cell 1 1");
		ContentBody.add(system_result1, "cell 1 2");		
		ContentBody.add(system_result2, "cell 1 3");
		ContentBody.add(system_result3, "cell 1 4");
		ContentBody.add(system_result4, "cell 1 5");
        ContentBody.add(suggestion, "cell 2 0,alignx center");	
		ContentBody.add(textPane, "cell 2 2 1 2,grow");		
		ContentBody.add(healthTitle, "cell 0 0 2 1,alignx center");
	}

	@Override
	public void initContentFoot() {
		contentFoot.setLayout(new MigLayout("", "[grow]", "[20%][60%]"));		
		title = new JLabel("雷达总系统历史健康状态表");
		title.setFont(new Font("宋体", Font.BOLD, 15));
		contentFoot.add(title, "cell 0 0,alignx center");
		Object[][] list=bigDataServiceImpl.getPreviousHI("1");    //暂时先这么写
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(list,new String[] {"编号", "健康评估日期", "雷达编号", "健康状态"}));
		table.setFont(new Font("宋体", Font.PLAIN, 14));		
		JSP= new JScrollPane(table);
		JSP.setEnabled(false);
		contentFoot.add(JSP, "cell 0 1,grow");
		
	}
	
	public String[] ChangeState(int[] HI) {
		String[] state = new String[HI.length];
		for(int i=0;i<HI.length;i++) {
			if(HI[i]>85) {
				state[i]="健康";
			}else if(HI[i]>60){
				state[i]="良好";
			}else {
				state[i]="故障";
			}
		}
		return state;	
	}
	
	public String suggest(int HI) {
       String text;
			if(HI>85) {
				text="雷达整体处于健康状态，各分系统运行状态较佳，可适当延长维保周期。";
			}else if(HI>60){
				text="雷达整体处于亚健康状态，尚未出现明显异常，可按照正常维保周期维护，注意对健康状态等级较低的分系统检查。";
			}else {
				text="雷达即将出现故障或已出现故障，请立即安排人员进行检修，防止更严重的故障发生！";
			}
		
		return text;	
	}
	
	
}
