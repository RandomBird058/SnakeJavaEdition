package src;

//MenuModel is-a Model
public class MenuModel extends Model{
	
	//has-a view
	MenuView view;

	/**
	 * Call constructor of Model, Instantiate view
	 * @param view The view to manipulate
	 */
	public MenuModel(MenuView view) 
	{
		super();
		this.view = view;
	}
	
	/**
	 * Creates a snake window and closes the view window
	 */
	public void startGame()
	{
		createSnakeWindow();
		view.close();
	}

}
