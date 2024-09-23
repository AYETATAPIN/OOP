package ru.nsu.demidov;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlackJackTest {
    @Test
    public void overflow_test() {
        ByteArrayInputStream in = new ByteArrayInputStream("1488\n2\n2\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        InputStream inputStream = System.in;  // сохраняем ссылку на ввод с клавиатуры
        OutputStream outputStream = System.out;
        PrintStream printStream = new PrintStream(out);
        System.setIn(in);                     // подменяем ввод
        System.setOut(printStream);
        String sampleOutput = "To choose an option \"1\" or \"2\"\n" +
                "Round 1\n" +
                "Your cards: [Two of Diamonds(2), Queen of Diamonds(10)], Score - 12\n" +
                "Dealer's cards: [Jack of Diamonds(10), <secret>], Score - 10 + <secret>\n" +
                "[Hit / Stand]\n" +
                "1488\n" +
                "Dealer reveals his cards: [Ace of Diamonds(11), Eight of Diamonds(8)], " +
                "Score - 19\n" +
                "You win\n" +
                "[Keep gambling / Daddy gave up]";
        //assert(Objects.equals(out.toString(), sampleOutput));
        System.out.println("123");
        String output = out.toString();
        System.out.println(output);
        System.setIn(inputStream);            // подменяем обратно
    }
}