
public class EnemyWaving extends MotionAsset
{
	public EnemyWaving(String enemyPath)
	{
		super(enemyPath);
	}
	
	public void step()
	{
		double x = this.getSpeed() * Math.cos(this.getDirection());
		double y = this.getSpeed() * Math.sin(this.getDirection());
		
		x += 2.0 * Math.cos(this.getSteps() * 0.1);
		y += 2.0 * Math.sin(this.getSteps() * 0.1);
		
		this.moveBy(x, y);
		this.setSteps(this.getSteps()+1.0);
	}
}
