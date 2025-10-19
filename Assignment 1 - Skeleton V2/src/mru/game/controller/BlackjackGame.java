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
	
	final double BLACKJACK_MULTIPLIER= 1.5;
	private static final int MIN_BET = 2;
	String result;
	boolean revealDealerCard;
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
			
			payout(result);
			
			keepPlaying = askToKeepGoing(input);
		}
		
		
	}
	
	
	
	

	//Setup Methods
	
	private void initializeRound() {
		//Method: Resets everything for a new game.
		//Clears both hands(Player and Dealer).
		//Reshuffles the deck.
		result = "";
		revealDealerCard = false;
		
		player.emptyHand();//Clear cards from players hand, gives both dealer and player new card deck.
		dealer.emptyHand();//Clear cards from players hand
		
		//Create and shuffle new card deck only when low.
		if (deck.size() < 15) {
			deck = new CardDeck();
			deck.shuffleDeck();
			
		}
		gameMenu.showNewRound();
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
		
		
//		dealer.addCardToHand(new Card(2, "heart"));
//		dealer.addCardToHand(new Card(1, "heart"));
		//for testing
//		player.addCardToHand(new Card(1, "heart"));
//		player.addCardToHand(new Card(2, "heart"));
//		dealer.addCardToHand(new Card(1, "heart"));
//		dealer.addCardToHand(new Card(2, "heart"));

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
			gameMenu.showAskBet(MIN_BET);
			try {
				bettingAmount = Integer.parseInt(input.nextLine());
				
				if (bettingAmount < MIN_BET) {
					gameMenu.showInvalidBet(MIN_BET);
				} else if (bettingAmount > maxBet) {
					gameMenu.showInvalidBetBalance(maxBet);
					
				} else {
					valid = true;
				}
				
			} catch (NumberFormatException e) {
				gameMenu.showPleaseEnterValidNum();
			}
		}
		
		
		gameMenu.showPlayersBet(bettingAmount);
		return bettingAmount;
	}
	
	private boolean askToKeepGoing(Scanner input) {
		gameMenu.showAskContinue();
		String choice = input.nextLine().toLowerCase().trim();
		return choice.equals("y");
	}
		
	private void checkPlayerBalance() { //This method will make sure the player never plays with $0.
		double balance = player.getBalance();
		
		if(balance >= 2) {
			gameMenu.showEnoughBal(balance);
		} else {
			gameMenu.showNotEnoughBal(balance);
		}
		
		
	}
	
	
	//Gameplay
	
	private void playerTurn(Scanner input) {
		boolean keepDrawing = true;

		while(keepDrawing) {
			int handValue = calculateHandValue(player.getHand());
			
			result = determineWinner();
			
			tableDisplay(player.getHand(), dealer.getHand()); // calls to display the cards on screen, fix the formatting and whitespace

			

			
			//check if both dealer/player has blackjack || check if only dealer has blackjack or player bust || check is only player has blackjack
			if (result == "instaTie" || result == "instaLoss" || result == "instaBlackjack" ) { 	
				return;
			}
			
			keepDrawing = playerHitStandChoice(); // calls to prompt hit or stand choice and returns falls if they choose to stand
			
			if (!keepDrawing) {
				revealDealerCard = !keepDrawing;
				dealerTurn();
			}
			
		}
	}
	
	private void tableDisplay(List<Card> playerHandList, List<Card> dealerHandList) {
		boolean show = result == "instaTie" || result == "instaLoss" || result == "instaBlackjack" || revealDealerCard == true;
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
			
			if (!show && i==1) {  //hide the second card of the dealer until game ends or player turn ends (stand)
				dealerCard = ""; 
			}
			else if (dealerHandList.size() <= i) { // check if the row being created is more than the size of the dealers hand
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
			gameMenu.showAsHitStand();
			String choice = input.nextLine();
			
			switch (choice) {
				case "1":
					player.addCardToHand(deck.dealCard());
					gameMenu.showHitIndicator();
					break;
				
				case "2":
					gameMenu.showStandIndicator();
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
		
		gameMenu.showDealerTurn();

		tableDisplay(player.getHand(), dealer.getHand());
		
		
		//calculate dealers initial total.
		int dealerTotal = calculateHandValue(dealer.getHand());
		
		
		//If the dealers total hand is less than 17, they must hit
		while (dealerTotal < 17) {
			gameMenu.showDealerHit(dealerTotal);
			
			//Draws another card from the deck and adds to dealer's hand
			dealer.addCardToHand(deck.dealCard());
		
			//Re calculate the dealer's hand since a new card was drawed.
			dealerTotal = calculateHandValue(dealer.getHand());
			
			tableDisplay(player.getHand(), dealer.getHand());
			
		}
		// Dealer stands once total is 17 or more
		gameMenu.showDealerStand(dealerTotal);
		
		result = determineWinner();
		
		if (result == "instaWin") { 	//dealer bust (dealer hsa more than 21
			return;
		}
	}
	
	
	//////////////NO NEED FOR THIS calculateHandValue() DOES THE JOB AND YOU DID while (dealerTotal < 17) WHICH IS GOOD ENOUGH TRUST AND TEST IT YOURSELF
	///
	///
	///
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
	 * if its less than 21 if the ace is worth 11, then the ace will be worth 11
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
		int handValue = calculateHandValue(handList);
		return handValue > 21;
	}
	
	/**
	 * This method determines the results of the game when it ends
	 * 
	 * @return the result f=of the match as a string
	 */
	private String determineWinner() {
		boolean playerHasBlackJack = isBlackJack(player.getHand());
		boolean DealerHasBlackJack = isBlackJack(dealer.getHand());
		int playerHandVal= calculateHandValue(player.getHand());
		int dealerHandVal= calculateHandValue(dealer.getHand());
		boolean playerBust= isBust(player.getHand());
		boolean dealerBust= isBust(dealer.getHand());
		
				
		
		//if the word returned has insta then the game ends
		
		if(playerBust) { //if playerBust  then player instantly lose
			return "instaLoss";
		}
		else if(dealerBust) { //if dealerBust then the player instantly wins
			return "instaWin";
		}
		else if(playerHasBlackJack && DealerHasBlackJack) { // if both have a blackjack game instantly ends in a tie
			return "instaTie";															  
		}
		else if (playerHasBlackJack) { //if only the player has the blackjack then player wins a special amount (1.5x) and player instantly wins
			return "instaBlackjack";
		}
		else if(DealerHasBlackJack) { // if only the dealer has a blackjack then player instantly loses
			return "instaLoss";											 					
		}
		else if (playerHandVal == dealerHandVal){	//if playerhandval is the same as daalerhandval then set the results to "regularTie"
			return "regularTie";						//but does not end the game
		}
		else if (playerHandVal < dealerHandVal ) { // if the playerhandval is less than dealer handval then set results to "regularLoss"
			return "regularLoss";
		}
		else if (playerHandVal > dealerHandVal) { // if the playerHandval is more than dealeHandVal then set results to "regularWin"
			return "regularWin";
		} else {
			return null;
		}

	}
	
	/**
	 * This method calculates and sets the final balance of the player
	 * @param result the result of the match, whether its a blackjack for the player, a tie, or just a regular win
	 */
	private void payout(String result) {
		
		
		switch (result) {
		case "instaBlackjack": 
			player.setBalance(player.getBalance() + (BLACKJACK_MULTIPLIER * bettingAmount));
			gameMenu.showPlayerBlackjack();
			gameMenu.showWin(BLACKJACK_MULTIPLIER * bettingAmount);
			player.incrementWins();
			gameMenu.showPlayerNewBalance(player.getBalance());
			break;
			
		
		case "regularWin":
		case "instaWin":
			player.setBalance(player.getBalance() +  bettingAmount);
			gameMenu.showWin(bettingAmount);
			player.incrementWins();
			gameMenu.showPlayerNewBalance(player.getBalance());
			break;
		
		case "regularTie":
		case "instaTie":
			gameMenu.showTie();
			//keep there current balance they win nor lose any
			break;

		case "regularLoss": 
		case "instaLoss":
			player.setBalance(player.getBalance() -  bettingAmount);
			gameMenu.showLoss(bettingAmount);
			gameMenu.showPlayerNewBalance(player.getBalance());
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