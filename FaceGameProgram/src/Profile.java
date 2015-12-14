
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel pCenter;
	private JPanel pPosts;
	private JPanel gamePanel;
	private JPanel imagePanel;
	private JPanel gameScorePanel;
	private JTable friendsTable;
	private TableModel model;
	private JTextArea friendsInfo;
	
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton deleteFriendButton;


	private JLabel welcome;
	private JLabel penguin;
	private JLabel imageLabel;
	private JLabel postTag;
	private JLabel status;
	private JLabel gameScoreLabel;
	private JLabel gameScoreTag;

//	private JLabel nameArea = new JLabel("");

	private JTextArea statusArea;

	
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	//private JTextField tf4;
	
	private ImageIcon img;
	private BufferedImage image;
	private JFrame container;
	private GameThread game;
	
	private Person loginUser;
	
	private JLabel name;
	private String picturePath;

	private int gameScore;
	private String post;
	private ArrayList<Integer> friendIds;
	
	private JComboBox searchFriends;

	
 	private UserProfileWriter upw;
	
//	public Profile () {
//		gui();
//	}

	public Profile(Person p) {
		this.post = "";
		this.picturePath = "emptyProfilePicture2.jpg";
		this.gameScore = 0;
		this.friendIds = new ArrayList<Integer>();
		
		loginUser = p;
		name = new JLabel(loginUser.getName());
		name.setFont(new Font("Serif", Font.ITALIC, 40));
		
		if (loginUser.getPicturePath().length() > 0) {
			picturePath = loginUser.getPicturePath();
//			System.out.println(picturePath);
		}
		ArrayList<Integer> gameScoreList = loginUser.getGameScore();
		if (gameScoreList != null && gameScoreList.size() > 0) {
			gameScore = gameScoreList.get(0);
		}
		
		ArrayList<Integer> userFriendsIds = loginUser.getFriends();
		if (userFriendsIds != null && userFriendsIds.size() > 0){
			friendIds = userFriendsIds;
		}
		ArrayList<String> posts = loginUser.getPosts();
		if(posts != null && posts.size() > 0) {
			post = posts.get(0);
		}
		
		gui();

	}

	public void gui() {
		
		this.p1 = new JPanel();
		this.p2 = new JPanel();
		this.p3 = new JPanel();
		this.p4 = new JPanel();
		this.pCenter = new JPanel();
		this.pPosts = new JPanel();
		this.gamePanel = new JPanel();
		this.imagePanel = new JPanel();
		this.gameScorePanel = new JPanel();
		this.friendsInfo = new JTextArea("");
		this.b1 = new JButton("Add Status");
		this.b2 = new JButton("Search!");
		this.b3 = new JButton("Update profile picture");
		this.b4 = new JButton("Play Game!");
		this.b5 = new JButton("Add Friends");
		
		this.statusArea = new JTextArea("");
		
		this.tf1 = new JTextField("New Status", 20);
		this.tf2 = new JTextField("Search Friends", 15);
		this.tf3 = new JTextField("Search users", 15);
		//this.tf4 = new JTextField("Image path", 15);
		
		this.container = new JFrame("Drone Wars");
		
		upw = new UserProfileWriter();
		
		ArrayList<Person> users = UserDatabase.getUsers();
		
		setVisible(true);
		setSize(1200, 800);
		setPreferredSize(new Dimension(1200, 800));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.imagePanel.setSize(600, 600);
		this.model = new DefaultTableModel(50, 1){
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       //Only the third column
			       return column == 3;
			   }
			};
		this.friendsTable = new JTable(model);
		
		p1.setBackground(new Color(0.9f, 1.0f, 1.0f));
		p1.add(tf1);
		p1.add(b1);
		
		ArrayList<Person> usersFromDatabase = UserDatabase.getUsers();
		System.out.println(usersFromDatabase.size());
		int databaseSize = UserDatabase.getNumberOfUsers();
		System.out.println(databaseSize);
		
