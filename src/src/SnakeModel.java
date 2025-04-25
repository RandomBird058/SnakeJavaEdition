package src;

import java.awt.Color;
import java.util.LinkedList;

public class SnakeModel extends Model {
	
	//Color the snake pieces will be
	private static final Color SNAKE_COLOR = Color.GREEN;
	
	//
	SnakeView view;
	
	//Instantiate list to store snake piece in order
	private LinkedList<Piece> snakeList;
	
	
	public SnakeModel(SnakeView view)
	{
		super();
		this.view = view;
		
		//List that will hold all of the pieces that comprise the snake
		snakeList = new LinkedList<>();
		
	}
	
	public void addSnakePiece(int row, int col)
	{
		//Add a new piece to the list
		snakeList.addLast(new Piece(row, col));
		
		//Make the snake piece visible on the grid
		view.setSnakeColor(snakeList.getLast().getRow(), snakeList.getLast().getCol());
	}
	
	/**
	 * Move the piece at the back of the list to the front
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
		view.setSnakeColor(snakeList.getFirst().getRow(), snakeList.getFirst().getCol());
		
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
