import java.util.ArrayList;

public class UserDatabase {
	private static ArrayList <Person> users;
	public UserDatabase(){
		users = new ArrayList<Person>();
	}
	public static void addProfile (Person p) {
		users.add(p);
	}
	
}
