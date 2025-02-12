package ru.nsu.demidov.primenumbers;

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
            int end = start + batchSize;
            if (i == threadCount - 1) {
                end = numbers.length;
            }
            threads[i] = createThread(numbers, start, end, result);
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Царя батюшку-главного прервали", e);
        }
        return result.get();
    }

    private Thread createThread(int[] numbers, int start, int end, AtomicBoolean result) {
        return new Thread(() -> {
            for (int i = start; i < end; i++) {
                if (isPrime(numbers[i]) == false) {
                    result.set(true);
                    break;
                }
            }
        });
    }
}


