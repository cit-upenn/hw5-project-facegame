import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Player extends Asset
{
	Sprite ship;
	Sprite gun;
	
	public Player()
	{
	
		//URL location = Player.class.getProtectionDomain().getCodeSource().getLocation();
		//String pwd = location.toString().replace("file:", "");
		
		String shipPath = "./jpeg.png";
		String gunPath = "./gun.png";
		
		// testing other collisions
		//shipPath = "./bullet.png";
		//gunPath = "./bullet.png";
		
		//System.out.println(shipPath.toString());
		//System.out.println(gunPath.toString());
		
		this.add(shipPath);
		this.add(gunPath);
		
		this.ship = this.sprites.get(0);
		this.gun = this.sprites.get(1);
		this.gun.setCentroid((this.ship.width-this.gun.width)*0.5,
							 (this.ship.height-this.gun.height)*0.5);
	}
	
	public void moveBy(double x, double y)
	{
		this.ship.moveBy(x, y);
		this.gun.moveBy(x, y);
	}
	
	public void rotateBy(double x)
	{
		this.gun.rotateBy(x);
	}
	
	public void step()
	{
		this.moveBy(0.0, 0.0);
	}
}
