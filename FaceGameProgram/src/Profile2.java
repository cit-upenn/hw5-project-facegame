import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Profile2 extends JFrame{
	
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel pCenter = new JPanel();
	private JPanel pPosts = new JPanel();
	private JPanel game = new JPanel();

	private JButton b1 = new JButton("Update!");
	private JButton b2 = new JButton("Search!");
	private JButton b3 = new JButton("Update Avatar");
	private JButton b4 = new JButton("Play Game!");
	private JButton b5 = new JButton("Add Friends");

	private JLabel imageLabel;
	private JLabel name; 
	private JLabel status;
	private JLabel postTag;
	private JTextArea textArea;
	
	private JTextField tf1 = new JTextField("What are you doing?", 20);
	private JTextField tf2 = new JTextField("Search Friends", 15);
	private JTextField tf3 = new JTextField("Search users", 15);
	private JTextField tf4 = new JTextField("Image path", 15);
	private ImageIcon img;
	private JTextArea statusField = new JTextArea("");
	public Profile2() {
		gui();
	}
	
	private void gui() {
		setVisible(true);
		setSize(1200, 800);
		setMinimumSize(new Dimension (1200, 400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		setLayout(new GridLayout(3,4));
//		
//		p1.setBackground(new Color(0.9f, 1.0f, 1.0f));
//		p1.add(tf1);
//		p1.add(b1);
//
//		p1.add(tf3);
//		p1.add(b5);
//		// added the image path input Textfield
//		p1.add(tf4);
//		p1.add(b3);
//		
//		p2.setBackground(new Color(0.9f, 0.9f, 0.9f));
//		p2.add(tf2);
//		p2.add(b2, BorderLayout.SOUTH);
//
//		p3.setBackground(new Color(0.9f, 0.9f, 0.9f));
//		p4.setBackground(new Color(0.9f, 0.9f, 0.9f));
//		
//		add(p1);
//		add(p2);
//		add(p3);
//		add(p4);
		
		
		p1.setBackground(new Color(0.9f, 1.0f, 1.0f));
		p1.add(tf1);
		p1.add(b1);

		p1.add(tf3);
		p1.add(b5);
		// added the image path input Textfield
		p1.add(tf4);
		p1.add(b3);

		p2.setBackground(new Color(0.9f, 0.9f, 0.9f));
		p2.add(tf2);
		p2.add(b2, BorderLayout.SOUTH);

		p3.setBackground(new Color(0.9f, 0.9f, 0.9f));
		p4.setBackground(new Color(0.9f, 0.9f, 0.9f));

		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.WEST);
		add(p3, BorderLayout.SOUTH);
		add(p4, BorderLayout.EAST);
		
		add(this.pCenter);
		
		pCenter.setLayout(new GridLayout(2,3));
		try {
			String imagePath = "penguin.png";
			BufferedImage image = ImageIO.read(new File(imagePath));
			JLabel picLabel = new JLabel(new ImageIcon(image));
			picLabel.setSize(300, 300);
			pCenter.add(picLabel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		name = new JLabel("Xinxin Ma");
//		this.textArea = new JTextArea(username, 6, 30);
		name.setFont(new Font("Serif", Font.ITALIC, 40));
//		this.textArea.setLineWrap(false);
//		this.textArea.setWrapStyleWord(false);
//		this.textArea.setOpaque(false);
//		this.textArea.setEditable(false);
		pCenter.add(name);
		pPosts.setBackground(new Color(0.9f, 1.0f, 1.0f));
		postTag = new JLabel ("Post Area: ");
		postTag.setFont(new Font("Serif", Font.ITALIC, 30));
		pPosts.add(postTag, BorderLayout.NORTH);
		pCenter.add(pPosts);
		status = new JLabel ("I feel really good today");
		pPosts.add(status);
		game.add(b4, BorderLayout.NORTH);
		pCenter.add(game);
		
		
		pack();

	}
	
	public static void main(String[] args) throws IOException {
		Profile2 startingProfile = new Profile2();
						
	}
}
