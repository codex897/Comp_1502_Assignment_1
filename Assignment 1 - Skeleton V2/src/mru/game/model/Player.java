package mru.game.model;

import java.util.ArrayList;
import java.util.List;

import mru.game.controller.Card;


/**
 * This class represents the Player
 * @author Haseeb
 * @author Lorenzo
 * @author Alex
 */
public class Player implements Comparable<Player> {
	
	/**
	 * This field is the name of the player
	 */
	private String name; 
	
	/**
	 * This field is the amount of wins the player has
	 */
	private int numOfWins;
	
	/**
	 * This field is the amount of money the player has
	 */
	private int balance;
	
	/**
	 * This List holds the card object for the current player
	 */
	private List<Card> hand;
	
	/**
	 * This Constructor initiates the ArrayList and constructs the player with the name balance and number of wins
	 * It also initiates an empty hand without any cards
	 * 
	 * @param name The name of the player
	 * @param balance The current Balance of the player
	 * @param numOfWins The number of wins the player has
	 */
	public Player(String name, int balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
		this.hand = new ArrayList<>();
	}

	/**
	 * gets the name of the player
	 * 
	 * @return the player name as a string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the player 
	 * 
	 * @param name the name of the player as a string
	 */
	public void setName(String name) {
		//use this if needed
		this.name = name;
	}
	
	/**
	 * returns the balance of the player
	 * 
	 * @return the balance that the player has as an int
	 */
	public int getBalance() {
		return balance;
	}
	
	/**
	 * Sets a new balance for the player
	 * 
	 * @param balance the new amount for the players balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	/**
	 * returns the number of wins a player has
	 * 
	 * @return the number of wins the player has as an int
	 */
	public int getNumOfWins() {
		return numOfWins;
	}

	/**
	 * Sets a new value for the players number of wins
	 * 
	 * @param numOfWins the new value of the number of wins the player has
	 */
	public void setNumOfWins(int numOfWins) {
		this.numOfWins = numOfWins;
	}
	
	/**
	 * returns a string that shows all the player information
	 * 
	 * @return a formated string of the player info
	 */
	@Override
	public String toString() {
		return "Name: " + name + "BALANCE" + balance + "Number of Wins:" + numOfWins;
	}
	
	/**
	 * increments the wins of the current player
	 */
	public void incrementWins() {
	this.numOfWins++;
	}
	
	/**
	 * clears the cards in the hand ArrayList
	 */
	public void emptyHand() {
		hand.clear();
	}
	
	/**
	 * .................................................
	 * 
	 * @param card
	 */
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Card> getHand() {
	  return hand;
	}

	/**
	 * Compare the player to another player by the number of wins they have and places the person with a more 
	 * wins first when sorted
	 * 
	 * @param otherPlayer The player that is being compared to
	 * @return A positive integer if the player has more wins, negative if less, 0 if the same amount of wins
	 */
	@Override
	public int compareTo(Player otherPlayer) {
		/*creates the comparison rules for .sort method in GameManager
		 * Rule: Sort by listing top score to lowest score
		 */
		
		return (otherPlayer.getNumOfWins() - this.getNumOfWins());
	}
	
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
}







//
//package mru.game.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import mru.game.controller.Card;
//
//public class Player implements Comparable<Player> {
//	String name; 
//	int numOfWins;
//	private int balance;
//	private List<Card> hand;
//	
//	public Player(String name, int balance, int numOfWins) {
//		this.name = name;
//		this.balance = balance;
//		this.numOfWins = numOfWins;
//		this.hand = new ArrayList<>();
//		
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public int getNumOfWins() {
//		return numOfWins;
//	}
//
//	public void setNumOfWins(int numOfWins) {
//		this.numOfWins = numOfWins;
//	}
//	
//	public int getBalance() {
//		return balance;
//	}
//	
//	public void setBalance(int balance) {
//		this.balance = balance;
//	}
//	
//	public void incrementWins() {
//		this.numOfWins++;
//	}
//	
//	public void emptyHand() {
//		hand.clear();
//	}
//	
//	public void addCardToHand(Card card) {
//		hand.add(card);
//	}
//	
//	@Override
//	public String toString() {
//		return "Name: " + name + "Number of Wins:" + numOfWins;
//	}
//	
//	public int compareTo(Player otherPlayer) {
//		/*creates the comparison rules for .sort method in GameManager
//		 * Rule: Sort by listing top score to lowest score
//		 */
//		
//		return (otherPlayer.getNumOfWins() - this.getNumOfWins());
//	}
//
//	public List<Card> getHand() {
//        return hand;
//    }
//	
//	
//	/**
//	 * This class represent each player record in the Database
//	 * It is basically a model class for each record in the txt file
//	 */
//}
