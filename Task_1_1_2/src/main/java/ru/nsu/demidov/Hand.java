package ru.nsu.demidov;

/**
 * hand of cards.
 */
public class Hand {
    private int numberOfCards;
    private int score;
    Card[] cards;

    {
        numberOfCards = 0;
        score = 0;
        cards = new Card[9];
    }

    int getScore() {
        return score;
    }

    /**
     * card addition method.
     */
    void addCard(Card currentCard) {
        cards[numberOfCards] = currentCard;
        numberOfCards++;
        score += currentCard.getValue();
        int WINNING_SCORE = 21;
        int aceValue = 11;
        if (score > WINNING_SCORE && currentCard.getValue() == aceValue) {
            score -= 10;
        }
    }

    /**
     * output of all cards.
     */
    String showAllCards(boolean isSecret) {
        StringBuilder showable = new StringBuilder();
        showable.append("[");
        //System.out.print("[");
        for (int i = 0; i < numberOfCards; ++i) {
            if (!(isSecret == true && numberOfCards == 2 && i == 1)) {
                showable.append(cards[i].displayInfo());
                //System.out.print(cards[i].displayInfo());
                if (i != numberOfCards - 1) {
                    showable.append(", ");
                    //System.out.print(", ");
                }
            } else {
                showable.append("<secret>");
                //System.out.print("<secret>");
            }
        }
        showable.append("], " + "Score - ");
        //System.out.print("], " + "Score - ");
        if (isSecret == true) {
            showable.append(cards[0].getValue()).append(" + <secret>");
            //System.out.println(cards[0].value + " + <secret>");
        } else {
            showable.append(score);
            //System.out.println(score);
        }
        return showable.toString();
    }
}