import java.util.ArrayList;

/**
 * This program stores user information in userDatabase.
 * 
 * @author Fanglin
 *
 */
public class UserDatabase {
	private static ArrayList<Person> users;

	/**
	 * constructor -- initialize a new user and read in the file represents this
	 * user line 0: name line 1: email line 2: password line 3: picture path
	 * line 4: game score line 5: friend list line 6: posts
	 */
	public UserDatabase() {

		UserProfileReader rd = new UserProfileReader("userDatabase.txt");
		ArrayList<String> lines = rd.getDatabaseLines();

		users = new ArrayList<Person>();

		if (lines != null) {
			for (String s : lines) {
				String filename = s + ".txt";
				UserProfileReader userFile = new UserProfileReader(filename);
				ArrayList<String> userInfo = userFile.getDatabaseLines();
				Person p = new Person(userInfo.get(0), userInfo.get(1),
						userInfo.get(2).toCharArray());

				p.setId(Integer.parseInt(s));

				p.setPicturePath(userInfo.get(3));

				String gameScoreLine = userInfo.get(4);
				if (gameScoreLine.length() > 0) {

					String[] gameScores = gameScoreLine.split(",");
					ArrayList<Integer> scores = new ArrayList<Integer>();

					for (int i = 0; i < gameScores.length; i++) {
						scores.add(Integer.parseInt(gameScores[i]));
					}

					p.setGameScore(scores);
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
					ArrayList<String> postLists = new ArrayList<String>();

					for (int i = 0; i < posts.length; i++) {
						postLists.add(posts[i]);
					}

					p.setPostList(postLists);
				}
				users.add(p);
			}
		}

	}

	/**
	 * add a profile for a user
	 * 
	 * @param p
	 */
	public static void addProfile(Person p) {
		users.add(p);

	}

	/**
	 * get the users
	 * 
	 * @return users
	 */
	public static ArrayList<Person> getUsers() {
		return users;
	}

	/**
	 * get number of users
	 * 
	 * @return users.size()
	 */
	public static int getNumberOfUsers() {
		return users.size();
	}

}
