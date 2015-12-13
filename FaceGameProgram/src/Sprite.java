import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The sprite class for displaying sprites
 * @author Rony Edde
 *
 */
public class Sprite extends JFrame
{
	private File imageFile;
	private BufferedImage bufferedImage;
	private Image image;
	private AffineTransform transform;
	private double posX;
	private double posY;
	private double angle;
	private double width;
	private double height;
	private double centroidX;
	private double centroidY;
	private double scaleX;
	private double scaleY;
	private Rectangle bounds;

	/**
	 * constructor for sprite class that takes an image path
	 * @param imageFile the path to the image
	 */
	public Sprite(String imageFile)
	{
		this.transform = new AffineTransform();
		this.posX = 0.0;
		this.posY = 0.0;
		this.angle = 0.0;
		this.centroidX = 0.0;
		this.centroidY = 0.0;
		
		try
		{
			this.imageFile = new File(imageFile);
			this.bufferedImage = ImageIO.read(this.imageFile);
			
			this.width = this.bufferedImage.getWidth();
			this.height = this.bufferedImage.getHeight();
			
			this.scaleX = 1.0;
			this.scaleY = 1.0;
			
			this.transform.setToIdentity();
			this.transform.scale(this.scaleX, this.scaleY);
			this.transform.translate(this.posX, this.posY);
			this.transform.rotate(this.angle, this.width * 0.5, this.height * 0.5);
			
			this.bounds = new Rectangle();
			this.updateBounds();
			

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
	
	/**
	 * set the centrod of the sprite
	 * @param x the x centroid coordinate
	 * @param y the y centroid coordinate
	 */
	public void setCentroid(double x, double y)
	{
		this.centroidX = x;
		this.centroidY = y;
	}
	
	/**
	 * updates the transform coordinates of the sprite
	 */
	public void updateTransform()
	{
		this.transform.setToIdentity();		
		this.transform.translate(this.posX+this.centroidX, this.posY+this.centroidY);
		this.transform.rotate(this.angle, this.width * 0.5, this.height * 0.5);
		this.transform.scale(this.scaleX, this.scaleY);
		this.updateBounds();
	}
	
	/**
	 * sets the bounds of the bounding box of the sprite
	 * @param x the x coordinate
	 * @param x the x coordinate
	 * @param width the width of the box
	 * @param height the height of the box
	 */
	public void setBounds(double x, double y, double width, double height)
	{
		this.bounds.setRect((int)x, (int)y, (int)width, (int)height);
	}
	
	/**
	 * automatically computes the bounds of the box after any transformation occurs
	 */
	public void updateBounds()
	{
		this.setBounds(this.posX+this.centroidX, 
					   this.posY+this.centroidY, 
                       this.width, 
                       this.height);
	}
	
	/**
	 * get the current bounds of the sprite
	 */
	public Rectangle getBounds()
	{
		return this.bounds;
	}
	
	/**
	 * sets the position of the sprite
	 * @param x the x position
	 * @param y the y position
	 */
	public void setPos(double x, double y)
	{
		this.posX = x;
		this.posY = y;
		this.updateTransform();
	}
	
	/**
	 * moves the sprite relative to the current position
	 * @param x the x value to move by
	 * @param y the y value to move by
	 */
	public void moveBy(double x, double y)
	{
		this.posX += x;
		this.posY += y;
		this.updateTransform();		
	}
	
	/**
	 *  rotates the sprite by the angle offset
	 * @param angle the angle value to rotate by
	 */
	public void rotateBy(double angle)
	{
		this.angle += angle;
		this.updateTransform();	
	}
	
	/**
	 * moves and rotates the sprite relative to current position and angle
	 * @param x the x offset
	 * @param y the y offset
	 * @param rotate the angle offset
	 */
	public void moveAndRotateBy(double x, double y, double rotate)
	{
		this.posX += x;
		this.posY += y;
		this.angle += rotate;
		this.updateTransform();		
	}
	
	/**
	 * sets the rotation of the sprite
	 * @param angle the angle to set the rotation to
	 */
	public void setRot(double angle)
	{
		this.angle = angle;
		this.updateTransform();
	}
	
	/**
	 * sets the scale of the sprite
	 * @param scaleX the X scale to set
	 * @param scaleY the Y scale to set
	 */
	public void setScale(double scaleX, double scaleY)
	{
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.updateTransform();
	}
	
	
	/**
	 * get the BufferedImage object
	 * @return a BufferedImage of the sprite
	 */
	public BufferedImage getImageBuffer()
	{
		return this.bufferedImage;
	}
	
	/*
	 * gets the Image of the sprite
	 */
	public Image getImage()
	{
		return this.image;
	}
	
	/**
	 * gets the x position of the sprite
	 * @return the x position
	 */
	public double getPosX()
	{
		return this.posX;
	}
	
	/**
	 * gets the y postion of the sprite
	 * @return the y position of the sprite
	 */
	public double getPosY()
	{
		return this.posY;
	}
	
	/**
	 * gets the angle of the sprite
	 * @return the angle in radians
	 */
	public double getAngle()
	{
		return this.angle;
	}
	
	/**
	 * get the transformation matrix of the sprite
	 * @return the AffineTransform matrix
	 */
	public AffineTransform getTransform()
	{
		return this.transform;
	}
	
	/**
	 * gets the width of the sprite
	 * @return width the width of the sprite
	 */
	public int getWidth()
	{
		return (int)this.width;
	}
	
	/**
	 * gets the heigth of the sprite
	 * @return width the heigth of the sprite
	 */
	public int getHeight()
	{
		return (int)this.height;
	}
}
