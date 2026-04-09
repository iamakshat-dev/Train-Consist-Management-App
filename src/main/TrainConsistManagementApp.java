package main;

import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

public class TrainConsistManagementApp {

    // ================= UC14: CUSTOM EXCEPTION =================
    public static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    // ================= PASSENGER BOGIE =================
    public static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.name = name;
            this.capacity = capacity;
        }

        public String getName() { return name; }
        public int getCapacity() { return capacity; }
    }

    // ================= GOODS BOGIE =================
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
    public static List<Bogie> filterBogiesUsingLoop(List<Bogie> bogies) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.getCapacity() > 60) result.add(b);
        }
        return result;
    }

    public static List<Bogie> filterBogiesUsingStream(List<Bogie> bogies) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .toList();
    }

    public static long measureLoopExecutionTime(List<Bogie> bogies) {
        long start = System.nanoTime();
        filterBogiesUsingLoop(bogies);
        return System.nanoTime() - start;
    }

    public static long measureStreamExecutionTime(List<Bogie> bogies) {
        long start = System.nanoTime();
        filterBogiesUsingStream(bogies);
        return System.nanoTime() - start;
    }

    // ================= MAIN =================
    public static void main(String[] args) throws InvalidCapacityException {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24)
        );

        System.out.println("Total Seats: " + calculateTotalSeats(bogies));
    }
}