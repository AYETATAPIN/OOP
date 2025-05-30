package ru.nsu.demidov.primeroparallelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MasterTest {
    @Test
    public void testProcessNumbers() {
        Master master = new Master();
        int[] arr = new int[]{1, 2, 3};
        int[] arr_prime = new int[]{3};
        assertFalse(master.processNumbers(arr));
        assertTrue(master.processNumbers(arr_prime));
        master.slaveEndAlert(0, true);
        master.slaveEndAlert(1, false);
    }
}