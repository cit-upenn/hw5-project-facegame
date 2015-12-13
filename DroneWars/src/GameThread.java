import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Threading class for running the game in a separate thread
 * @author Rony Edde
 *
 */
public class GameThread extends Thread
{
	GameEngine window;
	long currentTime;
	
	/**
	 * constructor to initialize
	 */
	public GameThread()
	{
		this.window = new GameEngine(null);
		this.currentTime = System.currentTimeMillis();
	}
	
	/**
	 * runs the thread
	 */
	public void run()
	{
		this.window.repaint(17);
		
		while(true)
		{
			long now = System.currentTimeMillis();
			if (now - this.currentTime >= 17)
			{	    	
				//this.window.eval();
				//this.window.draw();
				this.currentTime = System.currentTimeMillis();
				
			}
		}
	}
}
