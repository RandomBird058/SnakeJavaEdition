package src;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class View extends JFrame {
	
	public View()
	{	
		new JFrame();
		
		//TODO: (low priority) this could work but you need to add a way to drag the frame using the mouse
		//setUndecorated(true);
		
		setResizable(false);
	}
	
	/**
	 * Closes the window and frees its resources
	 */
	public void close()
	{
		dispose();
	}
}