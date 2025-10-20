package mru.test.BlackjackGame;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mru.game.controller.Card;
import mru.game.controller.Dealer;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void testDealerStartsWithEmptyHand() {
        assertTrue(dealer.getHand().isEmpty(), "Dealer hand should start empty");
    }

    @Test
    void testAddCardToHand() {
        Card card = new Card(1, "Hearts");
        dealer.addCardToHand(card);

        List<Card> hand = dealer.getHand();
        assertEquals(1, hand.size(), "Dealer hand should have 1 card after adding");
        assertEquals(card, hand.get(0), "Dealer hand should contain the added card");
    }

    @Test
    void testAddMultipleCardsToHand() {
        dealer.addCardToHand(new Card(10, "Spades"));
        dealer.addCardToHand(new Card(12, "Clubs"));

        assertEquals(2, dealer.getHand().size(), "Dealer should have 2 cards after adding");
    }

    @Test
    void testEmptyHand() {
        dealer.addCardToHand(new Card(5, "Diamonds"));
        dealer.addCardToHand(new Card(3, "Hearts"));
        dealer.emptyHand();

        assertTrue(dealer.getHand().isEmpty(), "Dealer hand should be empty after clearing");
    }

    @Test
    void testGetHandReturnsSameListReference() {
        List<Card> firstRef = dealer.getHand();
        List<Card> secondRef = dealer.getHand();

        assertSame(firstRef, secondRef, "getHand() should return the same list reference");
    }
}