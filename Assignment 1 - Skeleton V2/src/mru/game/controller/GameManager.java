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

   public void mainMenu() {
	while (true) {
        displayGameMenu();

        // Gets user input and also allows for it to not be case sensitive
        String choice = scanner.nextLine().trim().toLowerCase();

        // Process users choices
        switch (choice) {
            case "p":
                System.out.print("Please enter your name to play: ");
                String playerName = scanner.nextLine().trim();
                Player currentPlayer = searchPlayerByName(playerName);
                if (currentPlayer == null) {
                    // If the player is not found, create a new player with a default balance
                    System.out.println("Player not found. Creating a new profile.");
                    int defaultBalance = 100; // Set a default balance for new players
                    currentPlayer = new Player(playerName, defaultBalance, 0);
                    players.add(currentPlayer); // Add the new player to the list
                    savePlayerData(); // Save the new player data
                }
                playGame(currentPlayer); // Now play the game with the found or newly created player
                break;
            case "s":
                showSearchMenu();
                break;
            case "e":
                exitGame();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        
    }
}

	private void loadFile() throws IOException {
		File file = new File(PLAYER_DATABASE_FILE);
		if (file.exists()) {  //If the File exists, READ and Initiate the WHILE LOOP
			
			FileReader fileRead = new FileReader(file);
			BufferedReader rawPlayerData = new BufferedReader(fileRead);
			String currentLine= rawPlayerData.readLine();
			
			while(currentLine != null) {
				System.out.println(currentLine); //Temporary
				String[] splittedLine = currentLine.split(",");
				Player new_player= new Player(splittedLine[0],splittedLine[1],Integer.parseInt(splittedLine[2]));
				//System.out.println(new_player.toString());// temporary to test if its in the player object
				playersArrayList.add(new_player); //Add new player into the Player arraylist so the new object gets saved and not lost
				//secondArrayList.add(new-player(;
				currentLine= rawPlayerData.readLine();
			}
			
		}
		
		
	}
	
	private void startGame() {
		askName();
		
		while(true) {
			
			String userInput= menu.showMainMenu(); //this shows menu  and validates  the input in the menu class
			
			switch (userInput) {
				case "1":
					playGame();
					break;
					
					
				case "2":
					searchByName();
					
					break;
				case "3":
					save();
					return; //exit and stops the code
				
				default:
					menu.showInputErrorMessage();
			}
		}
	}
	
	private void askName() {
//		menu.showAskUserName();
		//logic for verfying old or new user goes here
		
		
//		menu.showWelcomeOldU();
//		menu.showWelcomeNewU();
//		
	}
			

		
		
	private void searchByName() {
		String userInputSearch = menu.showSearchMenu(); //this shows search menu  and validates  the input in the menu class
		switch (userInputSearch) {
			case "1":
				searchTop();
				break;
			
			case "2": 
				searchName();
				break;
			
			case "3":
				return; //
				
			default:
				menu.showInputErrorMessage();
		
	}





	}

	private void playGame() {
		// TODO Auto-generated method stub
		
	}

	private void save() {
		// TODO Auto-generated method stub
		
	}

	private void searchName() {
		// TODO logic to search for name
		menu.showAskName();
		
	}

	private void searchTop() {
		// TODO logic to search for the top
//		System.out.println(playersArrayList.get(2).getNumOfWins());
		//SOURCE: https://www.w3schools.com/java/java_advanced_sorting.asp
		playersArrayList.sort(null);
		System.out.println("temporary\n" +playersArrayList); // temporary
		menu.showSearchTop();

		
		
	}
	
	
public void showSearchMenu() {
	
}


public void  savePlayerData() {
	
}

}
