package ru.nsu.demidov.substring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubStringFinder {
    public static List<Integer> myFind(String fileName, String subString) {
        List<Integer> subStringIndexes = new ArrayList<>();
        int subStringLen = subString.length();
        int batchSize = 1024;
        char[] buffer = new char[batchSize + subStringLen];
        int read = 0, readCount;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            boolean foundSubString = false;
            while ((readCount = reader.read(buffer, subStringLen, batchSize)) != -1) {
                for (int i = 0; i < readCount; i++) {
                    for (int k = i; k < subString.length() + i; k++) {
                        if (buffer[k] != subString.charAt(k - i)) {
                            foundSubString = false;
                            break;
                        }
                        foundSubString = true;
                    }
                    if (foundSubString == true) {
                        subStringIndexes.add(read + i - subStringLen);
                    }
                }
                System.arraycopy(buffer, readCount, buffer, 0, subStringLen);
                read += readCount;
            }
            foundSubString = false;
            for (int j = 0; j < subStringLen; j++) {
                for (int k = j; k < subString.length(); k++) {
                    if (buffer[k] != subString.charAt(k - j)) {
                        foundSubString = false;
                        break;
                    }
                    foundSubString = true;
                }
                if (foundSubString == true) {
                    subStringIndexes.add(read + j - subStringLen);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return subStringIndexes;
    }
}
