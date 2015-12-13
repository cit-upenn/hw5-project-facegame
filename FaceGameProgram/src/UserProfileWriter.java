import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class write something into a given file "userDatabase" and make a new
 * file for each user to store user's name, email, password, game score, friends
 * and posts information.
 * 
 * @author Fanglin
 *
 */
public class UserProfileWriter {
<<<<<<< HEAD
	
	public void createUserInividuleData (Person p) {
		try {
			String filename = "";
			filename = p.getId() + ".txt";
			PrintWriter out = new PrintWriter(filename);
			
			out.println(p.getName());
			out.println(p.getEmail());
			String password = new String (p.getPassword());
			out.println(password);
			
			out.println(p.getPicturePath());
			
			if (p.getGameScore() != null) {
				for (int s: p.getGameScore()) {
					out.print(s + ",");
				}
			}
			out.println();
			
			if (p.getFriends() != null) {
				int f = 0;
				ArrayList<Integer> friendsIdx = p.getFriends();

				for (f=0; f < friendsIdx.size() - 1; f++) {
					out.print( friendsIdx.get(f) + ",");
				}
				if(friendsIdx.size() > 0)
					out.print(friendsIdx.get(f));
			}
			out.println();
			
			if (p.getPosts() != null) {
				for (String s: p.getPosts()) {
					out.print(s);
				}
			}
			out.println();
			
			out.flush();
			out.close();
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
=======

	/**
	 * This method writes in an ID into the userDatabase and creates a new file
	 * storing all information of the user.
	 * 
	 * @param p
	 */
>>>>>>> c6260023f64c4ae2f1009a86b843bd85c756c333
	public void write(Person p) {
		try {

			// write ID into the userDatabase

			// File file = new File("userDatabase.txt");
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream("userDatabase.txt", true));
			// FileWriter fw = new FileWriter("userDatabase.txt");
			// FileWriter fw = new FileWriter (file.getAbsoluteFile());
			BufferedWriter fbw = new BufferedWriter(writer);
			// PrintWriter out2 = new PrintWriter(new
			// FileWriter("userDatabase.txt",true));
			String id = "" + p.getId();
			fbw.write(id);
			fbw.newLine();
			fbw.close();

			// get a ID and make a file named by ID of the new user.
			String filename = "";
			filename = p.getId() + ".txt";
			PrintWriter out = new PrintWriter(filename);

			// store name, email and password information of the user
			out.println(p.getName());
			out.println(p.getEmail());
			String password = new String(p.getPassword());
			out.println(password);
			// store the picture profile information of the user
			out.println(p.getPicturePath());

			// if game score is not null then all the game scores are printed
			// out
			if (p.getGameScore() != null) {
				for (int s : p.getGameScore()) {
					out.print(s + ",");
				}
			}
			out.println();

			// if friends are not null then all friends of the user are printed
			// out
			if (p.getFriends() != null) {
				for (int f : p.getFriends()) {
					out.print(f + ",");
				}
			}
			out.println();

			// if posts are not null the all posts of the user are printed out
			if (p.getPosts() != null) {
				for (String s : p.getPosts()) {
					out.print(s);
				}
			}
			out.println();

			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
