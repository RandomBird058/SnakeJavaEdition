package Test;

import src.*;

//This class contains main and holds tests.
public class TestAndExecute{
	
	Model model;

	public static void main(String[] args) {
		TestAndExecute test = new TestAndExecute();
		
		test.testGameOver();
	}
	
	/**
	 * Constructor
	 */
	public TestAndExecute()
	{
		model = new Model();
	}
	
	/**
	 * Executes the program as it should be executed
	 */
	private void execute()
	{
		model.createMenuWindow();
	}
	
	/**
	 * Opens a menu window
	 */
	private void testMenuWindow()
	{
		model.createMenuWindow();
		System.out.println("Successfully opened");
	}
	
	/**
	 * Opens a snake window
	 */
	private void testSnakeWindow()
	{
		model.createSnakeWindow();
		System.out.println("Successfully opened");
	}
	
	/**
	 * Test game over screen
	 */
	private void testGameOver()
	{
		SnakeView snakeView = new SnakeView();
		snakeView.getModel().displayGameOver();
	}

}
