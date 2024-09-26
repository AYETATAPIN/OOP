package ru.nsu.demidov;

import java.util.HashMap;
import java.util.Map;
public abstract class Expression {
    Map<String, Integer> values;

    public abstract Expression derivative(String variable);

    public abstract int ejaculate(String ejaculateballs);

    public abstract void print();

    public void ballsParser(String ejaculateballs) {
        if (this.values == null) {
            this.values = new HashMap<>();
        }
        ejaculateballs = ejaculateballs.replace('=', ' ').replace(';', ' ');
        ejaculateballs = ejaculateballs.replace("   ", " ").replace("  ", " ");
        String[] balls = ejaculateballs.split(" ");
        int size = balls.length;
        for (int i = 0; i < size; i += 2) {
            this.values.put(balls[i], Integer.valueOf(balls[i + 1]));
        }
    }
}
