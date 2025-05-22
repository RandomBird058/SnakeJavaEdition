package game;

//MenuModel is-a Model
public class MenuModel extends Model{
	
	//MenuModel Has-a view
	private MenuView view;
	//MenuModel Has-a stats class
	private Stats stats;
	//MenuModel Has-a array holding data from stats
	private int[] data;

	/**
	 * Call constructor of Model, Instantiate view, stats and data array
	 * @param view The view to manipulate
	 */
	public MenuModel(MenuView view) 
	{
		super();
		this.view = view;
		stats = new Stats();
		//Format of stats file: ([goals],[games])
		data = stats.readData();
	}
	
	/**
	 * Create a snake window, close the view window and increment games played in file
	 */
	public void startGame()
	{
		//Create a snake window
		createSnakeWindow();
		
		//Increment the games played and then write it to file ([goals],[games])
		data[1] ++;
		stats.writeData(data);
		
		//Close the current view
		view.close();
	}
	
	/**
	 * Takes the goals int stored in the data array
	 * Inserts it into a string which is returned
	 * @return The message to be displayed on the view
	 */
	public String getGoalsMessage()
	{
		//Goals is first in array ([goals],[games])
		//If goals is not 0:
		if(data[0] != 0)
		{
			return "Goals eaten: " + data[0];
		}
		//If goals is 0:
		return "Eat Goals to get score!";
	}
	
	/**
	 * Takes the games int stored in the data array
	 * Inserts it into a string which is returned
	 * @return The message to be displayed on the view
	 */
	public String getGamesMessage()
	{
		//Games is second in array ([goals],[games])
		//If games is not 0:
		if(data[1] != 0)
		{
			return "Games played: " + data[1];
		}
		//If games is 0:
		return "Press Start to play!";
	}

}
