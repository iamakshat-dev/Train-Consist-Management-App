package main;

import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

public class TrainConsistManagementApp {

    // Passenger Bogie
    public static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String getName() { return name; }
        public int getCapacity() { return capacity; }
    }

    // Goods Bogie (UC12)
    public static class GoodsBogie {
        private String type;
        private String cargo;

        public GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        public String getType() { return type; }
        public String getCargo() { return cargo; }
    }

    // ================= UC9 =================
    public static Map<String, List<Bogie>> groupBogiesByType(List<Bogie> bogies) {
        return bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));
    }

    // ================= UC10 =================
    public static int calculateTotalSeats(List<Bogie> bogies) {
        return bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
    }

    // ================= UC11 =================
    public static boolean isValidTrainId(String trainId) {
        return Pattern.matches("TRN-\\d{4}", trainId);
    }

    public static boolean isValidCargoCode(String cargoCode) {
        return Pattern.matches("PET-[A-Z]{2}", cargoCode);
    }

    // ================= UC12 =================
    public static boolean isSafetyCompliant(List<GoodsBogie> bogies) {
        return bogies.stream()
                .allMatch(b ->
                        !b.getType().equalsIgnoreCase("Cylindrical")
                                || b.getCargo().equalsIgnoreCase("Petroleum")
                );
    }

    // ================= UC13 =================

    // Loop filtering
    public static List<Bogie> filterBogiesUsingLoop(List<Bogie> bogies) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.getCapacity() > 60) {
                result.add(b);
            }
        }
        return result;
    }

    // Stream filtering
    public static List<Bogie> filterBogiesUsingStream(List<Bogie> bogies) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .toList();
    }

    // Loop timing
    public static long measureLoopExecutionTime(List<Bogie> bogies) {
        long start = System.nanoTime();
        filterBogiesUsingLoop(bogies);
        long end = System.nanoTime();
        return end - start;
    }

    // Stream timing
    public static long measureStreamExecutionTime(List<Bogie> bogies) {
        long start = System.nanoTime();
        filterBogiesUsingStream(bogies);
        long end = System.nanoTime();
        return end - start;
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24),
                new Bogie("Sleeper", 70),
                new Bogie("AC Chair", 60)
        );

        System.out.println("Grouped: " + groupBogiesByType(bogies));
        System.out.println("Total Seats: " + calculateTotalSeats(bogies));

        System.out.println("Train ID Valid: " + isValidTrainId("TRN-1234"));
        System.out.println("Cargo Code Valid: " + isValidCargoCode("PET-AB"));

        List<GoodsBogie> goods = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Open", "Coal")
        );

        System.out.println("Safety Compliant: " + isSafetyCompliant(goods));

        long loopTime = measureLoopExecutionTime(bogies);
        long streamTime = measureStreamExecutionTime(bogies);

        System.out.println("\n=== UC13 Performance ===");
        System.out.println("Loop Time (ns): " + loopTime);
        System.out.println("Stream Time (ns): " + streamTime);
    }
}