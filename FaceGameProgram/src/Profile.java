
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.synth.SynthSeparatorUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Profile extends JFrame {
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel pCenter = new JPanel();
	private JPanel pPosts = new JPanel();
	private JPanel gamePanel = new JPanel();
	private JPanel imagePanel = new JPanel();
	private JPanel gameScorePanel = new JPanel();

	private JButton b1 = new JButton("Add Status");
	private JButton b2 = new JButton("Search!");
	private JButton b3 = new JButton("Update profile picture");
	private JButton b4 = new JButton("Play Game!");
	private JButton b5 = new JButton("Add Friends");
	private JButton b6 = new JButton ("Delete Freinds");

	private JLabel welcome;
	private JLabel penguin;
	private JLabel imageLabel;
	private JLabel postTag;
	private JLabel status;
	private JLabel gameScoreLabel;
	private JLabel gameScoreTag;
//	private JLabel nameArea = new JLabel("");
	
	private JTextArea statusArea = new JTextArea("");
	
	private JTextField tf1 = new JTextField("New Status", 20);
	private JTextField tf2 = new JTextField("Search Friends", 15);
	private JTextField tf3 = new JTextField("Search users", 15);
	private JTextField tf4 = new JTextField("Image path", 15);
	
	private ImageIcon img;
	private BufferedImage image;
	private JFrame container = new JFrame("Drone Wars");
	private GameThread game;
	
	private Person loginUser;
	
	private JLabel name;
	private String picturePath = "emptyProfilePicture2.jpg";
//	private String picturePath = "penguin.png";
	private int gameScore = 0;
	private String post = "";
	
	private JComboBox searchFriends;
	private JComboBox deleteFriends;
	
//	public Profile () {
//		gui();
//	}

	public Profile(Person p) {
		loginUser = p;
		name = new JLabel(loginUser.getName());
		name.setFont(new Font("Serif", Font.ITALIC, 40));
		
		if (loginUser.getPicturePath().length() > 0) {
			picturePath = loginUser.getPicturePath();
		}
		ArrayList<Integer> gameScoreList = loginUser.getGameScore();
		if (gameScoreList != null && gameScoreList.size() > 0) {
			gameScore = gameScoreList.get(0);
		}
		ArrayList<String> posts = loginUser.getPosts();
		if(posts != null && posts.size() > 0) {
			post = posts.get(0);
		}
		
		gui();

	}

	public void gui() {
		setVisible(true);
		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		p1.setBackground(new Color(0.9f, 1.0f, 1.0f));
		p1.add(tf1);
		p1.add(b1);
		
		ArrayList<Person> usersFromDatabase = UserDatabase.getUsers();
		System.out.println(usersFromDatabase.size());
		int databaseSize = UserDatabase.getNumberOfUsers();
		System.out.println(databaseSize);
		
		String[] userNameArray = new String[databaseSize];
		
		int j = 0;
		
		for (int i = 0; i < databaseSize;i++) {
			String userName = usersFromDatabase.get(i).getName();
			if (!userName.equals(loginUser.getName())){
				userNameArray[j] = userName;
				j++;
			}

		}
		
		searchFriends = new JComboBox(userNameArray);

//		p1.add(tf3);
		p1.add(searchFriends);
		p1.add(b5);
		// added the image path input Textfield
		p1.add(tf4);
		p1.add(b3);

		p2.setBackground(new Color(0.9f, 0.9f, 0.9f));
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		welcome = new JLabel ("We Love FaceGame!");
		welcome.setFont(new Font("Superclarendon", Font.BOLD | Font.ITALIC, 14));
		
		BufferedImage image= null;
		
		try {
			image = ImageIO.read(new File("penguin.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		penguin = new JLabel(new ImageIcon(image));
		JLabel penguin2 = new JLabel (new ImageIcon(image)); 
		
		JLabel penguin3 = new JLabel (new ImageIcon(image));
		JLabel penguin4 = new JLabel (new ImageIcon(image));

		
		p2.add(welcome);
		p2.add(penguin);
		p2.add(penguin2);
		p2.add(penguin3);
		p2.add(penguin4);

		p3.setBackground(new Color(0.9f, 0.9f, 0.9f));
		p4.setBackground(new Color(0.9f, 0.9f, 0.9f));
		
		

		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.WEST);
		add(p3, BorderLayout.SOUTH);
		add(p4, BorderLayout.EAST);
		add(this.pCenter);	
		pCenter.setLayout(new GridLayout(2,3));
		
		pCenter.add(imagePanel);
		
		try {
			updatePictureProfile(picturePath);
			this.imageLabel.setSize(40, 40);
			imagePanel.add(imageLabel);

		} catch (Exception e){
			e.printStackTrace();
		}		
		
		pCenter.add(name);
		
		pPosts.setLayout(new BoxLayout(pPosts,BoxLayout.PAGE_AXIS));
		pPosts.setBackground(new Color(0.9f, 1.0f, 1.0f));
		postTag = new JLabel ("Post Area: ");
		postTag.setFont(new Font("Serif", Font.ITALIC, 30));
		pPosts.add(postTag);
		pCenter.add(pPosts);
		
//		status = new JLabel (post);
//		status.setFont (new Font("Calibri", Font.PLAIN, 16));
//		pPosts.add(status);
		
		statusArea.setText(post);
		statusArea.setBackground(new Color(0.9f, 1.0f, 1.0f));
		pPosts.add(statusArea);
		
		b4.setFont(new Font("Serif", Font.ITALIC, 30));
		
		gamePanel.setLayout(new BoxLayout(gamePanel,BoxLayout.PAGE_AXIS));;
		gamePanel.add(b4);

		gameScoreLabel = new JLabel ("" + gameScore);
		gameScoreTag = new JLabel ("Your most recent game score is: ");
		gameScoreTag.setFont (new Font("Calibri", Font.PLAIN, 16));
		gameScoreLabel.setFont (new Font("Serif", Font.ITALIC, 30));
		
		gameScorePanel.add(gameScoreTag);
		gameScorePanel.add(gameScoreLabel);
		
		gamePanel.add(gameScorePanel);
		
		pCenter.add(gamePanel);
		
		
			
//		try {
//			
//			add(this.statusArea);
//			statusArea.setLocation(340, 400);
//			this.statusArea.setOpaque(false);
//			statusArea.setText(null);
//		
//			updatePictureProfile();
//			add(this.imageLabel);
//			imageLabel.setLocation(340, 50);
//			this.imageLabel.setSize(300, 300);
//					
//			add(this.nameArea);
//			nameArea.setLocation(650, 50);
//			this.nameArea.setOpaque(false);
//			updateNameArea();
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
			
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent updateStatusEvt) {
	//			updateStatus();
				updateStatusArea();
				pPosts.add(statusArea);
				
//				System.out.println(statusArea.getText());
				
			}
		});

		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pictureButtonEvt) {
				picturePath = tf4.getText();
				updatePictureProfile(picturePath);
				System.out.println(imageLabel.getText());
				imagePanel.add(imageLabel);
		
			}
		
		});

		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchGame();
			}
		});
		
		b5.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent addFriendsEvent){
				ArrayList <Person> userListInfo = UserDatabase.getUsers();
				int found = -1;
				for (int i = 0; i < userListInfo.size(); i++) {
					if (userListInfo.get(i).getName().compareTo(searchFriends.getSelectedItem().toString()) == 0) {
						found = i;
						break;
					}
				}
				if (found != -1) {
					loginUser.addFriend(found);
					System.out.println(loginUser.getFriends());
					UserProfileWriter upw = new UserProfileWriter();
					upw.createUserInividuleData(loginUser);
				}
			}
		});
		
		pack();
		
	}
	
