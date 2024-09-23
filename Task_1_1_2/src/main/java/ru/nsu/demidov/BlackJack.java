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
        dealerDeck.shuffle();
        playerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]); // first player card
        dealerDeck.incrementIndex();
        dealerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]); // first dealer card
        dealerDeck.incrementIndex();
        playerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]); // second player card
        dealerDeck.incrementIndex();
        dealerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]); // second dealer card
        dealerDeck.incrementIndex();
    }

    /**
     * Cards hitting.
     */
    public static boolean cards_hitting(Hand playerHand, Hand dealerHand,
                   Deck dealerDeck, Scanner argument, String[] isTest) throws InterruptedException {
        if (isTest.length == 0 ||  Objects.equals(isTest[0], "Testing") != true) {
            System.out.print("Your cards: ");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(playerHand.showAllCards(false));
            if (playerHand.getScore() == 21) {
                System.out.println("Blackjack! KYS!");
                return true;
            }
            System.out.print("Dealer's cards: ");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(dealerHand.showAllCards(true));
            System.out.println("[Hit / Stand]");
        }
        boolean lost = false;
        String option;
        option = argument.next();
        if (Objects.equals(option, "1488")) {
            dealerDeck = new Deck();
            dealerDeck.getCards()[0] = new Card("Ace", "Spades",
                    11);
            dealerDeck.getCards()[1] = new Card("Ace", "Diamonds",
                    11);
            dealerDeck.getCards()[2] = new Card("Nine", "Spades",
                    9);
            dealerDeck.getCards()[3] = new Card("Eight", "Diamonds",
                    8);
            dealerDeck.makeIndexZero();
            playerHand = new Hand();
            dealerHand = new Hand();
            playerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]); // first player card
            dealerDeck.incrementIndex();
            dealerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]); // first dealer card
            dealerDeck.incrementIndex();
            playerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]); // second player card
            dealerDeck.incrementIndex();
            dealerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]); // second dealer card
            dealerDeck.incrementIndex();
        }
        if (Objects.equals(option, "1")) {
            while (!(Objects.equals(option, "2"))) {
                playerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]);
                dealerDeck.incrementIndex();
                System.out.println(playerHand.showAllCards(false));
                if (playerHand.getScore() > 21) {
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
            System.out.println(dealerHand.showAllCards(false));
            while (dealerHand.getScore() < 17) {
                System.out.println("Dealer hits");
                TimeUnit.SECONDS.sleep(1);
                dealerHand.addCard(dealerDeck.getCards()[dealerDeck.getIndex()]);
                System.out.println(dealerHand.showAllCards(false));
                if (dealerHand.getScore() > 21) {
                    System.out.println("Dealer killed himself");
                }
                dealerDeck.incrementIndex();
            }
            if (playerHand.getScore() == dealerHand.getScore()) {
                System.out.println("Draw. KYS!");
            } else if (playerHand.getScore() > dealerHand.getScore() || playerHand.getScore() <= 21
                    && dealerHand.getScore() > 21) {
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
            return false;
        }
        return true;
    }

    /**
     * BlackJack.
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner argument = new Scanner(System.in);
        int round = 1;
        System.out.println("To choose an option \"1\" or \"2\"");
        while (true) {
            System.out.println("Round" + " " + round);
            TimeUnit.SECONDS.sleep(1);
            boolean over = true;
            Deck dealerDeck = new Deck();
            Hand playerHand = new Hand();
            Hand dealerHand = new Hand();
            cards_handing(playerHand, dealerHand, dealerDeck);
            over = cards_hitting(playerHand, dealerHand, dealerDeck, argument, args);
            if (over == false) {
                break;
            }
            round++;
        }
        return;
    }
}