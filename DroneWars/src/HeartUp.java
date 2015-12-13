/**
 * Heart power up asset
 * @author Rony Edde
 *
 */
public class HeartUp extends MotionAsset
{
	/**
	 * constructor for the heart power up asset
	 * @param heartUpPath the path to the image file
	 */
	public HeartUp(String heartUpPath)
	{
		super(heartUpPath);
	}
	
	/**
	 * animation step logic
	 */
	public void step()
	{
		double x = this.getSpeed() * Math.cos(this.getDirection());
		double y = this.getSpeed() * Math.sin(this.getDirection());
		
		this.moveBy(x, y);
		this.setSteps(this.getSteps()+1.0);
	}
}