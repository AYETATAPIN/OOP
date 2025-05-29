package ru.nsu.demidov.primeroparallelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PrimeDelimeter implements PrimeDetector {
    private final String host;
    private final int port;

    public PrimeDelimeter(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public boolean containsNotPrime(int[] numbers) throws Exception {
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.writeObject(numbers);
            out.flush();

            String response = in.readLine();
            return "TRUE".equals(response);
        } catch (IOException e) {
            throw new Exception("Nichego ne nashlos", e);
        }
    }
}