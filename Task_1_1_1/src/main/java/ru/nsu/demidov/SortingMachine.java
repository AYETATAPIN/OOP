package ru.nsu.demidov;

import java.util.Random;
/**
 * heapsort algorithm on Java.
 *
 * @author d.demidov
 * @version 1
 */

public class SortingMachine {
    /**
     * sift down algorithm.
     */
    public static void siftDown(int[] arr, int index, int n) { // SiftDown
        int max = index;
        int left = max * 2 + 1;
        int right = max * 2 + 2;
        if (left < n && arr[left] > arr[max]) {
            max = left;
        }
        if (right < n && arr[right] > arr[max]) {
            max = right;
        }
        if (max != index) {
            int temp = arr[max];
            arr[max] = arr[index];
            arr[index] = temp;
            siftDown(arr, max, n);
        }
    }

    /**
     * sorting algorithm.
     */

    public static int[] heapsort(int[] arr) { // Heap building and sorting
        int n = arr.length;
        int[] tempArr = new int[n];
        System.arraycopy(arr, 0, tempArr, 0, n);
        for (int i = n / 2 - 1; i >= 0; --i) { // Heap building
            siftDown(tempArr, i, n);
        }
        int[] sortedArr = new int[n];
        for (int i = n - 1; i >= 0; --i) { // Max & min swap and SiftDown
            sortedArr[i] = tempArr[0];
            int temp = tempArr[0];
            tempArr[0] = tempArr[i];
            tempArr[i] = temp;
            siftDown(tempArr, 0, i);
        }
        return sortedArr;
    }

    /**
     * asymptotic testing.
     */
    public static void main(String[] args) { // idk wouldn't run without it
        Random number = new Random();
        int[] arr1000 = new int[1000];
        for (int i = 0; i < 1000; ++i) {
            arr1000[i] = number.nextInt(-100000000, 100000000) + 1;
        }
        double time = System.currentTimeMillis();
        int[] sortedArr1000 = heapsort(arr1000);
        System.out.println("1000 elements - " + (double) (System.currentTimeMillis() - time));
        int[] arr10000 = new int[10000];
        for (int i = 0; i < 10000; ++i) {
            arr10000[i] = number.nextInt(-100000000, 100000000) + 1;
        }
        time = System.currentTimeMillis();
        int[] sortedArr10000 = heapsort(arr10000);
        System.out.println("10000 elements - " + (double) (System.currentTimeMillis() - time));
        int[] arr100000 = new int[100000];
        for (int i = 0; i < 100000; ++i) {
            arr100000[i] = number.nextInt(-100000000, 100000000) + 1;
        }
        time = System.currentTimeMillis();
        int[] sortedArr100000 = heapsort(arr100000);
        System.out.println("100000 elements - " + (double) (System.currentTimeMillis() - time));
        int[] arr1000000 = new int[1000000];
        for (int i = 0; i < 1000000; ++i) {
            arr1000000[i] = number.nextInt(-100000000, 100000000) + 1;
        }
        time = System.currentTimeMillis();
        int[] sortedArr1000000 = heapsort(arr1000000);
        System.out.println("1000000 elements - " + (double) (System.currentTimeMillis() - time));
        int[] arr2000000 = new int[2000000];
        for (int i = 0; i < 2000000; ++i) {
            arr2000000[i] = number.nextInt(-100000000, 100000000) + 1;
        }
        time = System.currentTimeMillis();
        int[] sortedArr2000000 = heapsort(arr2000000);
        System.out.println("2000000 elements - " + (double) (System.currentTimeMillis() - time));
        int[] arr5000000 = new int[5000000];
        for (int i = 0; i < 5000000; ++i) {
            arr5000000[i] = number.nextInt(-100000000, 100000000) + 1;
        }
        time = System.currentTimeMillis();
        int[] sortedArr5000000 = heapsort(arr5000000);
        System.out.println("5000000 elements - " + (double) (System.currentTimeMillis() - time));
        int[] arr10000000 = new int[10000000];
        for (int i = 0; i < 10000000; ++i) {
            arr10000000[i] = number.nextInt(-100000000, 100000000) + 1;
        }
        time = System.currentTimeMillis();
        int[] sortedArr10000000 = heapsort(arr10000000);
        System.out.println("10000000 elements - " + (double) (System.currentTimeMillis() - time));
    }

}
