package ru.nsu.demidov.primeroparallelo;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Master {
    private static final String SUBNET = "227.0.0.5";
    private static final String MASTER_ACK = "MASTER_ACK";
    private static final String SLAVE_ACK = "SLAVE_ACK";
    private static final int PORT = 5055;

    private final Set<InetAddress> workers = new HashSet<>();
    private final List<SlavePunisher> slavePunishers = new ArrayList<>();
    private final AtomicBoolean result = new AtomicBoolean(false);

    public boolean processNumbers(int[] numbers) {
        try {
            acknowledgeSlaves();
            envokeMaster();
            distributeParts(numbers);
            return result.get();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Царь батюшка-главный упал", e);
        }
    }

    public AtomicBoolean getResult() {
        return this.result;
    }

    private void envokeMaster() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        serverSocket.setSoTimeout(5000);

        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            try {
                Socket socket = serverSocket.accept();
                if (workers.contains(socket.getInetAddress())) {
                    SlavePunisher punisher = new SlavePunisher(socket, this);
                    slavePunishers.add(punisher);
                    punisher.start();
                } else {
                    socket.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    private void acknowledgeSlaves() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(SUBNET);

        byte[] buffer = MASTER_ACK.getBytes(StandardCharsets.UTF_8);
        DatagramPacket sent_data = new DatagramPacket(buffer, buffer.length, group, PORT);
        socket.send(sent_data);

        long end = System.currentTimeMillis() + 1000;
        while (System.currentTimeMillis() < end) {
            byte[] receivedBuffer = new byte[1024];
            DatagramPacket receivedData = new DatagramPacket(receivedBuffer, receivedBuffer.length);
            socket.receive(receivedData);

            String received = new String(receivedData.getData(), 0, receivedData.getLength());
            if (SLAVE_ACK.equals(received) == true) {
                workers.add(receivedData.getAddress());
            }
        }
    }
    

    private void distributeParts(int[] numbers) throws InterruptedException, IOException {
        int batchSize = numbers.length / slavePunishers.size();
        int remainder = numbers.length % slavePunishers.size();
        int start = 0;

        for (SlavePunisher slavePunisher : slavePunishers) {
            int end = start + batchSize;
            if (remainder > 0) {
                end++;
                remainder--;
            }
            int[] part = new int[end - start];
            System.arraycopy(numbers, start, part, start, end - start);
            slavePunisher.setTask(part);
            start = end;
        }
    }
}