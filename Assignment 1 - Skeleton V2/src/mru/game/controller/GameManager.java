package mru.game.controller;

import java.util.ArrayList;

import mru.game.model.Player;

import mru.game.view.AppMenu;

import java.io.*;

/**
 * This class Manages the loading and saving of the data as well as calls to run to start the game
 * @author Haseeb
 * @author Lorenzo
 * @author Alex
 */

public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
	
	
	
	/**
	 * This ArrayList Holds a list of Player Objects saved in the database
	 */
	ArrayList<Player> playersArrayList;
	
	AppMenu menu;
	
	/**
	 * This Final Variable holds the path for to the database
	 */
	final String PLAYER_DATABASE_FILE = "res/CasinoInfo.txt";
	
	/**
	 * This constructor initiates the AppMenu and the ArrayList and calls methods to load the saved data and start the game
	 */
	public GameManager() throws IOException{
		menu = new AppMenu();
		playersArrayList = new ArrayList<>();
		
		loadFile();
		
		startGame();
		
	}

	/**
	 * This Method loads the file into the playerArrayList by reading and splitting the PLAYER_DATABASE_FILE using delimiter of ","
	 * 
	 * @throws IOException if there's an issue with reading the file
	 */
	private void loadFile() throws IOException {
		File file = new File(PLAYER_DATABASE_FILE);
		if (file.exists()) {  //If the File exists, READ and Initiate the WHILE LOOP
			
			FileReader fileRead = new FileReader(file);
			BufferedReader rawPlayerData = new BufferedReader(fileRead);
			String currentLine= rawPlayerData.readLine();
			
			while(currentLine != null) {
//				System.out.println(currentLine); 
				String[] splittedLine = currentLine.split(",");
				Player new_player= new Player(splittedLine[0].toLowerCase(),Integer.parseInt(splittedLine[1]),Integer.parseInt(splittedLine[2]));
				//System.out.println(new_player.toString());// temporary to test if its in the player object
				playersArrayList.add(new_player); //Add new player into the Player arraylist so the new object gets saved and not lost
				//secondArrayList.add(new-player(;
				currentLine= rawPlayerData.readLine();
			}
			rawPlayerData.close();
		}
		
		
	}
	
	/**
	 * This Method Starts the game and calls for to prompt the main menu that loops until the user chooses to exit
	 * 
	 * @throws IOException if there's an issue with reading the file
	 */
	private void startGame() throws IOException {
		
		while(true) {
			
			String userInput= menu.showMainMenu(); //this shows menu  and validates  the input in the menu class
			
			switch (userInput) {
				case "p":
					Player currentUser = askUserName();
					playGame(currentUser);
					break;
					
					
				case "s":
					searchPlayerMenu();
					
					break;
				case "e":
					save();
					return; //exit and stops the code
				
				default:
					menu.showInputErrorMessage();
			}
		}
	}
	
	/**
	 * This Method calls a sub menu that allows the user to choose a way to search for a player
	 * 
	 */
	private void searchPlayerMenu() {
		String userInputSearch = menu.showSearchMenu(); //this shows search menu  and validates  the input in the menu class
		switch (userInputSearch) {
			case "t":
				searchTop();
				break;
			
			case "n": 
				searchForName();
				break;
			
			case "b":
				return; //
				
			default:
				menu.showInputErrorMessage();
		
		}





	}
	
	/**
	 * This method calls to validate a searched name, if the name is valid then a method is called that displays the player information, 
	 * and if the name is invalid it calls a method that displays an invalid message
	 * 
	 */
	private void searchForName() {
		Player playerInfo = searchNameValidation(menu.showAskName()); // showAskName() prompts a menu and asks the user to enter a name, then returns user input string data
		
		if (playerInfo != null) {
			menu.showPlayerInfo(playerInfo);
		}
		else {
			menu.showPlayerNotFound();
		}
	}
	
	
	
	/**
	 * This method calls to asks the name of the current player, if the current player is a returning player then it will call a method that greets 
	 * the player. If the current player is a new player, then it will create a new player object and add it into the playerArrayList
	 * 
	 * @return returns the new or returning player object
	 */
	private Player askUserName() {
		//logic for verfying old or new user goes here
		String userString = menu.showAskUserName();
		Player user = searchNameValidation(userString); 
		
		/*
		 * After searching name,
		 * If searchNameValidation() does return an existing player then return that existing player
		 * If not create a new player and  add to array
		 * then return that new player
		 */
		
		if (user != null) { // if user exists
			menu.showWelcomeOldUser(user);
			return user;
		} else {
			Player newUser = createNewUser(userString);
			playersArrayList.add(newUser);
			menu.showWelcomeNewUser(newUser);
			return newUser;
		}
	}
	
	/**
	 * This method creates a new Player object by initializing a new Player object with the initial balance and win value
	 * 
	 * @return returns the new player object
	 * @param newPlayerName the Players name that was not found in the database
	 */
	private  Player createNewUser(String newPlayerName) {
		int initialBal = 100;
		int initialWin = 0;
		return new Player(newPlayerName,initialBal, initialWin);
		
		
	}

	/**
	 * This method calls the........................................................
	 * 
	 * @return the player object after the game is played out
	 * @param userName the current player
	 */
	private Player playGame(Player userName) {
		// TODO logic for playing the game
		//acccepst the player object
		return null;
		
		
	}

	/**
	 * This method validate a players name by using a for loop to compare if the playerName is the same as any 
	 * of the name in the playersArrayList
	 * 
	 * @return returns the Player object found, or if the player is not found in the ArrayList then returns null
	 * @param playerName any player name as long as its a string
	 */
	private Player searchNameValidation(String playerName) {
		/*
		 * when this method is given a name,
		 * compare the name with each player name in the ArrayList,
		 * If a given name is the same as the array list, then return that Player object
		 * If not, then return null
		 * 
		 * This method can be reused, when searching for any name
		 */
		
		for (Player p: playersArrayList) {
			if (playerName.equals(p.getName())) {
				return p;
			
			}
	
		}
		return null;
	}

	/**
	 * This method sorts the player with the most number of wins in descending order and calls another method to display it
	 * 
	 */
	private void searchTop() {
		// TODO logic to search for the top
		
		//System.out.println(playersArrayList.get(2).getNumOfWins());
		//SOURCE: https://www.w3schools.com/java/java_advanced_sorting.asp
		playersArrayList.sort(null);
		
		menu.showSearchTop(playersArrayList);
		menu.enterToContinue();
		
		
	}
	
	/**
	 * This method saves the data of the players after playing the game into the database text file
	 * and formats it as "name,balance,numberOfWins"
	 * 
	 * @throws IOException if there's an issue with writing to the PLAYER_DATABASE_FILE
	 */
	private void save() throws IOException {
		// TODO Auto-generated method stub
		FileWriter filewriter = new FileWriter(PLAYER_DATABASE_FILE);
		PrintWriter outputFile = new PrintWriter(filewriter);
		
		menu.showSaveProgress();
		
		for (Player p: playersArrayList) { // puts all the players in the list into the outputfile/database
			outputFile.println(p.getName() + "," + p.getBalance() + "," + p.getNumOfWins());
		}
		outputFile.close();
	}

}
