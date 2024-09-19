package ru.nsu.demidov;

/**
 * playing card.
 */

public class Card {
    String name;
    String suit;
    int value;

    Card(String currentName, String currentSuit, int currentValue) {
        name = currentName;
        suit = currentSuit;
        value = currentValue;
    }

    /**
     * card name suit and value output.
     */
    void displayInfo() {
        System.out.print(name + " of " + suit + "(" + value + ")");
    }
}
