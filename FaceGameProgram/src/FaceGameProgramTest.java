import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
/**
 * FaceGame JUnit test class
 * @author Rony Edde
 *
 */
public class FaceGameProgramTest
{
	/**
	 * instance variables
	 */
	private Person newPerson;
	private int id;
	private static ArrayList<Person> users;
	private ArrayList<Integer> listOfFriends;
	
	@Test
	public void testGetErodedBBox()
	{
		Asset a = new Asset("heart.png");
		Rectangle rec1 =  a.getErodedBBox(0, 0);
		Rectangle rec2 =  a.getErodedBBox(0, 1);

		assertEquals(2, rec1.getWidth() - rec2.getWidth(), 0.0);
		assertEquals(2, rec1.getHeight() - rec2.getHeight(), 0.0);
		assertEquals(1, rec2.getX() - rec1.getX(), 0.0);
		assertEquals(1, rec2.getY() - rec1.getY(), 0.0);
	}
	
	@Test
	public void testGetBBox()
	{
		// resolution of heart.png is 15x15
		Asset a = new Asset("heart.png");
		Rectangle rec1 =  a.getErodedBBox(0, 0);
		assertEquals(15, rec1.getWidth(), 0.0);		
	}
	
	@Test
	public void testSetPos()
	{
		Sprite s = new Sprite("heart.png");
		s.setPos(10.0,  20.0);
		assertEquals(10, s.getPosX(), 0.0);
		assertEquals(20, s.getPosY(), 0.0);
	}
	
	@Test
	public void testSetScale()
	{
		Sprite s = new Sprite("heart.png");
		s.setScale(10.0, 20.0);
		assertEquals(10, s.getScaleX(), 0.0);
		assertEquals(20, s.getScaleY(), 0.0);
	}
	
	@Test
	public void testGetNumBullets()
	{
		GameEngine n = new GameEngine(null, null);
		assertEquals(0, n.getNumBullets());
	}
	
	@Test
	public void testRotateBy()
	{
		Player p = new Player();
		p.rotateBy(2.0);
		
		assertEquals(2.0, p.gun.getAngle(), 0.0);
	}
	
	public void testGetDifficulty()
	{
		//initial difficulty should be 1
		GameEngine n = new GameEngine(null, null);
		n.incrementDifficulty(1.0);
		assertEquals(2.0, n.getDifficulty(), 0.0);
	}
	
	@Test
	public void testGetAngle() 
	{
		Vec2D v = new Vec2D(0.5, 0.5);
		double angle = v.getAngle();
		assertEquals((Math.PI*0.25), angle, 0.0001);
	}
	
	@Test
	public void testGetX() 
	{
		Vec2D v = new Vec2D(10, 10);
		assertEquals(10, v.getX(), 0.0001);
	}
	
	@Test
	public void testGetY() 
	{
		Vec2D v = new Vec2D(10, 10);
		assertEquals(v.getY(), 10, 0.0001);
	}
	
	@Test
	public void testNormalize() 
	{
		Vec2D v = new Vec2D(10, 10);
		v.normalize();
		assertEquals(0.707106, v.getX(), 0.0001);
	}
	
	@Test
	public void testGetMag() 
	{
		Vec2D v = new Vec2D(10, 10);
		assertEquals(Math.sqrt(10 * 10 + 10 * 10), v.getMag(), 0.0001);
	}
	
	@Test
	public void testFireShots()
	{
		GameEngine n = new GameEngine(null, null);
		n.fireShots();
		assertEquals(1, n.getNumBullets(), 1.0);
	}
	
	@Test
	public void testCollidesWith()
	{
		Asset a1 = new Asset("ship.png");
		Asset a2 = new Asset("ship.png");
		
		assertEquals(true, a1.collidesWith(0, a2.getBBox(0)));
	}
	
	@Test
	public void testGetNumBombs()
	{
		GameEngine n = new GameEngine(null, null);
		assertEquals(n.getNumBombs(), 5, 0);
	}
	
	@Test
	public void testSetSpeed()
	{
		Bullet b = new Bullet();
		b.setSpeed(10);
		assertEquals(10, b.getSpeed(), 0.0);
	}
	
	@Test
	public void testGetNormalized() 
	{
		Vec2D v1 = new Vec2D(10, 10);
		Vec2D v2 = v1.getNormalized();
		
		assertEquals(Math.sqrt(10 * 10 + 10 * 10), v1.getMag(), 0.0001);
		assertEquals(1.0, v2.getMag(), 0.0001);
	}
	
