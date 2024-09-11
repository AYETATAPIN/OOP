package ru.nsu.demidov;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

// (.)(.)

class MainTest {
    @Test
    public void testing_machine() {
        Arrays.equals(new int[]{1, 2, 3, 4, 5}, Main.heapsort(new int[]{5, 4, 3, 2, 1}));
        Arrays.equals(new int[]{1, 1, 1, 1, 1}, Main.heapsort(new int[]{1, 1, 1, 1, 1}));
        Arrays.equals(new int[]{1}, Main.heapsort(new int[]{1}));
        Arrays.equals(new int[]{1, 5}, Main.heapsort(new int[]{1, 5}));
        Arrays.equals(new int[]{1, 5}, Main.heapsort(new int[]{5, 1}));
        Arrays.equals(new int[]{1, 2, 3, 4, 5}, Main.heapsort(new int[]{1, 2, 4, 3, 5}));
        Arrays.equals(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, Main.heapsort(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
    }
}
