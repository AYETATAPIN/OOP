package ru.nsu.demidov.substring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * subStringFinder class.
 */

public class SubStringFinder {

    /**
     * myFind method.
     */

    public static List<Integer> myFind(InputStream inputStream, String subString) throws Exception {
        List<Integer> subStringIndexes = new ArrayList<>();
        int subStringLen = subString.length();
        int batchSize = 1024;
        char[] buffer = new char[batchSize + subStringLen];
        int read = 0;
        int readCount;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            boolean foundSubString = false;
            while ((readCount = reader.read(buffer, subStringLen, batchSize)) != -1) {
                for (int i = 0; i < readCount; i++) {
                    for (int j = i; j < subString.length() + i; j++) {
                        if (buffer[j] != subString.charAt(j - i)) {
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
            for (int i = 0; i < subStringLen; i++) {
                for (int j = i; j < subString.length(); j++) {
                    if (buffer[j] != subString.charAt(j - i)) {
                        foundSubString = false;
                        break;
                    }
                    foundSubString = true;
                }
                if (foundSubString == true) {
                    subStringIndexes.add(read + i - subStringLen);
                }
            }
        } catch (IOException exception) {
            throw new Exception("Error opening the file: " + exception.getMessage());
        }
        return subStringIndexes;
    }
}
