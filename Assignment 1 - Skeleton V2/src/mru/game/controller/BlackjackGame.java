package mru.game.controller;

import java.io.InterruptedIOException;
import java.util.List;
import java.util.Scanner;

import mru.game.model.Player;

import mru.game.view.AppMenu;
import mru.game.view.gameplayMenu;

public class BlackjackGame {

	/**
	 * In this class you implement the game
	 * You should use CardDeck class here
	 * See the instructions for the game rules
	 */
	
	private Player player;
	private Dealer dealer;
	private int bettingAmount;
	private CardDeck deck;
	
	private static final int MIN_BET = 2;
	private static final boolean True = false;
	
	Scanner input ;
	private AppMenu menu;
	private gameplayMenu gameMenu;
	
	public BlackjackGame(Player player) { //Constructor
		input = new Scanner(System.in);
		menu = new AppMenu();
		gameMenu= new gameplayMenu();
		
		this.bettingAmount = bettingAmount;
		this.player = player;
		this.dealer = new Dealer();
		this.deck = new CardDeck();
		this.deck.shuffleDeck();
		
		
		startGame();
	}
	
	// Game Flow
	
	public void startGame(){
		// Checks player balance for eligibility before starting any rounds.
		checkPlayerBalance();
		
		boolean keepPlaying = true;
		
		Scanner input = new Scanner(System.in);
		
		//Thiss is the main entry loop, it will loop until the player quits or player balance = 0
		
		while (keepPlaying && player.getBalance() >= MIN_BET) { //while the player has at least the minimum betting requirement
			initializeRound(); // Calls the initializeRound method (resets,clears, re shuffles)
			bettingAmount = promptBet(input); // Ask player for bet amount.
			dealInitialCards();
	
			playerTurn(input);
			//The rest of the methods are needed still, like dealingintialcards, playerturn, delearturn, etc.

			
			
			
			keepPlaying = askToKeepGoing(input);
		}
		
		
	}
	
	
	
	

	//Setup Methods
	
	private void initializeRound() {
		//Method: Resets everything for a new game.
		//Clears both hands(Player and Dealer).
		//Reshuffles the deck.
		
		player.emptyHand();//Clear cards from players hand, gives both dealer and player new card deck.
		dealer.emptyHand();//Clear cards from players hand
		
		//Create and shuffle new card deck.
		deck = new CardDeck();
		deck.shuffleDeck();
		
		System.out.println("\n--- New Round ---");
	}
	/**
	 * Deals initial two cards to both player and dealer
	 * Dealer gets one face up and one face down
	 * Player gets both face up
	 */
	private void dealInitialCards(){
		//First Player Card
		player.addCardToHand(deck.dealCard());
		//First Dealer Card
		dealer.addCardToHand(deck.dealCard());
		//Second Player Card
		player.addCardToHand(deck.dealCard());
		//Second Dealer Card
		dealer.addCardToHand(deck.dealCard());
		
		//for testing
//		player.addCardToHand(new Card(2, "heart"));
//		player.addCardToHand(new Card(10, "heart"));
//		player.addCardToHand(new Card(12, "heart"));
		
	}
	
	//Betting / Checking player balance
	
