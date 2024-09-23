package ru.nsu.demidov;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

class BlackJackTest {
    @Test
    public void overflow_test() throws InterruptedException {
        ByteArrayInputStream in = new ByteArrayInputStream("1488\n2\n2\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        InputStream inputStream = System.in;
        PrintStream outputStream = System.out;
        PrintStream printStream = new PrintStream(out);
        System.setIn(in);
        System.setOut(printStream);

        //String output = "124";
        //System.out.println(output);

        BlackJack.main(new String[] {"Testing"});

        System.setIn(inputStream);
        System.setOut(outputStream);        // add this line
        /*String sampleOutput = "To choose an option \"1\" or \"2\"\n"
                + "Round 1\n"
                + "Dealer reveals his cards: [Ace of Diamonds(11), Eight of Diamonds(8)], "
                + "Score - 19\n"
                + "You win\n"
                + "[Keep gambling / Daddy gave up]\n";*/
        String sampleOutput = "To choose an option \"1\" or \"2\"\r\n" +
                "Round 1\r\n" +
                "Dealer reveals his cards: [Ace of Diamonds(11), Eight of Diamonds(8)], Score - 19\r\n" +
                "You win\r\n" +
                "[Keep gambling / Daddy gave up]\r\n";
        String output = out.toString();
        assert (Objects.equals(sampleOutput, output));
        System.out.println(output);


    }
}