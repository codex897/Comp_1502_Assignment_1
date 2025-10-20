package mru.game.view;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.controller.Card;
import mru.game.model.Player;


/**
 * This class handles all the display messages related with the gameplay
 */
public class gameplayMenu {
	
	private Scanner input;
	
	/**
	 * This constructor creates a new gameplay menu and sets up the scanner for the user input
	 */
	public gameplayMenu(){
		input = new Scanner(System.in);
	}
	
	/**
	 * This method shows the top part of the game table with labels for the player and dealer
	 */
	public void showSearchTableLabel() {
		System.out.println("");
		System.out.println("                 - BLACKJACK -                 ");
		System.out.println("+======================+======================+");
		System.out.println("||PLAYER               |DEALER               ||");
		System.out.println("+======================+======================+");
		
	
		}
	
	/**
	 * This method shows one row of the game table with player card and dealer card
	 * 
	 * @param playerCard the players card to display as a String
	 * @param dealerCard The dealers card to display as a String
	 */
	public void showTableCard(String playerCard, String dealerCard) {
		System.out.println(String.format("|%-22s|%-22s|", playerCard, dealerCard));
		System.out.println("+----------------------+----------------------+");
		
	}
	
	/**
	 * This method Asks the player how much they want to bet
	 * 
	 * @param minimumBet the minimum requirement that the player can bet
	 */
	public void showAskBet(int minimumBet) {
		System.out.println("");
		System.out.println("How much do you want to bet this round? (minimum $" + minimumBet + "): ");
	}
	
	/**
	 * this displays an invalid input indicator
	 * 
	 * @param minimumBet the minimum requirement that the player can bet
	 */
	public void showInvalidBet(int minimumBet) {
		System.out.println("Your bet must be at least $" + minimumBet + ".");

	}
	
	/**
	 * this displays the players bet
	 * 
	 * @param playersBet the amount that the player chose to bet
	 */
	public void showPlayersBet(int playersBet) {
		System.out.println("");
		System.out.println("Your bet $" + playersBet + ". Good luck!");

	}
	
	/**
	 * this displays an invalid input indicator when user enters a bet that is more than the players balance
	 * 
	 * @param maxBet the maximum amount a player can bet, which is there account balance
	 */
	public void showInvalidBetBalance(double maxBet) {
		System.out.println("You cannot bet more than your balance ($" + maxBet + ").");

	}
	
	/**
	 * this displays an invalid number indicator
	 */
	public void showPleaseEnterValidNum() {
		System.out.println("Please enter a valid number.");

	}
	
	/**
	 * this displays a question if the player wants to hit or stand
	 */
	public void showAsHitStand() {
			System.out.println("Would you like to \n(1) Hit \n(2) Stand ");

	}
	
	/**
	 * This method shows a message when a player chooses to hit
	 */
	public void showHitIndicator() {
		System.out.println("");
		System.out.println("You drew a new card");

	}
	
	/**
	 * This method shows a message when a player chooses to start a new round
	 */
	public void showNewRound() {
		System.out.println("");
		System.out.println("\n--- New Round ---");

	}
	
	/**
	 * This method shows a message when a player chooses to stand
	 */
	public void showStandIndicator() {
		System.out.println("");
		System.out.println("You chose to stand");
	}
	
	/**
	 * This method displays the total value of the players hand
	 * @param handValue the total value of the players hand
	 */
	public void showTotalHandValue(int handValue) {
		System.out.println("");
		System.out.println("Your Total Hand Value: " + handValue);
	}
	
	/**
	 * This shows a message when a player gets a blackjack
	 * (hand value is 21 the first round)
	 */
	public void showPlayerBlackjack() {
		System.out.println("");
		System.out.println("you have BLACKJACK!");
	}
	
	/**
	 * This shows a message when the dealer gets a blackjack
	 * (hand value is 21 the first round)
	 */
	public void showDealerBlackjack() {
		System.out.println("");
		System.out.println("Dealer has BLACKJACK!");
	}
	
	/**
	 * This shows a message if a player gets a Bust (hand value is over 21)
	 */
	public void showPlayerBusted() {
		System.out.println("");
		System.out.println("You BUSTED!");
	}
	
	/**
	 * This shows a message if the dealer gets a Bust (hand value is over 21)
	 */
	public void showDealerBusted() {
		System.out.println("");
		System.out.println("Dealer BUSTED!");
	}
	
	/**
	 * This method displays when a player wins and how much the player won
	 * @param prize the amount the player won
	 */
	public void showWin(double prize) {
		System.out.println("");
		System.out.println(String.format("You WON $%.2f", prize));
	}
	
	/**
	 * This method shows a message indicateing the player lost
	 * @param bet the amount the player lost from there bet
	 */
	public void showLoss(double bet) {
		System.out.println("");
		System.out.println(String.format("You LOST $%.2f", bet));
	}
	
	/**
	 * This method shows a message if the game is a tie
	 * Both dealer and player have the same hand value when the game ends
	 */
	public void showTie() {
		System.out.println("");
		System.out.println("The game is a TIE");
	}
	
	/**
	 * This method displays a message to ask if they want to play another round
	 */
	public void showAskContinue() {
		System.out.println("");
		System.out.println("Do you want to continue (y/n)?");
	}
	
	/**
	 * This method displays a message to indicate the player that they do not have enough balance in there account to play
	 * 
	 * @param balance the players balance amount
	 */
	public void showNotEnoughBal(double balance) {
		System.out.println("");
		System.out.println("You don't have enough to play $" + balance + ".");
	}
	
	/**
	 * This method displays a message to indicate the player that they do have enough balance in there account to play
	 * 
	 * @param balance the players balance amount
	 */
	public void showEnoughBal(double balance) {
		System.out.println("");
		System.out.println("You have enough to play $" + balance + "!");
	}
	
	/**
	 * This method displays a message to indicate that it is the dealers turn
	 */
	public void showDealerTurn() {
		System.out.println("");
		System.out.println("--- Dealer's Turn ---");
	}
	
	/**
	 * This method displays a the total hand the dealer has and indicates that they will hit
	 * 
	 * @param dealerTotal The total hand value of the dealer
	 */
	public void showDealerHit(int dealerTotal) {
		System.out.println("");
		System.out.println("Dealer has " + dealerTotal + " and hits....");
	}
	
	/**
	 * This method displays a message to indicate that the dealer stands
	 * 
	 * @param dealerTotal the total hand value that the dealer has
	 */
	public void showDealerStand(int dealerTotal) {
		System.out.println("");
		System.out.println("Dealer stands with total of: " + dealerTotal);
	}
	
	/**
	 * This method displays the new balance of the player after the game
	 * 
	 * @param playerBal  the balance of the player after the game
	 */
	public void showPlayerNewBalance(double playerBal) {
		System.out.println("");
	    System.out.println("-------------------------------");
	    System.out.printf("Your new balance: $%.2f%n", playerBal);
	    System.out.println("-------------------------------\n");
	}
	
	
	
	
}