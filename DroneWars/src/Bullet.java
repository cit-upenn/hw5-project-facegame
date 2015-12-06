

public class Bullet extends Asset
{
	Sprite bullet;
	double speed;
	
	public Bullet()
	{
		super();
		this.speed = 40.0;
		//URL location = Player.class.getProtectionDomain().getCodeSource().getLocation();
		//String pwd = location.toString().replace("file:", "");
		
		String bulletPath = "./bullet.png";
		
		this.add(bulletPath);
		//this.add("./src/bullet.png");
		this.bullet = this.sprites.get(0);
		this.bullet.setCentroid((-this.bullet.getWidth())*0.5,
							    (-this.bullet.getHeight())*0.5);
	}
	
	public void setSpeed(double value)
	{
		this.speed = value;
	}
	
	public void step()
	{
		double x = this.speed * Math.cos(this.bullet.getAngle());
		double y = this.speed * Math.sin(this.bullet.getAngle());
		this.moveBy(x, y);
	}
}

