package ru.nsu.demidov.primeroparallelo;

import java.util.Arrays;
public class ParallelStream implements PrimeDetector {
    @Override
    public boolean containsNotPrime(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(number -> !PrimeDetector.isPrime(number));
    }
}