package mru.game.controller;

import java.io.InterruptedIOException;
import java.util.List;
import java.util.Scanner;

import mru.game.model.Player;

import mru.game.view.AppMenu;
import mru.game.view.gameplayMenu;

/**
 * This class represents the logic for the blackjack game
 * 
 */
public class BlackjackGame {
	
	/**
	 * The player playing the game
	 */
	private Player player;
	
	/**
	 * The dealer in the game
	 */
	private Dealer dealer;
	
	/**
	 * The players bet for the round
	 */
	private int bettingAmount;
	
	/**
	 * the deck representing the full 52 shuffled card
	 */
	private CardDeck deck;
	
	/**
	 * The multiplier for when the player gets blackjack and wins the money 
	 */
	final double BLACKJACK_MULTIPLIER= 1.5;
	
	/**
	 * The minimum amount the player can bet
	 */
	private static final int MIN_BET = 2;
	
	/**
	 * the result of the current status/round
	 */
	String result;
	
	/**
	 * This indicates if the cards of the dealer should be revealed (flag)
	 */
	boolean revealDealerCard;
	
	Scanner input;
	
	/**
	 * The menu for displaying
	 */
	private AppMenu menu;
	
	/**
	 * The menu for displaying gameplay
	 */
	private gameplayMenu gameMenu;
	
	/**
	 * Creates a nee blackjack game and initializes the nessary game components to play the game
	 * 
	 * @param player the player object who is playing the game
	 */
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

	/**
	 * This method starts the game and loops until the player wants to stop
	 */
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
			
			payout(result);
			
