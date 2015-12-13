

import java.util.ArrayList;

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
 	
 	public Person (String name, String email, char[] pw) {
 		
 		id = UserDatabase.getNumberOfUsers();
 		this.name = name;
 		this.email = email;
 		password = pw;
		picturePath = "";
		gameScore = new ArrayList<Integer>();
		listOfFriends = new ArrayList<Integer>();
		posts = new ArrayList<String>();
 	}
 	
 	public int getId () {
 		return id;
 	}
 	public void setId (int id) {
 		this.id = id;
 	}
 	public String getName () {
 		return name;
 	}
	public char[] getPassword () {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
 	
 	public String getPicturePath () {
 		return picturePath;
 	}
 	public void setPicturePath (String picturePath) {
 		this.picturePath = picturePath;
 	}

 	public void setFriendList (ArrayList<Integer> list) {
 		listOfFriends = list;
 	}
 	
 	public void setPostList (ArrayList<String> list) {
 		posts = list;
 	}

	public void setGameScore (ArrayList<Integer> gs) {
		gameScore = gs;
	}
	public ArrayList<Integer> getGameScore() {
		return gameScore;
 	}
 	

	public ArrayList<String> getPosts() {
		return posts;
 	}
 	
	public ArrayList<Integer> getFriends () {
 		return listOfFriends; 
 	}
 	public boolean addFriend (int id) {
 		if (!listOfFriends.contains(id)) {
 			listOfFriends.add(id);
 			return true;
 		} else {
 			return false;
 		}
 	}
 	

 	
 		
 }
