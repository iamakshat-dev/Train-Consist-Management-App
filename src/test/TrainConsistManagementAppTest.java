package test;

import main.TrainConsistManagementApp;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    // ================= UC14 TESTS =================

    @Test
    void testException_ValidCapacityCreation() throws Exception {
        var bogie = new TrainConsistManagementApp.Bogie("Sleeper", 72);
        assertNotNull(bogie);
    }

    @Test
    void testException_NegativeCapacityThrowsException() {
        Exception ex = assertThrows(
                TrainConsistManagementApp.InvalidCapacityException.class,
                () -> new TrainConsistManagementApp.Bogie("Sleeper", -10)
        );

        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    void testException_ZeroCapacityThrowsException() {
        assertThrows(
                TrainConsistManagementApp.InvalidCapacityException.class,
                () -> new TrainConsistManagementApp.Bogie("Sleeper", 0)
        );
    }

    @Test
    void testException_ExceptionMessageValidation() {
        Exception ex = assertThrows(
                TrainConsistManagementApp.InvalidCapacityException.class,
                () -> new TrainConsistManagementApp.Bogie("AC Chair", 0)
        );

        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    void testException_ObjectIntegrityAfterCreation() throws Exception {
        var bogie = new TrainConsistManagementApp.Bogie("Sleeper", 72);

        assertEquals("Sleeper", bogie.getName());
        assertEquals(72, bogie.getCapacity());
    }

    @Test
    void testException_MultipleValidBogiesCreation() throws Exception {
        List<TrainConsistManagementApp.Bogie> list = Arrays.asList(
                new TrainConsistManagementApp.Bogie("Sleeper", 72),
                new TrainConsistManagementApp.Bogie("AC Chair", 56),
                new TrainConsistManagementApp.Bogie("First Class", 24)
        );

        assertEquals(3, list.size());
    }
}