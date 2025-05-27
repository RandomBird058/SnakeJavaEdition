package game;

/**
* @author Dominic Ricci
* @otherContributors: CISC 191 Class, Prof. Alex Chow
* @version 1.0
* @see
*      
* Responsibilities of class: 	Parent class of models. 
* 								Create menu windows and snake game windows
*/

public class Model {
	
	/**
	 * Constructor
	 */
	public Model() {}
	
	/**
	 * Creates a menu window by creating the view which then creates the controller/model
	 */
	public void createMenuWindow()
	{
		new MenuView();
	}
	
	/**
	 * Creates a snake window by creating the view which then creates the controller/model
	 */
	public void createSnakeWindow()
	{
		new SnakeView();
	}

}
