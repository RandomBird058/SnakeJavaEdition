package testandexecute;

import game.*;

//This class contains main and holds tests.
public class TestAndExecute{
	
	Model model;
	Stats stats;

	public static void main(String[] args) {
		TestAndExecute test = new TestAndExecute();
		test.execute();
	}
	
	/**
	 * Constructor
	 */
	public TestAndExecute()
	{
		model = new Model();
		stats = new Stats();
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
	
	/**
	 * Test reading data from the file
	 */
	private void testReadStats()
	{
		int[] data = stats.readData();
		for(int i = 0; i < 2; i++)
		{
			System.out.println(data[i]);
		}
	}
	
	/**
	 * Test writing data to the file
	 */
	private void testWriteStats()
	{
		int[] data = {0, 0};
		stats.writeData(data);
	}
}
