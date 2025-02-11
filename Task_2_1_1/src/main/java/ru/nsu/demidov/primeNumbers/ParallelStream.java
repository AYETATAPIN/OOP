package ru.nsu.demidov.primeNumbers;

import java.util.Arrays;

public class ParallelStream implements PrimeDetector {

    @Override
    public boolean containsNotPrime(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(number -> isPrime(number) == false);
    }
}
