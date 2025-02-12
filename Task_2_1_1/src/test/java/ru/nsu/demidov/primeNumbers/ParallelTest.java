package ru.nsu.demidov.primeNumbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Subsequent test.
 */

public class ParallelTest {
    @Test
    public void simpleTest() {
        int[] shortArr = {6, 8, 7, 13, 5, 9, 4};
        Parallel parallel = new Parallel(4);
        assertEquals(true, parallel.containsNotPrime(shortArr));
        int[] longArr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029,
                6998039, 20165149, 6998051, 6998053};
        assertEquals(false, parallel.containsNotPrime(longArr));
    }

}
