import java.net.URL;

public class Bullet extends Asset
{
	Sprite bullet;
	double speed;
	
	public Bullet()
	{
		super();
		this.speed = 40.0;
		URL location = Player.class.getProtectionDomain().getCodeSource().getLocation();
		String pwd = location.toString().replace("file:", "");
		
		String bulletPath = pwd+"bullet.png";
		
		this.add(bulletPath);
		//this.add("./src/bullet.png");
		this.bullet = this.sprites.get(0);
		this.bullet.setCentroid((-this.bullet.width)*0.5,
							    (-this.bullet.height)*0.5);
	}
	
	public void setSpeed(double value)
	{
		this.speed = value;
	}
	
	public void step()
	{
		double x = this.speed * Math.cos(this.bullet.angle);
		double y = this.speed * Math.sin(this.bullet.angle);
		this.moveBy(x, y);
	}
}

