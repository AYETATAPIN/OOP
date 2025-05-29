package ru.nsu.demidov.primeroparallelo;

import org.graalvm.collections.Pair;
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
    private static final int TASK_TIMEOUT = 10000;
    private final Set<InetAddress> slaves = new HashSet<>();
    private final List<SlavePunisher> slavePunishers = new ArrayList<>();
    private final AtomicBoolean result = new AtomicBoolean(false);
    private final Map<Integer, Boolean> slavesStatus = new HashMap<>();
    private final List<Pair<Integer, Integer>> taskRanges = new ArrayList<>();
    private int[] currentNumbers;

    public boolean processNumbers(int[] numbers) {
        this.currentNumbers = numbers;
        try {
            acknowledgeSlaves();
            envokeMaster();
            distributeTasks();
            waitForCompletion();
            return result.get();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Царь батюшка-главный упал", e);
        }
    }

    public synchronized void slaveEndAlert(int slaveId, boolean success) {
        slavesStatus.put(slaveId, success);
        notifyAll();
    }

    private void envokeMaster() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        serverSocket.setSoTimeout(5000);

        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            try {
                Socket socket = serverSocket.accept();
                if (slaves.contains(socket.getInetAddress())) {
                    int slaveId = slavePunishers.size();
                    SlavePunisher punisher = new SlavePunisher(socket, this, slaveId);
                    slavePunishers.add(punisher);
                    slavesStatus.put(slaveId, false);
                    punisher.start();
                } else {
                    socket.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    private void distributeTasks() throws IOException {
        if (slavePunishers.isEmpty() || currentNumbers.length == 0) {
            return;
        }
        int batchSize = currentNumbers.length / slavePunishers.size();
        int remainder = currentNumbers.length % slavePunishers.size();
        int start = 0;
        taskRanges.clear();

        for (SlavePunisher punisher : slavePunishers) {
            int end = start + batchSize;
            if (remainder > 0) {
                end++;
                remainder--;
            }
            taskRanges.add(Pair.create(start, end));
            int[] task = Arrays.copyOfRange(currentNumbers, start, end);
            punisher.setTask(task);
            start = end;
        }
    }

    private synchronized void waitForCompletion() throws InterruptedException, IOException {
        long end = System.currentTimeMillis() + TASK_TIMEOUT;
        while (System.currentTimeMillis() < end) {
            boolean noneMalfunctions = true;
            List<Integer> malfunctionedSlaves = new ArrayList<>();

            for (int i = 0; i < slavePunishers.size(); i++) {
                Boolean isMalfunctioned = slavesStatus.get(i);
                if (isMalfunctioned == null) {
                    noneMalfunctions = false;
                } else if (isMalfunctioned == false && slavePunishers.get(i).isTaskAssigned()) {
                    malfunctionedSlaves.add(i);
                }
            }
            if (noneMalfunctions == true || result.get() == true) {
                break;
            }

            if (malfunctionedSlaves.isEmpty() == false) {
                assertNewTasks(malfunctionedSlaves);
            }
            wait(1000);
        }

        for (SlavePunisher slavePunisher : slavePunishers) {
            try {
                if (slavePunisher.isAlive()) {
                    slavePunisher.interrupt();
                    slavePunisher.join(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void assertNewTasks(List<Integer> malfunctionedSlaves) throws IOException {
        for (int malfunctionedSlave : malfunctionedSlaves) {
            Pair<Integer, Integer> range = taskRanges.get(malfunctionedSlave);
            int[] task = Arrays.copyOfRange(currentNumbers, range.getLeft(), range.getRight());
            Socket socket = new Socket(getFreeSlave(), PORT);
            int taskSize = slavePunishers.size();
            SlavePunisher slavePunisher = new SlavePunisher(socket, this, taskSize);
            slavePunishers.add(slavePunisher);
            slavesStatus.put(taskSize, false);
            slavePunisher.setTask(task);
            slavePunisher.start();
        }
    }

    private InetAddress getFreeSlave() {
        return slaves.iterator().next();
    }

    private void acknowledgeSlaves() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(SUBNET);
        byte[] buffer = MASTER_ACK.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
        socket.send(packet);
        long endTime = System.currentTimeMillis() + 1000;
        while (System.currentTimeMillis() < endTime) {
            byte[] receivedBuffer = new byte[1024];
            DatagramPacket receivedPacket = new DatagramPacket(receivedBuffer, receivedBuffer.length);
            socket.receive(receivedPacket);
            String response = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            if (SLAVE_ACK.equals(response)) {
                slaves.add(receivedPacket.getAddress());
            }
        }
    }

    public AtomicBoolean getResult() {
        return result;
    }
}