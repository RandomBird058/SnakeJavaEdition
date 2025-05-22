package game;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

//MenuController is-a Controller
public class MenuController extends Controller {
	
	//Controller has-a model
	private MenuModel model;
	
	/**
	 * Instantiate model
	 * @param model
	 */
	public MenuController(MenuModel model)
	{
		this.model = model;
	}

	/**
	 * The only button on the menu screen is the start button. When this method is called, start the game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		model.startGame();
	}

	/**
	 * When the enter key or space key is pressed, start the game.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		//Get the numeric value of the key pressed
		int keyCode = e.getKeyCode();
		
		//If the key pressed is space of enter, tell model to start game
		// TODO: This doesn't work. When you press enter nothing happens
		if(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_ENTER)
		{
			model.startGame();
		}
		
	}

	//Unused
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}
