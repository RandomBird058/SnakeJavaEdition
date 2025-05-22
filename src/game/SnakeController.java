package game;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.Timer;

//SnakeController is-a Controller
public class SnakeController extends Controller{
	
	//SnakeController has-a model
	private SnakeModel model;
	
	//SnakeController has-a Timer to track time between moves
	private Timer moveTimer;
	
	//SnakeController has-a lastMove which stores the last direction moved
	private int lastMove;
	
	//Time between auto moves
	private int timerDelay;
	
	/**
	 * Instantiate the model, lastMove, timerDelay, timer and start the timer
	 * @param model The model to give commands to
	 */
	public SnakeController(SnakeModel model)
	{
		this.model = model;
		
		//Set to S key because the snake can't move down when the game starts
		lastMove = KeyEvent.VK_S;
		timerDelay = 250;
		moveTimer = new Timer(timerDelay, this);
		
		//Starts the timer
		moveTimer.start();
	}
	
	/**
	 * Stops the move timer
	 * Model doesn't have direct access to the timer so a method is needed
	 */
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
		//Stores the input as keycode
		int input = e.getKeyCode();
		
		//Up
		if(input == KeyEvent.VK_W || input == KeyEvent.VK_UP)
		{
			//If move is successful
			if(model.moveUp())
			{
				//Reset the timer
				moveTimer.restart();
				//Change last move to up
				lastMove = KeyEvent.VK_W;
			}
		}
		
		//Down
		if(input == KeyEvent.VK_S || input == KeyEvent.VK_DOWN)
		{
			//If move is successful
			if(model.moveDown())
			{
				//Reset the timer
				moveTimer.restart();
				//Change last move to down
				lastMove = KeyEvent.VK_S;
			}
		}
		
		//Left
		if(input == KeyEvent.VK_A || input == KeyEvent.VK_LEFT)
		{
			//If move is successful
			if(model.moveLeft())
			{
				//Reset the timer
				moveTimer.restart();
				//Change last move to left
				lastMove = KeyEvent.VK_A;
			}
		}
		
		//Right
		if(input == KeyEvent.VK_D || input == KeyEvent.VK_RIGHT)
		{
			//If move is successful
			if(model.moveRight())
			{
				//Reset the timer
				moveTimer.restart();
				//Change last move to right
				lastMove = KeyEvent.VK_D;
			}
		}
		
		//If escape, toggle the paused state
		if(input == KeyEvent.VK_ESCAPE)
		{
			model.togglePaused();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
