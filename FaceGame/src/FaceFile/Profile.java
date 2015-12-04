package FaceFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends JFrame{
	private JPanel p = new JPanel();
	private JButton b1 = new JButton("Update Status");
	private JLabel lab = new JLabel();
	private JTextField tf= new JTextField("New Status", 15);
	
	public Profile() {
		gui();
	}
	
	public void gui() {
		setVisible(true);
		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p.setBackground(new Color (1.0f,0.5f,0.5f));
		p.add(tf);
		p.add(b1);
		
		tf.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e)
			{
				String input = tf.getText();
			}
		});

		add(p,BorderLayout.NORTH);
		
	}
	public static void main(String[] args) {
		Profile startingProfile = new Profile();
	}

}
