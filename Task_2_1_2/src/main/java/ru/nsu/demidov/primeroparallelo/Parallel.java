package ru.nsu.demidov.primeroparallelo;

import java.util.concurrent.atomic.AtomicBoolean;
public class Parallel implements PrimeDetector {
    private final int threadCount;

    public Parallel(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public boolean containsNotPrime(int[] numbers) {
        if (numbers.length == 0) {
            return false;
        }
        AtomicBoolean result = new AtomicBoolean(false);
        Thread[] threads = new Thread[threadCount];
        int batchSize = numbers.length / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int start = i * batchSize;
            int end = (i == threadCount - 1) ? numbers.length : start + batchSize;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (!PrimeDetector.isPrime(numbers[j])) {
                        result.set(true);
                        break;
                    }
                }
            });
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread execution interrupted", e);
        }
        return result.get();
    }
}