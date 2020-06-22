package radar.UI.Components;

import java.awt.Color;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import radar.SpringUtil;
import radar.Entity.Radar;
import radar.ServiceImpl.ManagerServiceImpl;
import radar.ServiceImpl.RadarServiceImpl;
import radar.SwingWorker.SwingWorkerForManagerComBox;


@SuppressWarnings({"rawtypes","unchecked", "serial"})
public class ManagerComBox extends JComboBox{
	private DefaultComboBoxModel mode = null;
	private Object[] resultData = {"All"};
	private String ServiceImplName;
	private String methodName;
	public ManagerComBox(String ServiceImplName, String methodName) {
		this.ServiceImplName = ServiceImplName;
		this.methodName = methodName;
		
		//样式设置
		setMaximumRowCount(20);
		setBackground(Color.WHITE);
		setRenderer(new TwoDecimalRenderer(getRenderer()));
		//加载数据
		SwingWorkerForManagerComBox swb = new SwingWorkerForManagerComBox(this,ServiceImplName,methodName);
		swb.execute();
		mode = new DefaultComboBoxModel(resultData);
		setModel(mode);	
	}

		//根据雷达编号获取雷达所在的部队
		@SuppressWarnings("static-access")
		public void initData(String RadarName) {
			// TODO Auto-generated method stub
			SpringUtil s = new SpringUtil();
			RadarServiceImpl r = (RadarServiceImpl) s.getBean("RadarServiceImpl");
			List<Radar>  radarNumbers = r.getAllRadars();
			ManagerServiceImpl m = (ManagerServiceImpl) s.getBean("ManagerServiceImpl");
			Object[] datam=m.getManagers();			
			if(RadarName != null) {
				if(RadarName == "All" || RadarName.equals("All")) {
					if(datam!= null||datam.length!=0) {
						resultData = new String[1+datam.length];
						resultData[0] = "All";
						for(int i=0;i<datam.length;i++) {
							resultData[i+1] =datam[i];				
						}
					}
					mode = new DefaultComboBoxModel(resultData);
				    setModel(mode);
				}
				else {
					String[] data = new String[1];
//					data[0] = "All";
					int dataCounts = 0;
					for(int i=0;i<radarNumbers.size();i++) {
						Radar  ra= radarNumbers.get(i);
						if( ra.getRadarName()== RadarName 
								|| ra.getRadarName().equals(RadarName)) {
							data[dataCounts] = ra.getManagerId().getManagerName();
						}									
					}
					
					mode = new DefaultComboBoxModel(data);
					setModel(mode);	
				}			
			}	
		}

}
