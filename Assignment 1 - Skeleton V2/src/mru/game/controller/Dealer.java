package mru.game.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the dealer in the blackjack game
 */
public class Dealer {
	
	//Since we have a player class, we also need a dealer class 
	//with needed setter and getters for the dealers specific actions and moves
	
	/**
	 * all the dealer card they currently have
	 */
	private List<Card> hand; //Creates List
	
	/**
	 * initializes the dealer 
	 */
	public Dealer() {
		this.hand = new ArrayList<>();
	}
	
	/**
	 * this method gets the dealers current hand of cards
	 * @return the list of cards the dealer has
	 */
	public List<Card> getHand() {
		return hand;
	}
	
	/**
	 * Adds a card to the dealers hand
	 * @param card the card that is added to dealers hand
	 */
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	/**
	 * removes all the cards in dealers hand
	 */
	public void emptyHand() {
		hand.clear();
	}
}