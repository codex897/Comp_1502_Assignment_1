package mru.game.model;

public class Player implements Comparable<Player> {
	String name; 
	int balance; 
	int numOfWins;
	
	public Player(String name, int balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return balance;
	}

	public void setId(int id) {
		this.balance = balance;
	}

	public int getNumOfWins() {
		return numOfWins;
	}

	public void setNumOfWins(int numOfWins) {
		this.numOfWins = numOfWins;
	}
	
	public String toString() {
		return "Name: " + name + "BALANCE" + balance + "Number of Wins:" + numOfWins;
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
