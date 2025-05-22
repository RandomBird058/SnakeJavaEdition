package game;

import java.util.LinkedList;
import java.util.Random;

//SnakeModel is-a Model
public class SnakeModel extends Model {
	
	//SnakeModel has-a SnakeView
	private SnakeView view;
	
	//SnakeModel Has-a Stats class
	private Stats stats;
	
	//Declare a LinkedList to store snake pieces and a LinkedList to store goals
	private LinkedList<Piece> snakeList, goalList;
	
	//Declare an integer to hold blocked direction (1 Up, 2 Down, 3 Left, 4 Right)
	//Snake cannot move in this direction
	private int blockedDirection;
	
	//Declare an integer to hold goals eaten this game
	//TODO: Display on game over screen (low priority)
	private int goalsEaten;
	
	//Declare a boolean to tell if the game is paused
	private boolean paused;
	
	/**
	 * Call the Model constructor and instantiate objects 
	 * Create the first goal piece
//	 * TODO: Why not create snake pieces here too?
	 * @param view The view that the model will give commands to
	 */
	public SnakeModel(SnakeView view)
	{
		super();
		this.view = view;
		stats = new Stats();
		
		//Instantiate blocked direction as down to start
		blockedDirection = 2;
		
		//The game isn't paused to begin
		paused = false;
		
		//instantiate goals eaten as 0
		goalsEaten = 0;
		
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
	 * @param row The row assigned to the piece
	 * @param col The col assigned to the piece
	 */
	public void addSnakePiece(int row, int col)
	{
		//Add a new piece to the list
		snakeList.addLast(new Piece(row, col));
		
		//Make the snake piece visible on the grid
		view.setPieceColor(snakeList.getLast().getRow(), snakeList.getLast().getCol(), View.SNAKE_COLOR);
	}
	
	/**
	 * Generates the position of the goal, making sure there are no pieces at that position
	 * Adds the goal to the list and makes it visible
	 */
	public void generateGoalCoordinates(Piece goal)
	{
		//Confirms if the generated coordinates are clear or not
		boolean clear = false;
		
		//Generate random coordinate
		Random randCoord = new Random();
		
		//Integers to store coordinates
		int row = -1;
		int col = -1;
		
		//While space is not confirmed clear
		while(!clear)
		{
			//Randomly generate coordinates 
			row = randCoord.nextInt(SnakeView.GRID_DIMENSION);
			col = randCoord.nextInt(SnakeView.GRID_DIMENSION);
			
			//Get JPanel at coordinates. If it is not the same color as the snake, must be clear.
			//Seems strange but is more efficient than going through entire snakeList comparing coordinates
			if(view.getPanelAtCoordinates(row, col).getBackground() != View.SNAKE_COLOR)
			{
				clear = true;
			}
		}
		
		//Set the goal's coordinates to the confirmed coordinates
		goal.setRow(row);
		goal.setCol(col);
		
		//Make it visible
		view.setPieceColor(goal.getRow(), goal.getCol(), View.GOAL_COLOR);
	}
	
	/**
	 * Checks if the snake has reached the goal
	 * If yes, generate new goal coordinates and add a tail piece to the end of the snake
	 */
	private void checkGoalCollision()
	{
		//Coordinates of the head
		int snakeRow = snakeList.getFirst().getRow();
		int snakeCol = snakeList.getFirst().getCol();
	
		//For every active goal
		for(int i = 0; i < goalList.size(); i++)
		{
			//Coordinates of the goal being compared
			int goalRow = goalList.get(i).getRow();
			int goalCol = goalList.get(i).getCol();
			
			//If the snake occupies the same coordinates as the goal, generate new coordinates for the goal 
			//Increment goals eaten, update stats file, check for level up
			if(snakeRow == goalRow && snakeCol == goalCol)
			{
				//Increment goals eaten
				goalsEaten++;
				
				//Increment goals eaten from file
				int[] updatedData = stats.readData();
				updatedData[0] ++;
				
				//Update file with new goals
				stats.writeData(updatedData);
				
				//Generate new goal coords
				generateGoalCoordinates(goalList.get(i));
				
				//Add a snake piece to the end of the list
				addSnakePiece(snakeList.getLast().getRow(), snakeList.getLast().getCol());
				
				//Every 10 goals eaten, level up
				if(goalsEaten % 10 == 0)
				{
					levelUp();
				}
			}
		}
	}
	
	/**
	 * Checks if the head of the snake is going to occupy the same space as the body
	 * If so, game over
	 * TODO: Optimization is recommended if this is going to go large scale
	 */
	private void checkBodyCollision()
	{
		//Coordinates of the head
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
				displayGameOver();
			}
		}
	}
	
	/**
	 * Checks if a movement in a certain direction will move the snake out of bounds
	 * @param row The row direction requested to move in
	 * @param col The column direction requested to move in
	 * @return true if safe to move, false if unsafe
	 */
	private boolean checkOutOfBounds(int row, int col)
	{
		//Coordinates of the head
		int headRow = snakeList.getFirst().getRow();
		int headCol = snakeList.getFirst().getCol();
		
		//If head's row + the direction is less then 0 or greater than the grid
		if(headRow + row < 0 || headRow + row == SnakeView.GRID_DIMENSION)
		{
			//Display game over and return false
			displayGameOver();
			return false;
		}
		//If head's col + the direction is less then 0 or greater than the grid
		if(headCol + col < 0 || headCol + col == SnakeView.GRID_DIMENSION)
		{
			displayGameOver();
			return false;
		}
		return true;
	}
			
	/**
	 * Move the piece at the back of the snake list to the front
	 * Remove the moved piece from the back
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
	 * @param row Row to change row of first in list to
	 * @param col Col to change col of first in list to
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
	 * Moves the snake up by calling the moveBackFront and moveFrontPiece methods
	 * 		Checks if the user is trying to move in the blocked direction
	 * 		Checks if the user is moving out of bounds
	 * 		Checks if the game is paused
	 * @return true if the move was successful and false if it wasn't
	 */
	public boolean moveUp()
	{
		//If up isn't blocked and not paused
		if(blockedDirection != 1 && !paused)
		{
			//If safe to move
			if(checkOutOfBounds(-1, 0))
			{
				//Move the back piece to the front
				moveBackFront();
				
				//Move the piece at the front to the right coordinates and display change
				//Moving one row above piece behind it
				moveFrontPiece(snakeList.get(1).getRow() - 1, snakeList.get(1).getCol());
				
				//Check collisions
				checkGoalCollision();
				checkBodyCollision();
				
				//Block down
				blockedDirection = 2;
				
				//Successful
				return true;
			}
		}
		//Not successful
		return false;
	}
	
	/**
	 * Moves the snake down by calling the moveBackFront and moveFrontPiece methods
	 * 		Checks if the user is trying to move in the blocked direction
	 * 		Checks if the user is moving out of bounds
	 * 		Checks if the game is paused
	 * @return true if the move was successful and false if it wasn't
	 */
	public boolean moveDown()
	{
		//If down isn't blocked and not paused
		if(blockedDirection != 2 && !paused)
		{
			//If safe to move
			if(checkOutOfBounds(1, 0))
			{
				//Move the back piece to the front
				moveBackFront();
				
				//Move the piece at the front to the right coordinates and display change
				//Moving one row below piece behind it
				moveFrontPiece(snakeList.get(1).getRow() + 1, snakeList.get(1).getCol());
				
				//Check collisions
				checkGoalCollision();
				checkBodyCollision();
				
				//Block up
				blockedDirection = 1;
				
				//Successful
				return true;
			}
		}
		//Not successful
		return false;
	}
	
	/**
	 * Moves the snake left by calling the moveBackFront and moveFrontPiece methods
	 * 		Checks if the user is trying to move in the blocked direction
	 * 		Checks if the user is moving out of bounds
	 * 		Checks if the game is paused
	 * @return true if the move was successful and false if it wasn't
	 */
	public boolean moveLeft()
	{
		//If left isn't blocked and not paused
		if(blockedDirection != 3 && !paused)
		{
			//If safe to move
			if(checkOutOfBounds(0, -1))
			{
				//Move the back piece to the front
				moveBackFront();
				
				//Move the piece at the front to the right coordinates and display change
				//Moving one col left of the piece behind it
				moveFrontPiece(snakeList.get(1).getRow(), snakeList.get(1).getCol() - 1);
				
				//Check collisions
				checkGoalCollision();
				checkBodyCollision();
				
				//Block right
				blockedDirection = 4;
				
				//Successful
				return true;
			}
		}
		//Not successful
		return false;
	}
	
	/**
	 * Moves the snake right by calling the moveBackFront and moveFrontPiece methods
	 * 		Checks if the user is trying to move in the blocked direction
	 * 		Checks if the user is moving out of bounds
	 * 		Checks if the game is paused
	 * @return true if the move was successful and false if it wasn't
	 */
	public boolean moveRight()
	{
		
		//If right isn't blocked and not paused
		if(blockedDirection != 4 && !paused)
		{
			//If safe to move
			if(checkOutOfBounds(0, 1))
			{
				//Move the back piece to the front
				moveBackFront();
				
				//Move the piece at the front to the right coordinates and display change
				//Moving one col right of the piece behind it
				moveFrontPiece(snakeList.get(1).getRow(), snakeList.get(1).getCol() + 1);
				
				//Check collisions
				checkGoalCollision();
				checkBodyCollision();
				
				//Block left
				blockedDirection = 3;
				
				//Successful
				return true;
			}
		}
		//Not successful
		return false;
	}
	
	/**
	 * Adds another goal to the goalList and generates it on the grid
	 */
	public void levelUp()
	{
		goalList.addLast(new Piece(-1, -1));
		generateGoalCoordinates(goalList.getLast());
	}
	
	/**
	 * Toggles the paused field
	 */
	public void togglePaused()
	{
		if(paused == true)
		{
			paused = false;
		}
		else
		{
			paused = true;
		}
	}
	
	/**
	 * Tells the view to display the game over screen
	 */
	public void displayGameOver()
	{
		view.gameOver();
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
