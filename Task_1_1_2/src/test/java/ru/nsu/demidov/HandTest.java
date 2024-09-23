package ru.nsu.demidov;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class HandTest {
    @Test
    void infoDisplayinTest() {
        Hand sampleHand = new Hand();
        Card sampleCard0 = new Card("Two", "Spades", 2);
        Card sampleCard1 = new Card("Ace", "Spades", 11);
        sampleHand.addCard(sampleCard0);
        sampleHand.addCard(sampleCard1);
        assert (Objects.equals(sampleHand.showAllCards(false), "[Two of Spades(2), Ace of Spades(11)], Score - 13"));
        assert (Objects.equals(sampleHand.showAllCards(true), "[Two of Spades(2), <secret>], Score - 2 + <secret>"));
    }
}

//Your cards: [Eight of Diamonds(8), King of Hearts(10)], Score - 18
//Dealer's cards: [Eight of Spades(8), <secret>], Score - 8 + <secret>