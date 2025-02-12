package ru.nsu.demidov.primenumbers;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {6, 8, 7, 13, 5, 9, 4};
        int[] arr2 = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};

        PrimeDetector sequentialDetector = new Subsequent();
        System.out.println("Sequential (Array 1): " + sequentialDetector.containsNotPrime(arr1));
        System.out.println("Sequential (Array 2): " + sequentialDetector.containsNotPrime(arr2));

        PrimeDetector parallelThreadDetector = new Parallel(4);
        System.out.println("Parallel Thread (Array 1): " + parallelThreadDetector.containsNotPrime(arr1));
        System.out.println("Parallel Thread (Array 2): " + parallelThreadDetector.containsNotPrime(arr2));

        PrimeDetector parallelStreamDetector = new ParallelStream() ;
        System.out.println("Parallel Stream (Array 1): " + parallelStreamDetector.containsNotPrime(arr1));
        System.out.println("Parallel Stream (Array 2): " + parallelStreamDetector.containsNotPrime(arr2));
    }
}