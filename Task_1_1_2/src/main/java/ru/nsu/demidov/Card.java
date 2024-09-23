package ru.nsu.demidov;

/**
 * playing card.
 */

public class Card {
    private final String name;
    private final String suit;
    private final int value;

    Card(String currentName, String currentSuit, int currentValue) {
        name = currentName;
        suit = currentSuit;
        value = currentValue;
    }

    int getValue() {
        return value;
    }

    /**
     * card name suit and value output.
     */
    String displayInfo() {
        return (name + " of " + suit + "(" + value + ")");
    }
}
