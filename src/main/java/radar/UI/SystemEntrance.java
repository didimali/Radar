package radar.UI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;


public class SystemEntrance extends JFrame{
	
	/**
	 * 整个窗体框架类
	 */
	private static final long serialVersionUID = 1L;
	
	public SystemEntrance() {
		getContentPane().setLayout(new BorderLayout(0, 0));
	}
	
	public void initUI() {
		//调用Swing皮肤psg
    	try{
	   	  UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
	   	  SwingUtilities.updateComponentTreeUI(this);
   	    }
   	    catch(Exception e)
   	    {
   	    	System.out.println(e);
   	    }
		
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dimension = kit.getScreenSize();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //设置窗口全屏
        setBounds(0, 0, dimension.width, dimension.height);
		
       	setTitle("雷达PHM系统-首页");
       	//设置窗口大小
//       	setSize(806, 630);
       	//设置窗口屏幕居中
       	setLocationRelativeTo(null);
       	//关闭窗口即退出程序
       	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	//窗口不可以拉伸放缩
//       	setResizable(false);
       	Home home = new Home();
       	home.initUI();
//       	RadarActivityRecord RadarActivityRecord= new RadarActivityRecord();
//       	RadarActivityRecord.init();
        // 添加面板
        getContentPane().add(home);
//      getContentPane().add(RadarActivityRecord);

        // 设置界面可见
        setVisible(true);
	    }

}
