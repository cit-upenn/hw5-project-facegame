import java.lang.*;
public class MainRun 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//MainWindow win = new MainWindow();
		System.setProperty("sun.java2d.opengl","True");
		//GameThread game = new GameThread();
		//game.run();
		MainWindow win = new MainWindow();
		win.gameLoop();
	}

}
