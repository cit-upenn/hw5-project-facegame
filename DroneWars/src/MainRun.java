
/**
 * Game main run method for running the game on the same thread
 * @author Rony Edde
 *
 */
public class MainRun 
{
	/**
	 * the main method that creates an instance of the game and runs it
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//MainWindow win = new MainWindow();
		System.setProperty("sun.java2d.opengl","True");
		//GameThread game = new GameThread();
		//game.run();
		GameEngine win = new GameEngine(null);
		win.gameLoop();
	}

}
