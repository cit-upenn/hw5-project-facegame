import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class UserProfileReader {
	private String filename;
	private ArrayList<String> lines;
	
	public UserProfileReader (String file) {
		filename = file;
		lines = new ArrayList<String> ();
		
		readFile();
	}
	
	public ArrayList<String> getDatabaseLines() {
		return lines;
	}
	
	private void readFile() {
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner (inputFile);
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
			}
			
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
