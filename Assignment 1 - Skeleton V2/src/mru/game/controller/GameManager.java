package mru.game.controller;

import java.util.ArrayList;

import mru.game.model.Player;

import mru.game.view.AppMenu;

public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
	ArrayList<Player> players;
	AppMenu menu;
	
	//Constructor
	public GameManager(){
		System.out.println("\t\tGamemaneger Check");
		
		menu = new AppMenu();
		players = new ArrayList<>();
		
		
		startApp();
		
		
	}

	//methods start here
	
	private void startApp() {
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
		menu.showSearchTop();
		
	}
	
	

}
