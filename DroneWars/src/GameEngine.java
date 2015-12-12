
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.awt.RenderingHints;


import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Canvas;

import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameEngine extends Canvas {

	private BufferStrategy strategy;
	private boolean gameRunning = true;
	
	//private final Object redrawLock;
	JFrame win;
	JPanel panel;
	Graphics graphics;
	Graphics2D g2d;
	double rotate;
	int width;
	int height;
	HashMap<Character, Integer> keysPressed;
	ArrayList<Bullet> bullets;
	ArrayList<MotionAsset> enemies;
	ArrayList<PowerUp> powerUps;
	ArrayList<HeartUp> heartUps;
	Player player;
	ImageAsset lifeAsset;
	ImageAsset bombAsset;
	
	double difficulty;
	int numLives;
	int numBombs;
	int numBombBullets;
	boolean paused;
	boolean gameOver;
	double playerSpeed;
	boolean moveUp;
	boolean moveDown;
	boolean moveLeft;
	boolean moveRight;
	boolean rotateLeft;
	boolean rotateRight;
	boolean fireUp;
	boolean fireDown;
	boolean fireLeft;
	boolean fireRight;
	boolean useBomb;
	int powerUpCount;
	
	boolean firstLoop;
	boolean gameStarted;
	long lastFireTime;
	long fireInterval;
	long lastSpawnTime;
	long spawnInterval;
	long lastPowerUpSpawnTime;
	long powerUpSpawnInterval;
	long lastHeartUpSpawnTime;
	long heartUpSpawnInterval;
	long lastSoundTime;
	long soundInterval;
	
	int playerScore;
	int collisionThreshold;
	
	String enemyPath1;
	String enemyPath2;
	
	SoundEffects bulletSound;
	SoundEffects bombSound;
	SoundEffects hitSound;
	
	//private KeyEventListener listener;
	
	
	
	public GameEngine(JFrame containerFrame) 
	{
		// the frame where we will draw everything
		JFrame container = new JFrame("Drone Wars");
		
		if (containerFrame != null)
			container = containerFrame;
		
		// get the  the frame and set main resolution
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(640,480));
		panel.setLayout(null);
		
		// set the canvas and add the frame to it
		setBounds(0,0,640,480);
		panel.add(this);
		
		// DO NOT repaint.  Overriding the update to manage draw
		setIgnoreRepaint(true);
		
		// pack and make window visible 
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		// close the window if user hits escape
		container.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				//win.dispose();
				//win.setVisible(false);//System.exit(0);
			}
		});
		
		// add key listener
		addKeyListener(new KeyInputHandler());
		
		// get focus for key events
		requestFocus();

		// enable double buffering and accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		// setup main game parameters
		// TODO integrate with main window once everything works
		this.setup();
	}
	
	public void setup()
	{
		this.width = 640;
		this.height = 480;
		this.difficulty = 1.0;
		this.numLives = 30;
		this.numBombs = 5;
		this.numBombBullets = 20;
		this.paused = true;
		this.gameOver = false;
		this.keysPressed = new HashMap<Character, Integer>();
		this.bullets = new ArrayList<Bullet>();
		this.enemies = new ArrayList<MotionAsset>();
		this.powerUps = new ArrayList<PowerUp>();
		this.heartUps = new ArrayList<HeartUp>();
		this.player = new Player();
		this.playerSpeed = 4.0;
		this.firstLoop = true;
		this.gameStarted = false;
		
		this.fireInterval = 60;
		this.lastFireTime = System.currentTimeMillis();
		this.spawnInterval = 300;
		this.lastSoundTime = System.currentTimeMillis();
		this.powerUpSpawnInterval = 4000;
		this.lastPowerUpSpawnTime = System.currentTimeMillis();
		this.heartUpSpawnInterval = 6000;
		this.lastHeartUpSpawnTime = System.currentTimeMillis();
		this.soundInterval = 10;
		this.playerScore = 0;
		this.collisionThreshold = 8;
		this.powerUpCount = 0;
		
		this.lifeAsset = new ImageAsset("./heart.png");
		this.bombAsset = new ImageAsset("./bomb.png");
		this.enemyPath1 = "./enemy1.png";
		this.enemyPath2 = "./enemy2.png";
		
		this.bulletSound = new SoundEffects("./gun_fire.wav");
		this.bombSound = new SoundEffects("./bomb.wav");
		this.hitSound = new SoundEffects("./hit.wav");

		
		// set player coordinates at center
		this.player.moveBy(this.width * 0.5 - this.player.getBBox(0).getCenterX(),
						   this.height * 0.5 - this.player.getBBox(0).getCenterY());
    	
    	// temp testing of enemies
    	this.spawnEnemies(10);
		// initialise the entities in our game so there's something
		// to see at startup
	}
	

	public void gameLoop() 
	{
		long lastLoopTime = System.currentTimeMillis();
		// keep looping until we exit
		while (gameRunning) 
		{		
			// graphics context
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			        		   RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			// check running time passed
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			
			g.setColor(Color.black);
			g.fillRect(0,0,640,480);
			
			//this.setIgnoreRepaint(true);
			// clear screen
			g.fillRect(0, 0, this.width, this.height);
	    	
	    	
	    	// draw bullets
	    	this.drawBullets(g);
	    	// enemies
	    	this.drawEnemies(g);
	    	// player ship
	    	this.drawPlayer(g);
	    	// draw powerUps
	    	this.drawPowerUps(g);
	    	// draw heartUps
	    	this.drawHeartUps(g);
	    	// draw score
	    	this.drawScore(g);
	    	// draw lives
	    	this.drawLives(g);
	    	// draw bombs
	    	this.drawBombs(g);
	        
	    	if (!this.gameStarted)
	    		drawStartMenu(g);
	    	if (this.paused && this.gameStarted)
				this.drawPauseMenu(g);
	    	if (this.numLives <= 0)
	    		this.setGameOver(g);
	    	
			// we can now flip the buffer.  We're done drawing
			g.dispose();
			strategy.show();
			
			if (this.gameOver || (this.paused && !firstLoop))
				continue;
						
			if (this.moveLeft)
	    		this.player.moveBy(-this.playerSpeed, 0);
			if (this.moveRight)
				this.player.moveBy(this.playerSpeed, 0);
			if (this.moveUp)
				this.player.moveBy(0, -this.playerSpeed);
			if (this.moveDown)
				this.player.moveBy(0, this.playerSpeed);
			if (this.rotateLeft)
				this.player.rotateBy(-this.playerSpeed*0.1);
			if (this.rotateRight)
				this.player.rotateBy(this.playerSpeed*0.1);
			if (this.useBomb && this.numBombs > 0)
			{
				this.useBomb(20);
				this.numBombs--;
				this.useBomb = false;
				this.bombSound.run();
			}
			if (this.fireUp || this.fireDown || this.fireLeft || this.fireRight)
			{
				this.fireShots();
			}
			
			for(int i=0; i<this.bullets.size(); i++)
			{
	    		Bullet bSprite = this.bullets.get(i);
	    		
	    		if(bSprite.bullet.getPosX() > this.width+50 || 
	    				bSprite.bullet.getPosX() < -50 ||
	    				bSprite.bullet.getPosY() > this.height+50 ||
	    				bSprite.bullet.getPosY() < -50)
	    		{
	    			this.bullets.remove(i);
	    		}
	    		bSprite.step();
	    	}
			
			for(int i=0; i<this.powerUps.size(); i++)
			{
	    		PowerUp powerUp = this.powerUps.get(i);
	    		
	    		if(powerUp.entity.getPosX() > this.width+50 ||
	    				powerUp.entity.getPosX() < -50 ||
	    				powerUp.entity.getPosY() > this.height+50 ||
	    				powerUp.entity.getPosY() < -50)
	    		{
	    			this.powerUps.remove(i);
	    		}
	    		
	    		Rectangle bBox = powerUp.getErodedBBox(0, this.collisionThreshold);
	    		boolean collision = this.player.collidesWith(0, bBox);
				if(collision)
				{
					//System.out.println("enemy  bbox = " + bBox);
					//System.out.println("player bbox = " + this.player.getBBox(0));
					this.powerUps.remove(i);
					this.powerUpCount = 100;
				}
	    		
	    		powerUp.step();
	    	}
			
			for(int i=0; i<this.heartUps.size(); i++)
			{
	    		HeartUp heartUp = this.heartUps.get(i);
	    		
	    		if(heartUp.entity.getPosX() > this.width+50 || 
	    				heartUp.entity.getPosX() < -50 ||
	    				heartUp.entity.getPosY() > this.height+50 ||
	    				heartUp.entity.getPosY() < -50)
	    		{
	    			this.heartUps.remove(i);
	    		}
	    		
	    		Rectangle bBox = heartUp.getBBox(0);
	    		boolean collision = this.player.collidesWith(0, bBox);
				if(collision)
				{
					//System.out.println("enemy  bbox = " + bBox);
					//System.out.println("player bbox = " + this.player.getBBox(0));
					this.heartUps.remove(i);
					this.numLives+=2;
				}
	    		
				heartUp.step();
	    	}
			
			for (int i=0; i<this.enemies.size(); i++)
			{
				MotionAsset e = this.enemies.get(i);
				Rectangle bBox = e.getErodedBBox(0, this.collisionThreshold);
				boolean collision = false;
				for (int j=0; j<this.bullets.size(); j++)
				{
					Bullet bb = this.bullets.get(j);
					
					// collide with bullets
					collision = bb.collidesWith(0, bBox);
					if(collision)
					{
						this.enemies.remove(i);
						this.bullets.remove(j);
						this.playerScore+=10;
						this.hitSound.run();
						//System.out.println("Collision occurred");
						break;
					}
				}
				
				// collide with player
				collision = this.player.collidesWith(0, bBox);
				if(collision)
				{
					//System.out.println("enemy  bbox = " + bBox);
					//System.out.println("player bbox = " + this.player.getBBox(0));
					this.enemies.remove(i);
					this.numLives--;
				}
				
				e.aim(this.player);
				e.step();
			}
			
			// spawn new enemies
			this.spawnEnemies(2);
			
			// spawn power ups
			this.spawnPowerUps(1);
			
			// spawn heart ups
			this.spawnHeartUps(1);
			
			// slightly increase difficulty
			this.incrementDifficulty(0.001);
			
			// wait 17 millisec
			try { Thread.sleep(10); } catch (Exception e) {}
			
			this.firstLoop = false;
		}
	}

	public boolean canFire() 
	{
		// test if enough time elapsed to fire
		if (System.currentTimeMillis() - this.lastFireTime < this.fireInterval) 
		{
			return false;
		}
		
		this.lastFireTime = System.currentTimeMillis();
		if(this.powerUpCount > 0)
			this.powerUpCount--;
		
		return true;
	}

	public boolean canPlaySound() 
	{
		// test if enough time elapsed to fire
		if (System.currentTimeMillis() - this.lastSoundTime < this.soundInterval) 
		{
			return false;
		}
		
		this.lastSoundTime = System.currentTimeMillis();
		return true;
	}
	
	public boolean canSpawn()
	{
		// test if enough time elapsed to spawn
		if (System.currentTimeMillis() - this.lastSpawnTime < this.spawnInterval) 
		{
			return false;
		}
		
		this.lastSpawnTime = System.currentTimeMillis();
		return true;
	}
	
	public boolean canSpawnPowerUps()
	{
		// test if enough time elapsed to spawn
		if (System.currentTimeMillis() - this.lastPowerUpSpawnTime < this.powerUpSpawnInterval) 
		{
			return false;
		}
		
		this.lastPowerUpSpawnTime = System.currentTimeMillis();
		return true;
	}
	
	public boolean canSpawnHeartUps()
	{
		// test if enough time elapsed to spawn
		if (System.currentTimeMillis() - this.lastHeartUpSpawnTime < this.heartUpSpawnInterval) 
		{
			return false;
		}
		
		this.lastHeartUpSpawnTime = System.currentTimeMillis();
		return true;
	}
	
	public void fireShots()
	{
		// compute fire interval
		if (this.canFire())
		{
			double angle = 0.0;
			if(this.fireUp)
			{
				if(this.fireRight)
					angle = Math.PI*1.75;
				else if(this.fireLeft)
					angle = Math.PI*1.25;
				else
					angle = Math.PI*1.5;
			}
			else if(this.fireDown)
			{
				if(this.fireRight)
					angle = Math.PI*0.25;
				else if(this.fireLeft)
					angle = Math.PI*0.75;
				else
					angle = Math.PI*0.5;
			}
			else if(this.fireLeft)
				angle = Math.PI;
			else if(this.fireRight)
				angle = 0.0;
			
			this.player.gun.setRot(angle);			  
			
			if(this.powerUpCount <= 0)
			{
				double randRot = 0.05 * (Math.random() - 0.5) *2;
				double randMove = 20 * (0.5 + Math.random() * 0.5);
		
				Bullet bb = new Bullet();
				bb.setSpeed(randMove);
			
				bb.moveBy(this.player.ship.getPosX()+(this.player.ship.getWidth())*0.5, 
						this.player.ship.getPosY()+(this.player.ship.getHeight())*0.5);
				bb.rotateBy(this.player.gun.getAngle() + randRot);
				this.bullets.add(bb);
			}
			else
			{
				double randRot = 0.05 * (Math.random() - 0.5) *2;
				double randMove = 20 * (0.5 + Math.random() * 0.5);
		
				Bullet bb1 = new Bullet();
				Bullet bb2 = new Bullet();
				Bullet bb3 = new Bullet();
				bb1.setSpeed(randMove);
				bb2.setSpeed(randMove);
				bb3.setSpeed(randMove);
			
				bb1.moveBy(this.player.ship.getPosX()+(this.player.ship.getWidth())*0.5, 
						this.player.ship.getPosY()+(this.player.ship.getHeight())*0.5);
				bb1.rotateBy(this.player.gun.getAngle() + randRot);
				
				bb2.moveBy(this.player.ship.getPosX()+(this.player.ship.getWidth())*0.5, 
						this.player.ship.getPosY()+(this.player.ship.getHeight())*0.5);
				bb2.rotateBy(this.player.gun.getAngle() + randRot+0.52);
				
				bb3.moveBy(this.player.ship.getPosX()+(this.player.ship.getWidth())*0.5, 
						this.player.ship.getPosY()+(this.player.ship.getHeight())*0.5);
				bb3.rotateBy(this.player.gun.getAngle() + randRot-0.52);
				
				this.bullets.add(bb1);
				this.bullets.add(bb2);
				this.bullets.add(bb3);
			}
			this.bulletSound.run();
		}
	}
	
	public void useBomb(int numBullets)
	{
		double angle = 0.0;
		for (int i = 0; i<numBombBullets; i++)
		{
			angle = i * Math.PI * 2 / numBullets;
			Bullet bb = new Bullet();
			bb.setSpeed(10);
		
			bb.moveBy(this.player.ship.getPosX()+(this.player.ship.getWidth())*0.5, 
					  this.player.ship.getPosY()+(this.player.ship.getHeight())*0.5);
			bb.rotateBy(this.player.gun.getAngle() + angle);
			this.bullets.add(bb);
		}
	}


	public void spawnEnemies(int num)
	{
		if(this.canSpawn())
		{
			for (int i=0; i<num; i++)
			{
				double axis = Math.random();
				double x = 0.0;
				double y = 0.0;
				if (axis<0.5)
				{
					x = 2 * (Math.random()-0.5) * this.width;
					y = Math.round(Math.random()) * this.height;
				}
				else
				{
					x = Math.round(Math.random()) * this.width ;
					y = 2 * (Math.random()-0.5) * this.height;
				}
				
				if(x > 0 && x < this.width+30)
					x+=30;
				else if(x > -30)
					x-=30;
				
				if(y > 0 && y < this.height+30)
					y+=30;
				else if(y > -30)
					y-=30;
				
				
				MotionAsset e = null;
				if (Math.random() < 0.5)
					e = new EnemyWaving(this.enemyPath1);    	
				else
					e = new EnemyFollow(this.enemyPath2);
				
				// fit random to 0.2-1.0 and amp about 5
				double speed = ((Math.random() * 0.8) + 0.2) * 2 * this.difficulty;
				e.setSpeed(speed);
				
				e.moveBy(x, y);
				this.enemies.add(e);
				
			}
		}
	}
	
	public void spawnPowerUps(int num)
	{
		if(this.canSpawnPowerUps())
		{
			for (int i=0; i<num; i++)
			{
				double axis = Math.random();
				double x = 0.0;
				double y = 0.0;
				if (axis<0.5)
				{
					x = 2 * (Math.random()-0.5) * this.width;
					y = Math.round(Math.random()) * this.height;
				}
				else
				{
					x = Math.round(Math.random()) * this.width ;
					y = 2 * (Math.random()-0.5) * this.height;
				}
				
				if(x > 0 && x < this.width+30)
					x+=30;
				else if(x > -30)
					x-=30;
				
				if(y > 0 && y < this.height+30)
					y+=30;
				else if(y > -30)
					y-=30;
				
				
				PowerUp p = new PowerUp("./multi_shot.png");
				
				// fit random to 0.2-1.0 and amp about 5
				double speed = ((Math.random() * 0.8) + 0.2) * 2;
				p.setSpeed(speed);
				p.moveBy(x, y);
				p.aim(this.player);
				this.powerUps.add(p);
				
			}
		}
	}
	
	public void spawnHeartUps(int num)
	{
		if(this.canSpawnHeartUps())
		{
			for (int i=0; i<num; i++)
			{
				double axis = Math.random();
				double x = 0.0;
				double y = 0.0;
				if (axis<0.5)
				{
					x = 2 * (Math.random()-0.5) * this.width;
					y = Math.round(Math.random()) * this.height;
				}
				else
				{
					x = Math.round(Math.random()) * this.width ;
					y = 2 * (Math.random()-0.5) * this.height;
				}
				
				if(x > 0 && x < this.width+30)
					x+=30;
				else if(x > -30)
					x-=30;
				
				if(y > 0 && y < this.height+30)
					y+=30;
				else if(y > -30)
					y-=30;
				
				
				HeartUp p = new HeartUp("./heart.png");
				
				// fit random to 0.2-1.0 and amp about 5
				double speed = ((Math.random() * 0.8) + 0.2) * 2;
				p.setSpeed(speed);
				p.moveBy(x, y);
				p.aim(this.player);
				this.heartUps.add(p);
				
			}
		}
	}
	
	public void incrementDifficulty(double val)
	{
		this.difficulty += val;
	}
	
	public void drawBullets(Graphics2D g)
	{
		for(int i=0; i<this.bullets.size(); i++)
		{
			Bullet bullet = this.bullets.get(i);
			g.drawImage(bullet.bullet.getImage(), bullet.bullet.getTransform(), null);
		}
	}
	
	public void drawPlayer(Graphics2D g)
	{
		g.drawImage(this.player.ship.getImage(), this.player.ship.getTransform(), null);
    	g.drawImage(this.player.gun.getImage(), this.player.gun.getTransform(), null);
    	
    	//Rectangle r = this.player.getErodedBBox(0, 5);
    	//g.setColor(Color.RED);
    	//g.drawRect(r.x, r.y, r.width, r.height);
	}
	
	public void drawEnemies(Graphics2D g)
	{
		for(int i=0; i<this.enemies.size(); i++)
    	{
    		MotionAsset motionAsset = this.enemies.get(i);
    		g.drawImage(motionAsset.entity.getImage(), motionAsset.entity.getTransform(), null);
    		
    		//Rectangle r = enemy.getErodedBBox(0, 5);
        	//g.setColor(Color.BLUE);
        	//g.drawRect(r.x, r.y, r.width, r.height);
    	}
	}
	
	public void drawPowerUps(Graphics2D g)
	{
		for(int i=0; i<this.powerUps.size(); i++)
    	{
    		PowerUp powerUp = this.powerUps.get(i);
    		g.drawImage(powerUp.entity.getImage(), powerUp.entity.getTransform(), null);
    		
    		//Rectangle r = enemy.getErodedBBox(0, 5);
        	//g.setColor(Color.BLUE);
        	//g.drawRect(r.x, r.y, r.width, r.height);
    	}
	}
	
	public void drawHeartUps(Graphics2D g)
	{
		for(int i=0; i<this.heartUps.size(); i++)
    	{
    		HeartUp heartUp = this.heartUps.get(i);
    		g.drawImage(heartUp.entity.getImage(), heartUp.entity.getTransform(), null);
    		
    		//Rectangle r = enemy.getErodedBBox(0, 5);
        	//g.setColor(Color.BLUE);
        	//g.drawRect(r.x, r.y, r.width, r.height);
    	}
	}
	
	public void drawScore(Graphics2D g)
	{
		String scoreStr = "Player Score:" + this.playerScore;
    	Font font = new Font("Sans-Serif", Font.BOLD, 20);
        g.setFont(font);

        this.drawFont(g, scoreStr, 10, 30, new Color(1.0f, 0.6f, 0.0f));
	}

	public void drawLives(Graphics2D g)
	{
		String lifeStr = "lives";
    	Font font = new Font("Serif", Font.BOLD, 18);
        g.setFont(font);
        g.setColor(new Color(0.1f, 0.5f, 0.8f));
        g.drawString(lifeStr, 10, 52);
        
		for (int i=0; i<this.numLives; i++)
		{
			AffineTransform at = new AffineTransform();
			at.setToTranslation(47 + i*13, 40);
			g.drawImage(this.lifeAsset.image.getImage(), at, null);
		}
	}
	
	public void drawBombs(Graphics2D g)
	{
		String lifeStr = "bombs";
    	Font font = new Font("Serif", Font.BOLD, 18);
        g.setFont(font);
        g.setColor(new Color(0.8f, 0.1f, 0.1f));
        g.drawString(lifeStr, 10, 70);
        
		for (int i=0; i<this.numBombs; i++)
		{
			AffineTransform at = new AffineTransform();
			at.setToTranslation(62 + i*13, 60);
			g.drawImage(this.bombAsset.image.getImage(), at, null);
		}
	}
	
	public void drawFont(Graphics2D g, String str, int x, int y, Color c)
	{
		float RGB[] = {0.0f, 0.0f, 0.0f, 0.0f};
		c.getComponents(RGB);
		Color bright = new Color(RGB[0], RGB[1], RGB[2]);
		bright = bright.brighter().brighter();
		Color dark = new Color(RGB[0], RGB[1], RGB[2]);
		dark = dark.darker().darker();
		
		g.setColor(bright);
        g.drawString(str, x-1, y);
        g.setColor(dark);
        g.drawString(str, x, y+1);
        g.setColor(c);
        g.drawString(str, x, y); 
	}
	
	public void drawPauseMenu(Graphics2D g)
	{
		if(this.gameOver)
			return;
		
		String str1 = "Game Paused";
		String str2 = "Press P to resume";
    	Font font = new Font("Serif", Font.BOLD, 25);
        g.setFont(font);
        
        this.drawFont(g, str1, 240, 150, new Color(0.0f, 0.1f, 1.0f));
        this.drawFont(g, str2, 220, 180, new Color(0.1f, 1.0f, 0.1f));
	}

	public void drawStartMenu(Graphics2D g)
	{
		//if(this.gameOver)
		//	return;
		
		String str1 = "W A S D to move";
		String str2 = "Up Down Left Right arrows to shoot";
		String str3 = "press any key to start";
    	Font font = new Font("Serif", Font.BOLD, 25);
        g.setFont(font);
        
        this.drawFont(g, str1, 240, 150, new Color(0.8f, 0.0f, 0.5f));
        this.drawFont(g, str2, 130, 180, new Color(0.1f, 1.0f, 0.1f));
        this.drawFont(g, str3, 215, 210, new Color(0.5f, 0.5f, 0.5f));
	}
	
	public void setGameOver(Graphics2D g)
	{
		this.drawGameOverMenu(g);
		this.gameOver = true;
	}
	
	public void drawGameOverMenu(Graphics2D g)
	{
		String str1 = "Game Over";
		String str2 = "Score: " + this.playerScore;
		String str3 = "Press P to start a new game";
    	Font font = new Font("Serif", Font.BOLD, 25);
        g.setFont(font);
        
        this.drawFont(g, str1, 240, 150, new Color(1.0f, 0.1f, 0.0f));
        this.drawFont(g, str2, 240, 180, new Color(0.2f, 1.0f, 1.0f));
        this.drawFont(g, str3, 150, 210, new Color(0.5f, 0.5f, 0.5f));
	}
	
	public int getPlayerScore()
	{
		return this.playerScore;
	}
	
	private class KeyInputHandler extends KeyAdapter 
	{

		private int pressCount = 1;
		
		public void keyPressed(KeyEvent e) {
			// if the keys were pressed, update movement
			if (e.getKeyCode() == KeyEvent.VK_A)
	    		moveLeft = true;
	    	if (e.getKeyCode() == KeyEvent.VK_D)
	    		moveRight = true;
	    	if (e.getKeyCode() == KeyEvent.VK_W)
	    		moveUp = true;
	    	if (e.getKeyCode() == KeyEvent.VK_S)
	    		moveDown = true;
	    	if (e.getKeyCode() == KeyEvent.VK_Q)
	    		rotateLeft = true;
	    	if (e.getKeyCode() == KeyEvent.VK_E)
	    		rotateRight = true;
	    	if (e.getKeyCode() == KeyEvent.VK_UP)
	    		fireUp = true;
	    	if (e.getKeyCode() == KeyEvent.VK_DOWN)
	    		fireDown = true;
	    	if (e.getKeyCode() == KeyEvent.VK_LEFT)
	    		fireLeft = true;
	    	if (e.getKeyCode() == KeyEvent.VK_RIGHT)
	    		fireRight = true;
	    	if (e.getKeyCode() == KeyEvent.VK_P)
	    	{
	    		paused = !paused;
	    		if (gameOver)
	    			setup();
	    	}
	    	if (e.getKeyCode() == KeyEvent.VK_SPACE)
	    		useBomb = true;
		} 
		

		public void keyReleased(KeyEvent e) {
			// if the keys were released, update movement
			if (e.getKeyCode() == KeyEvent.VK_A)
	    		moveLeft = false;
	    	if (e.getKeyCode() == KeyEvent.VK_D)
	    		moveRight = false;
	    	if (e.getKeyCode() == KeyEvent.VK_W)
	    		moveUp = false;
	    	if (e.getKeyCode() == KeyEvent.VK_S)
	    		moveDown = false;
	    	if (e.getKeyCode() == KeyEvent.VK_Q)
	    		rotateLeft = false;
	    	if (e.getKeyCode() == KeyEvent.VK_E)
	    		rotateRight = false;
	    	if (e.getKeyCode() == KeyEvent.VK_UP)
	    		fireUp = false;
	    	if (e.getKeyCode() == KeyEvent.VK_DOWN)
	    		fireDown = false;
	    	if (e.getKeyCode() == KeyEvent.VK_LEFT)
	    		fireLeft = false;
	    	if (e.getKeyCode() == KeyEvent.VK_RIGHT)
	    		fireRight = false;
	    	if (e.getKeyCode() == KeyEvent.VK_SPACE)
	    		useBomb = false;
	    	else
	    	{
	    		gameStarted = true;
	    	}
		}

		public void keyTyped(KeyEvent e) 
		{
			// nothing for now
		}
	}
}


