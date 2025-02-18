package ru.nsu.demidov.primeNumbers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import org.junit.jupiter.api.Test;

/**
 * PrimeDetectorTest class.
 */

public class PrimeDetectorTest {

    @Test
    public void testIsPrime() {
        assertTrue(PrimeDetector.isPrime(2));
        assertTrue(PrimeDetector.isPrime(3));
        assertFalse(PrimeDetector.isPrime(1));
        assertFalse(PrimeDetector.isPrime(4));
    }

    @ParameterizedTest
    @ArgumentsSource(PrimeDetectorProvider.class)
    void testContainsNotPrimeShortArr(PrimeDetector primeDetector) {
        int[] shortArr = {6, 8, 7, 13, 5, 9, 4};
        Assertions.assertTrue(primeDetector.containsNotPrime(shortArr));
    }

    @ParameterizedTest
    @ArgumentsSource(PrimeDetectorProvider.class)
    void testContainsNotPrimeLongArr(PrimeDetector primeDetector) {
        int[] longArr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009,
           6998029, 6998039, 20165149, 6998051, 6998053};
        Assertions.assertFalse(primeDetector.containsNotPrime(longArr));
    }

    @ParameterizedTest
    @ArgumentsSource(PrimeDetectorProvider.class)
    void testPrimeArr(PrimeDetector primeDetector) {
        int[] primeArr = {2, 3, 5, 7, 11, 13};
        Assertions.assertFalse(primeDetector.containsNotPrime(primeArr));
    }

    @ParameterizedTest
    @ArgumentsSource(PrimeDetectorProvider.class)
    void testNonPrimeArray(PrimeDetector primeDetector) {
        int[] nonPrimeArr = {4, 6, 8, 9, 10, 12};
        Assertions.assertTrue(primeDetector.containsNotPrime(nonPrimeArr));
    }

    static class PrimeDetectorProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new Subsequent()),
                    Arguments.of(new Parallel(2)),
                    Arguments.of(new Parallel(4)),
                    Arguments.of(new ParallelStream())
            );
        }
    }
}
