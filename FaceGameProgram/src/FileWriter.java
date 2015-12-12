import java.io.PrintWriter;

public class FileWriter {
	public void write(Person p) {
		try {
			
			PrintWriter out2 = new PrintWriter("userDatabase.txt");
			out2.println(p.getId());
			out2.flush();
			out2.close();
			
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
			
			out.println(p.getGameScore());
			
			if (p.getFriends() != null) {
				for (int f: p.getFriends()) {
					out.print(f + ",");
				}
			}
			out.println();
			
			String posts = "";
			if (p.getPosts() != null) {
				for (String s: p.getPosts()) {
					posts += s;
				}
			}
			out.println(posts);
			
			out.flush();
			out.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
