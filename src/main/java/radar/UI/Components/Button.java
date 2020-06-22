package radar.UI.Components;

import javax.swing.JButton;
import radar.Tools.InitUIAndAction;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class Button extends JButton implements InitUIAndAction{
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	
	public Button(String text) {
		this.text = text;
		initUI();
		Action();
	}

	@Override
	public void initUI() {
		setOpaque(true);
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setFont(new Font("仿宋", Font.BOLD, 16));
		setText(text);
		setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), Color.WHITE, new Color(255, 255, 255))); 
	}

	@Override
	public void Action() {
		
		this.addMouseListener(new MouseAdapter() {
//			//鼠标悬停按钮变色
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				setBackground(Color.GRAY);
//				setForeground(Color.BLACK);
//				repaint();
//			}
//			//鼠标离开按钮颜色恢复
//			@Override
//			public void mouseExited(MouseEvent e) {
//				setBackground(Color.WHITE);
//				setForeground(Color.BLACK);
//			}
//			@Override
//			public void mousePressed(MouseEvent e) {
//				setBackground(new Color(21,114,232));
//				setForeground(Color.WHITE);
//				repaint();
//			}			
		});
	}
	
}
