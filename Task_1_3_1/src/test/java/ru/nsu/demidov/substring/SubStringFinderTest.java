package ru.nsu.demidov.substring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * SubStringFinderTest class.
 */

public class SubStringFinderTest {
    private File tempFile;

    @BeforeEach
    public void initEach() throws IOException {
        tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
    }

    @Test
    public void resourceTesting() throws FileNotFoundException {
        String resource = "chingchangchong.txt";
        InputStream input = getClass().getClassLoader().getResourceAsStream(resource);
        List<Integer> result = SubStringFinder.myFind(input, "肏");
        assertEquals(1, result.size());
    }
    
    @Test
    public void bigDataTesting() throws FileNotFoundException {
        long occurences = 2000000;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < occurences; i++) {
                sb.append("boobies");
            }
            writer.write(sb.toString());
        } catch (IOException exception) {
            throw new FileNotFoundException("Error opening the file: " + exception.getMessage());
        }
        List<Integer> result = SubStringFinder.myFind(new FileInputStream(tempFile),
            "boobies");
        assertEquals(occurences, result.size());
    }

    /*@Test
    public void myFindTesting() throws FileNotFoundException {
        long occurences = 1000000000L;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (long i = 0L; i < occurences; i++) {
                writer.write("boobsieboobiesboobs");
            }
            writer.write("boobsieboobiesboobs");
            writer.write("bebra");
        } catch (IOException exception) {
            throw new FileNotFoundException("Error opening the file: " + exception.getMessage());
        }
        String subString = "bebra";
        List<Integer> result = SubStringFinder.myFind(new FileInputStream(tempFile), subString);
        assertEquals(1, result.size());
    }*/

    @Test
    public void overlappingTesting() throws FileNotFoundException {
        long occurences = 100000;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < occurences; i++) {
                sb.append("аааааааааааааааааааааа");
            }
            writer.write(sb.toString());
        } catch (IOException exception) {
            throw new FileNotFoundException("Error opening the file: " + exception.getMessage());
        }
        String subString = "ааааааааааа";
        List<Integer> result = SubStringFinder.myFind(new FileInputStream(tempFile), subString);
        assertEquals(occurences * subString.length() * 2, result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(i, result.get(i).intValue());
        }
    }

    /**
     * AfterEach method.
     */

    @AfterEach
    public void deleteEach() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }
}