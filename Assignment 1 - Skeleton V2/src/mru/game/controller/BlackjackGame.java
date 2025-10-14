package mru.game.controller;

import java.io.InterruptedIOException;
import java.util.Scanner;

import mru.game.model.Player;

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
	
	
	
	public BlackjackGame(Player player, int bettingAmount) { //Constructor
		this.bettingAmount = bettingAmount;
		this.player = player;
		this.dealer = new Dealer();
		this.deck = new CardDeck();
		this.deck.shuffleDeck();
	}
	
	// Game Flow
	
	public void startGame(){
		// Checks player balance for eligibility before starting any rounds.
		
		if (player.getBalance() <= 0) {
			System.out.println("You have no funds. Returning to main menu...");
			return;
		}
		
		boolean keepPlaying = true;
		Scanner input = new Scanner(System.in);
		
		//Thiss is the main entry loop, it will loop until the player quits or player balance = 0
		
		while (keepPlaying && player.getBalance() > 0) {
			initializeRound(); // Calls the initializeRound method
			bettingAmount = promptBet(input); // Ask player for bet amount.
			System.out.println("\nYou've bet $" + bettingAmount + " for this round.\n");
			
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
	
	private void shuffleDeck(){
		
	}
	
	private void dealInitialCards(){
		
	}
	
	//Betting / Checking player balance
	
	private int promptBet(Scanner input) { 
		// Method: Ask player for bet amount, and makes sure there's enough balance, 
		// and bet amount >= 2, and makes sure it follows game rules
		int maxBet = player.getBalance();
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
		
	}
	
	
	//Gameplay
	
	private void playerTurn() {
		
	}
	
	private void dealerTurn() { //hit <= 16, stand >= 17
		
	}
	
	private void calculateHandValue() {
		
	}
	
	private void isBust(int hand) {
		
	}
	
	private void isBlackJack(int hand) { //Checks for a natural blackjack(Ace card + 10)
		
	}
	
	//Outcome
	
	private void determineWinner() {
		
	}
	
	private void payout(int result) {
		
	}
	
	
	//Display
	
	private void displayTable() {
		
	}
	
	private void displayResults() {
		
	}
	
	private void displayBalance() {
		
	}
	
	
	
	
	
	
	
	
	
	
}
