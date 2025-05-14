package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SnakeView extends View {
	
	//The scalar for the size of the window
	private static final double WINDOW_SCALAR = 1.25;
	//The value for how many grid spaces there will be in the grid
	protected static final int GRID_DIMENSION = 15;
	
	//Snake view has-a window dimension
	private final int windowDimension;
	
	//SnakeView has-a font
	private Font publicPixel;
	
	//SnakeView has-a controller
	private SnakeController controller;
	
	//SnakeView has-a model
	private SnakeModel model;
	
	//SnakeView knows the size of the screen
	private Dimension screenSize;
	
	//Declare 2d array to hold JPanels by coordinate
	private JPanel[][] gridArray;
	
	//SnakeView has-a grid panel
	private JPanel gridPanel;
	//SnakeView has-a return panel
	private JPanel returnPanel;
	//SnakeView has-a game over panel
	private JPanel gameOverPanel;
	//SnakeView has-a game over label
	private JLabel gameOverLabel;
	//SnakeView has-a return button
	private JButton returnButton;
	
	/**
	 * 
	 */
	public SnakeView()
	{
		//Get screen size using java toolkit
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Window dimension is the screen size divided by 2
		windowDimension = (int)(screenSize.getHeight() / WINDOW_SCALAR);
		
		//Window will always be a square (TODO: possibly change this? Low priority)
		setSize(windowDimension, windowDimension);
		
		//Instantiate the font in try-catch
		//TODO: The font isn't changing :(
		try {
			publicPixel = Font.createFont(Font.TRUETYPE_FONT, FONT_FILE);
		} catch (FontFormatException | IOException e) {
			System.out.println("Font not found");
			e.printStackTrace();
		}
		
		//Private method creates the grid
		createGrid();
		
		model = new SnakeModel(this);
		controller = new SnakeController(model);
		
		//Start the snake by adding two snake pieces to the list in model
		//Do not move to model b/c it causes problems!!!
		model.addSnakePiece((int)(GRID_DIMENSION /2), (int)(GRID_DIMENSION /2));
		model.addSnakePiece((int)(GRID_DIMENSION /2) + 1, (int)(GRID_DIMENSION /2));
//		model.addSnakePiece((int)(GRID_DIMENSION /2) + 2, (int)(GRID_DIMENSION /2));
//		model.addSnakePiece((int)(GRID_DIMENSION /2) + 3, (int)(GRID_DIMENSION /2));
//		model.addSnakePiece((int)(GRID_DIMENSION /2) + 4, (int)(GRID_DIMENSION /2));
				
		//Add key listener to the view window
		addKeyListener(controller);
		
		setVisible(true);
		
	}
	
	/**
	 * Adds the grid to the grid panel and adds the panel to the frame
	 */
	private void createGrid()
	{
		//
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
	 * Determines the color of a grid piece by making it the opposite of the piece before it. 
	 * Initializes color of the first grid piece.
	 * @param row row of the piece
	 * @param col column of the piece
	 */
	public void setGridColor(int row, int col)
	{
		//If the grid piece is the first, make its color one
		if(row == 0 && col == 0)
		{
			gridArray[row][col].setBackground(GRID_COLOR_1);
		}
		
		//Else if grid piece is in column 0, make it the opposite of the piece directly above it
		else if(col == 0)
		{
			if(gridArray[row - 1][col].getBackground() == GRID_COLOR_1)
			{
				gridArray[row][col].setBackground(GRID_COLOR_2);
			}
			else
			{
				gridArray[row][col].setBackground(GRID_COLOR_1);
			}
		}
		//Else make the color the opposite of the grid piece before it
		else
		{
			if(gridArray[row][col - 1].getBackground() == GRID_COLOR_1)
			{
				gridArray[row][col].setBackground(GRID_COLOR_2);
			}
			else
			{
				gridArray[row][col].setBackground(GRID_COLOR_1);
			}
		}
	}
	
	/**
	 * Sets the JPanel at the specified coordinate value to the snake color
	 * @param row row of the piece
	 * @param col column of the piece
	 */
	public void setPieceColor(int row, int col, Color color)
	{
		gridArray[row][col].setBackground(color);
	}
	
	/**
	 * Removes the grid and builds the game over screen
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

		//Add a border to the button
		returnButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		//Get rid of ugly line when button focused
		returnButton.setFocusPainted(false);
		
		//Size Components with font
		gameOverLabel.setFont(new Font(publicPixel.getName(), publicPixel.getStyle(), (int)(windowDimension / 6)));
		returnButton.setFont(new Font(publicPixel.getName(), publicPixel.getStyle(), (int)(windowDimension / 6)));
		
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
	
	public JPanel getPanelAtCoordinates(int row, int col)
	{
		return gridArray[row][col];
	}
	
	/**
	 * Test method to give testandexecute access to the model
	 */
	public SnakeModel getModel()
	{
		return model;
	}
}
