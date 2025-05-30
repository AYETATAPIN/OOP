package ru.nsu.demidov.primeroparallelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.Socket;

public class SlavePunisherTest {
    @Test
    public void testSetTask() throws IOException {
        Master master = new Master();
        Socket socket = new Socket();
        SlavePunisher punisher = new SlavePunisher(socket, master, 0);
        punisher.setTask(new int[]{2, 3, 5});
        assertTrue(punisher.isTaskAssigned());
    }

    @Test
    public void testRun() throws IOException {
        Master master = new Master();
        Socket socket = new Socket();
        SlavePunisher punisher = new SlavePunisher(socket, master, 0);
        punisher.setTask(new int[]{2, 3, 5});
    }
}