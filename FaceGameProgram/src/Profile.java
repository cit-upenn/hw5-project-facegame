

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends JFrame{
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JButton b1 = new JButton("Update Status");
	private JButton b2 = new JButton("Search!");
	private JButton b3 = new JButton("Add Profile Picture");
	private JButton b4 = new JButton("Play Game");
	private JButton b5 = new JButton ("Add Friends");
	private JLabel lab = new JLabel();
	private JTextField tf1= new JTextField("New Status", 15);
	private JTextField tf2 = new JTextField("Search Friends", 15);
	private JTextField tf3 = new JTextField("New Friends", 15);
	private JTextField tf4 = new JTextField ("Picture Path", 15);

	
	public Profile() {
		gui();
	}
	
	public void gui() {
		setVisible(true);
		setSize(1200, 800);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p1.setBackground(new Color (0.9f, 1.0f, 1.0f));
		p1.add(tf1);
		p1.add(b1);
		
		p1.add(tf3);
		p1.add(b5);
		
		p1.add(tf4);
		p1.add(b3);
		
		p2.setBackground(new Color (0.9f, 0.9f, 0.9f));
		p2.add(tf2);
		p2.add(b2,  BorderLayout.SOUTH);
		

		
		p3.setBackground(new Color(0.9f, 0.9f, 0.9f));
		p4.setBackground(new Color(0.9f, 0.9f, 0.9f));
		
		tf1.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e)
			{
				String input = tf1.getText();
			}
		});

		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.WEST);
		add(p3, BorderLayout.SOUTH);
		add(p4, BorderLayout.EAST);
		
		p1.add(b4,  BorderLayout.SOUTH);
		b4.addActionListener(new ActionListener() 
		{
			public void actionPerformed (ActionEvent e)
			{
			}
		});
	
/*		MainWindow win = new MainWindow();
		win.gameLoop();
		add(win);
		GameThread game = new GameThread();
		game.run();
		add(game.window);
*/		
		
		
	}
	
/*	private void launchGame()
	{
		MainWindow win = new MainWindow();
		win.gameLoop();
		add(win, BorderLayout.EAST);//, BorderLayout.EAST);
		pack();
	}
*/

//	public static void main(String[] args) {
//		Profile startingProfile = new Profile();
//	}	
	
}
