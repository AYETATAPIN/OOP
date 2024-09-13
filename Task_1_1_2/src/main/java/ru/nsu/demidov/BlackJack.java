package ru.nsu.demidov;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 * Игра с блэкджеком и шлюхами.
 *
 * @author d.demidov
 * @version 69
 */

public class BlackJack {
    /**
     * игральная карта.
     */
    public static class Card {
        String name;
        String suit;
        int value;

        Card(String currentName, String currentSuit, int currentValue) {
            name = currentName;
            suit = currentSuit;
            value = currentValue;
        }

        /**
         * вывод названия, масти и стоимости карты.
         */
        void displayInfo() {
            System.out.print(name + " " + suit + "(" + value + ")");
        }
    }

    /**
     * карты игрока.
     */
    public static class Hand {
        int numberOfCards;
        int score;
        Card[] cards;

        {
            numberOfCards = 0;
            score = 0;
            cards = new Card[9];
        }

        /**
         * метод добавления карты.
         */
        void addCard(Card currentCard) {
            cards[numberOfCards] = currentCard;
            numberOfCards++;
            score += currentCard.value;
            if (score > 21 && currentCard.value == 11) {
                score -= 10;
            }
        }

        /**
         * вывод всех карт.
         */
        void showAllCards(boolean isSecret) {
            System.out.print("[");
            for (int i = 0; i < numberOfCards; ++i) {
                if (!(isSecret == true && numberOfCards == 2 && i == 1)) {
                    cards[i].displayInfo();
                    if (i != numberOfCards - 1) {
                        System.out.print(", ");
                    }
                } else {
                    System.out.print("<секретик>");
                }
            }
            System.out.print("], " + "Счет - ");
            if (isSecret == true) {
                System.out.println(cards[0].value + " + <секретик>");
            } else {
                System.out.println(score);
            }
        }
    }

    /**
     * игральная колода.
     */
    public static class Deck {
        int numberOfCards;
        Card[] cards;

        /**
         * создание колоды из 36 карт.
         */
        void deckOf36() {
            cards[0] = new Card("Шестерка", "черви", 6);
            cards[1] = new Card("Шестерка", "буби", 6);
            cards[2] = new Card("Шестерка", "крести", 6);
            cards[3] = new Card("Шестерка", "пики", 6);

            cards[4] = new Card("Семерка", "черви", 7);
            cards[5] = new Card("Семерка", "буби", 7);
            cards[6] = new Card("Семерка", "крести", 7);
            cards[7] = new Card("Семерка", "пики", 7);

            cards[8] = new Card("Восьмерка", "черви", 8);
            cards[9] = new Card("Восьмерка", "буби", 8);
            cards[10] = new Card("Восьмерка", "крести", 8);
            cards[11] = new Card("Восьмерка", "пики", 8);

            cards[12] = new Card("Девятка", "черви", 9);
            cards[13] = new Card("Девятка", "буби", 9);
            cards[14] = new Card("Девятка", "крести", 9);
            cards[15] = new Card("Девятка", "пики", 9);

            cards[16] = new Card("Десятка", "черви", 10);
            cards[17] = new Card("Десятка", "буби", 10);
            cards[18] = new Card("Десятка", "крести", 10);
            cards[19] = new Card("Десятка", "пики", 10);

            cards[20] = new Card("Валет", "черви", 10);
            cards[21] = new Card("Валет", "буби", 10);
            cards[22] = new Card("Валет", "крести", 10);
            cards[23] = new Card("Валет", "пики", 10);

            cards[24] = new Card("Дама", "черви", 10);
            cards[25] = new Card("Дама", "буби", 10);
            cards[26] = new Card("Дама", "крести", 10);
            cards[27] = new Card("Дама", "пики", 10);

            cards[28] = new Card("Король", "черви", 10);
            cards[29] = new Card("Король", "буби", 10);
            cards[30] = new Card("Король", "крести", 10);
            cards[31] = new Card("Король", "пики", 10);

            cards[32] = new Card("Туз", "черви", 11);
            cards[33] = new Card("Туз", "буби", 11);
            cards[34] = new Card("Туз", "крести", 11);
            cards[35] = new Card("Туз", "пики", 11);


        }

