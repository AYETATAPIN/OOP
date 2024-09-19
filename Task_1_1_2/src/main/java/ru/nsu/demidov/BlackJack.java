package ru.nsu.demidov;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 * BlackJack with hookers.
 *
 * @author d.demidov
 * @version 69
 */

public class BlackJack {
    /**
     * Handing out cards.
     */
    public static void cards_handing(Hand playerHand, Hand dealerHand,
                                     Deck dealerDeck) throws InterruptedException {
        dealerDeck.deckIndex = 0;
        playerHand.addCard(dealerDeck.cards[dealerDeck.deckIndex]); // first player card
        dealerDeck.deckIndex++;
        dealerHand.addCard(dealerDeck.cards[dealerDeck.deckIndex]); // first dealer card
        dealerDeck.deckIndex++;
        playerHand.addCard(dealerDeck.cards[dealerDeck.deckIndex]); // second player card
        dealerDeck.deckIndex++;
        dealerHand.addCard(dealerDeck.cards[dealerDeck.deckIndex]); // second dealer card
        dealerDeck.deckIndex++;
        System.out.print("Your cards: ");
        TimeUnit.SECONDS.sleep(1);
        playerHand.showAllCards(false);
        if (playerHand.score == 21) {
            System.out.println("Blackjack! KYS!");
            return;
        }
        System.out.print("Dealer's cards: ");
        TimeUnit.SECONDS.sleep(1);
        dealerHand.showAllCards(true);
        System.out.println("[Hit / Stand]");
    }

    /**
     * Cards hitting.
     */
    public static void cards_hitting(Hand playerHand, Hand dealerHand,
                                     Deck dealerDeck) throws InterruptedException {
        boolean lost = false;
        Scanner argument = new Scanner(System.in);
        String option;
        option = argument.next();
        if (Objects.equals(option, "1")) {
            while (!(Objects.equals(option, "2"))) {
                playerHand.addCard(dealerDeck.cards[dealerDeck.deckIndex]);
                dealerDeck.deckIndex++;
                playerHand.showAllCards(false);
                if (playerHand.score > 21) {
                    System.out.println("STACK OVERFLOW");
                    lost = true;
                    break;
                }
                System.out.println("[Hit / Stand]");
                option = argument.next();
            }
        }
        if (lost != true) {
            System.out.print("Dealer reveals his cards: ");
            TimeUnit.SECONDS.sleep(1);
            dealerHand.showAllCards(false);
            while (dealerHand.score < 17) {
                System.out.println("Dealer hits");
                TimeUnit.SECONDS.sleep(1);
                dealerHand.addCard(dealerDeck.cards[dealerDeck.deckIndex]);
                dealerHand.showAllCards(false);
                if (dealerHand.score > 21) {
                    System.out.println("Dealer killed himself");
                }
                dealerDeck.deckIndex++;
            }
            if (playerHand.score == dealerHand.score) {
                System.out.println("Draw. KYS!");
            } else if (playerHand.score > dealerHand.score || playerHand.score <= 21
                    && dealerHand.score > 21) {
                System.out.println("You win");
            } else {
                System.out.println("You lose");
            }
        }
        System.out.println("[Keep gambling / Daddy gave up]");
        option = argument.next();
        while (!Objects.equals(option, "1") && !Objects.equals(option, "2")) {
            option = argument.next();
        }
        if (option.equals("2")) {
            System.exit(0);
        }
    }

    /**
     * BlackJack.
     */
    public static void main(String[] args) throws InterruptedException {
        int round = 1;
        System.out.println("To choose an option \"1\" or \"2\"");
        while (true) {
            System.out.println("Round" + " " + round);
            TimeUnit.SECONDS.sleep(1);
            Deck dealerDeck = new Deck();
            Hand playerHand = new Hand();
            Hand dealerHand = new Hand();
            cards_handing(playerHand, dealerHand, dealerDeck);
            cards_hitting(playerHand, dealerHand, dealerDeck);
            round++;
        }
    }
}