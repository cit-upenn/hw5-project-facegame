import java.util.ArrayList;

/**
 * This class provides all information of a single user.
 * 
 * @author Fanglin
 *
 */

public class Person {

	private int id;

 	private String name;
 	private String email;
 	private char[] password;
 	private String picturePath;

 	private ArrayList<Integer> gameScore;
 	
 	private ArrayList<Integer> listOfFriends;
	
 	private ArrayList<String> posts ;

 	public static int friendsCount;
 	
 	
 	

	/**
	 * constructor -- initialize an object person with arguments name, email and
	 * password
	 * 
	 * @param name
	 * @param email
	 * @param pw
	 */
	
	public Person(String name, String email, char[] pw) {

		// id = UserDatabase.getNumberOfUsers();
		this.name = name;
		this.email = email;
		password = pw;
		picturePath = "";
		gameScore = new ArrayList<Integer>();
		listOfFriends = new ArrayList<Integer>();
		posts = new ArrayList<String>();
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return password
	 */
	public char[] getPassword() {
		return password;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return picturePath
	 */
	public String getPicturePath() {
		return picturePath;
	}

	/**
	 * @param picturePath
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	/**
	 * id of friends
	 * 
	 * @param list
	 */
	public void setFriendList(ArrayList<Integer> list) {
		listOfFriends = list;
	}

	/**
	 * posts
	 * 
	 * @param list
	 */
	public void setPostList(ArrayList<String> list) {
		posts = list;
		
	}

	/**
	 * game scores
	 * 
	 * @param gs
	 */
	public void setGameScore(ArrayList<Integer> gs) {
		gameScore = gs;
	}

	/**
	 * @return gameScore
	 */
	public ArrayList<Integer> getGameScore() {
		return gameScore;
	}

	/**
	 * @return posts
	 */
	public ArrayList<String> getPosts() {
		return posts;
	}

	/**
	 * @return listOfFriends
	 */
	public ArrayList<Integer> getFriends() {
		return listOfFriends;
	}

	/**
	 * @param id
	 * @return ture if the id is not in listOfFriends, otherwise return false
	 */
	public boolean addFriend(int id) {
		if (!listOfFriends.contains(id)) {
			listOfFriends.add(id);
			return true;
		} else {
			return false;
		}
	}

}
