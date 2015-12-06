import java.awt.Rectangle;
import java.util.ArrayList;

public class Asset 
{
	ArrayList<Sprite> sprites;

	
	public Asset(ArrayList<String> spritePaths)
	{
		this.sprites = new ArrayList<Sprite>();
		for(String path : spritePaths)
		{
			this.add(path);
		}
	}

	public boolean collidesWith(int index, Rectangle rec)
	{
		if(this.sprites.size() >= 1)
		{
			Sprite sprite = this.sprites.get(0);
			Rectangle bounds = new Rectangle((int)sprite.getPosX(), (int)sprite.getPosY(),
		     		  					     (int)sprite.getPosX(), (int)sprite.getPosY());
			
			if(bounds.intersects(rec))
				return true;
		}
		return false;
	}
	
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
	
	public Asset(String spritePath)
	{
		this.sprites = new ArrayList<Sprite>();
		this.add(spritePath);
	}
	
	public Asset()
	{
		this.sprites = new ArrayList<Sprite>();
	}
	
	public void add(String spritePath)
	{
		Sprite sprite = new Sprite(spritePath);
		this.sprites.add(sprite);
	}
	
	public void moveBy(double x, double y)
	{
		for(Sprite sprite : this.sprites)
		{
			sprite.moveBy(x, y);
		}
	}
	
	public void rotateBy(double x)
	{
		for(Sprite sprite : this.sprites)
		{
			sprite.rotateBy(x);
		}
	}	
}