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
		System.out.println("You WON $" + prize);
	}
	
	/**
	 * This method shows a message indicateing the player lost
	 * @param bet the amount the player lost from there bet
	 */
	public void showLoss(double bet) {
		System.out.println("");
		System.out.println("You LOST $" + bet);
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
	
	
}