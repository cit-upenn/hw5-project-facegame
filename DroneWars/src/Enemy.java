import java.awt.Rectangle;

public class Enemy extends Asset
{
	Sprite enemy;
	private double speed;
	private double direction;
	private double targetDir;
	private double dirThreshold;
	
	public Enemy()
	{
		super();
		this.speed = 0.5;
		this.direction = 0.0;
		this.targetDir = 0.0;
		this.dirThreshold = 0.01;
		//URL location = Player.class.getProtectionDomain().getCodeSource().getLocation();
		//String pwd = location.toString().replace("file:", "");
		
		String enemyPath = "./penguin.png";
		
		this.add(enemyPath);
		//this.add("./src/bullet.png");
		this.enemy = this.sprites.get(0);
		this.enemy.setCentroid((-this.enemy.getWidth())*0.5,
							    (-this.enemy.getHeight())*0.5);
	}
	
	public void setSpeed(double value)
	{
		this.speed = value;
	}
	
	public double getDirection()
	{
		return this.direction;
	}
	
	public void setDirection(double dir)
	{
		this.direction = dir;
	}
	
	public double getTargetDir()
	{
		return this.targetDir;
	}
	
	public void setTargetDir(double dir)
	{
		this.targetDir = dir;
	}
	
	private void blendToDirection()
	{
		if(this.targetDir - this.direction < this.dirThreshold)
			this.direction += 0.01;
		else if(this.targetDir - this.direction > this.dirThreshold)
			this.direction -= 0.01;
	}
	
	
	public void aim(Asset target)
	{
		Rectangle srcBBox = this.getBBox(0);
		double srcX = srcBBox.getCenterX();
		double srcY = srcBBox.getCenterY();
		
		Rectangle targetBBox = target.getBBox(0);
		double targetX = targetBBox.getCenterX();
		double targetY = targetBBox.getCenterY();
		
		Vec2D v = new Vec2D(targetX - srcX, targetY - srcY);
		v.normalize();
		double angle = v.getAngle();
		this.direction = angle;
		this.sprites.get(0).setRot(this.direction);
		
	}
	
	public void step()
	{
		
		double x = this.speed * Math.cos(this.direction);
		double y = this.speed * Math.sin(this.direction);
		this.moveBy(x, y);
	}
}

