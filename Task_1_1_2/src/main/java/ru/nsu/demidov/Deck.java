package ru.nsu.demidov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * playing deck.
 */
public class Deck {
    private final int deckSize = 52;
    private int deckIndex = 0;
    private Card[] cards = new Card[deckSize];
    private final String[] names = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    private final String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private final int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    {
        deckOf52();
        deckIndex = 0;
    }

    void incrementIndex() {
        deckIndex++;
    }

    Card[] getCards() {
        return cards;
    }

    int getIndex() {
        return deckIndex;
    }

    void makeIndexZero() {
        deckIndex = 0;
    }

    /**
     * deck of 52 cards creation.
     */
    void deckOf52() {
        for (int nameIndex = 0; nameIndex < 8; ++nameIndex) { // from 2 to 10
            for (int suitIndex = 0; suitIndex < 4; ++suitIndex) {
                cards[deckIndex] = new Card(names[nameIndex], suits[suitIndex], values[nameIndex]);
                deckIndex++;
            }
        }
        for (int nameIndex = 8; nameIndex < 12; ++nameIndex) { // face cards
            for (int suitIndex = 0; suitIndex < 4; ++suitIndex) {
                cards[deckIndex] = new Card(names[nameIndex], suits[suitIndex], 10);
                deckIndex++;
            }
        }
        for (int nameIndex = 12; nameIndex < 13; ++nameIndex) { // aces
            for (int suitIndex = 0; suitIndex < 4; ++suitIndex) {
                cards[deckIndex] = new Card(names[nameIndex], suits[suitIndex], 11);
                deckIndex++;
            }
        }
    }

    /*
    cards shuffle
     */
    void shuffle() {
        List<Card> cardList = new ArrayList<>(List.of(cards));
        Collections.shuffle(cardList);
        cards = cardList.toArray(new Card[0]);
    }
}
