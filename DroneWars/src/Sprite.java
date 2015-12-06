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
	
	public void setCentroid(double x, double y)
	{
		this.centroidX = x;
		this.centroidY = y;
	}
	
	public void updateTransform()
	{
		this.transform.setToIdentity();		
		this.transform.translate(this.posX+this.centroidX, this.posY+this.centroidY);
		this.transform.rotate(this.angle, this.width * 0.5, this.height * 0.5);
		this.transform.scale(this.scaleX, this.scaleY);
		this.updateBounds();
		//this.transform.translate(this.posx, this.posy);
	}
	
	public void setBounds(double x, double y, double width, double height)
	{
		this.bounds.setRect((int)x, (int)y, (int)width, (int)height);
	}
	
	public void updateBounds()
	{
		this.setBounds(this.posX+this.centroidX, 
					   this.posY+this.centroidY, 
                       this.width, 
                       this.height);
	}
	
	public Rectangle getBounds()
	{
		return this.bounds;
	}
	
	public void setPos(double x, double y)
	{
		this.posX = x;
		this.posY = y;
		this.updateTransform();
	}
	
	public void moveBy(double x, double y)
	{
		this.posX += x;
		this.posY += y;
		this.updateTransform();		
	}
	
	public void rotateBy(double angle)
	{
		this.angle += angle;
		this.updateTransform();	
	}
	
	public void moveAndRotateBy(double x, double y, double rotate)
	{
		this.posX += x;
		this.posY += y;
		this.angle += rotate;
		this.updateTransform();		
	}
	
	public void setRot(double angle)
	{
		this.angle = angle;
		this.updateTransform();
	}
	
	public void setScale(double scaleX, double scaleY)
	{
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.updateTransform();
	}
	
	
	public BufferedImage getImageBuffer()
	{
		return this.bufferedImage;
	}
	
	public Image getImage()
	{
		return this.image;
	}
	
	public double getPosX()
	{
		return this.posX;
	}
	
	public double getPosY()
	{
		return this.posY;
	}
	
	public double getAngle()
	{
		return this.angle;
	}
	
	public AffineTransform getTransform()
	{
		return this.transform;
	}
	
	public int getWidth()
	{
		return (int)this.width;
	}
	
	public int getHeight()
	{
		return (int)this.height;
	}
	
	
	/*
	@Override(non-Javadoc)
	protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      g.drawImage(this.image, (int)this.posx, (int)this.posx, this);
	      g.setColor(Color.yellow);
	      int rectCount = 10;
	      //g.drawImage(img1, myX, myY, this);
	      int height = getHeight() / rectCount;
	      int width = 27;
	      int x = getWidth() - width;
	      for (int i = 0; i < rectCount; i++) {
	         int y = i * height;
	         g.drawRect(x, y, width, height);
	      }
	      Font font1 = new Font("Serif", Font.BOLD, 36);
	      g.setFont(font1);
	      g.drawString(Integer.toString(10), 500, 100);
	   }
	   */
}
