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
	Profile faceGame;
	/**
	 * constructor to initialize
	 */
	public GameThread(JFrame container, Profile p)
	{
		this.faceGame = p;
		this.window = new GameEngine(container, p);
		this.currentTime = System.currentTimeMillis();
	}
	
	/**
	 * runs the thread
	 */
	public void run()
	{
		//this.window.repaint(17);
		this.window.gameLoop();

	}
}
