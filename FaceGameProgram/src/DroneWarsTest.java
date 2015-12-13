import static org.junit.Assert.*;

import org.junit.Test;

public class DroneWarsTest 
{

	@Test
	public void testVec2DAngle() 
	{
		Vec2D v = new Vec2D(0.5, 0.5);
		double angle = v.getAngle();
		assertEquals((Math.PI*0.25), angle, 0.0001);
	}
	
	@Test
	public void testVec2DGetX() 
	{
		Vec2D v = new Vec2D(10, 10);
		assertEquals(10, v.getX(), 0.0001);
	}
	
	@Test
	public void testVec2DGetY() 
	{
		Vec2D v = new Vec2D(10, 10);
		assertEquals(v.getY(), 10, 0.0001);
	}
	
	@Test
	public void testVec2DNormalize() 
	{
		Vec2D v = new Vec2D(10, 10);
		v.normalize();
		assertEquals(0.707106, v.getX(), 0.0001);
	}
	
	@Test
	public void testVec2DGetMag() 
	{
		Vec2D v = new Vec2D(10, 10);
		assertEquals(Math.sqrt(10 * 10 + 10 * 10), v.getMag(), 0.0001);
	}
	
	@Test
	public void testVec2DGetNormalizedVec() 
	{
		Vec2D v1 = new Vec2D(10, 10);
		Vec2D v2 = v1.getNormalized();
		
		assertEquals(Math.sqrt(10 * 10 + 10 * 10), v1.getMag(), 0.0001);
		assertEquals(1.0, v2.getMag(), 0.0001);
	}
	
	@Test
	public void testBulletSetGetSpeed() 
	{
		Bullet b = new Bullet();
		b.setSpeed(10);
		assertEquals(10.0, b.getSpeed(), 0.0001);
	}
	
	@Test
	public void testPlayerMoveBy() 
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
	public void testEnemyRotateBy() 
	{
		EnemyFollow ef = new EnemyFollow("./enemy1.png");
		
		double oldAngle = ef.getSprite(0).getAngle();
		ef.rotateBy(2.36587);
		double newAngle = ef.getSprite(0).getAngle();
		
		assertEquals(2.3658, (newAngle - oldAngle), 0.0001);
	}
	
	@Test
	public void testEnemyAim() 
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
	public void testEnemyStep() 
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
	public void testSpritePos() 
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
	public void testPowerUpPos() 
	{
		PowerUp p = new PowerUp("./enemy1.png");
		
		double oldX = p.getSprite(0).getPosX();
		double oldY = p.getSprite(0).getPosY();
		
		p.moveBy(10.0,  10.0);
		
		double newX = p.getSprite(0).getPosX();
		double newY = p.getSprite(0).getPosY();

		assertEquals(10.0, (newX - oldX), 0.0001);
		assertEquals(10.0, (newY - oldY), 0.0001);
	}
	
	@Test
	public void testDifficultyIncrement() 
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
}



