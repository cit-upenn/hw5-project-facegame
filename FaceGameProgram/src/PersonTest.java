import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Person class.
 * 
 * @author JuliaMa
 *
 */
public class PersonTest {

	/**
	 * instance variables
	 */
	private Person newPerson;
	private int id;
	private static ArrayList<Person> users;
	private ArrayList<Integer> listOfFriends;

	/**
	 * initialize -- first create a valid user account then add it to user
	 * database
	 */
	@Before
	public void setUp() {
		UserDatabase userDB = new UserDatabase();
		id = userDB.getNumberOfUsers();
		char[] password = { 'a', 'b', 'c' };
		newPerson = new Person("Xinxin", "xinxma@seas.upenn.edu", password);
		userDB.addProfile(newPerson);
		users = userDB.getUsers();
		listOfFriends = newPerson.getFriends();

	}

	@Test
	public void testPersonNotNull() {
		assertNotNull("Person cannot be null", newPerson);

	}

	/**
	 * Creates another valid user and test these IDs should not be equal.
	 */
	@Test
	public void testIDsNotEqual() {

		int id2;
		Person newPerson2;
		char[] password2 = { 'a', 'b', 'c', 'd' };
		newPerson2 = new Person("Guanqing", "guanqing@seas.upenn.edu",
				password2);
		users.add(newPerson2);
		// users.size() is the size of users, the user id should be (size - 1)
		// because the user id is counted from 0.
		id2 = users.size() - 1;

		assertNotEquals(id, id2);
	}

	@Test
	public final void testAddExistingFriend() {
		listOfFriends.add(0);
		listOfFriends.add(1);
		listOfFriends.add(2);
		assertFalse(newPerson.addFriend(1));

	}

	@Test
	public final void testAddNewFriend() {
		listOfFriends.add(0);
		listOfFriends.add(1);
		listOfFriends.add(2);
		assertTrue(newPerson.addFriend(4));

	}

}
