import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Asset class that manages 2D elements
 * Collisions images, transforms and behaviors
 * @author Rony Edde
 *
 */
public class Asset 
{
	ArrayList<Sprite> sprites;

	/**
	 * constructor initialization
	 * @param spritePaths
	 */
	public Asset(ArrayList<String> spritePaths)
	{
		this.sprites = new ArrayList<Sprite>();
		for(String path : spritePaths)
		{
			this.add(path);
		}
	}

	/**
	 * checks if sprite collides with another rectangle
	 * @param index the index of the sprite
	 * @param rec the rectangle to check against
	 * @return true if a collision occurred
	 */
	public boolean collidesWith(int index, Rectangle rec)
	{
		if(this.sprites.size() >= 1)
		{
			// get bounds
			Rectangle bounds = getBBox(index);
			
			if(bounds.intersects(rec))
				return true;
		}
		return false;
	}
	
	/**
	 * gets the bounding box of the sprite index
	 * @param index the sprite index
	 * @return bounding box
	 */
	public Rectangle getBBox(int index)
	{
		Rectangle bBox = new Rectangle();
		if(this.sprites.size() >= index-1)
		{
			Sprite sprite = this.sprites.get(0);
			bBox = sprite.getBounds();
		}
		return bBox;
	}
	
	/**
	 * gets an eroded bounding box 
	 * @param index the sprite index
	 * @param scale the number of pixels to erode
	 * @return Rectangle bbox
	 */
	public Rectangle getErodedBBox(int index, int scale)
	{
		Rectangle bBox = this.sprites.get(index).getBounds();
		return new Rectangle(bBox.x+scale, 
							 bBox.y+scale, 
							 (bBox.width-scale*2), 
							 (bBox.height-scale*2));		
	}
	
	/**
	 * constructor that takes a sprite as initialiser
	 * @param spritePath
	 */
	public Asset(String spritePath)
	{
		this.sprites = new ArrayList<Sprite>();
		this.add(spritePath);
	}
	
	/**
	 * constructor that takes no parameters
	 * default operation for an empty asset
	 */
	public Asset()
	{
		this.sprites = new ArrayList<Sprite>();
	}
	
	/**
	 * creates and adds a sprite from the specified path
	 * @param spritePath to the image on disk
	 */
	public void add(String spritePath)
	{
		Sprite sprite = new Sprite(spritePath);
		this.sprites.add(sprite);
	}
	
	/**
	 * moves the asset on screen offset from the current location
	 * @param x the x coordinate offset
	 * @param y the y coordinate offset
	 */
	public void moveBy(double x, double y)
	{
		for(Sprite sprite : this.sprites)
		{
			sprite.moveBy(x, y);
		}
	}
	
	/**
	 * rotates the asset by the specified angle
	 * @param x the offset angle in radians from the current angle
	 */
	public void rotateBy(double x)
	{
		for(Sprite sprite : this.sprites)
		{
			sprite.rotateBy(x);
		}
	}
	
	/**
	 * gets the sprite at the index of the asset
	 * @param index the index of the sprite
	 * @return Sprite at index
	 */
	public Sprite getSprite(int index)
	{
		if (index < this.sprites.size())
		{
			return this.sprites.get(index);
		}
		return null;
	}
}