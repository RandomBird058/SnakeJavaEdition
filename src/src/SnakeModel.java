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
		snakeList.addLast(new Piece(row, col, SNAKE_COLOR));
		
		//Remove the panel at the specified coordinates and then add the snake in its place
		view.removeComponent(row, col);
		view.addPiece(row, col, snakeList.getLast());
		
	}
	
	private void swapFrontBack()
	{
		//Add the subject to the front and remove it from the back of the list
		snakeList.addFirst(snakeList.getLast());
		snakeList.removeLast();
	}
	
	/**
	 * TODO: Rethink this whole thing mane
	 */
	public void moveUp()
	{
		//Reference is the piece at the front
		Piece reference = snakeList.getFirst();
		//Subject is the piece to be moved
		Piece subject = snakeList.getLast();
		
		//Test
		System.out.println("(Before swap) Reference coordinates: " + reference.getRow() + ", Subject: " + subject.getRow());
		
		//Swap positions of first and last piece in list
		swapFrontBack();
		
		//Test
		System.out.println("(After swap) Reference coordinates: " + reference.getRow() + ", Subject: " + subject.getRow());
		
		//Remove the subject from the grid visually
		view.removeComponent(subject.getRow(), subject.getCol());
		
		//Change the stored coordinate of the subject one row up
		subject.setRow(reference.getRow() - 1);
		
		//Add the subject to the grid in its new place
		view.addPiece(subject.getRow(), subject.getCol(), subject);
		
		//Test that the list is the same as the subject
		System.out.println("Test subject = first list: Subject: " + subject.getRow() + ", FirstList: " + snakeList.getFirst().getRow());
	}
	
	public void moveDown()
	{
		
	}
	
	public void moveLeft()
	{
		
	}
	
	public void moveRight()
	{
		
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
