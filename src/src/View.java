package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;

public class View extends JFrame {
	
	//The file holding the text font
	protected static final File FONT_FILE = new File("PublicPixel-rv0pA.ttf");
	
	//Grid Colors will alternate
	//Grid color
	protected static final Color GRID_COLOR_1 = Color.DARK_GRAY;
	//Grid color
	protected static final Color GRID_COLOR_2 = Color.BLACK;
	//Fg color
	protected static final Color FG_COLOR = Color.GREEN;
	//Bg color
	protected static final Color BG_COLOR = Color.DARK_GRAY;
	//Snake color
	protected static final Color SNAKE_COLOR = Color.GREEN;
	//Goal color
	protected static final Color GOAL_COLOR = Color.ORANGE;

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