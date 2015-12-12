import java.lang.*;

import javax.swing.JFrame;
public class MainRun 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//MainWindow win = new MainWindow();
		System.setProperty("sun.java2d.opengl","True");
		//GameThread game = new GameThread();
		//game.run();
		JFrame container = new JFrame("Drone Wars");
		GameEngine win = new GameEngine(container);
		win.gameLoop();
	}

}
