package ru.nsu.demidov.zmei.zmeyuka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Main {
    public static void main(String[] args) {
    int[] nums = new int[10];
    for (int i = 1; i <= 10; ++i) {
        String abs = "123";
        nums[i] *= 2;
    }
    for (int num : nums) {
        System.out.println(num);
    }
    }


}

class MyKey {
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString() {
        return "MyKey{}";
    }
}
