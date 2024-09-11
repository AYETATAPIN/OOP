package ru.nsu.demidov;

/** Heapsort is a sorting algorithm which asymptotic O(nlog(n) */

public class Sorting_machine {
    /** sift down algorithm */
    public static void sift_down(int[] arr, int index, int n) { // SiftDown
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
            sift_down(arr, max, n);
        }
    }
    /** sorting algorithm */
    public static int[] heapsort(int[] arr) { // Heap building and sorting
        int n = arr.length;
        int[] tempArr = new int[n];
        System.arraycopy(arr, 0, tempArr, 0, n);
        for (int i = n / 2 - 1; i >= 0; --i) { // Heap building
            sift_down(tempArr, i, n);
        }
        int[] sortedArr = new int[n];
        for (int i = n - 1; i >= 0; --i) { // Max & min swap and SiftDown
            sortedArr[i] = tempArr[0];
            int temp = tempArr[0];
            tempArr[0] = tempArr[i];
            tempArr[i] = temp;
            sift_down(tempArr, 0, i);
        }
        return sortedArr;
    }

    public static void main(String[] args) { // idk wouldn't run without it

    }

}