	private int promptBet(Scanner input) { 
		// Method: Ask player for bet amount, and makes sure there's enough balance, 
		// and bet amount >= 2, and makes sure it follows game rules
		double maxBet = player.getBalance();
		int bet = 0;
		boolean valid = false;
		
		while (!valid) {
			System.out.println("Enter your bet (minimum $" + MIN_BET + "): ");
			try {
				bettingAmount = Integer.parseInt(input.nextLine());
				
				if (bettingAmount < MIN_BET) {
					System.out.println("Your bet must be at least $" + MIN_BET + ".");
				} else if (bettingAmount > maxBet) {
					System.out.println("You cannot bet more than your balance ($" + maxBet + ").");
				} else {
					valid = true;
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
		
		System.out.println("Your bet $" + bettingAmount + ". Good lyck!");
		return bettingAmount;
	}
	
	private boolean askToKeepGoing(Scanner input) {
		System.out.println("Would you like to keep playing (y/n)? ");
		String choice = input.nextLine().toLowerCase().trim();
		return choice.equals("y");
	}
	
	private int updateBalanceAfterRound(int result) { // Method: Adjusts player's balance based on game outcome.
		return result;
	}
	
	private void checkPlayerBalance() { //This method will make sure the player never plays with $0.
		double balance = player.getBalance();
		
		if(balance >= 2) {
			System.out.println("You have enough to play $" + balance + "!");
		} else {
			System.out.println("You don't have enough to play $" + balance + ".");
		}
		
		
	}
	
	
	//Gameplay
	
	private void playerTurn(Scanner input) {
		boolean keepPlaying = true;

		while(keepPlaying) {
			System.out.println("\nYour Hand: " + player.getHand()); // temporary display
			int handValue = calculateHandValue(player.getHand());
			System.out.println("Your Total Hand Value: " + handValue); // temporary display
			
			tableDisplay(player.getHand(), dealer.getHand());  //calls to display the cards on screen
			
			if (isBust(player.getHand())) { 		//check if player is bust
				System.out.println("You Busted!"); // temporary display
				break;
			}
			
			if(isBlackJack(player.getHand())) {  //check if player is bust
				System.out.println("You Have BLACKJACK!"); // temporary display
				break; 
			}
	
			keepPlaying = playerHitStandChoice(); // calls to prompt hit or stand choice and returns falls if they choose to stand
		}
	}
	
	private void tableDisplay(List<Card> playerHandList, List<Card> dealerHandList) { 
		String playerCard = "";		
		String dealerCard = "";	
		int maxHandSize = dealerHandList.size();		
		
		gameMenu.showSearchTableLabel();				//first call to display the labels

		if (playerHandList.size() > dealerHandList.size()){				//if the playershand more than dealerHandList.size()
			maxHandSize = playerHandList.size();		//set the maxHandSize to the one with the most amount of cards
		}
		
		for(int i = 0; i < maxHandSize; i++) {		//the loop makes a row for the table and makes as much as the maxHandSize
			
			if (playerHandList.size() <= i) {		//check if the row being created is more than the the size of the players hand
				playerCard = "";						//if so, then display a blank in the row for the player collumn
			}
			else {					
				playerCard = playerHandList.get(i).toString();		//else, turn the Card object to a string
			}
			
			if (dealerHandList.size() <= i) {		//check if the row being created is more than the the size of the players hand
				dealerCard = "";						//if so, then display a blank in the row for the player collumn
			}
			else {					
				dealerCard = dealerHandList.get(i).toString();		//else, turn the Card object to a string
			}
			gameMenu.showTableCard(playerCard, dealerCard);			
		}
//		menu.showPlayerData(name, n);
			
	
		
	}

	private boolean playerHitStandChoice() {
		boolean keepPlaying = true; 
		while(true) {
			System.out.println("Would you like to \n(1) Hit \n(2) Stand ");  // temporary display
			String choice = input.nextLine();
			
			switch (choice) {
				case "1":
					player.addCardToHand(deck.dealCard());
					System.out.println("You drew a new card.");  // temporary display
					break;
				
				case "2":
					System.out.println("You chose to stand."); // temporary display
					keepPlaying = false;
					break;
				
				default :
					menu.showInputErrorMessage();
			}
			return keepPlaying;
		}

		
	}

	private void dealerTurn() { //hit <= 16, stand >= 17
		
	}
	
	
	/**
	 * This method calculates the total value of a hand
	 * 
	 * first loop: adds up the total of all the cards in the hand
	 * and keeps track of the aces (does not add it yet)
	 * Second loop: if there are aces, and if the total value of the hand is more
	 * than 21 if the ace is worth 11, then the ace will be worth 1
	 * if its less than 21 if the ace is worth 11 then the ace will be worth 11
	 * 
	 * @param list this is the list that contains the several cards the dealer or the player has
	 * @return Returns the total value of the hand as an int
	 */
	private int calculateHandValue(List<Card> list) {
		int handValueTotal = 0;
		int totalAce = 0;

		
		for (Card card: list) {				//read the cards one by one and analyze what rank they are
			if(card.getRank() == 1) { 		//if the card is an ace then keep track how much aces by incrementing totalAce
				totalAce ++; 	
			}
			else if(card.getRank() > 10) {  //Any rank that is more than 10 is Jack, Queen, King, all worth 10
				handValueTotal += 10;
			}
			else {
				handValueTotal += card.getRank();
			}
		}
		
		for (int i = 0; i < totalAce; i++) { 
		
			if (handValueTotal + 11 > 21) { //if the handValueTotal plus an ace as 11 would be more than 21 
				handValueTotal += 1; 		//Then only add 1 to handValueTotal
			}
			else {
				handValueTotal += 11;		//else add 11 to handvalueTotal
			}
		}
		
		return handValueTotal;
		
	}
	

	
	private boolean isBlackJack(List<Card> handList) { //Checks for a natural blackjack(Ace card + 10)
		int handValue = calculateHandValue(handList);
		
		return handList.size() == 2 && handValue == 21;
		
	}
	
	//Outcome
	
	/**
	 * This method checks if the total hand is above 21
	 * 
	 * If the total hand is above 21, then that is considered a bust
	 * 
	 * @param handVal the total value of a hand (calculated using .calculateHandValue() method
	 * @return true if total hand is above 21, False if less
	 */
	private boolean isBust(List<Card> handList) { // handval is calculated from using calculateHandValue method
		int handValue = calculateHandValue(player.getHand());
		return handValue > 21;

	}
	
	/**
	 * This method determines the results of the game when it ends
	 * 
	 * @return if the match was a tie loss or a regular win
	 */
	private String determineWinner() {
		int playerHandVal= calculateHandValue(player.getHand());
		int dealerHandVal= calculateHandValue(dealer.getHand());
		String results = "";

		if (playerHandVal == dealerHandVal){	//if playerhandval is the same as daalerhandval then set the results to "tie"
			results = "tie";						//but does not end the game
		}
		else if (playerHandVal < dealerHandVal ) { // if the playerhandval is less than dealer handval then set results to "loss"
			results = "loss";
		}
		else if (playerHandVal > dealerHandVal) { // if the playerHandval is more than dealeHandVal then set results to "regularWin"
			results = "regularWin";
		} 
		
		return  results;

	}
	
	/**
	 * This method calculates and sets the final balance of the player
	 * @param result the result of the match, whether its a blackjack for the player, a tie, or just a regular win
	 */
	private void payout(String result) {
		double blackJackPayoutMultiplier= 1.5;
		
		switch (result) {
		case "blackjack": 
			player.setBalance(player.getBalance() + (blackJackPayoutMultiplier * bettingAmount));
			break;
			
		
		case "regularWin":
			player.setBalance(player.getBalance() +  bettingAmount);
			break;
		
		case "tie":
			//keep there current balance they win nor lose any
			break;

		case "loss": 
			player.setBalance(player.getBalance() -  bettingAmount);
			break;	
		}
	}
	
	
	//Display
	
	private void displayTable() {
		
	}
	
	private void displayResults() {
		
	}
	
	private void displayBalance() {
		
	}
	
	
	
	
	
	
	
	
	
	
}