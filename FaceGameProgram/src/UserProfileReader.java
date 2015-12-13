import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads user profile in line by line.
 * 
 * @author Fanglin
 */
public class UserProfileReader {

	private String filename;
	private ArrayList<String> lines;

	/**
	 * constructor -- initialize file and new arraylist contains lines in this
	 * file
	 * 
	 * @param file
	 */
	public UserProfileReader(String file) {
		filename = file;
		lines = new ArrayList<String>();

		readFile();
	}

	/**
	 * This method get lines stored in userDatabase.
	 * 
	 * @return lines
	 */
	public ArrayList<String> getDatabaseLines() {
		return lines;
	}

	/**
	 * this method reads file in, import content in the file line by line
	 */
	private void readFile() {
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile);

			while (in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
