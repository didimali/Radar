package radar.UI.TopPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import radar.UI.Components.Button;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class TestTopPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TestTopPanel() {
		setBackground(Color.WHITE);
		setOpaque(true);
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel logo = new JPanel();
		add(logo, BorderLayout.WEST);
		logo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("     LOGO");
		lblNewLabel.setFont(new Font("仿宋", Font.BOLD, 20));
		Dimension size = new Dimension(160,60);
		lblNewLabel.setPreferredSize(new Dimension(164, 60));
		logo.add(lblNewLabel, BorderLayout.CENTER);
		
		
		JPanel topTools = new JPanel();
		topTools.setBackground(Color.WHITE);
		add(topTools, BorderLayout.CENTER);
		topTools.setLayout(new MigLayout("", "[140][140][140][140]", "[5][40][5]"));
		
		JButton btnNewButton_1 = new Button("New button");
		topTools.add(btnNewButton_1, "cell 0 1,grow");
		
		JButton btnNewButton_7 = new Button("New button");
		topTools.add(btnNewButton_7, "cell 1 1,grow");
		
		JButton btnNewButton_8 = new Button("New button");
		topTools.add(btnNewButton_8, "cell 2 1,grow");
		
		JButton btnNewButton_9 = new Button("New button");
		topTools.add(btnNewButton_9, "cell 3 1,grow");
	}


	
	

}
