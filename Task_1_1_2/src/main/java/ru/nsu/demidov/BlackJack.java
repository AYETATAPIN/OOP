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
        int numberOfCards, score;
        Card[] Cards;

        {
            numberOfCards = 0;
            score = 0;
            Cards = new Card[9];
        }
        /**
         * метод добавления карты.
         */
        void addCard(Card currentCard) {
            Cards[numberOfCards] = currentCard;
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
                    Cards[i].displayInfo();
                    if (i != numberOfCards - 1) {
                        System.out.print(", ");
                    }
                } else {
                    System.out.print("<секретик>");
                }
            }
            System.out.print("], " + "Счет - ");
            if (isSecret == true) {
                System.out.println(Cards[0].value + " + <секретик>");
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
        Card[] Cards;

        /**
         * создание колоды из 36 карт.
         */
        void deckOf36() {
            Cards[0] = new Card("Шестерка", "черви", 6);
            Cards[1] = new Card("Шестерка", "буби", 6);
            Cards[2] = new Card("Шестерка", "крести", 6);
            Cards[3] = new Card("Шестерка", "пики", 6);

            Cards[4] = new Card("Семерка", "черви", 7);
            Cards[5] = new Card("Семерка", "буби", 7);
            Cards[6] = new Card("Семерка", "крести", 7);
            Cards[7] = new Card("Семерка", "пики", 7);

            Cards[8] = new Card("Восьмерка", "черви", 8);
            Cards[9] = new Card("Восьмерка", "буби", 8);
            Cards[10] = new Card("Восьмерка", "крести", 8);
            Cards[11] = new Card("Восьмерка", "пики", 8);

            Cards[12] = new Card("Девятка", "черви", 9);
            Cards[13] = new Card("Девятка", "буби", 9);
            Cards[14] = new Card("Девятка", "крести", 9);
            Cards[15] = new Card("Девятка", "пики", 9);

            Cards[16] = new Card("Десятка", "черви", 10);
            Cards[17] = new Card("Десятка", "буби", 10);
            Cards[18] = new Card("Десятка", "крести", 10);
            Cards[19] = new Card("Десятка", "пики", 10);

            Cards[20] = new Card("Валет", "черви", 10);
            Cards[21] = new Card("Валет", "буби", 10);
            Cards[22] = new Card("Валет", "крести", 10);
            Cards[23] = new Card("Валет", "пики", 10);

            Cards[24] = new Card("Дама", "черви", 10);
            Cards[25] = new Card("Дама", "буби", 10);
            Cards[26] = new Card("Дама", "крести", 10);
            Cards[27] = new Card("Дама", "пики", 10);

            Cards[28] = new Card("Король", "черви", 10);
            Cards[29] = new Card("Король", "буби", 10);
            Cards[30] = new Card("Король", "крести", 10);
            Cards[31] = new Card("Король", "пики", 10);

            Cards[32] = new Card("Туз", "черви", 11);
            Cards[33] = new Card("Туз", "буби", 11);
            Cards[34] = new Card("Туз", "крести", 11);
            Cards[35] = new Card("Туз", "пики", 11);


        }

        /**
         * создание колоды из 52 карт.
         */
        void deckOf52() {
            deckOf36();
            Cards[36] = new Card("Двойка", "черви", 2);
            Cards[37] = new Card("Двойка", "буби", 2);
            Cards[38] = new Card("Двойка", "крести", 2);
            Cards[39] = new Card("Двойка", "пики", 2);

            Cards[40] = new Card("Тройка", "черви", 3);
            Cards[41] = new Card("Тройка", "буби", 3);
            Cards[42] = new Card("Тройка", "крести", 3);
            Cards[43] = new Card("Тройка", "пики", 3);

            Cards[44] = new Card("Четверка", "черви", 4);
            Cards[45] = new Card("Четверка", "буби", 4);
            Cards[46] = new Card("Четверка", "крести", 4);
            Cards[47] = new Card("Четверка", "пики", 4);

            Cards[48] = new Card("Пятерка", "черви", 5);
            Cards[49] = new Card("Пятерка", "буби", 5);
            Cards[50] = new Card("Пятерка", "крести", 5);
            Cards[51] = new Card("Пятерка", "пики", 5);
        }

        /**
         * тасовка колоды.
         */
        void shuffle() {
            Random number = new Random();
            for (int i = 0; i < 1000; ++i) {
                int temp_index = number.nextInt(10000) % numberOfCards;
                int switch_index = number.nextInt(10000) % numberOfCards;
                Card temp = Cards[temp_index];
                Cards[temp_index] = Cards[switch_index];
                Cards[switch_index] = temp;
            }
        }

        /**
         * конструктор колоды.
         */
        Deck(int size) {
            numberOfCards = size;
            Cards = new Card[size];
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
    public static void Game(String[] args) throws InterruptedException {
        int round = 1;
        while (true) {
            System.out.println("Раунд" + " " + round);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Выберите тип колоды: [36/52]");
            boolean lost = false;
            Scanner argument = new Scanner(System.in);
            int deckSize = argument.nextInt();
            Deck dealerDeck = new Deck(deckSize);
            Hand playerHand = new Hand(), dealerHand = new Hand();
            int deckIndex = 0;
            playerHand.addCard(dealerDeck.Cards[deckIndex]); // first player card
            deckIndex++;
            dealerHand.addCard(dealerDeck.Cards[deckIndex]); // first dealer card
            deckIndex++;
            playerHand.addCard(dealerDeck.Cards[deckIndex]); // second player card
            deckIndex++;
            dealerHand.addCard(dealerDeck.Cards[deckIndex]); // second dealer card
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
                    playerHand.addCard(dealerDeck.Cards[deckIndex]);
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
                    dealerHand.addCard(dealerDeck.Cards[deckIndex]);
                    dealerHand.showAllCards(false);
                    if (dealerHand.score > 21) {
                        System.out.println("Дилер вскрылся");
                    }
                    deckIndex++;
                }
                if (playerHand.score == dealerHand.score) {
                    System.out.println("Ничья. Везунчики");
                } else if (playerHand.score > dealerHand.score || playerHand.score <= 21 && dealerHand.score > 21) {
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
    public static class whores {

    }
}