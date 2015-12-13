
/**
 * The player class  that contains sprites.  Inherits from Asset class
 * @author Rony Edde
 *
 */
public class Player extends Asset
{
	Sprite ship;
	Sprite gun;
	
	/**
	 * constructo for the player class with unmodifiable settings
	 */
	public Player()
	{	
		String shipPath = "./ship.png";
		String gunPath = "./gun.png";
		
		this.add(shipPath);
		this.add(gunPath);
		
		this.ship = this.sprites.get(0);
		this.gun = this.sprites.get(1);
		this.gun.setCentroid((this.ship.getWidth()-this.gun.getWidth())*0.5,
							 (this.ship.getHeight()-this.gun.getHeight())*0.5);
	}
	
	/**
	 * updates the position of both sprites to a relative offset
	 */
	public void moveBy(double x, double y)
	{
		this.ship.moveBy(x, y);
		this.gun.moveBy(x, y);
	}
	
	/**
	 * updates the directional angle of both sprites to a relative offset
	 */
	public void rotateBy(double x)
	{
		this.gun.rotateBy(x);
	}
	
	/**
	 * no auto stepping loging for the time being
	 */
	public void step()
	{
		this.moveBy(0.0, 0.0);
	}
}
