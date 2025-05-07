package src;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

public class SnakeModel extends Model {
	
	//
	SnakeView view;
	
	//Declare list to store snake piece in order
	private LinkedList<Piece> snakeList;
	//Declare list to store goal pieces (For possible implementation of several goals active at once)
	private LinkedList<Piece> goalList;
	
	//Declare char to hold blocked direction (U up, D down, L left, R right)
	//Snake cannot move in this direction
	private char blockDirection;
	
	/**
	 * 
	 * @param view
	 */
	public SnakeModel(SnakeView view)
	{
		super();
		this.view = view;
		
		//Instantiate direction as down to start
		blockDirection = 'D';
		
		//List that will hold all of the pieces that comprise the snake
		snakeList = new LinkedList<>();
		
		//List that will hold all of the goals in play
		goalList = new LinkedList<>();
		
		//Create the first goal piece and generate viable coordinates
		goalList.addLast(new Piece(-1, -1));
		generateGoalCoordinates(goalList.getLast());
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
		view.setPieceColor(snakeList.getLast().getRow(), snakeList.getLast().getCol(), View.SNAKE_COLOR);
	}
	
	/**
	 * Generates the position of the goal, making sure there are no pieces in the way
	 * Adds the goal to the list and makes it visible
	 */
	public void generateGoalCoordinates(Piece goal)
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
			if(view.getPanelAtCoordinates(row, col).getBackground() != View.SNAKE_COLOR)
			{
				clear = true;
			}
		}
		
		//Set the goal's coordinates to the confirmed coordinates
		goal.setRow(row);
		goal.setCol(col);
		
		//Make it visible
		view.setPieceColor(goalList.getLast().getRow(), goalList.getLast().getCol(), View.GOAL_COLOR);
	}
	
	/**
	 * Checks if the snake has reached the goal. If yes, generate new goal coordinates and TODO: create a new tail piece
	 */
	private void checkGoalCollision()
	{
		//Store coordinates of the head of the snake
		int snakeRow = snakeList.getFirst().getRow();
		int snakeCol = snakeList.getFirst().getCol();
	
		//For every active goal
		for(int i = 0; i < goalList.size(); i++)
		{
			int goalRow = goalList.get(i).getRow();
			int goalCol = goalList.get(i).getCol();
			
			//If the snake has reached the goal, generate new coordinates for the goal
			if(snakeRow == goalRow && snakeCol == goalCol)
			{
				System.out.println("Goal reached");
				generateGoalCoordinates(goalList.get(i));
			}
		}
	}
	
	private void checkBodyCollision()
	{
		//Coorinates of the head
		int headRow = snakeList.getFirst().getRow();
		int headCol = snakeList.getFirst().getCol();
		
		//For all pieces in snake list excluding head, check for collision with head
		for(int i = 1; i < snakeList.size(); i++)
		{
			//Coordinates of the piece to compare
			int pieceRow = snakeList.get(i).getRow();
			int pieceCol = snakeList.get(i).getCol();
			
			//If head coordinates are same as body piece coordinates, game over
			if(headRow == pieceRow && headCol == pieceCol)
			{
				System.out.println("Body collision");
				displayGameOver();
			}
		}
	}
	
	//TODO
	private void checkOutOfBounds()
	{
		//Coorinates of the head
		int headRow = snakeList.getFirst().getRow();
		int headCol = snakeList.getFirst().getCol();
		
		
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
		view.setPieceColor(snakeList.getFirst().getRow(), snakeList.getFirst().getCol(), View.SNAKE_COLOR);
		
	}
	
	/**
	 * 
	 */
	public void moveUp()
	{
		//If it didn't move up last turn
		if(blockDirection != 'U')
		{
			//Move the back piece to the front
			moveBackFront();
			
			//Move the piece at the front to the right coordinates and display change
			//Moving one row above piece behind it
			moveFrontPiece(snakeList.get(1).getRow() - 1, snakeList.get(1).getCol());
			
			//Check collisions
			checkGoalCollision();
			checkBodyCollision();
			
			//Update last direction
			blockDirection = 'D';
		}
	}
	
	public void moveDown()
	{
		//If it didn't move down last turn
		if(blockDirection != 'D')
		{
			//Move the back piece to the front
			moveBackFront();
			
			//Move the piece at the front to the right coordinates and display change
			//Moving one row below piece behind it
			moveFrontPiece(snakeList.get(1).getRow() + 1, snakeList.get(1).getCol());
			
			//Check collisions
			checkGoalCollision();
			checkBodyCollision();
			
			//Update last direction
			blockDirection = 'U';
		}
	}
	
	public void moveLeft()
	{
		//If it didn't move left last turn
		if(blockDirection != 'L')
		{
			//Move the back piece to the front
			moveBackFront();
			
			//Move the piece at the front to the right coordinates and display change
			//Moving one col left of the piece behind it
			moveFrontPiece(snakeList.get(1).getRow(), snakeList.get(1).getCol() - 1);
			
			//Check collisions
			checkGoalCollision();
			checkBodyCollision();
			
			//Update last direction
			blockDirection = 'R';
		}
	}
	
	public void moveRight()
	{
		//If it didn't move right last turn
		if(blockDirection != 'R')
		{
			//Move the back piece to the front
			moveBackFront();
			
			//Move the piece at the front to the right coordinates and display change
			//Moving one col right of the piece behind it
			moveFrontPiece(snakeList.get(1).getRow(), snakeList.get(1).getCol() + 1);
			
			//Check collisions
			checkGoalCollision();
			checkBodyCollision();
			
			//Update last direction
			blockDirection = 'L';
		}
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
