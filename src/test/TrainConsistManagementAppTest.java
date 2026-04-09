package test;

import main.TrainConsistManagementApp;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    // Helper method
    private List<TrainConsistManagementApp.Bogie> createSampleBogies() {

        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("Sleeper", 72));
        list.add(new TrainConsistManagementApp.Bogie("AC Chair", 56));
        list.add(new TrainConsistManagementApp.Bogie("First Class", 24));
        list.add(new TrainConsistManagementApp.Bogie("Sleeper", 70));
        list.add(new TrainConsistManagementApp.Bogie("AC Chair", 60));

        return list;
    }

    // ===================== UC9 TESTS =====================

    @Test
    void testGrouping_BogiesGroupedByType() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                TrainConsistManagementApp.groupBogiesByType(bogies);

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                TrainConsistManagementApp.groupBogiesByType(bogies);

        assertEquals(2, result.get("Sleeper").size());
        assertEquals(2, result.get("AC Chair").size());
    }

    @Test
    void testGrouping_DifferentBogieTypes() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                TrainConsistManagementApp.groupBogiesByType(bogies);

        assertEquals(3, result.size());
    }

    @Test
    void testGrouping_EmptyBogieList() {
        List<TrainConsistManagementApp.Bogie> bogies = new ArrayList<>();
        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                TrainConsistManagementApp.groupBogiesByType(bogies);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGrouping_SingleBogieCategory() {
        List<TrainConsistManagementApp.Bogie> bogies = new ArrayList<>();
        bogies.add(new TrainConsistManagementApp.Bogie("Sleeper", 72));
        bogies.add(new TrainConsistManagementApp.Bogie("Sleeper", 70));

        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                TrainConsistManagementApp.groupBogiesByType(bogies);

        assertEquals(1, result.size());
        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_MapContainsCorrectKeys() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                TrainConsistManagementApp.groupBogiesByType(bogies);

        assertTrue(result.keySet().containsAll(
                Arrays.asList("Sleeper", "AC Chair", "First Class")
        ));
    }

    @Test
    void testGrouping_GroupSizeValidation() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                TrainConsistManagementApp.groupBogiesByType(bogies);

        assertEquals(2, result.get("Sleeper").size());
        assertEquals(2, result.get("AC Chair").size());
        assertEquals(1, result.get("First Class").size());
    }

    @Test
    void testGrouping_OriginalListUnchanged() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        int originalSize = bogies.size();

        TrainConsistManagementApp.groupBogiesByType(bogies);

        assertEquals(originalSize, bogies.size());
    }

    // ===================== UC10 TESTS =====================

    @Test
    void testReduce_TotalSeatCalculation() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        int result = TrainConsistManagementApp.calculateTotalSeats(bogies);

        assertEquals(72 + 56 + 24 + 70 + 60, result);
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        int result = TrainConsistManagementApp.calculateTotalSeats(bogies);

        assertTrue(result > 0);
    }

    @Test
    void testReduce_SingleBogieCapacity() {
        List<TrainConsistManagementApp.Bogie> bogies = new ArrayList<>();
        bogies.add(new TrainConsistManagementApp.Bogie("Sleeper", 72));

        int result = TrainConsistManagementApp.calculateTotalSeats(bogies);

        assertEquals(72, result);
    }

    @Test
    void testReduce_EmptyBogieList() {
        List<TrainConsistManagementApp.Bogie> bogies = new ArrayList<>();
        int result = TrainConsistManagementApp.calculateTotalSeats(bogies);

        assertEquals(0, result);
    }

    @Test
    void testReduce_CorrectCapacityExtraction() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();

        int expected = bogies.stream()
                .map(b -> b.getCapacity())
                .reduce(0, Integer::sum);

        int result = TrainConsistManagementApp.calculateTotalSeats(bogies);

        assertEquals(expected, result);
    }

    @Test
    void testReduce_AllBogiesIncluded() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        int result = TrainConsistManagementApp.calculateTotalSeats(bogies);

        assertEquals(282, result);
    }

    @Test
    void testReduce_OriginalListUnchanged() {
        List<TrainConsistManagementApp.Bogie> bogies = createSampleBogies();
        int originalSize = bogies.size();

        TrainConsistManagementApp.calculateTotalSeats(bogies);

        assertEquals(originalSize, bogies.size());
    }
}