/*	private void updateNameArea() {
		
		String username = "Xinxin Ma";
		this.nameArea.setText(username);			
		this.nameArea.setFont(new Font("Serif", Font.ITALIC, 40));
	}
*/
//	private void updateStatus() {
//		String input = tf1.getText();
//		
//		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date dateobj = new Date();
//		
//		GeoLocation gl = new GeoLocation();
//		gl.getLocationData();
//		String[] glStrings = gl.getLocationStrings();
//		String finalLocation = "";
//		String s;
//		for (int i = 1; i < glStrings.length - 5; i++)
//		{	
//			s = glStrings[glStrings.length - 5 - i];
//			finalLocation +=  s  + ", ";
//		}
//		s = glStrings[glStrings.length - 5];
//		finalLocation += s;
//		
//		this.status.setText(input + "\n" + "Date: " + df.format(dateobj) + "\n" + "Location: " + finalLocation);
//		this.statusArea.setFont(new Font("Calibri", Font.PLAIN, 16));
//		this.statusArea.setLocation(340, 450);
		
//	}
	
	public void updateScore(int score) {
		gameScore = score;
		ArrayList<Integer> gs = new ArrayList<Integer>();
		gs.add(gameScore);
		loginUser.setGameScore(gs);
		this.gameScoreLabel.setText("" + score);
		//JLabel newScore = new JLabel ("" + score);
		//gameScorePanel = new JPanel ();
		//gameScoreLabel = new JLabel ("" + gameScore);
		//gameScoreTag = new JLabel ("Your most recent game score is: ");
		//gameScorePanel.add(gameScoreTag);
		//gameScorePanel.add(gameScoreLabel);
		//gamePanel.add(gameScorePanel, BorderLayout.CENTER);
	}

	private void updateStatusArea()
	{
		this.statusArea.setText("");
		String input = tf1.getText();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		
		GeoLocation gl = new GeoLocation();
		gl.getLocationData();
		String[] glStrings = gl.getLocationStrings();
		String finalLocation = "";
		String s;
		for(int i = 1; i < glStrings.length - 5; i++)
		{	
			s = glStrings[glStrings.length - 5 - i];
			finalLocation +=  s  + "\n";
		}
		s = glStrings[glStrings.length - 5];
		finalLocation += s;
		String textAreaInput = input + "\n" + "Date: " + df.format(dateobj) + "\n" + "Location: " + finalLocation;
		System.out.println(textAreaInput);
		this.statusArea.setText(textAreaInput);
		this.statusArea.setFont(new Font("Calibri", Font.PLAIN, 16));
//		this.statusArea.setLocation(340, 450);
		//this.imageLabel.setLocation(340, 50);
			
	}
	
	private void updatePictureProfile(String picturePath) {
		// TODO Auto-generated method stub
//		String imagePath = "./penguin.png";
		//String imagePath = tf4.getText();
		
//		img = new ImageIcon(getClass().getResource("bomb.png"));
//		BufferStrategy strategy = getBufferStrategy() ;
//		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
//		//img = new Image ("bomb.png");
//		
//		//Component buf = ImageIO.read(file);
//
//		//pCenter.add(buf);
//		add(pCenter, BorderLayout.CENTER);
//		pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.PAGE_AXIS));
//
//		imageLabel = new JLabel(img);
//		pCenter.add (imageLabel);
//
//		add(imageLabel);
		System.out.println(picturePath);
		
		
		BufferedImage originalImage = null;
		BufferedImage image = null;
		try {
			originalImage = ImageIO.read(new File(picturePath));
			image = new BufferedImage (originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			AffineTransform at = new AffineTransform();
			double scaleX = 150.0 / originalImage.getWidth();
			double scaleY = 150.0 / originalImage.getHeight();
			at.scale(scaleX,  scaleY);
			AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			ato.filter(originalImage, image);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Come to catch!");
			e.printStackTrace();
		}
		this.imageLabel = new JLabel(new ImageIcon(image));
		Dimension d = new Dimension(10, 10);
		this.imageLabel.setSize(d);;
		
	}
	/*
	 * MainWindow win = new MainWindow(); win.gameLoop(); add(win); GameThread
	 * game = new GameThread(); game.run(); add(game.window);
	 */

	private void launchGame() 
	{
		this.updateScore(50);
		
		game = new GameThread(this.container, this);
		//game.window.gameInit();
		game.start();
		// p1.add(container, BorderLayout.CENTER);
		//game.window.getPlayerScore();
		// game.run();

		// add(game.window, BorderLayout.EAST);//, BorderLayout.EAST);
		// game.window.run();
		// pack();
	}

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
		char[] pw ={'a','b','c'};
		Person loginUser = new Person("Xinxin", "sss@upenn.edu", pw);
		Profile startingProfile = new Profile(loginUser);
//		startingProfile.updateScore (5);
				
	}


}
