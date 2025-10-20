package mru.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import mru.game.model.Player;

import mru.game.controller.*;
public class PlayerTest {

	@Test
	public void testPlayerConstructor() { 
		/*
		 * test if the fields match the initiated player variables
		 * Tests the getName getBalance getNumOfWins
		 */
		Player player = new Player("bob", 100, 0);
		assertEquals("bob", player.getName());
		assertEquals(100, player.getBalance());  //This test the getbalance too
		assertEquals(0, player.getNumOfWins());
	}
	
	@Test
	public void testsetName() {
		Player player = new Player("bob", 100, 0);
		player.setName("Lebron");
		assertNotEquals("bob", player.getName());
		assertNotEquals("jim", player.getName());
		assertNotEquals("lebron", player.getName()); // test when its lowercase all lowercase
		assertEquals("Lebron", player.getName());
	}
	@Test
	public void testsetBalZero() {
		Player player = new Player("bob", 100, 0);
		player.setBalance(0);
		
		assertNotEquals(100, player.getBalance());
		assertNotEquals(99999, player.getBalance());
		assertNotEquals("Lebron", player.getBalance());
		assertEquals(0, player.getBalance());
	}
	@Test
	public void testsetBalExtreme() {
		Player player = new Player("bob", 100, 0);
		player.setBalance(99999);
		
		assertNotEquals(100, player.getBalance());
		assertNotEquals(9999999, player.getBalance());
		assertNotEquals("Lebron", player.getBalance());
		assertEquals(99999, player.getBalance());
	}
	@Test
	public void testsetNumOfWinExtreme() {
		Player player = new Player("bob", 100, 0);
		player.setNumOfWins(99999);
		
		assertNotEquals(0, player.getNumOfWins());
		assertNotEquals(100, player.getNumOfWins());
		assertNotEquals(88888, player.getNumOfWins());
		assertEquals(99999, player.getNumOfWins());

	}
	@Test
	public void testToString() {
		Player player = new Player("bob", 100, 0);
		assertEquals("Name: bob, BALANCE: 100.0, Number of Wins: 0", player.toString());
	}
	
	@Test
	public void testIncrementWins() {
		Player player = new Player("bob", 100, 0);
		player.incrementWins();
		
		assertEquals(1, player.getNumOfWins());
		assertNotEquals(0, player.getNumOfWins());
		assertNotEquals(2, player.getNumOfWins());
	}
	
	@Test
	public void testCompareToLessVSMore () {
		Player player = new Player("bob", 100, 1);
		Player player2 = new Player("Jordan", 100, 99);
		assertTrue(player.compareTo(player2) > 0);
		
	}
	
	@Test
	public void testCompareToMoreVSLess () {
		Player player = new Player("bob", 100, 100);
		Player player2 = new Player("Jordan", 100, 99);
		assertFalse(player.compareTo(player2) > 0);
		assertTrue(player.compareTo(player2) < 0);
		
	}
	
	@Test
	public void testCompareToEqualVSEqual () {
		Player player = new Player("bob", 100, 99);
		Player player2 = new Player("Jordan", 100, 99);
		assertFalse(player.compareTo(player2) > 0);
		assertFalse(player.compareTo(player2) < 0);
		assertTrue(player.compareTo(player2) == 0);
		
	}
	
   @Test
   
    void testAddCardToHand() {
        Player player = new Player("TestPlayer", 100.0, 5);
        Card card1 = new Card(1, "Hearts");   
        Card card2 = new Card(10, "Spades");  
        Card card3 = new Card(5, "Diamonds");
        
        player.addCardToHand(card1);
        List<Card> hand = player.getHand();
        
        assertEquals(1, hand.size());
        assertEquals(card1, hand.get(0));
        
        		// Test adding multiple cards
        player.addCardToHand(card2);
        player.addCardToHand(card3);
        
        	assertEquals(3, hand.size());
        assertEquals(card2, hand.get(1));
        assertEquals(card3, hand.get(2));
    }

    @Test
    void testGetHand() {
    	
       Player player = new Player("TestPlayer", 100.0, 5);
        Card card1 = new Card(1, "Hearts");   
        Card card2 = new Card(10, "Spades");  
        
        player.addCardToHand(card1);
        player.addCardToHand(card2);
        
        
        List<Card> hand = player.getHand();
        assertEquals(2, hand.size());
        assertTrue(hand.contains(card1));
        assertTrue(hand.contains(card2));
    }

    @Test
    void testEmptyHand() {
        Player player = new Player("TestPlayer", 100.0, 5);
        Card card1 = new Card(1, "Hearts");   
        Card card2 = new Card(10, "Spades"); 
        	Card card3 = new Card(5, "Diamonds"); 
        
        player.addCardToHand(card1);
        player.addCardToHand(card2);
        assertEquals(2, player.getHand().size());
        
        // Test emptying hand
        player.emptyHand();
        assertTrue(player.getHand().isEmpty());
        
        
    }
    
    
    // Used for calculating hand
    private int calculateHandValue(List<Card> list) {
		int handValueTotal = 0;
		int totalAce = 0;

		
		for (Card card: list) {				//read the cards one by one and analyze what rank they are
			if(card.getRank() == 1) { 		//if the card is an ace then keep track how much aces by incrementing totalAce
				totalAce ++; 	
			}
			else if(card.getRank() > 10) {  //Any rank that is more than 10 is Jack, Queen, King, all worth 10
				handValueTotal += 10;
			}
			else {
				handValueTotal += card.getRank();
			}
		}
		
		for (int i = 0; i < totalAce; i++) { 
		
			if (handValueTotal + 11 > 21) { //if the handValueTotal plus an ace as 11 would be more than 21 
				handValueTotal += 1; 		//Then only add 1 to handValueTotal
			}
			else {
				handValueTotal += 11;		//else add 11 to handvalueTotal
			}
		}
		
		return handValueTotal;
		
	}
    
    @Test
    void testCalculateplayerHand() {
    	Player player = new Player("TestPlayer", 100.0, 5);
        Card card1 = new Card(1, "Hearts");   
        Card card2 = new Card(10, "Spades");  
        Card card3 = new Card(9, "Diamonds"); 
        
        player.addCardToHand(card1);
        player.addCardToHand(card2);
        player.addCardToHand(card3);
        
        int totalHandValue = calculateHandValue(player.getHand());
        assertEquals(20, totalHandValue);
        
        player.emptyHand();
        
        //calculate ace as an 11
        Card card4 = new Card(1, "Hearts");   
        Card card5 = new Card(9, "Spades");  
        Card card6 = new Card(1, "Diamonds"); 
        player.addCardToHand(card4);
        player.addCardToHand(card5);
        player.addCardToHand(card6);
        
        totalHandValue = calculateHandValue(player.getHand());
        assertEquals(21, totalHandValue);
  
        
    }


}
