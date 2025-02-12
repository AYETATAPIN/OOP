package ru.nsu.demidov.primeNumbers;

/**
 * Subsequent class.
 */

public class Subsequent implements PrimeDetector {
    @Override
    public boolean containsNotPrime(int[] numbers) {
        for (int number : numbers) {
            if (isPrime(number) == false) {
                return true;
            }
        }
        return false;
    }
}