        /**
         * создание колоды из 52 карт.
         */
        void deckOf52() {
            deckOf36();
            cards[36] = new Card("Двойка", "черви", 2);
            cards[37] = new Card("Двойка", "буби", 2);
            cards[38] = new Card("Двойка", "крести", 2);
            cards[39] = new Card("Двойка", "пики", 2);

            cards[40] = new Card("Тройка", "черви", 3);
            cards[41] = new Card("Тройка", "буби", 3);
            cards[42] = new Card("Тройка", "крести", 3);
            cards[43] = new Card("Тройка", "пики", 3);

            cards[44] = new Card("Четверка", "черви", 4);
            cards[45] = new Card("Четверка", "буби", 4);
            cards[46] = new Card("Четверка", "крести", 4);
            cards[47] = new Card("Четверка", "пики", 4);

            cards[48] = new Card("Пятерка", "черви", 5);
            cards[49] = new Card("Пятерка", "буби", 5);
            cards[50] = new Card("Пятерка", "крести", 5);
            cards[51] = new Card("Пятерка", "пики", 5);
        }

        /**
         * тасовка колоды.
         */
        void shuffle() {
            Random number = new Random();
            for (int i = 0; i < 1000; ++i) {
                int tempIndex = number.nextInt(10000) % numberOfCards;
                int switchIndex = number.nextInt(10000) % numberOfCards;
                Card temp = cards[tempIndex];
                cards[tempIndex] = cards[switchIndex];
                cards[switchIndex] = temp;
            }
        }

        /**
         * конструктор колоды.
         */
        Deck(int size) {
            numberOfCards = size;
            cards = new Card[size];
            if (size == 36) {
                deckOf36();
            } else {
                deckOf52();
            }
            shuffle();
        }
    }

    /**
     * блэкджек.
     */
    public static void game(String[] args) throws InterruptedException {
        int round = 1;
        while (true) {
            System.out.println("Раунд" + " " + round);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Выберите тип колоды: [36/52]");
            boolean lost = false;
            Scanner argument = new Scanner(System.in);
            int deckSize = argument.nextInt();
            Deck dealerDeck = new Deck(deckSize);
            Hand playerHand = new Hand();
            Hand dealerHand = new Hand();
            int deckIndex = 0;
            playerHand.addCard(dealerDeck.cards[deckIndex]); // first player card
            deckIndex++;
            dealerHand.addCard(dealerDeck.cards[deckIndex]); // first dealer card
            deckIndex++;
            playerHand.addCard(dealerDeck.cards[deckIndex]); // second player card
            deckIndex++;
            dealerHand.addCard(dealerDeck.cards[deckIndex]); // second dealer card
            deckIndex++;
            System.out.print("Ваши карты: ");
            TimeUnit.SECONDS.sleep(1);
            playerHand.showAllCards(false);
            if (playerHand.score == 21) {
                System.out.println("Блэкджек! KYS!");
                break;
            }
            System.out.print("Карты дилера: ");
            TimeUnit.SECONDS.sleep(1);
            dealerHand.showAllCards(true);
            System.out.println("Маловато будет? [да/нет]");
            String option;
            option = argument.next();

            if (Objects.equals(option, "1")) {
                while (!(Objects.equals(option, "2"))) {
                    playerHand.addCard(dealerDeck.cards[deckIndex]);
                    deckIndex++;
                    playerHand.showAllCards(false);
                    if (playerHand.score > 21) {
                        System.out.println("ПЕРЕПОЛНЕНИЕ");
                        lost = true;
                        break;
                    }
                    System.out.println("Еще? [МАЛОВАТО БУДЕТ/хватит]");
                    option = argument.next();
                }
            }
            if (lost != true) {
                System.out.print("Дилер вскрывается: ");
                TimeUnit.SECONDS.sleep(1);
                dealerHand.showAllCards(false);
                while (dealerHand.score < 17) {
                    System.out.println("Дилеру маловато");
                    TimeUnit.SECONDS.sleep(1);
                    dealerHand.addCard(dealerDeck.cards[deckIndex]);
                    dealerHand.showAllCards(false);
                    if (dealerHand.score > 21) {
                        System.out.println("Дилер вскрылся");
                    }
                    deckIndex++;
                }
                if (playerHand.score == dealerHand.score) {
                    System.out.println("Ничья. Везунчики");
                } else if (playerHand.score > dealerHand.score || playerHand.score <= 21
                        && dealerHand.score > 21) {
                    System.out.println("Ты прогрел дилера на фишки.");
                } else {
                    System.out.println("Дилер прогрел тебя на фишки");
                }
            }
            System.out.println("Еще по одной?");
            option = argument.next();
            while (!Objects.equals(option, "1") && !Objects.equals(option, "2")) {
                option = argument.next();
            }
            if (option.equals("2")) {
                return;
            }
            round++;
        }
    }

    /**
     * шлюхи.
     */
    public static class Whores {

    }
}