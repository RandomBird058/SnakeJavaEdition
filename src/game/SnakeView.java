package game;

/**
* @author Dominic Ricci
* @otherContributors: CISC 191 Class, Prof. Alex Chow
* @version 1.0
* @see Font: Agogo - https://www.fontspace.com/a-gogo-font-f28855
*      
* Responsibilities of class: 	Display a grid on the snake game window
* 								Create a game over screen when told by the model
* 								Change the colors of each piece on the grid based on the model
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//SnakeView is-a View
public class SnakeView extends View {
	
	//The scalar for the size of the window
	private static final double WINDOW_SCALAR = 1.1;
	
	//The integer for how many grid spaces there will be in the grid
	protected static final int GRID_DIMENSION = 16;
	
	//Snake view has-a window dimension
	private final int windowDimension;
	
	//SnakeView has-a font
	private Font agogo;
	
	//SnakeView has-a controller
	private SnakeController controller;
	
	//SnakeView has-a model
	private SnakeModel model;
	
	//SnakeView has-a dimension for the size of the screen
	private Dimension screenSize;
	
	//SnakeView has-a 2d array to hold JPanels by coordinate
	private JPanel[][] gridArray;
	
	//SnakeView has-many JPanels
	private JPanel gridPanel, returnPanel, gameOverPanel;
	
	//SnakeView has-a game over label
	private JLabel gameOverLabel;
	
	//SnakeView has-a return button
	private JButton returnButton;
	
	/**
	 * Call view constructor, instantiate objects and build the window
	 */
	public SnakeView()
	{
		super();
		//Get screen size using java toolkit
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Window dimension is the screen size divided by 2
		windowDimension = (int)(screenSize.getHeight() / WINDOW_SCALAR);
		
		//Window will always be a square (TODO: possibly change this? Low priority)
		setSize(windowDimension, windowDimension);
		
		//Instantiate the font in try-catch
		try {
			agogo = Font.createFont(Font.TRUETYPE_FONT, FONT_FILE);
			System.out.println("Font made succesfully");
		} catch (FontFormatException | IOException e) {
			System.out.println("Font not found");
			e.printStackTrace();
		}
		
		//Private method creates the grid
		createGrid();
		
		//Instantiate the model and controller
		model = new SnakeModel(this);
		controller = new SnakeController(model);
		
		//Start the snake by adding two snake pieces to the list in model
		//Do not move to model b/c it causes problems!!!
		model.addSnakePiece((int)(GRID_DIMENSION /2), (int)(GRID_DIMENSION /2));
		model.addSnakePiece((int)(GRID_DIMENSION /2) + 1, (int)(GRID_DIMENSION /2));
				
		//Add key listener to the view window
		addKeyListener(controller);
		
		//Make window visible
		setVisible(true);
		
	}
	
	/**
	 * Adds the grid to the grid panel and adds the panel to the frame
	 */
	private void createGrid()
	{
		//Instantiate the panel and the grid layout
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(GRID_DIMENSION, GRID_DIMENSION));
		
		//Array that will hold the posotions of every panel in the array
		gridArray = new JPanel[GRID_DIMENSION][GRID_DIMENSION];
		
		//Fill the grid with panels
		for(int r = 0; r < GRID_DIMENSION; r++)
		{
			for(int c = 0; c < GRID_DIMENSION; c++)
			{
				//Declare new JPanel added to array
				gridArray[r][c] = new JPanel();
				
				//Set the color of the piece
				setGridColor(r, c);
				
				//Add to the JPanel
				gridPanel.add(gridArray[r][c]);
			}
		}
		
		//Color
		gridPanel.setBackground(GRID_COLOR_2);
		
		//Add the panel containing all grid pieces to the center
		add(gridPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Determines the color of a grid piece using a binary matrix.
	 * @param row row of the piece
	 * @param col column of the piece
	 */
	public void setGridColor(int row, int col)
	{
		/**		
		 * The binary matrix for the grid for alternating colors is as follows:
		 * (1 means even num, 0 means odd num)
		 * 		c1	c2	c3	c4	cn
		 * r1	00	01	00	01	0n
		 * r2	10	11	10	11	1n
		 * r3	00	01	00	01	0n
		 * r4	10	11	10	11	1n
		 * rn	n0	n1	n0	n1	nn
		 * So if the row and column are both even or both odd, one color.
		 * If only one is even or odd, other color.
		 * (Add 1 so that the smallest possible row/col value is 1, not 0. 0 messes everything up)
		 */
		//If row and col are both even or both odd
		if(((row + 1) % 2) == ((col + 1) % 2))
		{
			//This color
			gridArray[row][col].setBackground(GRID_COLOR_1);
		}
		//If row and col are not both even or odd
		else
		{
			//Other color
			gridArray[row][col].setBackground(GRID_COLOR_2);
		}
	}
	
	/**
	 * Sets the JPanel at the specified coordinate value to the snake color
	 * @param row row of the piece
	 * @param col Column of the piece
	 */
	public void setPieceColor(int row, int col, Color color)
	{
		gridArray[row][col].setBackground(color);
	}
	
	/**
	 * Removes the grid and builds the game over screen
	 * TODO: Make this another window?
	 */
	public void gameOver()
	{
		System.out.println("GameOver (View)");
		
		//Declare components and add action listener to button
		gameOverPanel = new JPanel();
		returnPanel = new JPanel();
		gameOverLabel = new JLabel("Game Over");
		returnButton = new JButton("Return");
		returnButton.addActionListener(controller);
		
		//Colors
		gameOverPanel.setBackground(BG_COLOR);
		gameOverPanel.setForeground(FG_COLOR);
		
		returnPanel.setBackground(BG_COLOR);
		returnPanel.setForeground(FG_COLOR);
		
		gameOverLabel.setBackground(BG_COLOR);
		gameOverLabel.setForeground(FG_COLOR);
		
		returnButton.setBackground(BG_COLOR);
		returnButton.setForeground(FG_COLOR);

		//Add a border to the button (invisible for now because the font isnt centered)
		returnButton.setBorder(BorderFactory.createLineBorder(View.BG_COLOR, 2));
		
		//Get rid of ugly line when button focused
		returnButton.setFocusPainted(false);
		
		//Size Components with font
		gameOverLabel.setFont(agogo.deriveFont(Font.PLAIN, (int)(windowDimension/6)));
		returnButton.setFont(agogo.deriveFont(Font.PLAIN, (int)(windowDimension/6)));
		
		//Window no longer takes in inputs from controller
		removeKeyListener(controller);
		//Stops the timer in the controller
		controller.stopMoveTimer();
		
		//Remove the grid from the screen
		remove(gridPanel);
		
		//Add components to panel and panel to frame
		gameOverPanel.add(gameOverLabel);
		returnPanel.add(returnButton);
		add(gameOverPanel, BorderLayout.NORTH);
		add(returnPanel, BorderLayout.CENTER);
		
		repaint();
		revalidate();
	}
	
	/**
	 * Returns the JPanel in the array with the specified coordinates
	 * @param row The row of the piece
	 * @param col The col of the piece
	 * @return The piece at the coordinates
	 */
	public JPanel getPanelAtCoordinates(int row, int col)
	{
		return gridArray[row][col];
	}
	
	/**
	 * Test method to give testandexecute access to the model
	 * TODO: Can be removed soon
	 */
	public SnakeModel getModel()
	{
		return model;
	}
}
