package src;

//MenuModel is-a Model
public class MenuModel extends Model{
	
	//has-a view
	private MenuView view;
	private Stats stats;

	/**
	 * Call constructor of Model, Instantiate view
	 * @param view The view to manipulate
	 */
	public MenuModel(MenuView view) 
	{
		super();
		this.view = view;
		stats = new Stats();
	}
	
	/**
	 * Creates a snake window and closes the view window
	 */
	public void startGame()
	{
		createSnakeWindow();
		view.close();
	}
	
	//TODO: Eats goals / start to play messages won't appear
	public String getGoalsMessage()
	{
		if(stats.getGoalsEaten() != "0")
		{
			return "Times played: " + stats.getGoalsEaten();
		}
		return "Eat Goals!";
	}
	
	public String getGamesMessage()
	{
		if(stats.getGamesPlayed() != "0")
		{
			return "Goals eaten: " + stats.getGamesPlayed();
		}
		return "Press Start to play!";
	}

}
