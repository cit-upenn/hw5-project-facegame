
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
		
		this.window.gameLoop();
		
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