//		String[] friendsList = new String[]
		
		
		String[] userNameArray = new String[databaseSize];
		int j = 0;
		
		for (int i = 0; i < databaseSize;i++) {
			String userName = usersFromDatabase.get(i).getName();
			if (!userName.equals(loginUser.getName())){
				userNameArray[j] = userName;
				j++;
			}

		}
		
		this.searchFriends = new JComboBox(userNameArray);


//		p1.add(tf3);
		p1.add(searchFriends);
		p1.add(b5);
		
		// added the image path input Textfield
		//p1.add(tf4);
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
		//JLabel penguin2 = new JLabel (new ImageIcon(image)); 
		//JLabel penguin3 = new JLabel (new ImageIcon(image));
		//JLabel penguin4 = new JLabel (new ImageIcon(image));

		

		this.friendsTable = new JTable(model);
		this.friendsTable.setShowHorizontalLines(false);
		this.friendsTable.setBackground(new Color(0.8f, 0.8f, 0.8f));
		this.friendsTable.setShowVerticalLines(false);
		this.friendsTable.setShowVerticalLines(false);
		this.friendsTable.setRowHeight(20);

		// fore testing purposes
//		this.model.setValueAt("friend1", 0, 0);
//		this.model.setValueAt("friend2", 1, 0);
		// test end
		

		
		
		this.friendsTable.addMouseListener(new MouseAdapter()
		{
            @Override
            public void mouseClicked(MouseEvent e)
            {
            	int index = friendsTable.getSelectedRow();
            	if(index != -1)
            	{
            		if (model.getValueAt(index, 0) != null)
            		{	
            			if (index<friendIds.size())
            			{
            				Person clickedFriend = users.get(friendIds.get(index));
            				String strValue = clickedFriend.getPosts().get(0);
            				friendsInfo.setText(clickedFriend.getName() + "'s status is: \n" + strValue);
            			}
            		}
            	}
            }
        });
		
		
		
		for (int i=0; i<friendIds.size(); i++)
		{
			Person friendPerson = users.get(friendIds.get(i));
			this.model.setValueAt(friendPerson.getName(), i, 0);
		}
		
		JLabel friendsLabelField = new JLabel("Friends List");
		friendsLabelField.setFont(new Font("Serif", Font.ITALIC, 27));
		
		JLabel friendsInfoLabel = new JLabel("Friend Info");
		friendsInfoLabel.setFont(new Font("Serif", Font.ITALIC, 25));
		p2.add(friendsInfoLabel);
		
		p2.add(this.friendsInfo);
		
		// add friends title for friends list
		p2.add(friendsLabelField);
		// add friends table
		p2.add(this.friendsTable);

		// update table
		this.updateFriendsTable(friendIds.size());
		
		//p2.add(welcome);
		//p2.add(penguin);
		//p2.add(penguin2);
		//p2.add(penguin3);
		//p2.add(penguin4);

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
			updatePictureProfile(picturePath, false);
			this.imageLabel.setSize(300, 300);
			this.imagePanel.setSize(300, 300);
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
		//JScrollPane scroll = new JScrollPane (statusArea, 
		//		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		//pPosts.add(scroll);
		
		statusArea.setAutoscrolls(true);
		statusArea.setText(post);
		statusArea.setFont(new Font("Calibri", Font.PLAIN, 16));
		System.out.println(post);
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
				//pPosts.add(statusArea);
