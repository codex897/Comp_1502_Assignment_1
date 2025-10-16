package mru.game.controller;

import java.io.InterruptedIOException;
import java.util.List;
import java.util.Scanner;

import mru.game.model.Player;
//Testing Commit
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
		int balance = player.getBalance();
		
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
			System.out.println("\nYour Hand: " + player.getHand());
			int handValue = calculateHandValue(player.getHand());
			System.out.println("Your Total Hand Value: " + handValue);
			
			//Conditions set for if player bust
			if (handValue > 21) {
				System.out.println("You Busted!");
				break; // Stops loop
			}
			
			System.out.println("Would you like to \n(1) Hit \n(2) Stand ");
			int choice = 0;
			
			choice = Integer.parseInt(input.nextLine());
			
			if (choice == 1) {
				player.addCardToHand(deck.dealCard());
				System.out.println("You drew a new card.");
			}
			
			else if (choice == 2) {
				System.out.println("You chose to stand.");
				keepPlaying = false;
			}
			else {
				System.out.println("Invalid Choice. Please enter 1 or 2");
			}
		}
	}
	
	private void dealerTurn() { //hit <= 16, stand >= 17
		
	}
	
	private int calculateHandValue(List<Card> list) {
		return bettingAmount;
		
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