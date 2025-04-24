package src;

public class Model {
	
	/**
	 * Constructor
	 */
	public Model() {}
	
	/**
	 * Creates a menu window by creating the view which then creates and controller/model
	 */
	public void createMenuWindow()
	{
		new MenuView();
	}
	
	/**
	 * Creates a snake window by creating the view which then creates and controller/model
	 */
	public void createSnakeWindow()
	{
		new SnakeView();
	}

}
