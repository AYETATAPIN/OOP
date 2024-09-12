package ru.nsu.demidov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void reversed_test() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, SortingMachine.heapsort(new int[]{5, 4, 3, 2,
                1}));
    }
    @Test
    public void same_elements_test() {
        assertArrayEquals(new int[]{1, 1, 1, 1, 1}, SortingMachine.heapsort(new int[]{1, 1, 1, 1,
                1}));

    }
    @Test
    public void single_element_test() {
        assertArrayEquals(new int[]{1}, SortingMachine.heapsort(new int[]{1}));
    }
    @Test
    public void two_elements_sorted_test() {
        assertArrayEquals(new int[]{1, 5}, SortingMachine.heapsort(new int[]{1, 5}));
    }
    @Test
    public void two_elements_unsorted_test() {
        assertArrayEquals(new int[]{1, 5}, SortingMachine.heapsort(new int[]{5, 1}));
    }
    @Test
    public void sorted_test() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, SortingMachine.heapsort(new int[]{1, 2, 4, 3,
                    5}));
    }
    @Test
    public void extreme_values_unsorted_test() {
        assertArrayEquals(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE},
                SortingMachine.heapsort(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
    }
    @Test
    public void extreme_values_sorted_test() {
        assertArrayEquals(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE},
                SortingMachine.heapsort(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
    }

}
