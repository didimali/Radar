package radar.UI.TopPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;

public class TopPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel logo;
	private JLabel title;
	protected JPanel topTools;
	public TopPanel() {
		setBackground(Color.WHITE);
		setOpaque(true);
		
		setLayout(new BorderLayout(0, 0));
		
		logo = new JPanel();
		add(logo, BorderLayout.WEST);
		logo.setLayout(new BorderLayout(0, 0));
		
		title = new JLabel("雷达PHM系统");
		title.setIcon(getIcon("radar1",this));
		title.setOpaque(true);
		title.setBackground(new Color(0, 204, 255));
		title.setFont(new Font("仿宋", Font.BOLD, 20));
		title.setPreferredSize(new Dimension(164, 60));
		logo.add(title, BorderLayout.CENTER);
		
		
		topTools = new JPanel();
		topTools.setBackground(Color.WHITE);
		add(topTools, BorderLayout.CENTER);
		
	}
	
	/**
	 * @author :madi
	 * @param: image name
	 * @return: Image
	 */
	public  static  ImageIcon getIcon(String imageName,Object c){		
		
		try {	
			InputStream inputStream=c.getClass().getResourceAsStream("/images/"+imageName+".png");
			if(inputStream != null) {
				BufferedImage bi=ImageIO.read(inputStream);
				Image im=(Image)bi;
				ImageIcon icon = new ImageIcon(im);
				return icon;
			}
			else
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
