package ru.nsu.demidov.primenumbers;

import java.util.Arrays;

/**
 * ParallelStream class.
 */

public class ParallelStream implements PrimeDetector {

    /**
     * containsNotPrime method.
     */

    @Override
    public boolean containsNotPrime(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(number -> isPrime(number) == false);
    }
}
