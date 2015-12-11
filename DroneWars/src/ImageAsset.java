import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ImageAsset extends Asset
{
	Sprite image;
	
	public ImageAsset(String imagePath)
	{
			
		this.add(imagePath);
		
		this.image = this.sprites.get(0);
		this.image.setCentroid(this.image.getWidth() * 0.5,
							   this.image.getHeight() * 0.5);
	}
	
	public void moveBy(double x, double y)
	{
		this.image.moveBy(x, y);
	}
	
	public void rotateBy(double x)
	{
		this.image.rotateBy(x);
	}
	
	public void step()
	{
		this.moveBy(0.0, 0.0);
	}
}
