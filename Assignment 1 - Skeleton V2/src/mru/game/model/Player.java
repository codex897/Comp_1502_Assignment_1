package mru.game.model;

import java.util.ArrayList;
import java.util.List;

import mru.game.controller.Card;

public class Player implements Comparable<Player> {
	String name; 
	String id; 
	int numOfWins;
	private int balance;
	private List<Card> hand;
	
	public Player(String name, String id, int numOfWins, int startingBalance) {
		this.name = name;
		this.id = id;
		this.balance = startingBalance;
		this.numOfWins = numOfWins;
		this.hand = new ArrayList<>();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "Name: " + name + "ID" + id + "Number of Wins:" + numOfWins;
	}
	
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
