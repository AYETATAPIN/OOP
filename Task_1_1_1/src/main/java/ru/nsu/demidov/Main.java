package ru.nsu.demidov;

/** Heapsort is a sorting algorithm which asymptotic O(nlog(n) */

public class Main { /** main */
    public static void swapping(int[] arr, int index, int n) { /** SiftDown */
        int max = index;
        int left = max * 2 + 1, right = max * 2 + 2;
        if (left < n && arr[left] > arr[max])
            max = left;
        if (right < n && arr[right] > arr[max])
            max = right;
        if (max != index) {
            int temp = arr[max];
            arr[max] = arr[index];
            arr[index] = temp;
            swapping(arr, max, n);
        }
    }

    public static int[] heapsort(int[] arr) { /** Heap building and sorting */
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; --i) { /** Heap building */
            swapping(arr, i, n);
        }
        int[] sorted_arr = new int[n];
        for (int i = n - 1; i >= 0; --i) { /** Max & min swap and SiftDown */
            sorted_arr[i] = arr[0];
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swapping(arr, 0, i);
        }
        return sorted_arr;
    }

    public static void main(String[] args) { /** idk wouldn't run without it */

    }

}