	@Test
	public void isEmpty()
	{
		GeoLocation g = new GeoLocation();
		boolean check = false;
		
		if (g.isEmpty() && g.getLocationData().compareTo("") == 0)
			check = true;
		else if (!g.isEmpty() && g.getLocationData().compareTo("") != 0)
			check = true;
		
		assertEquals(true, check);
	}
	
	@Test
	public void testGetSpeed() 
	{
		Bullet b = new Bullet();
		b.setSpeed(10.0);
		double get = b.getSpeed();
		assertEquals(10.0, get, 0.0001);
	}
	
	@Test
	public void testMoveBy() 
	{
		Player p = new Player();
		double oldPosX = p.getSprite(0).getPosX();
		double oldPosY = p.getSprite(0).getPosY();
		
		p.moveBy(2.0, 5.0);
		double newPosX = p.getSprite(0).getPosX();
		double newPosY = p.getSprite(0).getPosY();
		
		assertEquals(2.0, newPosX - oldPosX, 0.0001);
		assertEquals(5.0, newPosY - oldPosY, 0.0001);
	}
	
	@Test
	public void testImageAssetMoveBy() 
	{
		ImageAsset p = new ImageAsset("heart.png");
		double oldPosX = p.getSprite(0).getPosX();
		double oldPosY = p.getSprite(0).getPosY();
		
		p.moveBy(2.0, 5.0);
		double newPosX = p.getSprite(0).getPosX();
		double newPosY = p.getSprite(0).getPosY();
		
		assertEquals(2.0, newPosX - oldPosX, 0.0001);
		assertEquals(5.0, newPosY - oldPosY, 0.0001);
	}
	
	@Test
	public void testEnemyRotateBy() 
	{
		EnemyFollow ef = new EnemyFollow("./enemy1.png");
		
		double oldAngle = ef.getSprite(0).getAngle();
		ef.rotateBy(2.36587);
		double newAngle = ef.getSprite(0).getAngle();
		
		assertEquals(2.3658, (newAngle - oldAngle), 0.0001);
	}
	
	@Test
	public void testAim() 
	{
		EnemyFollow ef = new EnemyFollow("./enemy1.png");
		EnemyFollow ef2 = new EnemyFollow("./enemy1.png");
		
		double oldAngle = ef.getSprite(0).getAngle();
		
		//ef2.moveBy(10.0, 10.0);
		ef2.setSpeed(100000);
		ef2.setDirection(0.70710678118);
		ef2.step();
		ef2.getSprite(0).updateTransform();
		
		ef.setSpeed(0.0);
		ef.aim(ef2);
		ef.step();
		ef.getSprite(0).updateTransform();
		
		double newAngle = ef.getSprite(0).getAngle();

		assertEquals(0.707106, (newAngle - oldAngle), 0.001);
	}
	
	@Test
	public void testStep() 
	{
		EnemyFollow ef = new EnemyFollow("./enemy1.png");
		
		double oldX = ef.getSprite(0).getPosX();
		double oldY = ef.getSprite(0).getPosY();	

		ef.setSpeed(100000.0);
		ef.setDirection(0.0);
		ef.step();
		
		double newX = ef.getSprite(0).getPosX();
		double newY = ef.getSprite(0).getPosY();

		assertEquals(100000.0, (newX - oldX), 0.01);
		assertEquals(0.0, (newY - oldY), 0.0001);
	}
	
	@Test
	public void testGetPos() 
	{
		Sprite s = new Sprite("./enemy1.png");
		
		double oldX = s.getPosX();
		double oldY = s.getPosY();
		
		s.moveBy(10.0,  10.0);
		s.updateTransform();
		
		double newX = s.getPosX();
		double newY = s.getPosY();

		assertEquals(10.0, (newX - oldX), 0.0001);
		assertEquals(10.0, (newY - oldY), 0.0001);
	}
	
	
	@Test
	public void testIncrementDifficulty() 
	{
		GameEngine g = new GameEngine(null, null);
		
		double oldDifficulty = g.getDifficulty();
		g.incrementDifficulty(2);

		assertEquals(2.0, g.getDifficulty() - oldDifficulty, 0.0001);
	}

	@Test
	public void testGetPlayerScore() 
	{
		GameEngine g = new GameEngine(null, null);
		
		int score = g.getPlayerScore();

		assertEquals(0, score);
	}

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
