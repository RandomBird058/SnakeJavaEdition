package game;

import javax.swing.JPanel;

//Piece is-a JPanel
public class Piece extends JPanel{
	
	//Piece has-a row and column
	private int row, col;
	
	/**
	 * Initialize the row and column of the piece
	 * @param row of the piece
	 * @param col of the piece
	 */
	public Piece(int row, int col)
	{
		this.row = row;
		this.col = col;
	}

	/**
	 * Get the row of the piece
	 * @return The row of the piece
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * Get the column of the piece
	 * @return The column of the piece
	 */
	public int getCol()
	{
		return col;
	}
	
	/**
	 * Set the row to inputed value
	 * @param row The new row of the piece
	 */
	public void setRow(int row)
	{
		this.row = row;
	}
	
	/**
	 * Set the column to inputed value
	 * @param col The new column of the piece
	 */
	public void setCol(int col)
	{
		this.col = col;
	}
}
