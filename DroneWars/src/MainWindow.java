import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JFrame;

public class MainWindow extends JFrame implements KeyListener, ActionListener
{
	private final Object redrawLock;
	JFrame win;
	JPanel panel;
	Graphics graphics;
	Graphics2D g2d;
	double rotate;
	int width;
	int height;
	HashMap<Character, Integer> keysPressed;
	ArrayList<Bullet> bullets;
	Player player;
	//private KeyEventListener listener;
	
	Sprite sprite;
	public MainWindow()
	{
		super();
		// TODO Auto-generated method stub
		this.redrawLock = new Object();
		//this.listener = new KeyEventListener(); 
		this.width = 640;
		this.height = 480;
		this.keysPressed = new HashMap<Character, Integer>();
		this.bullets = new ArrayList<Bullet>();
		this.player = new Player();
		
		win = new JFrame("test window");
		
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		
		panel.setLayout(new FlowLayout());
		//JLabel label = new JLabel("test label");
		//JButton button = new JButton();
		//button.setText("Test");

		
		Color col = new Color(0, 0, 0);
		panel.setBackground(col);
		panel.setFocusable(true);
		//win.addKeyListener(this);
		panel.addKeyListener(this);
		
		//panel.add(label);
		//panel.add(button);
		win.add(panel);
		panel.setSize(640, 480);
		
		win.setSize(640, 480);
		win.setPreferredSize(new Dimension(this.width, this.height));
		win.setLocationRelativeTo(null);
		win.setResizable(false);
		win.pack();
		win.setVisible(true);
		
		this.graphics = this.panel.getGraphics();
		this.g2d = (Graphics2D)this.graphics;
		
    	try
    	{
    		//this.sprite = new Sprite("./resources/jpeg.png");
    		//this.sprite.setPos(120, 120);
    		//this.sprite.setRot(0);
    		this.player = new Player();
    	}
    	catch(Exception exception)
    	{
    		exception.printStackTrace();
    		System.out.print("could not load image");
    	}
	}
	 
	public void resume() 
	{
        synchronized (redrawLock) 
        {
            redrawLock.notify();
        }
    }
	
	public void waitForPaint() 
	{
	        try 
	        {
	            synchronized (redrawLock) 
	            {
	                redrawLock.wait();
	            }
	        } 
	        catch (InterruptedException e) 
	        {
	        	e.printStackTrace();
	        }
	    }
	
	public void eval()
	{
		double speed = 2;
		for(Character x : this.keysPressed.keySet())
    	{
    		if (x == 'a')
        		this.player.moveBy(-speed, 0);
    		else if (x == 'd')
    			this.player.moveBy(speed, 0);
    		else if (x == 's')
    			this.player.moveBy(0, speed);
    		else if (x == 'w')
    			this.player.moveBy(0, -speed);
    		else if (x == 'q')
    			this.player.rotateBy(-speed*0.1);
    		else if (x == 'e')
    			this.player.rotateBy(speed*0.1);
    		else if (x == 'i')
    		{
    			Bullet b = new Bullet();
    			double bWidth = b.sprites.get(0).width;
    			double bHeight = b.sprites.get(0).height;
    			
    			b.moveBy(this.player.ship.posx+(this.player.ship.width)*0.5, 
    					 this.player.ship.posy+(this.player.ship.height)*0.5);
    			b.rotateBy(this.player.gun.angle);
    			this.bullets.add(b);
    		}
    	}
		
		for(int i=0; i<this.bullets.size(); i++)
		{
    		Bullet bSprite = this.bullets.get(i);
    		
    		if(bSprite.bullet.posx > this.width+50 || 
    				bSprite.bullet.posx < -50 ||
    				bSprite.bullet.posy > this.height+50 ||
    				bSprite.bullet.posy < -50)
    		{
    			this.bullets.remove(i);
    		}
	
    		bSprite.step();
    	}	
	}
	
	public void draw()
	{	   	
    	this.graphics.fillRect(0, 0, this.width, this.height); 	
    	this.g2d.drawImage(this.player.ship.image, this.player.ship.transform, null);
    	this.g2d.drawImage(this.player.gun.image, this.player.gun.transform, null);
    	
    	for(int i=0; i<this.bullets.size(); i++)
    	{
    		Bullet bSprite = this.bullets.get(i);
    		this.g2d.drawImage(bSprite.bullet.image, bSprite.bullet.transform, null);
    	}
    	
    	//double bounds[] = this.sprite.getBounds();
    	//System.out.printf("Bounds = %f %f %f %f\n/system"
    	//		+ "", bounds[0], bounds[1], bounds[2], bounds[3]);
    	//this.waitForPaint();
	}
	
    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) 
    {

    }

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) 
    {
    	//System.out.println("TYPED KEY " + e.getKeyChar());
    	char c = e.getKeyChar();
    	this.keysPressed.put(c, 0);
    	//if (!this.keys.contains(c))
    	//	this.keys.add(c);
    }

    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) 
    {
    	this.keysPressed.remove(e.getKeyChar());
    }
    
	public void actionPerformed(ActionEvent e) 
	{
		//System.out.println("NO ACTION YET");
		
	}

}
