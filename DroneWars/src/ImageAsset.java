import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * The main asset that deals with images
 * @author Rony Edde
 *
 */
public class ImageAsset extends Asset
{
	Sprite image;
	
	/**
	 * The constructor to the ImageAsset
	 * @param imagePath the path string
	 */
	public ImageAsset(String imagePath)
	{
			
		this.add(imagePath);
		
		this.image = this.sprites.get(0);
		this.image.setCentroid(this.image.getWidth() * 0.5,
							   this.image.getHeight() * 0.5);
	}
	
	/**
	 * moves the asset relative to the current position
	 */
	public void moveBy(double x, double y)
	{
		this.image.moveBy(x, y);
	}
	
	/**
	 * rotates the asset relative to the current rotation value
	 */
	public void rotateBy(double x)
	{
		this.image.rotateBy(x);
	}
	
	/**
	 * disabled logic only drawing allowed
	 */
	public void step()
	{
		this.moveBy(0.0, 0.0);
	}
}
