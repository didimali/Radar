package radar.UI.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import radar.SpringUtil;
import radar.Entity.Radar;
import radar.ServiceImpl.RadarServiceImpl;
import radar.SwingWorker.SwingWorkerForRadarComBox;

@SuppressWarnings({ "serial", "rawtypes" })
public class RadarComBox  extends JComboBox{
	private DefaultComboBoxModel mode = null;
	private Object[] resultData = {"All"};
	private String ServiceImplName;
	private String methodName;

		/*
		 * 构造一个包含数据的下拉框
		 * @param ServiceImplName:下拉框获取数据将要调用的ServiceImpl类的名字
	     * @param methodName:下拉框获取数据将要调用的ServiceImpl类中方法的名字
		 */
		
		@SuppressWarnings({ "unchecked" })
		public RadarComBox(String ServiceImplName,String methodName) {
			
			this.ServiceImplName = ServiceImplName;
			this.methodName = methodName;
			
			//样式设置
			setMaximumRowCount(20);
			setBackground(Color.WHITE);
			setRenderer(new TwoDecimalRenderer(getRenderer()));
			//加载数据
			SwingWorkerForRadarComBox swb = new SwingWorkerForRadarComBox(this,ServiceImplName,methodName);
			swb.execute();
			mode = new DefaultComboBoxModel(resultData);
			setModel(mode);	
		}

		//根据部队筛选雷达
		@SuppressWarnings({ "static-access", "unchecked" })
		public void initRadar(String managerName) {
			if(managerName != null) {
				SpringUtil s = new SpringUtil();
				RadarServiceImpl r = (RadarServiceImpl) s.getBean("RadarServiceImpl");
				List<Radar>  radarNumbers = r.getAllRadars();
				Object[] dataR=r.getRadars();
				if(managerName == "All" || managerName.equals("All")) {
					if(dataR!= null||dataR.length!=0) {
						resultData = new String[1+dataR.length];
						resultData[0] = "All";
						for(int i=0;i<dataR.length;i++) {
							resultData[i+1] =dataR[i];				
						}
					}
					mode = new DefaultComboBoxModel(resultData);
				    setModel(mode);	
			    }			
			    else {
					String[] data = new String[radarNumbers.size()+1];
					data[0] = "All";
					int dataCounts = 1;
					
					for(int i=0;i<radarNumbers.size();i++) {
						Radar rn = radarNumbers.get(i);
						if(rn.getManagerId().getManagerName() == managerName 
								|| rn.getManagerId().getManagerName().equals(managerName)) {
							data[dataCounts] = rn.getRadarName();
							dataCounts++;
						}									
					}
					resultData = new String[dataCounts];
					for(int i=0;i<dataCounts;i++) {
						resultData[i] = data[i];
					}
					mode = new DefaultComboBoxModel(resultData);
					setModel(mode);	
				}			
			}		
		}
	
	@SuppressWarnings({"rawtypes","unchecked"})
	class TwoDecimalRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = -6518120547224228417L;
		private ListCellRenderer defaultRenderer;

		  public TwoDecimalRenderer(ListCellRenderer defaultRenderer) {
		    this.defaultRenderer = defaultRenderer;
		  }

		  @Override
		  public Component getListCellRendererComponent(JList list, Object value,
		      int index, boolean isSelected, boolean cellHasFocus) {
		    Component c = defaultRenderer.getListCellRendererComponent(list, value,
		        index, isSelected, cellHasFocus);
		    c.setFont(new Font("仿宋", Font.PLAIN, 13));
		    if (c instanceof JLabel) {
		    	
		      if (isSelected) {
		        c.setBackground(new Color(135,206,250));
		        c.setForeground(new Color(255,0,0));
		      } else {
		        c.setBackground(Color.WHITE);
		      }
		    } else {
		      c.setBackground(Color.red);
		      c = super.getListCellRendererComponent(list, value, index, isSelected,
		          cellHasFocus);
		    }
		    return c;
		  }
	}
	

}
