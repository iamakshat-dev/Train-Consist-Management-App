package test;

import main.TrainConsistManagementApp;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

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

    // ================= UC12 =================
    @Test
    void testSafety_AllBogiesValid() {
        List<TrainConsistManagementApp.GoodsBogie> list = Arrays.asList(
                new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Petroleum"),
                new TrainConsistManagementApp.GoodsBogie("Open", "Coal")
        );
        assertTrue(TrainConsistManagementApp.isSafetyCompliant(list));
    }

    // ================= UC13 =================

    @Test
    void testLoopFilteringLogic() {
        var result = TrainConsistManagementApp.filterBogiesUsingLoop(sampleBogies());
        assertTrue(result.stream().allMatch(b -> b.getCapacity() > 60));
    }

    @Test
    void testStreamFilteringLogic() {
        var result = TrainConsistManagementApp.filterBogiesUsingStream(sampleBogies());
        assertTrue(result.stream().allMatch(b -> b.getCapacity() > 60));
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        var loop = TrainConsistManagementApp.filterBogiesUsingLoop(sampleBogies());
        var stream = TrainConsistManagementApp.filterBogiesUsingStream(sampleBogies());
        assertEquals(loop.size(), stream.size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        long loopTime = TrainConsistManagementApp.measureLoopExecutionTime(sampleBogies());
        long streamTime = TrainConsistManagementApp.measureStreamExecutionTime(sampleBogies());

        assertTrue(loopTime > 0);
        assertTrue(streamTime > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            list.add(new TrainConsistManagementApp.Bogie("Sleeper", 50 + (i % 50)));
        }

        var result = TrainConsistManagementApp.filterBogiesUsingStream(list);
        assertNotNull(result);
    }
}