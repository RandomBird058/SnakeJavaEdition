package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Stats {
	//The file format is: (goals eaten games played)
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
		//If format is incorrect, correct the format and call the method again
		else {
			correctFormat();
			return getGamesPlayed();
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
		//If format is incorrect, correct the format and call the method again
		else {
			correctFormat();
			return getGoalsEaten();
		}
	}
	
	public void incrementGoalsEaten()
	{
		if(checkFormat())
		{
			int goalsEaten = Integer.parseInt(getGoalsEaten()) + 1;
			int gamesPlayed = Integer.parseInt(getGamesPlayed());
			
			System.out.println("IncrGoals goals: " + goalsEaten);
			System.out.println("IncrGoals games: " + gamesPlayed);
			
			try (FileWriter writer = new FileWriter(statsFile)){
				writer.write(goalsEaten + " " + gamesPlayed);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//If format is incorrect, correct the format and call the method again
		else {
			correctFormat();
			incrementGoalsEaten();
		}
	}
	
	public void incrementGamesPlayed()
	{
		if(checkFormat())
		{
			int goalsEaten = Integer.parseInt(getGoalsEaten());
			int gamesPlayed = Integer.parseInt(getGamesPlayed()) + 1;
			
			System.out.println("IncrGames goals: " + goalsEaten);
			System.out.println("IncrGames games: " + gamesPlayed);
			
			try (FileWriter writer = new FileWriter(statsFile)){
				writer.write(goalsEaten + " " + gamesPlayed);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//If format is incorrect, correct the format and call the method again
		else {
			correctFormat();
			incrementGamesPlayed();
		}
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
		int integer;
		//Check if there are at least two entries. Having more than 2 is irrelevant
		for(int i = 0; i < 2; i++)
		{
			//If scanner doesn't have next, false
			if(!fileScnr.hasNext())
			{
				return false;
			}
			
			//Check if the value scanned is an integer. if not, return false.
			try {
				integer = Integer.parseInt(fileScnr.next());
			}
			catch(NumberFormatException e)
			{
				return false;
			}
		}
		//Close scanner and return true
		fileScnr.close();
		return true;
	}
	
	//Called if format is not correct. Corrects the format.
	private void correctFormat()
	{
		//TODO: remove
		System.out.println("File contents corrected");
		try (FileWriter writer = new FileWriter(statsFile)){
			writer.write(0 + " " + 0);
//			//TODO: remove
//			System.out.println("File contents corrected to: " + getGoalsEaten() + getGamesPlayed());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
