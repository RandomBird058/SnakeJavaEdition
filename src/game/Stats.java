package game;

/**
* @author Dominic Ricci
* @otherContributors: CISC 191 Class, Prof. Alex Chow
* @version 1.0
* @see
*      
* Responsibilities of class: 	Write to the stats file based on models
* 								Read the stats file to the models
*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Stats {
	
	//The file format is: ([goals],[games])
	private File file;

	/**
	 * Instantiate the file object
	 */
	public Stats()
	{
		//If no file exists, will create the file at that location.
		file = new File("stats.dat");
	}
	
	/**
	 * Reads the .dat file and returns a array with the data inside
	 * @return The value of games stored in the file
	 */
	public int[] readData()
	{
		//Declare array to hold data from file ([goals],[games])
		int[] data = new int[2];
		
		//Try to create data input stream attached to file
		try(DataInputStream input = new DataInputStream(new FileInputStream(file)))
		{
			//Fill the data array with ints read from file
			for(int i = 0; i < 2; i++)
			{
				data[i] = input.read();
			}
			return data;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//If this code runs, the try was unsuccessful
		data[0] = -1;
		data[1] = -1;
		return data;
	}
	
	/**
	 * Writes integers from a data array to the .dat file
	 * @param data takes in a data array to overwrite what is currently in the file
	 */
	public void writeData(int[] data)
	{
		//Try to create data input stream attached to file
		try(DataOutputStream output = new DataOutputStream(new FileOutputStream(file)))
		{
			//Write ints from data array to the file ([goals],[games])
			for(int i = 0; i < 2; i++)
			{
				output.write(data[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}