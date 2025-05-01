package src;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

public class SnakeModel extends Model {
	
	//
	SnakeView view;
	
	//Instantiate list to store snake piece in order
	private LinkedList<Piece> snakeList;
	//Instantiate list to store goal pieces (For possible implementation of several goals active at once)
	private LinkedList<Piece> goalList;
	
	/**
	 * 
	 * @param view
	 */
	public SnakeModel(SnakeView view)
	{
		super();
		this.view = view;
		
		//List that will hold all of the pieces that comprise the snake
		snakeList = new LinkedList<>();
		
		//List that will hold all of the goals in play
		goalList = new LinkedList<>();
	}
	
	/**
	 * Creates a piece, adds it to the end of the snake and calls method to make it visible
	 * @param row
	 * @param col
	 */
	public void addSnakePiece(int row, int col)
	{
		//Add a new piece to the list
		snakeList.addLast(new Piece(row, col));
		
		//Make the snake piece visible on the grid
		view.setPieceColor(snakeList.getLast().getRow(), snakeList.getLast().getCol(), SnakeView.SNAKE_COLOR);
	}
	
	/**
	 * Generates the position of the goal, making sure there are no pieces in the way
	 * Adds the goal to the list and makes it visible
	 */
	public void generateGoal()
	{
		//Confirms if the generated coordinates are clear or not
		boolean clear = false;
		
		//Generate random coordinate
		Random randCoord = new Random();
		
		//Store coordinates
		int row = -1;
		int col = -1;
		
		//While space is not confirmed clear
		while(!clear)
		{
			row = randCoord.nextInt(SnakeView.GRID_DIMENSION - 1);
			col = randCoord.nextInt(SnakeView.GRID_DIMENSION - 1);
			
			//Get JPanel at coordinates. If it is not the same color as the snake, must be clear.
			//More efficient than going through entire snakeList comparing coordinates
			if(view.getPanelAtCoordinates(row, col).getBackground() != SnakeView.SNAKE_COLOR)
			{
				clear = true;
			}
		}
		
		//Make piece with confirmed coordinates
		goalList.addLast(new Piece(row, col));
		
		//Make it visible
		view.setPieceColor(goalList.getLast().getRow(), goalList.getLast().getCol(), SnakeView.GOAL_COLOR);
	}
			
	
	/**
	 * Move the piece at the back of the snake list to the front
	 */
	private void moveBackFront()
	{
		//Adds the piece at the back of the list to the front
		snakeList.addFirst(snakeList.getLast());
		//Removes from the back of the list
		snakeList.removeLast();
	}
	
	/**
	 * After swapping front and back piece in list, do necessary procedures to:
	 * 				Change the color of the old coordinate of the front piece to grid color
	 * 				Change the coordinates of the front piece
	 * 				Change the color of the new coordinate of the front piece to snake color
	 * 
	 * @param row row to change row of first in list to
	 * @param col col to change col of first in list to
	 */
	private void moveFrontPiece(int row, int col)
	{
		//Set the location of the back (now front) piece to grid color
		view.setGridColor(snakeList.getFirst().getRow(), snakeList.getFirst().getCol());
		
		//Change the row of the back (now front) piece to the new coordinates
		snakeList.getFirst().setRow(row);
		snakeList.getFirst().setCol(col);
		
		//Set the new location of the back (now front) piece to snake color
		view.setPieceColor(snakeList.getFirst().getRow(), snakeList.getFirst().getCol(), SnakeView.SNAKE_COLOR);
		
	}
	//TODO: There's some sort of problem with movement towards the beginning that fixes itself after you press enough directions?
	/**
	 * 
	 */
	public void moveUp()
	{
		//Move the back piece to the front
		moveBackFront();
		
		//Move the piece at the front to the right coordinates and display change
		//Moving one row above piece behind it
		moveFrontPiece(snakeList.get(1).getRow() - 1, snakeList.get(1).getCol());
	}
	
	public void moveDown()
	{
		//Move the back piece to the front
		moveBackFront();
		
		//Move the piece at the front to the right coordinates and display change
		//Moving one row below piece behind it
		moveFrontPiece(snakeList.get(1).getRow() + 1, snakeList.get(1).getCol());
	}
	
	public void moveLeft()
	{
		//Move the back piece to the front
		moveBackFront();
		
		//Move the piece at the front to the right coordinates and display change
		//Moving one col left of the piece behind it
		moveFrontPiece(snakeList.get(1).getRow(), snakeList.get(1).getCol() - 1);
	}
	
	public void moveRight()
	{
		//Move the back piece to the front
		moveBackFront();
		
		//Move the piece at the front to the right coordinates and display change
		//Moving one col right of the piece behind it
		moveFrontPiece(snakeList.get(1).getRow(), snakeList.get(1).getCol() + 1);
	}
	
	/**
	 * Tells the view to display the game over screen
	 */
	public void displayGameOver()
	{
		view.gameOver();
		System.out.println("DisplayGameOver");
	}
	
	/**
	 * Closes the snake window and opens a new menu window
	 */
	public void closeWindow()
	{
		createMenuWindow();
		view.close();
	}
	
}
