/**
 * custom 2D vector helper class
 * @author Rony Edde
 *
 */
public class Vec2D 
{
	private double x;
	private double y;
	private double magnitude;
	
	/**
	 * constructor that creates a 2D vector class
	 * @param x the x component
	 * @param y the y component
	 */
	public Vec2D(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.magnitude = this.getMag();
	}

	/**
	 * gets the x component of the vector
	 * @return the x component
	 */
	public double getX()
	{
		return this.x;
	}
	
	/**
	 * gets the y component of the vector
	 * @return the y component
	 */
	public double getY()
	{
		return this.y;
	}
	
	/**
	 * gets the magnitude of the vector
	 * @return the magnitude of the vector
	 */
	public double getMag()
	{
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	/**
	 * gets a normalized vector of the current one
	 * @return a 2DVec copy that is normalized
	 */
	public Vec2D getNormalized()
	{
		double mag = this.getMag();
		double x = this.x / mag;
		double y = this.y / mag;
		
		Vec2D normalized = new Vec2D(x, y);
		
		return normalized;
	}
	
	/**
	 * normalized the current vector
	 */
	public void normalize()
	{
		double mag = this.getMag();
		this.x /= mag;
		this.y /= mag;
	}
	
	/**
	 * gets the angle of the vector
	 * @return the angle in radians
	 */
	public double getAngle()
	{
		double angle = Math.atan(y / x);
		
		if(x < 0)
			angle +=  Math.PI;
		return angle;
	}
}
