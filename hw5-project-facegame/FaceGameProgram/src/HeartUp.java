
public class HeartUp extends MotionAsset
{
	public HeartUp(String heartUpPath)
	{
		super(heartUpPath);
	}
	
	public void step()
	{
		double x = this.getSpeed() * Math.cos(this.getDirection());
		double y = this.getSpeed() * Math.sin(this.getDirection());
		
		this.moveBy(x, y);
		this.setSteps(this.getSteps()+1.0);
	}
}