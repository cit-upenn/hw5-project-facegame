public class PowerUp extends Enemy
{
	public PowerUp(String powerUpPath)
	{
		super(powerUpPath);
	}
	
	public void step()
	{
		double x = this.getSpeed() * Math.cos(this.getDirection());
		double y = this.getSpeed() * Math.sin(this.getDirection());
		
		this.moveBy(x, y);
		this.setSteps(this.getSteps()+1.0);
	}
}