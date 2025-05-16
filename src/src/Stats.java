package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Stats {
	//The file format is: (goals eaten, games played)
	private File statsFile;
	private Scanner fileScnr;
	
	public Stats()
	{
		fileScnr = null;
		statsFile = new File("Stats.txt");
	}
	
	public String getGamesPlayed()
	{
		String games;
		if(checkFormat()) 
		{
			try {
				instantiateScanner();
				games = fileScnr.next();
			} catch (FileNotFoundException e) {
				games = null;
				e.printStackTrace();
			}
			finally
			{
				if(fileScnr != null)
				{
					fileScnr.close();
				}
			}
			return games;
		}
		else {
			return null;
		}
	}
	
	public String getGoalsEaten()
	{
		String games;
		if(checkFormat()) 
		{
			try {
				instantiateScanner();
				fileScnr.next();
				games = fileScnr.next();
			} catch (FileNotFoundException e) {
				games = null;
				e.printStackTrace();
			}
			finally
			{
				if(fileScnr != null)
				{
					fileScnr.close();
				}
			}
			return games;
		}
		else {
			return null;
		}
	}
	
	public void writeGamesPlayed(String games)
	{
		checkFormat();
	}
	
	public void writeGoalsEaten(String goals)
	{
		checkFormat();
		
	}
	
	/**
	 * Checks if the file is in the correct format. If not, it overrides the file to the correct format
	 */
	public boolean checkFormat()
	{
		//Check the scanner can be instantiated
		try {
			instantiateScanner();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		//Check if there are at least two entries. Having more than 2 is irrelevant
		for(int i = 0; i < 2; i++)
		{
			//If scanner doesn't have next, false
			if(!fileScnr.hasNext())
			{
				return false;
			}
			fileScnr.next();
		}
		//Close scanner and return true
		fileScnr.close();
		return true;
	}
	
	/**
	 * Attempts to initiate the scanner
	 * @throws FileNotFoundException
	 */
	private void instantiateScanner() throws FileNotFoundException
	{
		statsFile = new File("Stats.txt");
		fileScnr = new Scanner(statsFile);
	}
}
