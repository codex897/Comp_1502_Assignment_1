package mru.game.controller;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	
	//Since we have a player class, we also need a dealer class 
	//with needed setter and getters for the dealers specific actions and moves
	
	private List<Card> hand; //Creates List
	
	public Dealer() {
		this.hand = new ArrayList<>();
	}
	
	public List<Card> getHand() {
		return hand;
	}
	
	public void oneFaceCardReveal() {
		//Add code for when the dealer reveals the face down card if needed.
	}
	
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	public void emptyHand() {
		hand.clear();
	}
}
