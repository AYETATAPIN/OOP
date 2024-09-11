package ru.nsu.demidov;

public class Test {
    static int[] arr = {5, 4, 3, 2, 1};
    static int[] sorted_arr = Main.heapsort(arr);

    public void print_res(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.println(sorted_arr[i]);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.println(sorted_arr[i]);
        }
    }
}
