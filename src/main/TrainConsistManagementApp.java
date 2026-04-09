package main;

import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TrainConsistManagementApp {

    // Bogie Model
    public static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String getName() {
            return name;
        }

        public int getCapacity() {
            return capacity;
        }

        @Override
        public String toString() {
            return name + " -> " + capacity;
        }
    }

    // ✅ UC9: Grouping
    public static Map<String, List<Bogie>> groupBogiesByType(List<Bogie> bogies) {
        return bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));
    }

    // ✅ UC10: Total Seat Calculation using reduce()
    public static int calculateTotalSeats(List<Bogie> bogies) {
        return bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
    }

    // ✅ UC11: Validate Train ID
    public static boolean isValidTrainId(String trainId) {
        String regex = "TRN-\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(trainId);
        return matcher.matches();
    }

    // ✅ UC11: Validate Cargo Code
    public static boolean isValidCargoCode(String cargoCode) {
        String regex = "PET-[A-Z]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cargoCode);
        return matcher.matches();
    }

    // Demo main
    public static void main(String[] args) {

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("AC Chair", 60));

        // 🔹 UC9 Output
        Map<String, List<Bogie>> grouped = groupBogiesByType(bogies);

        System.out.println("Grouped Bogies:");
        grouped.forEach((type, list) -> {
            System.out.println("\nBogie Type: " + type);
            list.forEach(b -> System.out.println("Capacity -> " + b.getCapacity()));
        });

        // 🔹 UC10 Output
        int totalSeats = calculateTotalSeats(bogies);

        System.out.println("\n==============================");
        System.out.println("Total Seating Capacity: " + totalSeats);
        System.out.println("==============================");

        // 🔹 UC11 Output
        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        System.out.println("\n==============================");
        System.out.println("Train ID (" + trainId + ") Valid: " + isValidTrainId(trainId));
        System.out.println("Cargo Code (" + cargoCode + ") Valid: " + isValidCargoCode(cargoCode));
        System.out.println("==============================");
    }
}