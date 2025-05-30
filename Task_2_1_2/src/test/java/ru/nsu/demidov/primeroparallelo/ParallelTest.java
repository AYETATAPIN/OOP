package ru.nsu.demidov.primeroparallelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParallelTest {
    @Test
    public void testContainsNotPrime() {
        Parallel parallel = new Parallel(2);
        int[] arr = new int[]{1, 2, 3};
        int[] arr_prime = new int[]{3};
        assertTrue(parallel.containsNotPrime(arr));
        assertFalse(parallel.containsNotPrime(arr_prime));
    }
}