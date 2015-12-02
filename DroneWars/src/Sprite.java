import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite 
{
	File imageFile;
	BufferedImage bufferedImage;
	Image image;
	AffineTransform transform;
	double posx;
	double posy;
	double angle;
	double width;
	double height;
	double centroidx;
	double centroidy;

	public Sprite(String imageFile)
	{
		this.transform = new AffineTransform();
		this.posx = 0.0;
		this.posy = 0.0;
		this.angle = 0.0;
		this.centroidx = 0.0;
		this.centroidy = 0.0;
		
		try
		{
			this.imageFile = new File(imageFile);
			this.bufferedImage = ImageIO.read(this.imageFile);
			
			this.width = this.bufferedImage.getWidth();
			this.height = this.bufferedImage.getHeight();

			
			this.transform.setToIdentity();
			this.transform.translate(this.posx, this.posy);
			this.transform.rotate(this.angle, this.width * 0.5, this.height * 0.5);
			

			BufferedImage buf = ImageIO.read(this.imageFile);
		
		// generate a hardware accelerated image
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		this.image = gc.createCompatibleImage(buf.getWidth(),buf.getHeight(),Transparency.BITMASK);
		
		// draw image in the accelerated graphics buffer
		image.getGraphics().drawImage(this.bufferedImage,0,0,null);
		//this.bufferedImage.getGraphics().drawImage(buf,0,0,null);

		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			System.out.print("could not load image");
		}
	}
	
	public void setCentroid(double x, double y)
	{
		this.centroidx = x;
		this.centroidy = y;
	}
	
	public void updateTransform()
	{
		this.transform.setToIdentity();
		this.transform.translate(this.posx+this.centroidx, this.posy+this.centroidy);
		this.transform.rotate(this.angle, this.width * 0.5, this.height * 0.5);
		//this.transform.translate(this.posx, this.posy);
	}
	
	public void setPos(double x, double y)
	{
		this.posx = x;
		this.posy = y;
		this.updateTransform();		
	}
	
	public void moveBy(double x, double y)
	{
		this.posx += x;
		this.posy += y;
		this.updateTransform();		
	}
	
	public void rotateBy(double angle)
	{
		this.angle += angle;
		this.updateTransform();	
	}
	
	public void moveAndRotateBy(double x, double y, double rotate)
	{
		this.posx += x;
		this.posy += y;
		this.angle += rotate;
		this.updateTransform();		
	}
	
	public void setRot(double angle)
	{
		this.angle = angle;
		this.updateTransform();
	}
	
	public void setScale(double width, double height)
	{
		this.width = width;
		this.height = height;
		this.updateTransform();
	}
	
	public double[] getBounds()
	{
		double bounds[] = {0, 0, 0, 0};
		double x = this.posx + this.centroidx;
		double y = this.posy + this.centroidy;
		bounds[0] = x;
		bounds[1] = x + this.width;
		bounds[2] = y;
		bounds[3] = y + this.height;
		
		return bounds;
	}
	
	public BufferedImage getImageBuffer()
	{
		return this.bufferedImage;
	}
	
	public Image getImage()
	{
		return this.image;
	}
}
