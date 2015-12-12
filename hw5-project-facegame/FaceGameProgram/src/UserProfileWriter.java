
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class UserProfileWriter {
	
	public void write(Person p) {
		try {
//			File file = new File("userDatabase.txt");
			OutputStreamWriter writer = new OutputStreamWriter (new FileOutputStream("userDatabase.txt", true));
//			FileWriter fw = new FileWriter("userDatabase.txt");
//			FileWriter fw = new FileWriter (file.getAbsoluteFile());
			BufferedWriter fbw = new BufferedWriter(writer); 
//			PrintWriter out2 = new PrintWriter(new FileWriter("userDatabase.txt",true));
			String id = "" +p.getId();
			fbw.write(id);
			fbw.newLine();
			fbw.close();
			
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
				for (int f: p.getFriends()) {
					out.print(f + ",");
				}
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
	
}

