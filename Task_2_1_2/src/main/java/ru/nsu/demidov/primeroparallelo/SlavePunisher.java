package ru.nsu.demidov.primeroparallelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SlavePunisher extends Thread {
    private final Master master;
    private final int slaveId;
    private ObjectOutputStream out;
    private BufferedReader in;
    private boolean taskAssigned = false;

    public SlavePunisher(Socket socket, Master master, int slaveId) throws IOException {
        this.master = master;
        this.slaveId = slaveId;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void setTask(int[] task) throws IOException {
        out.writeObject(task);
        out.flush();
        taskAssigned = true;
    }

    public boolean isTaskAssigned() {
        return taskAssigned;
    }

    @Override
    public void run() {
        boolean isMalfunctioned = false;
        try {
            String response;
            while ((response = in.readLine()) != null) {
                if ("TRUE".equals(response)) {
                    master.getResult().set(true);
                }
                isMalfunctioned = true;
                break;
            }
        } catch (IOException e) {
            System.err.println("Slave " + slaveId + " malfunctioned");
        } finally {
            master.slaveEndALert(slaveId, isMalfunctioned);
        }
    }
}