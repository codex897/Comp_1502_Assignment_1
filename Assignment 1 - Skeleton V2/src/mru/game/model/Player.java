package mru.game.model;

import java.util.ArrayList;
import java.util.List;

import mru.game.controller.Card;

public class Player implements Comparable<Player> {
	String name; 
	int numOfWins;
	private int balance;
	private List<Card> hand;
	
	public Player(String name, int balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
		this.hand = new ArrayList<>();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumOfWins() {
		return numOfWins;
	}

	public void setNumOfWins(int numOfWins) {
		this.numOfWins = numOfWins;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void incrementWins() {
		this.numOfWins++;
	}
	
	public void emptyHand() {
		hand.clear();
	}
	
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "Number of Wins:" + numOfWins;
	}
	
	public int compareTo(Player otherPlayer) {
		/*creates the comparison rules for .sort method in GameManager
		 * Rule: Sort by listing top score to lowest score
		 */
		
		return (otherPlayer.getNumOfWins() - this.getNumOfWins());
	}

	public List<Card> getHand() {
        return hand;
    }
	
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
}
