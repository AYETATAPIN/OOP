package ru.nsu.demidov.substring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;


public class SubStringFinderTest {
    private File tempFile;

    @BeforeEach
    public void initEach() throws IOException, IOException {
        tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
    }

    @Test
    public void bigDataTesting() throws Exception {
        long Occurences = 2000000;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Occurences; i++) {
                sb.append("boobies");
            }
            writer.write(sb.toString());
        }
        List<Integer> result = SubStringFinder.myFind(new FileInputStream(tempFile), "boobies");
        assertEquals(Occurences, result.size());
    }

    @Test
    public void myFindTesting() throws Exception {
        long Occurences = 1000000000L;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            StringBuilder sb = new StringBuilder();
            for (long iL = 0L; iL < Occurences; iL++) {
                //sb.append("boobsieboobiesboobs");
                writer.write("boobsieboobiesboobs");
            }
            sb.append("bebra");
            writer.write("boobsieboobiesboobs");
            writer.write("bebra");
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        String subString = "bebra";
        List<Integer> result = SubStringFinder.myFind(new FileInputStream(tempFile), subString);
        assertEquals(1, result.size());
    }

    @Test
    public void overlappingTesting() throws Exception {
        long Occurences = 100000;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Occurences; i++) {
                sb.append("аааааааааааааааааааааа");
            }
            writer.write(sb.toString());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        String subString = "ааааааааааа";
        List<Integer> result = SubStringFinder.myFind(new FileInputStream(tempFile), subString);
        assertEquals(Occurences * subString.length() * 2, result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(i, result.get(i).intValue());
        }
    }

    @AfterEach
    public void deleteEach() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }
}