/**
 * Enemy Follow class for enemies that follow the player and have a waving motion
 * @author Rony Edde
 *
 */
public class EnemyWaving extends MotionAsset
{
	/**
	 * constructor with the path to the image file
	 * @param enemyPath image file path
	 */
	public EnemyWaving(String enemyPath)
	{
		super(enemyPath);
	}
	
	/**
	 * animation logic for the asset
	 */
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
