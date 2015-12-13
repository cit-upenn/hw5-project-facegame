/**
 * The power up asset inheriting from MotionAsset
 * @author Rony Edde
 *
 */
public class PowerUp extends MotionAsset
{
	/**
	 * default parent constructor invoked no overrides
	 * @param powerUpPath the path to the image file
	 */
	public PowerUp(String powerUpPath)
	{
		super(powerUpPath);
	}
	
	/**
	 * stepping override for behavior steps
	 */
	public void step()
	{
		double x = this.getSpeed() * Math.cos(this.getDirection());
		double y = this.getSpeed() * Math.sin(this.getDirection());
		
		this.moveBy(x, y);
		this.setSteps(this.getSteps()+1.0);
	}
}