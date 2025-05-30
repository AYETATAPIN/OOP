package ru.nsu.demidov.primeroparallelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PrimeDetectorTest {
    @Test
    public void testIsPrime() {
        assertFalse(PrimeDetector.isPrime(0));
        assertFalse(PrimeDetector.isPrime(1));
        assertTrue(PrimeDetector.isPrime(2));
        assertTrue(PrimeDetector.isPrime(3));
        assertFalse(PrimeDetector.isPrime(4));
        assertFalse(PrimeDetector.isPrime(15));
    }
}