

import java.util.ArrayList;

public class Person {
	
	private String name;
	private String email;
	private char[] password;
	private String picturePath;
	private String status;
	private int id;
	private ArrayList<Integer> listOfFriends;
	
	public static int friendsCount;
	
	public Person (String name, String email, char[] pw) {
		this.name = name;
		this.email = email;
		password = pw;
		id = friendsCount;
		friendsCount ++;
	}
	
	public int getId () {
		return id;
	}
	public String getName () {
		return name;
	}
	
	public String getPicturePath () {
		return picturePath;
	}
	
	public void setPicturePath (String picturePath) {
		this.picturePath = picturePath;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus () {
		return status;
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
