package ru.nsu.demidov;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
 * Игра с блэкджеком и шлюхами.
 * @author d.demidov
 * @version 69
 */
public class blackJack {
    /**
     * игральная карта.
     */
    public static class card {
        String name;
        String suit;
        int value;

        card(String current_name, String current_suit, int current_value) {
            name = current_name;
            suit = current_suit;
            value = current_value;
        }
        /**
         * вывод названия, масти и стоимости карты.
         */
        void display_info() {
            System.out.print(name + " " + suit + "(" + value + ")");
        }

    }
    /**
     * карты игрока.
     */
    public static class hand {
        int number_of_cards, score;
        card[] cards;

        {
            number_of_cards = 0;
            score = 0;
            cards = new card[9];
        }
        /**
         * метод добавления карты.
         */
        void add_card(card current_card) {
            cards[number_of_cards] = current_card;
            number_of_cards++;
            score += current_card.value;
            if (score > 21 && current_card.value == 11) {
                score -= 10;
            }
        }
        /**
         * вывод всех карт.
         */
        void show_all_cards(boolean is_secret) {
            System.out.print("[");
            for (int i = 0; i < number_of_cards; ++i) {
                if (!(is_secret == true && number_of_cards == 2 && i == 1)) {
                    cards[i].display_info();
                    if (i != number_of_cards - 1) {
                        System.out.print(", ");
                    }
                } else {
                    System.out.print("<секретик>");
                }
            }
            System.out.print("], " + "Счет - ");
            if (is_secret == true) {
                System.out.println(cards[0].value + " + <секретик>");
            } else {
                System.out.println(score);
            }
        }
    }
    /**
     * игральная колода.
     */
    public static class deck {
        int number_of_cards;
        card[] cards;
        /**
         * создание колоды из 36 карт.
         */
        void deck_of_36() {
            cards[0] = new card("Шестерка", "черви", 6);
            cards[1] = new card("Шестерка", "буби", 6);
            cards[2] = new card("Шестерка", "крести", 6);
            cards[3] = new card("Шестерка", "пики", 6);

            cards[4] = new card("Семерка", "черви", 7);
            cards[5] = new card("Семерка", "буби", 7);
            cards[6] = new card("Семерка", "крести", 7);
            cards[7] = new card("Семерка", "пики", 7);

            cards[8] = new card("Восьмерка", "черви", 8);
            cards[9] = new card("Восьмерка", "буби", 8);
            cards[10] = new card("Восьмерка", "крести", 8);
            cards[11] = new card("Восьмерка", "пики", 8);

            cards[12] = new card("Девятка", "черви", 9);
            cards[13] = new card("Девятка", "буби", 9);
            cards[14] = new card("Девятка", "крести", 9);
            cards[15] = new card("Девятка", "пики", 9);

            cards[16] = new card("Десятка", "черви", 10);
            cards[17] = new card("Десятка", "буби", 10);
            cards[18] = new card("Десятка", "крести", 10);
            cards[19] = new card("Десятка", "пики", 10);

            cards[20] = new card("Валет", "черви", 10);
            cards[21] = new card("Валет", "буби", 10);
            cards[22] = new card("Валет", "крести", 10);
            cards[23] = new card("Валет", "пики", 10);

            cards[24] = new card("Дама", "черви", 10);
            cards[25] = new card("Дама", "буби", 10);
            cards[26] = new card("Дама", "крести", 10);
            cards[27] = new card("Дама", "пики", 10);

            cards[28] = new card("Король", "черви", 10);
            cards[29] = new card("Король", "буби", 10);
            cards[30] = new card("Король", "крести", 10);
            cards[31] = new card("Король", "пики", 10);

            cards[32] = new card("Туз", "черви", 11);
            cards[33] = new card("Туз", "буби", 11);
            cards[34] = new card("Туз", "крести", 11);
            cards[35] = new card("Туз", "пики", 11);


        }
        /**
         * создание колоды из 52 карт.
         */
        void deck_of_52() {
            deck_of_36();
            cards[36] = new card("Двойка", "черви", 2);
            cards[37] = new card("Двойка", "буби", 2);
            cards[38] = new card("Двойка", "крести", 2);
            cards[39] = new card("Двойка", "пики", 2);

            cards[40] = new card("Тройка", "черви", 3);
            cards[41] = new card("Тройка", "буби", 3);
            cards[42] = new card("Тройка", "крести", 3);
            cards[43] = new card("Тройка", "пики", 3);

            cards[44] = new card("Четверка", "черви", 4);
            cards[45] = new card("Четверка", "буби", 4);
            cards[46] = new card("Четверка", "крести", 4);
            cards[47] = new card("Четверка", "пики", 4);

            cards[48] = new card("Пятерка", "черви", 5);
            cards[49] = new card("Пятерка", "буби", 5);
            cards[50] = new card("Пятерка", "крести", 5);
            cards[51] = new card("Пятерка", "пики", 5);
        }
        /**
         * тасовка колоды.
         */
        void shuffle() {
            Random number = new Random();
            for (int i = 0; i < 1000; ++i) {
                int temp_index = number.nextInt(10000) % number_of_cards;
                int switch_index = number.nextInt(10000) % number_of_cards;
                card temp = cards[temp_index];
                cards[temp_index] = cards[switch_index];
                cards[switch_index] = temp;
            }
        }
        /**
         * конструктор колоды.
         */
        deck(int size) {
            number_of_cards = size;
            cards = new card[size];
            if (size == 36) {
                deck_of_36();
            } else {
                deck_of_52();
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
            int deck_size = argument.nextInt();
            deck dealer_deck = new deck(deck_size);
            hand player_hand = new hand(), dealer_hand = new hand();
            int deck_index = 0;
            player_hand.add_card(dealer_deck.cards[deck_index]); // first player card
            deck_index++;
            dealer_hand.add_card(dealer_deck.cards[deck_index]); // first dealer card
            deck_index++;
            player_hand.add_card(dealer_deck.cards[deck_index]); // second player card
            deck_index++;
            dealer_hand.add_card(dealer_deck.cards[deck_index]); // second dealer card
            deck_index++;
            System.out.print("Ваши карты: ");
            TimeUnit.SECONDS.sleep(1);
            player_hand.show_all_cards(false);
            if (player_hand.score == 21) {
                System.out.println("Блэкджек! KYS!");
                break;
            }
            System.out.print("Карты дилера: ");
            TimeUnit.SECONDS.sleep(1);
            dealer_hand.show_all_cards(true);
            System.out.println("Маловато будет? [да/нет]");
            String option;
            option = argument.next();

            if (Objects.equals(option, "1")) {
                while (!(Objects.equals(option, "2"))) {
                    player_hand.add_card(dealer_deck.cards[deck_index]);
                    deck_index++;
                    player_hand.show_all_cards(false);
                    if (player_hand.score > 21) {
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
                dealer_hand.show_all_cards(false);
                while (dealer_hand.score < 17) {
                    System.out.println("Дилеру маловато");
                    TimeUnit.SECONDS.sleep(1);
                    dealer_hand.add_card(dealer_deck.cards[deck_index]);
                    dealer_hand.show_all_cards(false);
                    if (dealer_hand.score > 21) {
                        System.out.println("Дилер вскрылся");
                    }
                    deck_index++;
                }
                if (player_hand.score == dealer_hand.score) {
                    System.out.println("Ничья. Везунчики");
                } else if (player_hand.score > dealer_hand.score || player_hand.score <= 21 && dealer_hand.score > 21) {
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