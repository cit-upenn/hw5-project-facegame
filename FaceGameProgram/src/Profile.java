
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Profile extends JFrame {
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel pCenter = new JPanel();

	private JButton b1 = new JButton("Update Status");
	private JButton b2 = new JButton("Search!");
	private JButton b3 = new JButton("Add Profile Picture");
	private JButton b4 = new JButton("Play Game");
	private JButton b5 = new JButton("Add Friends");

	private JLabel imageLabel;
	private JTextArea textArea;
	private JTextArea statusArea;


	private JTextField tf1 = new JTextField("New Status", 15);
	private JTextField tf2 = new JTextField("Search Friends", 15);
	private JTextField tf3 = new JTextField("New Friends", 15);
	private JTextField tf4 = new JTextField("Picture Path", 15);
	private Image img;

	private BufferedImage image;
	private JFrame container = new JFrame("Drone Wars");
	private GameThread game;

	public Profile() {
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

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent updateStatusEvt) {
				tf1ActionPerformed(updateStatusEvt);

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

		try {
			BufferedImage image = ImageIO.read(new File("./1.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(image));
			add(picLabel);
			picLabel.setLocation(340, 100);
			picLabel.setSize(200, 200);

			JTextArea textArea = new JTextArea("Name", 6, 20);
			textArea.setFont(new Font("Serif", Font.ITALIC, 32));
			textArea.setLineWrap(false);
			textArea.setWrapStyleWord(false);
			textArea.setOpaque(false);
			textArea.setEditable(false);
			add(textArea);
			textArea.setLocation(600, 120);
			textArea.setSize(200, 200);

		} catch (Exception e) {
			e.printStackTrace();
		}

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

	private void pictureButtonActionPerformed(ActionEvent evt) {
		// paint a picture to profile
		String imgPath = tf4.getText();
//		img = new ImageIcon(getClass().getResource("bomb.png"));
		//BufferStrategy strategy = getBufferStrategy() ;
		//		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		//		img = new Image ("bomb.png");
		
		
		// Image buf = ImageIO.read(file);

		// pCenter.add(buf);
		//add(pCenter, BorderLayout.CENTER);
		//pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.PAGE_AXIS));

		//imageLabel = new JLabel(img);
		// pCenter.add (imageLabel);

		//add(imageLabel);

	}

	private void tf1ActionPerformed(ActionEvent evt) {
		String input = tf1.getText();
		statusArea.append(input + "\n");
		tf1.selectAll();
		statusArea.setCaretPosition(statusArea.getDocument().getLength());

		// Person user = new Person(inputName, inputEmail, inputPw);

		// UserDatabase.addProfile(user);
		// Profile userProfile = new Profile();
	}

	public static void main(String[] args) throws IOException {
		Profile startingProfile = new Profile();

	}

}
