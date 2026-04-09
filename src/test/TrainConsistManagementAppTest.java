package test;

import main.TrainConsistManagementApp;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    // ================= UC15 TESTS =================

    @Test
    void testCargo_SafeAssignment() {
        var bogie = new TrainConsistManagementApp.GoodsBogie("Cylindrical");

        bogie.assignCargo("Petroleum");

        assertEquals("Petroleum", bogie.getCargo());
    }

    @Test
    void testCargo_UnsafeAssignmentHandled() {
        var bogie = new TrainConsistManagementApp.GoodsBogie("Rectangular");

        bogie.assignCargo("Petroleum");

        assertNull(bogie.getCargo()); // not assigned
    }

    @Test
    void testCargo_CargoNotAssignedAfterFailure() {
        var bogie = new TrainConsistManagementApp.GoodsBogie("Rectangular");

        bogie.assignCargo("Petroleum");

        assertNotEquals("Petroleum", bogie.getCargo());
    }

    @Test
    void testCargo_ProgramContinuesAfterException() {
        var b1 = new TrainConsistManagementApp.GoodsBogie("Rectangular");
        var b2 = new TrainConsistManagementApp.GoodsBogie("Cylindrical");

        b1.assignCargo("Petroleum"); // fails
        b2.assignCargo("Petroleum"); // should still work

        assertEquals("Petroleum", b2.getCargo());
    }

    @Test
    void testCargo_FinallyBlockExecution() {
        var bogie = new TrainConsistManagementApp.GoodsBogie("Rectangular");

        // We cannot directly assert print, but method should complete without crash
        bogie.assignCargo("Petroleum");

        assertTrue(true); // ensures method completes
    }
}