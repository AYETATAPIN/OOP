package ru.nsu.demidov.primeroparallelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SlavePunisher extends Thread {
    private final Socket socket;
    private final Master master;
    private ObjectOutputStream out;
    private BufferedReader in;
    private int[] task;

    public SlavePunisher(Socket socket, Master master) throws IOException {
        this.socket = socket;
        this.master = master;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void setTask(int[] task) throws IOException {
        this.task = task;
        out.writeObject(task);
        out.flush();
    }

    @Override
    public void run() {
        boolean finished = false;
        try {
            String response;
            while ((response = in.readLine()) != null) {
                if ("TRUE".equals(response)) {
                    master.getResult().set(true);
                }
                finished = true;
                master.notifyAll();
            }
        } catch (IOException e) {
            finished = true;
            master.notifyAll();
        }
    }
}