package ru.nsu.demidov.primeNumbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SubsequentTest {
    @Test
    public void simpleTest() {
        int[] shortArr = {6, 8, 7, 13, 5, 9, 4};
        Subsequent subsequent = new Subsequent();
        assertEquals(true, subsequent.containsNotPrime(shortArr));
        int[] longArr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029,
                6998039, 20165149, 6998051, 6998053};
        assertEquals(false, subsequent.containsNotPrime(longArr));
    }

}
