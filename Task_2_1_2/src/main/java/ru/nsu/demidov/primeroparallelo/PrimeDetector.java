package ru.nsu.demidov.primeroparallelo;

public interface PrimeDetector {
    boolean containsNotPrime(int[] numbers) throws Exception;

    static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
