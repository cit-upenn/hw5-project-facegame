import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameThread extends Thread
{
	MainWindow window;
	long currentTime;
	
	public GameThread()
	{
		this.window = new MainWindow();
		this.currentTime = System.currentTimeMillis();
	}
	
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
				//System.out.println("draw " + (now - this.currentTime));
				this.currentTime = System.currentTimeMillis();
				
			}
			//System.out.println("	TIME ELAPSED " + (now - this.currentTime));
		}
	}
}
