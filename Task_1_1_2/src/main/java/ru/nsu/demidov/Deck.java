package ru.nsu.demidov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * playing deck.
 */
public class Deck {
    int deck_size = 52;
    int deck_index = 0;
    Card[] cards = new Card[deck_size];
    String[] names = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
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
        for (int name_index = 0; name_index < 8; ++name_index) { // from 2 to 10
            for (int suit_index = 0; suit_index < 4; ++suit_index) {
                cards[deck_index] = new Card(names[name_index], suits[suit_index], values[name_index]);
                deck_index++;
            }
        }
        for (int name_index = 8; name_index < 12; ++name_index) { // face cards
            for (int suit_index = 0; suit_index < 4; ++suit_index) {
                cards[deck_index] = new Card(names[name_index], suits[suit_index], 10);
                deck_index++;
            }
        }
        for (int name_index = 12; name_index < 13; ++name_index) { // aces
            for (int suit_index = 0; suit_index < 4; ++suit_index) {
                cards[deck_index] = new Card(names[name_index], suits[suit_index], 11);
                deck_index++;
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
