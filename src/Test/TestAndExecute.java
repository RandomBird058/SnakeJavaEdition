package Test;

import src.*;

//This class contains main and holds tests.
public class TestAndExecute{
	
	Model model;
	Stats stats;

	public static void main(String[] args) {
		TestAndExecute test = new TestAndExecute();
		test.execute();
//		test.testCheckFormat();
//		test.testGetGoalsGetGames();
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
	
	private void testCheckFormat()
	{
		stats = new Stats();
		System.out.println(stats.checkFormat());
	}
	
	private void testGetGoalsGetGames()
	{
		System.out.println("Games: " + stats.getGamesPlayed() + " Goals: " + stats.getGoalsEaten());
	}

}
