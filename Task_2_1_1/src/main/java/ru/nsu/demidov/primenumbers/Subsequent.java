package ru.nsu.demidov.primenumbers;

/**
 * Subsequent class.
 */

public class Subsequent implements PrimeDetector {
    @Override
    public boolean containsNotPrime(int[] numbers) {
        for (int number : numbers) {
            if (PrimeDetector.isPrime(number) == false) {
                return true;
            }
        }
        return false;
    }
}
