package radar.UI.ContentPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;
import radar.UI.Components.Chooser;
import radar.UI.Components.ContentPanel;
import radar.UI.Components.Table;

public class RadarActivityRecord extends ContentPanel implements InterfaceForContentPanel{
	private JLabel RNumber;
	private JTextField RadarNumber;
	private JButton LookUpRadar;
	private JLabel StartTime;
	private JTextField startDate;
	private JLabel EndTime;
	private JTextField endDate;
	private Table table;
	private JScrollPane panel_2;
	
	private JButton firstPage;
	private JButton previousPage;
	private JButton nextPage;
	private JButton lastPage;
	public RadarActivityRecord() {
		init();
	}

	/**
	 * 雷达管理-数据统计-开机记录
	 */
	private static final long serialVersionUID = -618723157908696380L;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//添加内容面板头部		
				initContentTop();
				//雷达编号标签以及输入框
				contentTop.add(RNumber,"cell 0 1,grow");
				contentTop.add(RadarNumber,"cell 1 1,grow");
				
				// 定义开机日历控件面板类
				Chooser chooser1 = Chooser.getInstance();
				startDate = new JTextField();
				startDate.setToolTipText("起始时间");
				startDate.setHorizontalAlignment(SwingConstants.CENTER);
				startDate.setBounds(372, 20, 80, 21);
			    chooser1.register(startDate);
			    
				contentTop.add(StartTime,"cell 2 1,grow");
				contentTop.add(startDate,"cell 3 1,grow");

				// 定义关机日历控件面板类
				Chooser chooser2 = Chooser.getInstance();
				endDate = new JTextField();
				endDate.setToolTipText("起始时间");
				endDate.setHorizontalAlignment(SwingConstants.CENTER);
				endDate.setBounds(372, 20, 80, 21);
			    chooser2.register(endDate);
				
				contentTop.add(EndTime,"cell 4 1,grow");
				contentTop.add(endDate,"cell 5 1,grow");
				contentTop.add(LookUpRadar,"cell 6 1,grow");
				
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

	private void in() {
		DefaultTableModel model = new DefaultTableModel(table.getPageData(),
                table.header);
        table.setModel(model);
        table.setStyle();
	}

	@Override
	public void Action() {
		// TODO Auto-generated method stub
		//搜索按钮事件
		LookUpRadar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String	radarNumber =	RadarNumber.getText();
				String startTimeText = startDate.getText();
				String	endTimeText	= endDate.getText();
				try{				
					table.getRecordByRadarAndTime(radarNumber,startTimeText,endTimeText);
						}
				catch(Exception execption) {
					execption.printStackTrace();
				}

			}
		});

		//底部按钮事件
				//首页
				firstPage.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						table.getFirstPage();
						//返回当前页
						in();
						
					}

					
				});
				//底部按钮事件
					//上一页
				previousPage.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						//页数-1
						table.getPreviousPage();
						//返回当前页
						in();
					}
				});
				//底部按钮事件
					//下一页
				nextPage.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						//页数+1
						table.getNextPage();
						//返回当前页
						in();
					}
				});
				//底部按钮事件
					//末页
				lastPage.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						table.getLastPage();
						//返回当前页
						in();
					}
				});
	}

	@Override
	public void initContentTop() {
		// TODO Auto-generated method stub
	contentTop.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow]", "[25%][grow][25%]"));
	//雷达编号标签以及输入框
	RNumber = new JLabel("编号:");
	RNumber.setFont(new Font("仿宋", Font.PLAIN, 14));
	RNumber.setHorizontalAlignment(SwingConstants.RIGHT);
	RadarNumber = new JTextField();
	RadarNumber.setHorizontalAlignment(SwingConstants.CENTER);
	RadarNumber.setFont(new Font("微软雅黑", Font.PLAIN, 12));
	//雷达开机时间标签以及输入框
	StartTime = new JLabel("开机时间:");
	StartTime.setFont(new Font("仿宋", Font.PLAIN, 14));
	StartTime.setHorizontalAlignment(SwingConstants.RIGHT);
	//雷达关机时间标签以及输入框
	EndTime = new JLabel("关机时间：");
	EndTime.setFont(new Font("仿宋", Font.PLAIN, 14));
	EndTime.setHorizontalAlignment(SwingConstants.RIGHT);
	//搜索按钮
	LookUpRadar = new JButton("搜索");
	LookUpRadar.setForeground(Color.BLACK);
	LookUpRadar.setOpaque(true);
	LookUpRadar.setBackground(new Color(255, 255, 255));
	LookUpRadar.setFont(new Font("仿宋", Font.PLAIN, 14));
	LookUpRadar.setBackground(Color.WHITE);
	
	}

	@Override
	public void initContentBody() {
		// TODO Auto-generated method stub
		ContentBody.setLayout(new MigLayout("", "[grow]", "[grow]"));
		String[] header = { "序号", "雷达编号", "开机时间","关机时间","活动目的","是否故障"};
		table = new Table("RadarActivityServiceImpl", "getRadarActivity",header);
		panel_2 = new JScrollPane(table);		
		panel_2.setBackground(Color.WHITE);
		panel_2.setOpaque(true);
	}

	@Override
	public void initContentFoot() {
		// TODO Auto-generated method stub
		contentFoot.setLayout(new MigLayout("", "[10%][grow][10][grow][10][grow][10][grow][10%]", "[10%][80%][10%]"));
		firstPage = new Button("首 页");
		previousPage = new Button("上 一 页");
		nextPage = new Button("下 一 页");
		lastPage = new Button("尾 页");
	}

}
