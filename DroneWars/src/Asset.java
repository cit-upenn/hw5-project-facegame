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