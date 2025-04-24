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

//TODO (major) rewrite the end
public class SnakeView extends View {
	
	//The scalar for the size of the window
	private static final double WINDOW_SCALAR = 1.25;
	//The value for how many grid spaces there will be in the grid
	private static final int GRID_DIMENSION = 15;
	//The file holding the text font
	private static final File FONT_FILE = new File("PublicPixel-rv0pA.ttf");
	
	//Grid Colors will alternate
	//Grid color
	private static final Color GRID_COLOR_1 = Color.DARK_GRAY;
	//Grid color
	private static final Color GRID_COLOR_2 = Color.BLACK;
	//Fg color
	private static final Color FG_COLOR = Color.GREEN;
	//Bg color
	private static final Color BG_COLOR = Color.DARK_GRAY;
	
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
		model = new SnakeModel(this);
		controller = new SnakeController(model);
		
		//Get screen size using java toolkit
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Window dimension is the screen size divided by 2
		windowDimension = (int)(screenSize.getHeight() / WINDOW_SCALAR);
		
		//Window will always be a square (TODO: possbily change this?)
		setSize(windowDimension, windowDimension);
		
		//Instantiate the font in try-catch
		//TODO: The font isn't changing :(
		try {
			publicPixel = Font.createFont(Font.TRUETYPE_FONT, FONT_FILE);
		} catch (FontFormatException | IOException e) {
			System.out.println("Font not found");
			e.printStackTrace();
		}
		
		//Private methods create components of the window
		createGrid();
		createSnake();
		
		//Add key listener to the view window
		addKeyListener(controller);
		
		setVisible(true);
		
	}
	
	/**
	 * Adds the grid to the grid panel and adds the panel to the frame
	 * TODO: Determine if you actually need the 2D array
	 */
	private void createGrid()
	{
		//
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(GRID_DIMENSION, GRID_DIMENSION));
		
		//Array that will hold the posotions of every panel in the array
		gridArray = new JPanel[GRID_DIMENSION][GRID_DIMENSION];
		
		//Keeps track of what number space is being created
		int spaceIndex = 1;
		
		//Fill the grid with panels
		for(int r = 0; r < GRID_DIMENSION; r++)
		{
			for(int c = 0; c < GRID_DIMENSION; c++)
			{
				//Declare new JPanel added to array
				gridArray[r][c] = new JPanel();
				
				//If even square, this color
				if(spaceIndex % 2 == 0)
				{
					gridArray[r][c].setBackground(GRID_COLOR_1);
				}
				//Else other color
				else
				{
					gridArray[r][c].setBackground(GRID_COLOR_2);
				}
				
				//Add to the JPanel
				gridPanel.add(gridArray[r][c]);
				
				//Next space
				spaceIndex++;
			}
		}
		//Color
		gridPanel.setBackground(GRID_COLOR_2);
		
		//Add the panel containing all grid pieces to the center
		add(gridPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Starts the snake and places it in the middle of the grid
	 */
	private void createSnake()
	{
		//Add the first snake piece in the center of the grid (head) and the second one row below (tail)
		model.addSnakePiece(GRID_DIMENSION / 2, GRID_DIMENSION / 2);
		model.addSnakePiece((GRID_DIMENSION / 2) - 1, GRID_DIMENSION / 2);
		
//		//Add the snake pieces to the array
//		TODO: Is the array necessary?
//		//TODO: Make this more readable
//		gridArray[snakeList.getFirst().getRow()][snakeList.getFirst().getCol()] = snakeList.getFirst();
//		gridArray[snakeList.getLast().getRow()][snakeList.getLast().getCol()] = snakeList.getLast();
	}
	
	/**
	 * Removes a piece/JPanel from a specific coordinate
	 * @param row row of the piece
	 * @param col column of the piece
	 */
	public void removeComponent(int row, int col)
	{
		//Equation for calculating the index of a piece on the grid: index = r * cols + c
		int index = row * GRID_DIMENSION + col;
		gridPanel.remove(index);
		
	}
	
	/**
	 * Adds a JPanel to the specified coordinates
	 * @param row row of the piece
	 * @param col column of the piece
	 */
	public void addJPanel(int row, int col)
	{
		//Equation for calculating the index of a piece on the grid: index = r * cols + c
		int index = row * GRID_DIMENSION + col;
		gridPanel.add(new JPanel(), index);
		
		gridPanel.revalidate();
		gridPanel.repaint();
	}
	
	/**
	 * Adds a specified piece to the specified coordinates
	 * @param row row of the piece
	 * @param col column of the piece
	 * @param piece the piece to be added
	 */
	public void addPiece(int row, int col, Piece piece)
	{
		//Equation for calculating the index of a piece on the grid: index = r * cols + c
		int index = row * GRID_DIMENSION + col;
		gridPanel.add(piece, index);
		
		gridPanel.revalidate();
		gridPanel.repaint();
	}
	
	/**
	 * Replaces a space on the grid with a piece
	 * Places the piece where its stored coordinates say it should be
	 * @param piece the piece to be moved/placed
	 */
	public void replaceGridPiece(Piece piece)
	{
		//Equation for calculating the index of a piece on the grid: index = r * cols + c
		int index = piece.getRow() * GRID_DIMENSION + piece.getCol();
		gridPanel.remove(index);
		gridPanel.add(piece, index);
		gridPanel.getComponent(index).setBackground(Color.GREEN);
		gridPanel.revalidate();
		gridPanel.repaint();
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
		
		//Window no longer takes in key inputs
		removeKeyListener(controller);
		
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
	 * Test method to give testandexecute access to the model
	 */
	public SnakeModel getModel()
	{
		return model;
	}
}
