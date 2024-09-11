package ru.nsu.demidov;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    public void reversed_test() {
        Arrays.equals(new int[]{1, 2, 3, 4, 5}, Sorting_machine.heapsort(new int[]{5, 4, 3, 2, 1}));
    }

    public void same_elements_test() {
        Arrays.equals(new int[]{1, 1, 1, 1, 1}, Sorting_machine.heapsort(new int[]{1, 1, 1, 1, 1}));
    }

    public void single_element_test() {
        Arrays.equals(new int[]{1}, Sorting_machine.heapsort(new int[]{1}));
    }

    public void two_elements_sorted_test() {
        Arrays.equals(new int[]{1, 5}, Sorting_machine.heapsort(new int[]{1, 5}));
    }

    public void two_elements_unsorted_test() {
        Arrays.equals(new int[]{1, 5}, Sorting_machine.heapsort(new int[]{5, 1}));
    }

    public void sorted_test() {
        Arrays.equals(new int[]{1, 2, 3, 4, 5}, Sorting_machine.heapsort(new int[]{1, 2, 4, 3, 5}));
    }

    public void extreme_values_unsorted_test() {
        Arrays.equals(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE},
                Sorting_machine.heapsort(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
    }

    public void extreme_values_sorted_test() {
        Arrays.equals(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE},
                Sorting_machine.heapsort(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
    }

}
