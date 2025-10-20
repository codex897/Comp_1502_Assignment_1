package mru.test.BlackjackGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mru.game.controller.*;
class CardDeckTest {

	@Test
	void testNewDeckShuffle() {
		CardDeck deck = new CardDeck();								
		ArrayList<Card> oldDeck = new ArrayList<Card>(deck.getDeck()); // make a new array list to copy
		
		System.out.println(oldDeck);
		deck.shuffleDeck();
		System.out.println(deck.getDeck()); // after shuffling the deck ahould be changed
		System.out.println(oldDeck); //after shuffling the deck should not be changed and
		
		assertNotEquals(oldDeck, deck.getDeck());  //check if new shuffled deck and the old deck DOES NOT match
	}
	
	@Test
	void testMultipleShuffle() {
		CardDeck deck = new CardDeck();								
		ArrayList<Card> oldDeck = null ;
		for(int i= 0; i< 5; i++) { // suffle multiple times and check if they are different each time
			assertNotEquals(oldDeck, deck.getDeck());
			oldDeck = new ArrayList<Card>(deck.getDeck());
			deck.shuffleDeck();
			
		}
	}
		
	@Test
	void testGetDeck() {
		CardDeck deck = new CardDeck();
		assertNotNull(deck.getDeck());
		
	}
	
	@Test
	void testDeckSize() {
		CardDeck deck = new CardDeck();
		assertEquals(52, deck.getDeck().size());
		
	}
	
	@Test
	void testDealCardsDeckSize() {
		CardDeck deck = new CardDeck();
		assertEquals(52, deck.getDeck().size());
		deck.dealCard();
		assertEquals(51, deck.getDeck().size());
		
	}
	
	@Test
	void testDealCardsDeckSameCard() {
		CardDeck deck = new CardDeck();
		ArrayList<Card> oldDeck = new ArrayList<Card>(deck.getDeck()); // make a new array list to copy
		deck.dealCard();
		assertEquals(deck.getDeck().get(0), oldDeck.get(1)); //first card in the new deck should equal second card in the old deck after dealing a card
		assertNotEquals(deck.getDeck().get(0), oldDeck.get(0)); //first card should not equal in both decks
		
	}
	

}