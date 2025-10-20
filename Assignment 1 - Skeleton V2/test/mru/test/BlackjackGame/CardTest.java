package mru.test.BlackjackGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.game.controller.*;
class CardTest {

	@Test
	void testGetRank() {
		Card card = new Card(1, "hearts");
		
		assertEquals(1 ,card.getRank());
	}
	
	@Test
	void testGetWrongRank() {
		Card card = new Card(1, "hearts");
		
		assertNotEquals(2 ,card.getRank());
	}
	
	@Test
	void testGetSuit() {
		Card card = new Card(1, "hearts");
		
		assertEquals("hearts" ,card.getSuit());
	}
	
	@Test
	void testGetWrongSuit() {
		Card card = new Card(1, "hearts");
		
		assertNotEquals("diamond" ,card.getSuit());
	}
	
	@Test
	void testToStringAce() {
		Card card = new Card(1, "Hearts");
		assertEquals("Ace of Hearts",card.toString());
	}
	@Test
	void testToStringJack() {
		Card card = new Card(11, "Hearts");
		assertEquals("Jack of Hearts",card.toString());
	}
	
	@Test
	void testToStringQueen() {
		Card card = new Card(12, "Hearts");
		assertEquals("Queen of Hearts",card.toString());
	}
	
	@Test
	void testToStringKing() {
		Card card = new Card(13, "Hearts");
		assertEquals("King of Hearts",card.toString());
	}
	
	@Test
	void testToStringKingDefault() { // check to see if will still be "King of Hearts" when the rank is 99, since its the default
		Card card = new Card(99, "Hearts");
		assertEquals("King of Hearts",card.toString());
	}
	
	@Test
	void testSetRank() {
		Card card = new Card(1, "Hearts");
		card.setRank(11);
		assertEquals(11 ,card.getRank());
		assertEquals("Jack of Hearts",card.toString());
	}
	
	@Test
	void testSetWrongRank() {
		Card card = new Card(1, "Hearts");
		card.setRank(11);
		assertEquals(11 ,card.getRank());
		assertNotEquals("King of Hearts",card.toString());
	}
	
	@Test
	void testSetSuit() {
		Card card = new Card(1, "Hearts");
		card.setSuit("Diamond");
		assertEquals("Diamond" ,card.getSuit());
		assertEquals("Ace of Diamond",card.toString());
	}
	
	@Test
	void testSetWrongSuit() {
		Card card = new Card(1, "Hearts");
		card.setSuit("Diamond");
		assertEquals("Diamond" ,card.getSuit());
		assertNotEquals("Ace of Hearts",card.toString());
	}

}