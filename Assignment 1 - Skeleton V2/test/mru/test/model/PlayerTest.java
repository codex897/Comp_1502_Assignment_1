package mru.test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.game.model.Player;

public class PlayerTest {

	@Test
	public void testPlayerConstructor() { 
		/*
		 * test if the fields match the initiated player variables
		 * Tests the getName getBalance getNumOfWins
		 */
		Player player = new Player("bob", 100, 0);
		assertEquals("bob", player.getName());
		assertEquals(100, player.getBalance());
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
		assertEquals("Name: bob, BALANCE: 100, Number of Wins: 0", player.toString());
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
	
	//STILL MISSING CARD TEST

}
