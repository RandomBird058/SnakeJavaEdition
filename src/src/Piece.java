package src;

import java.awt.Color;

import javax.swing.JPanel;

public class Piece extends JPanel{
	
	private int row;
	private int col;
	
	/**
	 * 
	 * @param row
	 * @param col
	 */
	public Piece(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * 
	 * @return
	 */
	public int getCol()
	{
		return col;
	}
	
	/**
	 * 
	 * @param row
	 */
	public void setRow(int row)
	{
		this.row = row;
	}
	
	/**
	 * 
	 * @param col
	 */
	public void setCol(int col)
	{
		this.col = col;
	}
}
