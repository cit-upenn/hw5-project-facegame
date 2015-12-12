

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
 		
 		this.name = name;
 		this.email = email;
 		password = pw;
 		id = friendsCount;
 		friendsCount ++;
 		password = pw;
 		id = friendsCount;
 		friendsCount ++;
		picturePath = "";
		gameScore = null;
		listOfFriends = null;
		posts = null;
 	}
 	
 	public int getId () {
 		return id;
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
