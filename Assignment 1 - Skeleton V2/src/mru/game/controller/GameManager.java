package mru.game.controller;


public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */

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

	
public void showSearchMenu() {
	
}


public void  savePlayerData() {
	
}

}
