package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Font: Public Pixel - https://www.fontspace.com/public-pixel-font-f72305
//MenuView is-a View
public class MenuView extends View {
	
	//The scalar for how big the menu window will be
	private static final int WINDOW_SCALAR = 2;
	
	//The height and width that the window will be
	private final int windowHeight, windowWidth;
	
	//MenuView has-a model
	private MenuModel model;
	
	//MenuView has-a controller
	private MenuController controller;
	
	//MenuView has-a screen size dimension
	private Dimension screenSize;
	
	//MenuView has-a font
	private Font publicPixel;
	
	//MenuView has-many panels
	private JPanel titlePanel, statsPanel, startPanel;
	
	//MenuView has-many labels
	private JLabel titleLabel, timesPlayedLabel, goalsEatenLabel;
	
	//MenuView has-a button
	private JButton startButton;

	/**
	 * Call View constructor, Instantiate model/controller, build window
	 */
	public MenuView() {
		super();
		
		//Instantiate model and controller (Window = view + model + controller)
		model = new MenuModel(this);
		controller = new MenuController(model);
		
		//Get screen size using java toolkit
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Set the width and height to a value determined by the screen dimension divided by a scalar
		windowWidth = ((int)screenSize.getWidth() / WINDOW_SCALAR);
		windowHeight = ((int)screenSize.getHeight() / WINDOW_SCALAR);
		
		//Set frame dimensions
		setSize(windowWidth, windowHeight);
		
		//Instantiate the font in try-catch
		//TODO: The font isn't changing :( see View
		try {
			publicPixel = Font.createFont(Font.TRUETYPE_FONT, FONT_FILE);
		} catch (FontFormatException | IOException e) {
			System.out.println("Font not found");
			e.printStackTrace();
		}
		
		//Methods create the components of the frame
		createTitle();
		createStart();
		createStats();
		
		//TODO: Doesn't work. see MenuController
		//Window listens for keyboard input
		addKeyListener(controller);
		
		//TODO: ^^^
		//Take focus away from the button
		requestFocus();
		
		//Window is visible
		setVisible(true);
	}
	
	/**
	 * Instantiates the components for the title and adds to the frame north
	 */
	private void createTitle()
	{
		//Instantiate panel to hold title label and change its properties
		titlePanel = new JPanel();
		
		//Instantiate label to hold the title and change its properties
		titleLabel = new JLabel("Snake Java Edition");
		
		//Color the components
		titlePanel.setBackground(View.BG_COLOR);
		titleLabel.setForeground(View.FG_COLOR);
		
		//TODO: Font doesn't. See View
		//Resize the font. Make a new font with the same attributes as Og font but size proportional to window width
		titleLabel.setFont(new Font(publicPixel.getName(), publicPixel.getStyle(), (int)(windowWidth/10)));

		//Add label to the panel and panel to the frame
		titlePanel.add(titleLabel);
		add(titlePanel, BorderLayout.NORTH);
	}
	
	/**
	 * Instantiates the components for start button and adds to the frame center
	 */
	private void createStart()
	{
		//Instantiate the panel to hold start button and change its properties
		startPanel = new JPanel();
		
		//Instantiate the button to start the game and change its properties
		startButton = new JButton("START");
		
		//Add an action listener to the button
		startButton.addActionListener(controller);
		
		//Color components
		startPanel.setBackground(BG_COLOR);
		startButton.setForeground(FG_COLOR);
		startButton.setBackground(BG_COLOR);
		
		//Add a border to the button
		startButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		//Get rid of the ugly line when the button is focused
		startButton.setFocusPainted(false);
	
		//TODO: Font doesn't work. See View
		//Resize the font. Make a new font with the same attributes as Og font but size proportional to window width
		startButton.setFont(new Font(publicPixel.getName(), publicPixel.getStyle(), (int)(windowWidth/20)));
		
		//Add button to the panel and panel to the frame
		startPanel.add(startButton);
		add(startPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Instantiates the components for stats area and adds to the frame south
	 */
	private void createStats()
	{
		//Instantiate the panel to hold the stats labels
		statsPanel = new JPanel();
		
		//Instantiate the labels to display the stats
		timesPlayedLabel = new JLabel(model.getGamesMessage());
		goalsEatenLabel = new JLabel(model.getGoalsMessage());
		
		//Color components
		timesPlayedLabel.setBackground(FG_COLOR);
		goalsEatenLabel.setBackground(FG_COLOR);
		statsPanel.setBackground(FG_COLOR);
		
		//TODO: Font doesn't work. See View
		//Set font of labels
		timesPlayedLabel.setFont(new Font(publicPixel.getName(), publicPixel.getStyle(), (int)(windowWidth/30)));
		goalsEatenLabel.setFont(new Font(publicPixel.getName(), publicPixel.getStyle(), (int)(windowWidth/30)));
		
		//Add labels to the panel and panel to the frame
		statsPanel.add(timesPlayedLabel);
		statsPanel.add(goalsEatenLabel);
		add(statsPanel, BorderLayout.SOUTH);
	}
	

}
