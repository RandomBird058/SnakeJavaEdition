package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Controller is-a Action Listener, KeyListener
public abstract class Controller implements ActionListener, KeyListener{
	
	//Action listener method
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	//Key listener methods
	@Override
	public abstract void keyPressed(KeyEvent e);
	@Override
	public abstract void keyTyped(KeyEvent e);
	@Override
	public abstract void keyReleased(KeyEvent e);


}
