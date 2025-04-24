package src;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MenuController extends Controller {
	
	//
	private MenuModel model;
	
	/**
	 * 
	 * @param model
	 */
	public MenuController(MenuModel model)
	{
		this.model = model;
	}

	/**
	 * The only button on the menu screen is the start button. So when this method is called, start the game.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		model.startGame();
	}

	/**
	 * When the enter key or space key is pressed, start the game.
	 * TODO: This doesn't work. When you press enter nothing happens
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_ENTER)
		{
			model.startGame();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
