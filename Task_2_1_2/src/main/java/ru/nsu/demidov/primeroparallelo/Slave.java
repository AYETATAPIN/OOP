package ru.nsu.demidov.primeroparallelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Slave {
    private static final String SUBNET = "127.0.0.1";
    private static final String MASTER_ACK = "MASTER_ACK";
    private static final String SLAVE_ACK = "SLAVE_ACK";
    private static final int PORT = 5055;

    public void start() throws Exception {
        for (; ; ) {
            InetAddress managerAddress = discoverManager();
            if (managerAddress == null) {
                throw new Exception("Couldn't get manager address");
            }
            processTasks(managerAddress);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    InetAddress discoverManager() throws IOException {
        MulticastSocket socket = new MulticastSocket(PORT);
        socket.setReuseAddress(true);
        InetAddress group = InetAddress.getByName(SUBNET);
        socket.joinGroup(group);

        byte[] buffer = new byte[1024];
        DatagramPacket received_data = new DatagramPacket(buffer, buffer.length);
        socket.receive(received_data);

        String received = new String(received_data.getData(), 0, received_data.getLength());
        if (MASTER_ACK.equals(received)) {
            byte[] response = SLAVE_ACK.getBytes(StandardCharsets.UTF_8);
            DatagramPacket responsePacket = new DatagramPacket(
                    response, response.length, received_data.getAddress(), received_data.getPort());
            socket.send(responsePacket);
            return received_data.getAddress();
        }
        return null;

    }

    void processTasks(InetAddress managerAddress) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(managerAddress, PORT);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Subsequent subsequent = new Subsequent();
        for (; ; ) {
            int[] numbers = (int[]) in.readObject();
            if (numbers.length == 0) {
                break;
            }
            boolean hasNotPrime = subsequent.containsNotPrime(numbers);
            out.println(hasNotPrime ? "TRUE" : "FALSE");
        }
    }
}
