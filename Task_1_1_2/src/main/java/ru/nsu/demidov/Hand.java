package ru.nsu.demidov;

/**
 * hand of cards.
 */
public class Hand {
    int winningScore = 21;
    int aceValue = 11;
    int numberOfCards;
    int score;
    Card[] cards;

    {
        numberOfCards = 0;
        score = 0;
        cards = new Card[9];
    }

    /**
     * card addition method.
     */
    void addCard(Card currentCard) {
        cards[numberOfCards] = currentCard;
        numberOfCards++;
        score += currentCard.value;
        if (score > winningScore && currentCard.value == aceValue) {
            score -= 10;
        }
    }

    /**
     * output of all cards.
     */
    void showAllCards(boolean isSecret) {
        System.out.print("[");
        for (int i = 0; i < numberOfCards; ++i) {
            if (!(isSecret == true && numberOfCards == 2 && i == 1)) {
                System.out.print(cards[i].displayInfo());
                if (i != numberOfCards - 1) {
                    System.out.print(", ");
                }
            } else {
                System.out.print("<secret>");
            }
        }
        System.out.print("], " + "Score - ");
        if (isSecret == true) {
            System.out.println(cards[0].value + " + <secret>");
        } else {
            System.out.println(score);
        }
    }
}