//				System.out.println(statusArea.getText());
				upw.createUserInividuleData(loginUser);
				
				
			}
		});

		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pictureButtonEvt) {

				//picturePath = tf4.getText();
				updatePictureProfile(picturePath, true);
//				imagePanel.add(imageLabel);
				loginUser.setPicturePath(picturePath);
				upw.createUserInividuleData(loginUser);
		
			}
		
		});

		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchGame();
			}
		});
		
		b5.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent addFriendsEvent){
				int found = -1;
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getName().compareTo(searchFriends.getSelectedItem().toString()) == 0) {
						found = i;
						break;
					}
				}
				if (found != -1) {
					loginUser.addFriend(found);
					friendIds = loginUser.getFriends();
					upw.createUserInividuleData(loginUser);
					updateFriendsTable(friendIds.size());
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
		upw.createUserInividuleData(loginUser);
		this.gameScoreLabel.setText("" + score);
		//JLabel newScore = new JLabel ("" + score);
		//gameScorePanel = new JPanel ();
		//gameScoreLabel = new JLabel ("" + gameScore);
		//gameScoreTag = new JLabel ("Your most recent game score is: ");
		//gameScorePanel.add(gameScoreTag);
		//gameScorePanel.add(gameScoreLabel);
		//gamePanel.add(gameScorePanel, BorderLayout.CENTER);
	}

	private void updateFriendsTable(int clearSize)
	{
		if (clearSize > 0){
			for (int i = 0; i < clearSize; i++) {
				this.model.setValueAt("", clearSize, 0);
			}
		}
		ArrayList<Integer> friendIds = loginUser.getFriends();
		ArrayList<Person> users = UserDatabase.getUsers();
		
		for (int i=0; i<friendIds.size(); i++)
		{
			Person friendPerson = users.get(friendIds.get(i));
			this.model.setValueAt(friendPerson.getName(), i, 0);
		}
	}
	
	private void updateStatusArea()
	{
		//this.statusArea.setText("");
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
			finalLocation +=  s  + ", ";
		}
		s = glStrings[glStrings.length - 5];
		finalLocation += s;
		String textAreaInput = input + "\n" + "Date: " + df.format(dateobj) + "\n" + "Location: " + finalLocation;
		System.out.println(textAreaInput);
		String outputText = textAreaInput + "\n\n" + this.statusArea.getText();
		this.statusArea.setText(outputText);
		ArrayList<String> postListString = new ArrayList<String>();
		postListString.add(outputText);
		loginUser.setPostList(postListString);
		System.out.println(postListString);
		this.statusArea.setFont(new Font("Calibri", Font.PLAIN, 16));
//		this.statusArea.setLocation(340, 450);
		//this.imageLabel.setLocation(340, 50);
			
	}
	
	private void updatePictureProfile(String picturePath, boolean dialog) {
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
		
		//FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
		//fd.setDirectory(".");
		
		if (dialog)
		{
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG, PNG Images", "jpg", "PNG");
			chooser.setFileFilter(filter);
			chooser.getCurrentDirectory();

			int returnVal = chooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) 
			{
				picturePath = chooser.getSelectedFile().getAbsolutePath();
			}
			File currentPic = new File(picturePath);
			this.picturePath = picturePath;
			if (currentPic.exists() == false)
				return;
		}
		
		BufferedImage originalImage = null;
		BufferedImage image = null;
		try {
			originalImage = ImageIO.read(new File(picturePath));
			image = new BufferedImage (300, 300, BufferedImage.TYPE_INT_ARGB);
			
			AffineTransform at = new AffineTransform();
			double scaleX = 1.0 * 300.0 / originalImage.getWidth();
			double scaleY = 1.0 * 300.0 / originalImage.getHeight();
			at.scale(scaleX,  scaleX);
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
		//this.imagePanel.setSize(new Dimension(600, 600));
		
		if (this.imagePanel.getComponentCount() > 0)
			this.imagePanel.remove(0);
		
		this.imagePanel.add(this.imageLabel, BorderLayout.NORTH);
		imageLabel.setSize(300, 300);
		imagePanel.setSize(300, 300);
		//this.imagePanel.repaint();
		//this.imageLabel.repaint();
		repaint();
		//this.pCenter.repaint();
		//this.imagePanel.repaint();
		//this.imageLabel.repaint();
		//this.imagePanel.update(null);
		
	}
	/*
	 * MainWindow win = new MainWindow(); win.gameLoop(); add(win); GameThread
	 * game = new GameThread(); game.run(); add(game.window);
	 */

	private void launchGame() 
	{
		this.updateScore(0);
		
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
	
//	public static void main(String[] args) throws IOException {
//		char[] pw ={'a','b','c'};
//		Person loginUser = new Person("Xinxin", "sss@upenn.edu", pw);
//		Profile startingProfile = new Profile(loginUser);
////		startingProfile.updateScore (5);
//				
//	}


}
