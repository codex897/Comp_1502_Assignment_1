package mru.game.controller;

import java.util.ArrayList;

import mru.game.model.Player;

import mru.game.view.AppMenu;

import java.io.*;

public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
	ArrayList<Player> playersArrayList;
	AppMenu menu;
	final String PLAYER_DATABASE_FILE = "res/CasinoInfo.txt";
	
	//Constructor
	public GameManager() throws IOException{
		System.out.println("\t\tGamemaneger Check"); //TEMPORARY
		
		menu = new AppMenu();
		playersArrayList = new ArrayList<>();
		
		loadFile();
		
		startGame();
		
		
	}

	private void loadFile() throws IOException {
		File file = new File(PLAYER_DATABASE_FILE);
		if (file.exists()) {  //If the File exists, READ and Initiate the WHILE LOOP
			
			FileReader fileRead = new FileReader(file);
			BufferedReader rawPlayerData = new BufferedReader(fileRead);
			String currentLine= rawPlayerData.readLine();
			
			while(currentLine != null) {
//				System.out.println(currentLine); //Temporary
				String[] splittedLine = currentLine.split(",");
				Player new_player= new Player(splittedLine[0],Integer.parseInt(splittedLine[1]),Integer.parseInt(splittedLine[2]));
				//System.out.println(new_player.toString());// temporary to test if its in the player object
				playersArrayList.add(new_player); //Add new player into the Player arraylist so the new object gets saved and not lost
				//secondArrayList.add(new-player(;
				currentLine= rawPlayerData.readLine();
			}
			rawPlayerData.close();
		}
		
		
	}
	
	private void startGame() throws IOException {
		String userName = askUserName().getName();
		
		while(true) {
			
			String userInput= menu.showMainMenu(); //this shows menu  and validates  the input in the menu class
			
			switch (userInput) {
				case "1":
					playGame();
					break;
					
					
				case "2":
					searchPlayer();
					
					break;
				case "3":
					save();
					return; //exit and stops the code
				
				default:
					menu.showInputErrorMessage();
			}
		}
	}
		
	private void searchPlayer() {
		String userInputSearch = menu.showSearchMenu(); //this shows search menu  and validates  the input in the menu class
		switch (userInputSearch) {
			case "1":
				searchTop();
				break;
			
			case "2": 
				searchName(menu.showAskName()); // showAskName() prompts a menu and asks the user to enter a name, then returns user input string data
				break;
			
			case "3":
				return; //
				
			default:
				menu.showInputErrorMessage();
		
		}





	}
	
	
	
	
	private Player askUserName() {
		//logic for verfying old or new user goes here
		String userString = menu.showAskUserName();
		Player user = searchName(userString); 
		
		/*
		 * After searching name,
		 * If searchName() does return an existing player then return that existing player
		 * If not create a new player and  add to array
		 * then return that new player
		 */
		
		if (user != null) { // if user exists
			menu.showWelcomeOldU(user.getName());
			return user;
		} else {
			menu.showWelcomeNewU(userString);
			Player newUser = createNewUser(userString);
			playersArrayList.add(newUser);
			return newUser;
		}
	}
	

	private  Player createNewUser(String newPlayerName) {
		// TODO Auto-generated method stub
		int initialBal = 100;
		int initialWin = 0;
		return new Player(newPlayerName,initialBal, initialWin);
		
		
	}

	private void playGame() {
		// TODO Auto-generated method stub
		
	}


	private Player searchName(String playerName) {
		// TODO logic to search for name
		// menu.showAskName(); temporary
		
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
				System.out.println("\nplayer exists");
				return p;
			}
	
		}
		return null;
	}

	private void searchTop() {
		// TODO logic to search for the top
		
		//System.out.println(playersArrayList.get(2).getNumOfWins());
		//SOURCE: https://www.w3schools.com/java/java_advanced_sorting.asp
		playersArrayList.sort(null);
		
		System.out.println("temporary\n" +playersArrayList); // temporary
		menu.showSearchTop();

		
		
	}
	
	private void save() throws IOException {
		// TODO Auto-generated method stub
		FileWriter filewriter = new FileWriter(PLAYER_DATABASE_FILE);
		PrintWriter outputFile = new PrintWriter(filewriter);
		
		
		for (Player p: playersArrayList) {
			outputFile.println(p.getName() + "," + p.getId() + "," + p.getNumOfWins());
		}
		outputFile.close();
	}

}
