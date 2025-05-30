package ru.nsu.demidov.primeroparallelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubsequentTest {
    @Test
    public void testContainsNotPrime() {
        Subsequent subsequent = new Subsequent();
        int[] arr = new int[]{1, 2, 3};
        int[] arr_prime = new int[]{3};
        assertTrue(subsequent.containsNotPrime(arr));
        assertFalse(subsequent.containsNotPrime(arr_prime));
    }
}