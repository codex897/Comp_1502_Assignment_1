package mru.test.BlackjackGame;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import mru.game.controller.Card;
import mru.game.controller.GameManager;

class GameManagerTest {

	//most methods and instance field are set to private 
	
	/*
	 * testConstructor works but the game must launch and press "e" immediately
	 * This means that it is not automatic like what the pdf instruction said
	 * so it is not needed
	 * 
	 * Uncomment the method to try it
	 */
	
	import org.junit.jupiter.api.Test;
	import static org.junit.jupiter.api.Assertions.*;

	public class CardTest {

	    @Test
	    void testCardCreation() {
	        Card card = new Card("Hearts", "Ace");
	        assertEquals("Hearts", card.getSuit());
	        assertEquals("Ace", card.getRank());
	    }

	    @Test
	    void testFaceCardToString() {
	        Card queen = new Card("Spades", "Queen");
	        assertEquals("Queen of Spades", queen.toString());
	    }

	    @Test
	    void testNumberCardToString() {
	        Card five = new Card("Clubs", "5");
	        assertEquals("5 of Clubs", five.toString());
	    }
	

//	@Test
//	void testConstructor() throws IOException { //to test if  the gameManager runs press "e" after when testing
//		GameManager gameManager = new GameManager();
//		assertNotNull(gameManager);
//	}

}
