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
	
	Scanner input;
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

	public void startGame(){ // ************* THIS IS NOT DONE STILL NEED TO ADD THE REST OF THE METHODS IN ORDER, THEN THE GAME WILL RUN AND UPDATE CORRECTLY **************
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
		
		//Create and shuffle new card deck only when low.
		if (deck.size() < 15) {
			deck = new CardDeck();
			deck.shuffleDeck();
			System.out.println("Reshuffling deck...");//Temporary Print Statement
		}
		
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
			
			tableDisplay(player.getHand(), dealer.getHand()); // calls to display the cards on screen, fix the formatting and whitespace

			
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

		gameMenu.showSearchTableLabel(); // first call to display the labels

		if (playerHandList.size() > dealerHandList.size()) { // if the players hand has more than dealerHandList.size()
			maxHandSize = playerHandList.size(); // set the maxHandSize to the one with the most amount of cards
		}

		for (int i = 0; i < maxHandSize; i++) { // the loop makes a row for the table and makes as much as the maxHandSize

			if (playerHandList.size() <= i) { // check if the row being created is more than the size of the players hand
				playerCard = ""; // if so, then display a blank in the row for the player column
			}
			else {					
				playerCard = playerHandList.get(i).toString(); // else, turn the Card object to a string
			}

			if (dealerHandList.size() <= i) { // check if the row being created is more than the size of the dealers hand
				dealerCard = ""; // if so, then display a blank in the row for the dealer column
			}
			else {					
				dealerCard = dealerHandList.get(i).toString(); // else, turn the Card object to a string
			}

			gameMenu.showTableCard(playerCard, dealerCard);
		}
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
	/**
	 * Controls the dealers turn.
	 * The dealer must always draw a card until their hand value is at least 17.
	 * If the dealer's total hand value goes over 21, they bust which means they lose
	 * automatically.
	 * 
	 * Rules: 
	 * Dealer shows their card before starting their turn.
	 * Dealer has to hit while total is 16 or less.
	 * Dealer has to stand once total is 17 or higher (including soft 17)
	 * If the dealer goes over 21 total hand value, it's a bust and 
	 * the player wins the round.
	 */

	private void dealerTurn() { //hit <= 16, stand >= 17
		
		System.out.println("\n--- Dealer's Turn ---");
		
		//Reveal the delears face down card
		System.out.println("Dealer reveals their hidden card.");
		System.out.println("Dealers hand:" + dealer.getHand());
		
		
		//calculate dealers initial total.
		int dealerTotal = calculateDealerHandValue(dealer.getHand());
		
		
		//If the dealers total hand is less than 17, they must hit
		while (dealerTotal < 17) {
			System.out.println("Dealer has " + dealerTotal + " and hits....");
			
			
			//Draws another card from the deck and adds to dealer's hand
			dealer.addCardToHand(deck.dealCard());
			
			
			//Re calculate the dealer's hand since a new card was drawed.
			dealerTotal = calculateDealerHandValue(dealer.getHand());
			
			// Print hand so player knows what was drawn
			System.out.println("Dealers new hand: " + dealer.getHand());
		}
		// Dealer stands once total is 17 or more
		System.out.println("Dealer stands with total of: " + dealerTotal);
		
		//If the dealer's total hand goes over 21, which means bust
		if(dealerTotal > 21) {
			System.out.println("Dealer has bust!");
		}	
	}
	
	/**
	 * This method calculates the total hand value for the dealer according
	 * to the balckjack game rules.
	 * 
	 * Calculate the total value of the dealer's cards. It handles face cards
	 * (J, Q, K) as 10, and also makes sure the rule for the ace is good
	 * (1 or 11) depending on which one of those numbers keeps the dealers total hand
	 * value within (17-21) - inclusive.
	 * 
	 * Rules:
	 * Face cards = 10.
	 * Aces initially count as 1.
	 * If we count Ace as an 11 and the total stays between 17 and 21(inclusive),
	 * it will be treated as 11.(soft 17 rule)
	 * If the total hand value goes over 21, the dealer will bust
	 * 
	 * @param hand A list of Card objects representing the dealers hand.
	 * @return The total value of the dealer's hand.
	 */
	private int calculateDealerHandValue(List<Card> hand) {
		
		//Variables, dealers hand total, how many aces in hand.
		int total = 0;
		int aces = 0;
		
		//loop through each card in the dealers hand
		for (Card c : hand) {
			//getting the numerical value of the card
			int value = c.getValue();
			
			//face cards(J, Q, K) = 10
			if(value > 10) {
				value = 10;
			}
			
			
			total += value;
			
			//If the card is an Ace(value 1)
			if (c.getValue() == 1) {
				aces++;
			}
		}
		
		//Applying the soft 17 rule
		if (aces > 0 && total + 10 >= 17 && total + 10 <= 21) {
			total +=10;
		}
		//return final total value of dealers hand
		return total;
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
		int dealerHandVal= calculateDealerHandValue(dealer.getHand());
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
	
	
	
	/**
	 * Displays the final outcome of the round after both the player and dealer have finished
	 * there turn(s).
	 * 
	 * Shows whether the player won, lost, tied, or got a blackjack,
	 * and displays the updated balance afterwards.
	 * 
	 * @param result The outcome of the round (eg. "blackjack", "regularwin", "loss", or "tie").
	 */
	private void displayResults(String result) { // *Lorenzo You have separate methods for each case in gameplayMenu so I 
												// 	made this since its all in one method which should be easier to use and call.*
												// If we can't have it in this class just move it to another but make sure its accessible.
	    System.out.println();
	    System.out.println("-------- ROUND RESULTS --------");

	    // Determine message based on result outcome
	    switch (result.toLowerCase()) {
	        case "blackjack":
	            System.out.println("Blackjack! You win payout!");
	            break;

	        case "regularwin":
	            System.out.println("You win this round!");
	            break;

	        case "tie":
	            System.out.println("It's a tie! Your bet is returned.");
	            break;

	        case "loss":
	            System.out.println("Dealer wins this round.");
	            break;

	        default:
	            System.out.println("Round done.");
	            break;
	    }

	    // Display the updated balance at the end of the round
	    System.out.println("-------------------------------");
	    System.out.printf("Your new balance: $%.2f%n", player.getBalance());
	    System.out.println("-------------------------------\n");
	}


	
	
	
	
	
	
}