			keepPlaying = askToKeepGoing(input);
		}
		
		
	}

	//Setup Methods
	
	/**
	 * Prepares a new round of Blackjack.
	 * This method Resets everything for a new game.
	 * Clears both hands(Player and Dealer)
	 * Reshuffles the deck when it becomes low.
	 */
	
	private void initializeRound() {
		// Reset round status values to start round fresh
		result = ""; //Clears any previous round result
		revealDealerCard = false; //Hides dealer's second card at the start of a new round
		
		//Clear cards from player's and dealer's hands from last round
		player.emptyHand();
		dealer.emptyHand();
		
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
	
	/**
	 * Prompts the player to enter a betting amount before each round.
	 * 
	 * Makes sure that the player's inputs follow all betting rules
	 * @param input the {@link Scanner} used to get user input from the console.
	 * @return The validated bet amount as an integer.
	 */
	private int promptBet(Scanner input) { 
		
		//Get the player's current max allowed bet(total balance)
		double maxBet = player.getBalance();
		
		//Local variables for validation
		int bet = 0;
		boolean valid = false;
		
		//Keeps looping until the player enters a valid betting amount.
		while (!valid) {
			gameMenu.showAskBet(MIN_BET);
			
			try {
				//Comvert the player's input into an integer
				bettingAmount = Integer.parseInt(input.nextLine());
				
				//Validation checks
				//If the betting amount is below minimum bet, show an error message
				if (bettingAmount < MIN_BET) {
					gameMenu.showInvalidBet(MIN_BET);
				} else if (bettingAmount > maxBet) {
					gameMenu.showInvalidBetBalance(maxBet);
				} else {
					valid = true;
				}
				
			} catch (NumberFormatException e) {
				// If the player enters anything that isn't a number
				gameMenu.showPleaseEnterValidNum();
			}
		}
		
		
		gameMenu.showPlayersBet(bettingAmount);
		return bettingAmount;
	}
	
	/**
	 * Asks the player if they would like to keep playing another round.
	 * 
	 * The player's input is taken and converted to lowercase and trimmed of spaces.
	 * If player enters 'y', the game will continue any other input will end the game loop.
	 * @param input the {@link Scanner} object used to get the player's input.
	 * @return {@code true} if the player chooses to continue, {@code false} otherwise.
	 */
	
	private boolean askToKeepGoing(Scanner input) {
		gameMenu.showAskContinue();
		// Read the player's choice, trim spaces, and convert to lowercase
		String choice = input.nextLine().toLowerCase().trim();
		// If player enters "y", continue playing, if not then stop the game
		return choice.equals("y");
	}
	
	/**
	 * Checks if the player's balance is enough to play another round.
	 * 
	 * Method: Makes sure the player's balance is not below minimum bet amount.
	 * It calls different display messages through the {@code gameplayMenu} depending
	 * on whether the player has enough money or not.
	 * 
	 * 
	 */
	private void checkPlayerBalance() {
		// Get the player's current balance using the Player class getter
		double balance = player.getBalance();
		
		// If the balance meets or goes over minimum bet, show message.
		if(balance >= 2) {
//			gameMenu.showEnoughBal(balance);
		} else {
			gameMenu.showNotEnoughBal(balance);
		}
		
	}
	
	
	//Gameplay
	
	/**
	 * Runs the player's actions phase for the current round.
	 * 
	 * While acting, the dealers hole card remains hidden.
	 * After each action, the current table is displayed.
	 * If the player busts, the turn ends right away.
	 * If the player stands, the dealer's hole is revealed and then the dealer plays.
	 * 
	 * @param input shared {@link Scanner} for user input (Stand/Hit).
	 */
	
	private void playerTurn(Scanner input) {
		boolean keepDrawing = true;

		while(keepDrawing) {
			//Recalculate and show current state each loop iteration
			int handValue = calculateHandValue(player.getHand());
			
			result = determineWinner();
			
			tableDisplay(player.getHand(), dealer.getHand()); // calls to display the cards on screen, fix the formatting and whitespace

			

			
			//check if both dealer/player has blackjack || check if only dealer has blackjack or player bust || check is only player has blackjack
			if (result == "instaTie" || result == "instaLoss" || result == "instaBlackjack" ) { 	
				return;
			}
			
			keepDrawing = playerHitStandChoice(); // calls to prompt hit or stand choice and returns false if they choose to stand
			
			if (!keepDrawing) {
				
				revealDealerCard = !keepDrawing;
				//Dealer will draw cards until their total is at least 17
				dealerTurn();
			}
			
		}
	}
	
	
	/**
	 * This method creates a table by calling print statements from the gameplayMenu class
	 * by using loop to print each row.
	 * The method also hides the second card of the dealer until the game ends or the player chooses to stand (dealers turn)
	 * @param playerHandList a list of card objects the player has
	 * @param dealerHandList a list of card objects the dealer  has
	 */
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
	
	/**
	 * Asks the player to choose between "Hit" or "Stand".
	 * 
	 * This method will keep asking for input until they enter a valid option.
	 * 
	 * Player can:
	 * Hit- draw another card from the deck.
	 * Stand - end their turn and allow the dealer to play.
	 * 
	 * 
	 * @return {@code true} if the player chooses to hit(draw cards),
	 * 		   {@code false} if the player chooses to stand(end their turn).
	 */
	private boolean playerHitStandChoice() {
		boolean keepPlaying = true;
		//Looop continues until a valid input is given ("1" or "2")
		while(true) {
			
			gameMenu.showAsHitStand();
			
			//Read player's input from console
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
	

	/**
	 * Checks if the given hand is a natural blackjack.
	 * 
	 * A natural blackjack happens when the first two cards dealt total to 
	 * exactly 21.
	 * 
	 * 
	 * @param handList a list of {@link Card} objects representing either the
	 * player's or dealer's hand.
	 * @return {@code true} if the hand has exactly two cards and 
	 * their total value equals 21, {@code false} otherwise.
	 */
	private boolean isBlackJack(List<Card> handList) {
		//Calculate the total hand value.
		int handValue = calculateHandValue(handList);
		
		//A natural blackjack happens when:
		// The hand contains exactly two cards.
		// The total value of those two cards equals 21
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
		
		if(playerBust) { //if playerBust then player instantly lose
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
	
}
