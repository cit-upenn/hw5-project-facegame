
public class Vec2D 
{
	private double x;
	private double y;
	private double magnitude;
	
	public Vec2D(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.magnitude = this.getMag();
	}

	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getMag()
	{
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public Vec2D getNormalized()
	{
		double mag = this.getMag();
		double x = this.x / mag;
		double y = this.y / mag;
		
		Vec2D normalized = new Vec2D(x, y);
		
		return normalized;
	}
	
	public void normalize()
	{
		double mag = this.getMag();
		this.x /= mag;
		this.y /= mag;
	}
	
	public double getAngle()
	{
		double angle = Math.atan(y / x);
		
		if(x < 0)
			angle +=  Math.PI;
		return angle;
	}
}
