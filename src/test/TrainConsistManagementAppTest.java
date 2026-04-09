package test;

import main.TrainConsistManagementApp;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    // Helper
    private List<TrainConsistManagementApp.Bogie> sampleBogies() {
        return Arrays.asList(
                new TrainConsistManagementApp.Bogie("Sleeper", 72),
                new TrainConsistManagementApp.Bogie("AC Chair", 56),
                new TrainConsistManagementApp.Bogie("First Class", 24),
                new TrainConsistManagementApp.Bogie("Sleeper", 70),
                new TrainConsistManagementApp.Bogie("AC Chair", 60)
        );
    }

    // ================= UC9 =================
    @Test
    void testGrouping() {
        var result = TrainConsistManagementApp.groupBogiesByType(sampleBogies());
        assertTrue(result.containsKey("Sleeper"));
    }

    // ================= UC10 =================
    @Test
    void testTotalSeats() {
        assertEquals(282,
                TrainConsistManagementApp.calculateTotalSeats(sampleBogies()));
    }

    // ================= UC11 =================
    @Test
    void testValidTrainId() {
        assertTrue(TrainConsistManagementApp.isValidTrainId("TRN-1234"));
    }

    @Test
    void testInvalidTrainId() {
        assertFalse(TrainConsistManagementApp.isValidTrainId("TRN12"));
    }

    @Test
    void testValidCargo() {
        assertTrue(TrainConsistManagementApp.isValidCargoCode("PET-AB"));
    }

    @Test
    void testInvalidCargo() {
        assertFalse(TrainConsistManagementApp.isValidCargoCode("PET-ab"));
    }

    // ================= UC12 =================

    @Test
    void testSafety_AllBogiesValid() {
        List<TrainConsistManagementApp.GoodsBogie> list = Arrays.asList(
                new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Petroleum"),
                new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Petroleum")
        );

        assertTrue(TrainConsistManagementApp.isSafetyCompliant(list));
    }

    @Test
    void testSafety_CylindricalWithInvalidCargo() {
        List<TrainConsistManagementApp.GoodsBogie> list = Arrays.asList(
                new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Coal")
        );

        assertFalse(TrainConsistManagementApp.isSafetyCompliant(list));
    }

    @Test
    void testSafety_NonCylindricalBogiesAllowed() {
        List<TrainConsistManagementApp.GoodsBogie> list = Arrays.asList(
                new TrainConsistManagementApp.GoodsBogie("Open", "Coal"),
                new TrainConsistManagementApp.GoodsBogie("Box", "Grain")
        );

        assertTrue(TrainConsistManagementApp.isSafetyCompliant(list));
    }

    @Test
    void testSafety_MixedBogiesWithViolation() {
        List<TrainConsistManagementApp.GoodsBogie> list = Arrays.asList(
                new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Petroleum"),
                new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Coal")
        );

        assertFalse(TrainConsistManagementApp.isSafetyCompliant(list));
    }

    @Test
    void testSafety_EmptyBogieList() {
        assertTrue(TrainConsistManagementApp.isSafetyCompliant(new ArrayList<>()));
    }
}