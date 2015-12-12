import java.util.ArrayList;

public class UserDatabase {
	private static ArrayList <Person> users;
	public UserDatabase(){
		FileReader rd = new FileReader("userDatabase.txt");
		ArrayList <String> lines = rd.getLines();
		if (lines != null) {
			for (String s: lines) {
				String filename = s + ".txt"; 
				FileReader userFile = new FileReader(filename);
				ArrayList<String> userInfo = userFile.getLines();
				Person p = new Person (userInfo.get(0), userInfo.get(1),userInfo.get(2).toCharArray());
				
				p.setPicturePath(userInfo.get(3));
				
				String gameScoreLine = userInfo.get(4);
				String[] gameScores = gameScoreLine.split(",");
				ArrayList<Integer> scores = new ArrayList<Integer>();
				
				for (int i = 0; i < gameScores.length; i++) {
					scores.add(Integer.parseInt(gameScores[i]));
				}
				
				p.setGameScore (scores);
				
				
				
			}
		}
		users = new ArrayList<Person>();
	}
	public static void addProfile (Person p) {
		users.add(p);
	}
	public static ArrayList<Person> getUsers() {
		return users;
	}
	
}
