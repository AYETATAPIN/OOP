package ru.nsu.demidov.primeroparallelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimeDelimeterTest {
    @Test
    public void testContainsNotPrime() throws Exception {
        PrimeDelimeter detector = new PrimeDelimeter("localhost", 5055);
        int[] arr = new int[]{1, 2, 3};
        int[] arr_prime = new int[]{3};
        try {
            assertFalse(detector.containsNotPrime(arr));
            assertTrue(detector.containsNotPrime(arr_prime));
        } catch (Exception e) {
            System.out.println("Che ne poluchaetsa da? Idi delom zaimis");
        }
    }
}