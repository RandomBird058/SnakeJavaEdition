package main;

//MenuModel is-a Model
public class MenuModel extends Model{
	
	//Has-a view
	private MenuView view;
	//Has-a stats class
	private Stats stats;
	//Has-a array holding data from stats
	private int[] data;

	/**
	 * Call constructor of Model, Instantiate view
	 * @param view The view to manipulate
	 */
	public MenuModel(MenuView view) 
	{
		super();
		this.view = view;
		stats = new Stats();
		//([goals],[games])
		data = stats.readData();
	}
	
	/**
	 * Creates a snake window and closes the view window
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
	 * 
	 * @return
	 */
	public String getGoalsMessage()
	{
		//Goals is first in array ([goals],[games])
		if(data[0] != 0)
		{
			return "Goals eaten: " + data[0];
		}
		return "Eat Goals to get score!";
	}
	
	public String getGamesMessage()
	{
		//Games is second in array ([goals],[games])
		if(data[1] != 0)
		{
			return "Games played: " + data[1];
		}
		return "Press Start to play!";
	}

}
