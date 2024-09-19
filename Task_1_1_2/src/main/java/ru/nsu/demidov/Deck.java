package ru.nsu.demidov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * playing deck.
 */
public class Deck {
    int deckSize = 52;
    int deckIndex = 0;
    Card[] cards = new Card[deckSize];
    String[] names = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Jack", "Queen", "King", "Ace"};
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    {
        deckOf52();
        shuffle();
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

    /**
     * deck shuffle.
     */
    void shuffle() {
        List<Card> cardList = new ArrayList<>(List.of(cards));
        Collections.shuffle(cardList);
        cards = cardList.toArray(new Card[0]);
    }
}
