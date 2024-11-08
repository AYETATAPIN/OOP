package ru.nsu.demidov.substring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubStringFinderTest {
    private File tempFile;

    @BeforeEach
    public void initEach() throws IOException {
        tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
    }

    @Test
    public void bigDataTesting() throws Exception {
        long OCCURENCES = 2000000;
        List<Integer> result = SubStringFinder.myFind("input.txt", "boobies");
        assertEquals(OCCURENCES, result.size());
        /*for (int i = 0; i < result.size(); i++) {
            assertEquals(i * 19 + 7, result.get(i).intValue());
        }*/

    }

    @Test
    public void myFindTesting() throws Exception {
        long OCCURENCES = 100000000000000L;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            StringBuilder sb = new StringBuilder();
            for (long iL = 0L; iL < OCCURENCES; iL++) { 
                sb.append("boobsieboobiesboobs");
            }
            sb.append("bebra");
            writer.write(sb.toString());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        String subString = "bebra";
        List<Integer> result = SubStringFinder.myFind(tempFile.getAbsolutePath(), subString);
        assertEquals(1, result.size());
    }

    @Test
    public void overlappingTesting() throws Exception {
        long OCCURENCES = 100000;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < OCCURENCES; i++) {
                sb.append("аааааааааааааааааааааа");
            }
            writer.write(sb.toString());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        String subString = "ааааааааааа";
        List<Integer> result = SubStringFinder.myFind(tempFile.getAbsolutePath(), subString);
        assertEquals(OCCURENCES * subString.length() * 2, result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(i, result.get(i).intValue());
        }
    }
}