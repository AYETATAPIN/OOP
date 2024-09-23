package ru.nsu.demidov;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    void deckCreationTest() {
        Card[] sampleCards = new Card[52];
        sampleCards[0] = new Card("Two", "Hearts", 2);
        sampleCards[1] = new Card("Two", "Diamonds", 2);
        sampleCards[2] = new Card("Two", "Clubs", 2);
        sampleCards[3] = new Card("Two", "Spades", 2);

        sampleCards[4] = new Card("Three", "Hearts", 3);
        sampleCards[5] = new Card("Three", "Diamonds", 3);
        sampleCards[6] = new Card("Three", "Clubs", 3);
        sampleCards[7] = new Card("Three", "Spades", 3);

        sampleCards[8] = new Card("Four", "Hearts", 4);
        sampleCards[9] = new Card("Four", "Diamonds", 4);
        sampleCards[10] = new Card("Four", "Clubs", 4);
        sampleCards[11] = new Card("Four", "Spades", 4);

        sampleCards[12] = new Card("Five", "Hearts", 5);
        sampleCards[13] = new Card("Five", "Diamonds", 5);
        sampleCards[14] = new Card("Five", "Clubs", 5);
        sampleCards[15] = new Card("Five", "Spades", 5);

        sampleCards[16] = new Card("Six", "Hearts", 6);
        sampleCards[17] = new Card("Six", "Diamonds", 6);
        sampleCards[18] = new Card("Six", "Clubs", 6);
        sampleCards[19] = new Card("Six", "Spades", 6);

        sampleCards[20] = new Card("Seven", "Hearts", 7);
        sampleCards[21] = new Card("Seven", "Diamonds", 7);
        sampleCards[22] = new Card("Seven", "Clubs", 7);
        sampleCards[23] = new Card("Seven", "Spades", 7);

        sampleCards[24] = new Card("Eight", "Hearts", 8);
        sampleCards[25] = new Card("Eight", "Diamonds", 8);
        sampleCards[26] = new Card("Eight", "Clubs", 8);
        sampleCards[27] = new Card("Eight", "Spades", 8);

        sampleCards[28] = new Card("Nine", "Hearts", 9);
        sampleCards[29] = new Card("Nine", "Diamonds", 9);
        sampleCards[30] = new Card("Nine", "Clubs", 9);
        sampleCards[31] = new Card("Nine", "Spades", 9);

        sampleCards[32] = new Card("Ten", "Hearts", 10);
        sampleCards[33] = new Card("Ten", "Diamonds", 10);
        sampleCards[34] = new Card("Ten", "Clubs", 10);
        sampleCards[35] = new Card("Ten", "Spades", 10);

        sampleCards[36] = new Card("Jack", "Hearts", 10);
        sampleCards[37] = new Card("Jack", "Diamonds", 10);
        sampleCards[38] = new Card("Jack", "Clubs", 10);
        sampleCards[39] = new Card("Jack", "Spades", 10);

        sampleCards[40] = new Card("Queen", "Hearts", 10);
        sampleCards[41] = new Card("Queen", "Diamonds", 10);
        sampleCards[42] = new Card("Queen", "Clubs", 10);
        sampleCards[43] = new Card("Queen", "Spades", 10);

        sampleCards[44] = new Card("King", "Hearts", 10);
        sampleCards[45] = new Card("King", "Diamonds", 10);
        sampleCards[46] = new Card("King", "Clubs", 10);
        sampleCards[47] = new Card("King", "Spades", 10);

        sampleCards[48] = new Card("Ace", "Hearts", 11);
        sampleCards[49] = new Card("Ace", "Diamonds", 11);
        sampleCards[50] = new Card("Ace", "Clubs", 11);
        sampleCards[51] = new Card("Ace", "Spades", 11);

        Deck sampleDeck = new Deck();

        for (int i = 0; i < 52; ++i) {
            assert (Objects.equals(sampleDeck.getCards()[i].displayInfo(),
                        sampleCards[i].displayInfo()));
        }

    }
}