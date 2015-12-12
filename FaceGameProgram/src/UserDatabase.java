import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

 public class UserDatabase {
 	private static ArrayList <Person> users;
 	
 	public UserDatabase(){
 		
		UserProfileReader rd = new UserProfileReader("userDatabase.txt");
		ArrayList <String> lines = rd.getDatabaseLines();
		
 		users = new ArrayList<Person>();
 		
		if (lines != null) {
			for (String s: lines) {
				String filename = s + ".txt"; 
				UserProfileReader userFile = new UserProfileReader(filename);
				ArrayList<String> userInfo = userFile.getDatabaseLines();
				Person p = new Person (userInfo.get(0), userInfo.get(1),userInfo.get(2).toCharArray());
				
				p.setId(Integer.parseInt(s));
				
				p.setPicturePath(userInfo.get(3));
				
				String gameScoreLine = userInfo.get(4);
				if (gameScoreLine.length() > 0) {
					
					String[] gameScores = gameScoreLine.split(",");
					ArrayList<Integer> scores = new ArrayList<Integer>();
					
					for (int i = 0; i < gameScores.length; i++) {
						scores.add(Integer.parseInt(gameScores[i]));
					}
					
					p.setGameScore (scores);
				}
				
				String friendsLine = userInfo.get(5);
				if (friendsLine.length() > 0) {
					String[] friends = friendsLine.split(",");
					ArrayList<Integer> friendList = new ArrayList<Integer>();
					
					for (int i = 0; i < friends.length; i++) {
						friendList.add(Integer.parseInt(friends[i]));
					}
					
					p.setFriendList(friendList);
				}

				String postList = userInfo.get(6);
				if (postList.length() > 0) {
					String[] posts = postList.split("Name: ");
					ArrayList<String> postLists = new ArrayList <String>();
					
					for (int i = 0; i < posts.length; i++) {
						postLists.add(posts[i]);
					}
					
					p.setPostList(postLists);
				}
				users.add(p);
			}
		}

 	}
 	public static void addProfile (Person p) {
 		users.add(p);
 		
 	}
	public static ArrayList<Person> getUsers() {
		return users;
	}
	
	public static int getNumberOfUsers() {
		return users.size();
	}
 	
 }
