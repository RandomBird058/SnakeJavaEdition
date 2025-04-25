package src;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SnakeController extends Controller{
	
	private SnakeModel model;
	
	/**
	 * 
	 * @param model
	 */
	public SnakeController(SnakeModel model)
	{
		this.model = model;
	}

	/**
	 * Pressing the "return" button on the game over screen tells the model to execute ___
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		model.closeWindow();
	}

	/**
	 * When WASD or arrow keys pressed, tell model to move in that direction
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int input = e.getKeyCode();
		
		//Up
		if(input == KeyEvent.VK_W || input == KeyEvent.VK_UP)
		{
			model.moveUp();
			System.out.println("Up");
		}
		
		//Down
		if(input == KeyEvent.VK_S || input == KeyEvent.VK_DOWN)
		{
			model.moveDown();
			System.out.println("Down");
		}
		
		//Left
		if(input == KeyEvent.VK_A || input == KeyEvent.VK_LEFT)
		{
			model.moveLeft();
			System.out.println("Left");

		}
		
		//Right
		if(input == KeyEvent.VK_D || input == KeyEvent.VK_RIGHT)
		{
			model.moveRight();
			System.out.println("Right");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
