package src;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.Timer;

public class SnakeController extends Controller{
	
	//SnakeController has-a model
	private SnakeModel model;
	
	//SnakeController has-a move timer
	private Timer moveTimer;
	
	//SnakeController has-a lastMove which stores the last direction moved
	private int lastMove;
	
	
	public SnakeController(SnakeModel model)
	{
		this.model = model;
		lastMove = KeyEvent.VK_S;
		moveTimer = new Timer(500, this);
		moveTimer.start();
	}
	
	public void stopMoveTimer()
	{
		moveTimer.stop();
	}

	/**
	 * Pressing the "return" button on the game over screen tells the model to execute closeWindow
	 * The timer triggering tells the model to move
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//If it is a button, it is the return button and so should close the model
		if(e.getSource() instanceof JButton)
		{
			model.closeWindow();
		}
		
		//If it is the move timer, should move
		if(e.getSource() == moveTimer)
		{
			//Move the snake in the last direction moved
			if(lastMove == KeyEvent.VK_W)
			{
				model.moveUp();
			}
			if(lastMove == KeyEvent.VK_S)
			{
				model.moveDown();
			}
			if(lastMove == KeyEvent.VK_D)
			{
				model.moveRight();
			}
			if(lastMove == KeyEvent.VK_A)
			{
				model.moveLeft();
			}
		}
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
			
			//Reset the timer
			moveTimer.restart();
			//Change last move to up
			lastMove = KeyEvent.VK_W;
		}
		
		//Down
		if(input == KeyEvent.VK_S || input == KeyEvent.VK_DOWN)
		{
			model.moveDown();
			
			//Reset the timer
			moveTimer.restart();
			//Change last move to down
			lastMove = KeyEvent.VK_S;
		}
		
		//Left
		if(input == KeyEvent.VK_A || input == KeyEvent.VK_LEFT)
		{
			model.moveLeft();
			
			//Reset the timer
			moveTimer.restart();
			//Change last move to left
			lastMove = KeyEvent.VK_A;
		}
		
		//Right
		if(input == KeyEvent.VK_D || input == KeyEvent.VK_RIGHT)
		{
			model.moveRight();
			
			//Reset the timer
			moveTimer.restart();
			//Change last move to right
			lastMove = KeyEvent.VK_D;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
