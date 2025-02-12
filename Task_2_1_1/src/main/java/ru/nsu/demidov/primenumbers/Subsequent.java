package ru.nsu.demidov.primenumbers;

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
