
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

public class Profile extends JFrame {
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel pCenter = new JPanel();

	private JButton b1 = new JButton("Update!");
	private JButton b2 = new JButton("Search!");
	private JButton b3 = new JButton("Update Avatar");
	private JButton b4 = new JButton("Enjoy Game!");
	private JButton b5 = new JButton("Add Friends");

	private JLabel imageLabel;
	private JTextArea textArea;
	
	private JTextField tf1 = new JTextField("What are you doing?", 20);
	private JTextField tf2 = new JTextField("Search Friends", 15);
	private JTextField tf3 = new JTextField("Search users", 15);
	private JTextField tf4 = new JTextField("Image path", 15);
	private ImageIcon img;
	private JTextArea statusField = new JTextArea("");

	private BufferedImage image;
	private JFrame container = new JFrame("Drone Wars");
	private GameThread game;
	
	
	
	private static Person loginUser;

	public Profile(Person p) {
		loginUser = p;
		gui();

	}

	public void gui() {
		setVisible(true);
		setSize(1200, 800);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);

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
		p1.add(b4, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		

		try {
			String imagePath = "./2.pic.jpg";
			BufferedImage image = ImageIO.read(new File(imagePath));
			JLabel picLabel = new JLabel(new ImageIcon(image));
			add(picLabel);
			picLabel.setLocation(340, 50);
			picLabel.setSize(300, 300);
			
			String username = "                                                       Xinxin Ma";
  			this.textArea = new JTextArea(username, 6, 30);
			this.textArea.setFont(new Font("Serif", Font.ITALIC, 32));
			this.textArea.setLineWrap(false);
			this.textArea.setWrapStyleWord(false);
			this.textArea.setOpaque(false);
			this.textArea.setEditable(false);
			add(this.textArea);
			this.textArea.setLocation(300, 40);
			this.textArea.setSize(800, 100);
			
			Dimension m = new Dimension();
			m.setSize(400, 400);
			this.textArea.setAlignmentX(100);
			this.textArea.setAlignmentX(200);
			this.textArea.setPreferredSize(m);
			
			Dimension preferredSize = new Dimension(20, 30);
			this.textArea.setPreferredSize(preferredSize);
			
			//pack();
			
			this.statusField.setLocation(300, 600);
			this.statusField.setOpaque(false);
			add(this.statusField);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent updateStatusEvt) {
				updateStatusField();
			}
		});

		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pictureButtonEvt) {
				pictureButtonActionPerformed(pictureButtonEvt);

			}
		});

		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	launchGame();
			}
		});
		
	}

	private void updateStatusField()
	{
		this.statusField.setText("");
		String input = tf1.getText();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		
		GeoLocation gl = new GeoLocation();
		gl.getLocationData();
		String[] glStrings = gl.getLocationStrings();
		String finalLocation = "";
		for(String s : glStrings)
		{
			finalLocation +=  s  + System.lineSeparator();
		}
		
		this.statusField.setText(input + " " + df.format(dateobj) + finalLocation);
		
	}
	/*
	 * MainWindow win = new MainWindow(); win.gameLoop(); add(win); GameThread
	 * game = new GameThread(); game.run(); add(game.window);
	 */

//	private void launchGame() {
//		game = new GameThread(this.container);
//		game.window.gameInit();
//		game.start();
//		// p1.add(container, BorderLayout.CENTER);
//		game.window.getPlayerScore();
//		// game.run();
//
//		// add(game.window, BorderLayout.EAST);//, BorderLayout.EAST);
//		// game.window.run();
//		// pack();
//	}

	/*
	 * private void launchGame() { MainWindow win = new MainWindow();
	 * win.gameLoop(); add(win, BorderLayout.EAST);//, BorderLayout.EAST);
	 * pack(); }
	 */

//	private void pictureButtonActionPerformed(ActionEvent evt) {
//		
//		String imgPath = tf4.getText();
//		img = new ImageIcon(getClass().getResource("bomb.png"));
//		BufferStrategy strategy = getBufferStrategy() ;
//		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
//		//img = new Image ("bomb.png");
//		
//		Component buf = ImageIO.read(file);
//
//		pCenter.add(buf);
//		add(pCenter, BorderLayout.CENTER);
//		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.PAGE_AXIS));
//
//		imageLabel = new JLabel(img);
//		pCenter.add (imageLabel);
//
//		add(imageLabel);
//
//	}
	
	public static void main(String[] args) throws IOException {
		Profile startingProfile = new Profile(loginUser);
						
	}


}
