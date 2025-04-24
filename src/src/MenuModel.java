package src;

public class MenuModel extends Model{
	
	MenuView view;

	public MenuModel(MenuView view) 
	{
		super();
		this.view = view;
	}
	
	public void startGame()
	{
		createSnakeWindow();
		view.close();
	}

}
