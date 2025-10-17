package mru.test.BlackjackGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mru.game.controller.Card;



/**
 * Tests for the Card class.
 */
public class CardTest {

    @Test
    void testCardCreationAndGetters() {
        Card card = new Card(5, "Hearts");
        assertEquals(5, card.getRank());
        assertEquals("Hearts", card.getSuit());
    }

    @Test
    void testSettersUpdateValues() {
        Card card = new Card(7, "Diamonds");
        card.setRank(9);
        card.setSuit("Clubs");
        assertEquals(9, card.getRank());
        assertEquals("Clubs", card.getSuit());
    }

    @Test
    void testToStringForNumberCard() {
        Card card = new Card(10, "Hearts");
        assertEquals("10 of Hearts", card.toString());
    }

    @Test
    void testToStringForAce() {
        Card card = new Card(1, "Spades");
        assertEquals("Ace of Spades", card.toString());
    }

    @Test
    void testToStringForJack() {
        Card card = new Card(11, "Clubs");
        assertEquals("Jack of Clubs", card.toString());
    }

    @Test
    void testToStringForQueen() {
        Card card = new Card(12, "Diamonds");
        assertEquals("Queen of Diamonds", card.toString());
    }

    @Test
    void testToStringForKing() {
        Card card = new Card(13, "Hearts");
        assertEquals("King of Hearts", card.toString());
    }

    @Test
    void testGetValueForNumberCards() {
        Card card = new Card(7, "Hearts");
        assertEquals(7, card.getValue(), "Number cards should keep their face value");
    }

    @Test
    void testGetValueForFaceCards() {
        Card jack = new Card(11, "Clubs");
        Card queen = new Card(12, "Diamonds");
        Card king = new Card(13, "Spades");

        assertEquals(10, jack.getValue(), "Jack should have value 10");
        assertEquals(10, queen.getValue(), "Queen should have value 10");
        assertEquals(10, king.getValue(), "King should have value 10");
    }

    @Test
    void testGetValueForAce() {
        Card ace = new Card(1, "Hearts");
        assertEquals(1, ace.getValue(), "Ace should have base value of 1");
    }
}


