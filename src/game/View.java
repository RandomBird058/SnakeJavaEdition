package game;

/**
* @author Dominic Ricci
* @otherContributors: CISC 191 Class, Prof. Alex Chow
* @version 1.0
* @see
*      
* Responsibilities of class: 	Be able to close itself
* 								Hold constants used by all views
*/

import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

//Font: Public Pixel - https://www.fontspace.com/public-pixel-font-f72305
//View is-a JFrame
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
		//TODO: (low priority) this could work but you need to add a way to drag the frame using the mouse
		//setUndecorated(true);
		
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Closes the window and frees its resources
	 */
	public void close()
	{
		dispose();
	}
}