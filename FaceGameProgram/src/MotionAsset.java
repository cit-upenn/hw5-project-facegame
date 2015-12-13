import java.awt.Rectangle;

/**
 * abstract class for motion assets.  This will be used for all emenies
 * @author Rony Edde
 *
 */
public abstract class MotionAsset extends Asset
{
	Sprite entity;
	private double speed;
	private double direction;
	private double targetDir;
	private double dirThreshold;
	private double steps;
	
	/**
	 * default behavior of all enemy initialization
	 * @param enemyPath the path to the image file
	 */
	public MotionAsset(String enemyPath)
	{
		super();
		this.speed = 0.5;
		this.direction = 0.0;
		this.targetDir = 0.0;
		this.dirThreshold = 0.01;
		this.steps = 0.0;
		
		this.add(enemyPath);

		this.entity = this.sprites.get(0);
		this.entity.setCentroid((-this.entity.getWidth())*0.5,
							    (-this.entity.getHeight())*0.5);
	}
	
	/**
	 * The step number if being incremented return that value
	 * @return
	 */
	public double getSteps()
	{
		return this.steps;
	}
	
	/**
	 * set the number of steps the asset has been updated
	 * @param step number of steps to set
	 */
	public void setSteps(double step)
	{
		this.steps = step;
	}
	
	/**
	 * get the speed value
	 * @return the asset's speed
	 */
	public double getSpeed()
	{
		return this.speed;
	}
	
	/**
	 * set the speed value of the asset
	 * @param value the speed value
	 */
	public void setSpeed(double value)
	{
		this.speed = value;
	}
	
	/**
	 * get the directional angle of the asset in radians
	 * @return the angle
	 */
	public double getDirection()
	{
		return this.direction;
	}
	
	/**
	 * set the directional angle of the asset in radians
	 * @param dir the angular direction
	 */
	public void setDirection(double dir)
	{
		this.direction = dir;
	}
	
	/**
	 * gets the target direction 
	 * @return the angle to the target's position
	 */
	public double getTargetDir()
	{
		return this.targetDir;
	}
	
	/**
	 * set the angular direction to the target
	 * @param dir
	 */
	public void setTargetDir(double dir)
	{
		this.targetDir = dir;
	}
	
	/**
	 * blends the current angle with the target angle
	 */
	private void blendToDirection()
	{
		if(this.targetDir - this.direction < this.dirThreshold)
			this.direction += 0.01;
		else if(this.targetDir - this.direction > this.dirThreshold)
			this.direction -= 0.01;
	}
	
	/**
	 * aims current asset to the target destination
	 * @param target the target asset to aim to
	 */
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
	
	/**
	 * default step animation logig behavior
	 */
	public void step()
	{
		double x = this.speed * Math.cos(this.direction);
		double y = this.speed * Math.sin(this.direction);
		this.moveBy(x, y);
		this.steps++;
	}
}

