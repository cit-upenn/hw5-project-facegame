
public class Enemy extends Asset
{
	Sprite enemy;
	double speed;
	
	public Enemy()
	{
		super();
		this.speed = 0.5;
		//URL location = Player.class.getProtectionDomain().getCodeSource().getLocation();
		//String pwd = location.toString().replace("file:", "");
		
		String enemyPath = "./penguin.png";
		
		this.add(enemyPath);
		//this.add("./src/bullet.png");
		this.enemy = this.sprites.get(0);
		this.enemy.setCentroid((-this.enemy.width)*0.5,
							    (-this.enemy.height)*0.5);
	}
	
	public void setSpeed(double value)
	{
		this.speed = value;
	}
	
	public void step()
	{
		//double x = this.speed * Math.cos(this.enemy.angle);
		//double y = this.speed * Math.sin(this.enemy.angle);
		//this.moveBy(x, y);
	}
}

