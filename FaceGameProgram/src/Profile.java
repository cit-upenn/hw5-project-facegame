
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
/**
 * @author fanglinlu xinxinma Rony
 * This is a class creating the main window for the FaceGame program.
 */

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
	private JButton b3;
	private JButton b4;
	private JButton b5;


	private JLabel welcome;
	private JLabel imageLabel;
	private JLabel postTag;
	private JLabel gameScoreLabel;
	private JLabel gameScoreTag;


	private JTextArea statusArea;

	
	private JTextField tf1;

	private JFrame container;
	private GameThread game;
	
	private Person loginUser;
	
	private JLabel name;
	private String picturePath;

	private int gameScore;
	private String post;
	private ArrayList<Integer> friendIds;
	
	private JComboBox<String[]> searchFriends;

	
 	private UserProfileWriter upw;
	
/**
 * This is the constructor for the class.
 * @param p
 */
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
/**
 * This is the gui method which dispalys everything on the frame. 
 */
	public void gui() {
/**
 * initialize the variables
 */
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
		this.b3 = new JButton("Update profile picture");
		this.b4 = new JButton("Play Game!");
		this.b5 = new JButton("Add Friends");
		
		this.statusArea = new JTextArea("");
		
		this.tf1 = new JTextField("New Status", 20);
		
		this.container = new JFrame("Drone Wars");
		
		upw = new UserProfileWriter();
		
		ArrayList<Person> users = UserDatabase.getUsers();
		
		setVisible(true);
		setSize(1200, 800);
		setPreferredSize(new Dimension(1200, 800));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.imagePanel.setSize(600, 600);
		this.model = new DefaultTableModel(50, 1) {
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
		
// Add information about the comboBox. 
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


		p1.add(searchFriends);
		p1.add(b5);
		
		// added the image path input Textfield
		p1.add(b3);
		
// set up the west panel
		p2.setBackground(new Color(0.9f, 0.9f, 0.9f));
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
	
	

		this.friendsTable = new JTable(model);
		this.friendsTable.setShowHorizontalLines(false);
		this.friendsTable.setBackground(new Color(0.8f, 0.8f, 0.8f));
		this.friendsTable.setShowVerticalLines(false);
		this.friendsTable.setShowVerticalLines(false);
		this.friendsTable.setRowHeight(20);

		
		
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

		p3.setBackground(new Color(0.9f, 0.9f, 0.9f));
		p4.setBackground(new Color(0.9f, 0.9f, 0.9f));
		
		//add panels to the frame.

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
		//set up the post area
		pPosts.setLayout(new BoxLayout(pPosts,BoxLayout.PAGE_AXIS));
		pPosts.setBackground(new Color(0.9f, 1.0f, 1.0f));
		postTag = new JLabel ("Post Area: ");
		postTag.setFont(new Font("Serif", Font.ITALIC, 30));
		pPosts.add(postTag);
		pCenter.add(pPosts);
		
		statusArea.setAutoscrolls(true);
		statusArea.setText(post);
		statusArea.setFont(new Font("Calibri", Font.PLAIN, 16));
		System.out.println(post);
		statusArea.setBackground(new Color(0.9f, 1.0f, 1.0f));
		pPosts.add(statusArea);
		
		b4.setFont(new Font("Serif", Font.ITALIC, 30));
		
		// add the game panel
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
		
		
		//add actionListeners	
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent updateStatusEvt) {
				updateStatusArea();
				upw.createUserInividuleData(loginUser);
				
				
			}
		});

		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pictureButtonEvt) {

				updatePictureProfile(picturePath, true);
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
	
/**
 * This is a method to update scores.
 * @param score
 */
	
	public void updateScore(int score) {
		gameScore = score;
		ArrayList<Integer> gs = new ArrayList<Integer>();
		gs.add(gameScore);
		loginUser.setGameScore(gs);
		upw.createUserInividuleData(loginUser);
		this.gameScoreLabel.setText("" + score);

	}
/**
 * This is a method to update friends' table. 
 * @param clearSize
 */
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
	/**
	 * This is a method to update status area in the post areas. 
	 */
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

			
	}
	/**
	 * This is a method to update profiel pictures. 
	 * @param picturePath
	 * @param dialog
	 */
	private void updatePictureProfile(String picturePath, boolean dialog) {

		System.out.println(picturePath);
	
		
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

		
		if (this.imagePanel.getComponentCount() > 0)
			this.imagePanel.remove(0);
		
		this.imagePanel.add(this.imageLabel, BorderLayout.NORTH);
		imageLabel.setSize(300, 300);
		imagePanel.setSize(300, 300);

		repaint();

		
	}
	
	/**
	 * This is a method to launch game. 
	 */

	private void launchGame() 
	{
		this.updateScore(0);
		
		game = new GameThread(this.container, this);

		game.start();
	}



}
