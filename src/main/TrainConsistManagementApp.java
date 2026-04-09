package main;

import java.util.*;
import java.util.stream.Collectors;

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

    // ✅ UC9 Core Logic
    public static Map<String, List<Bogie>> groupBogiesByType(List<Bogie> bogies) {
        return bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getName));
    }

    // Demo main
    public static void main(String[] args) {

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));
        bogies.add(new Bogie("AC Chair", 60));

        Map<String, List<Bogie>> grouped = groupBogiesByType(bogies);

        System.out.println("Grouped Bogies:");

        grouped.forEach((type, list) -> {
            System.out.println("\nBogie Type: " + type);
            list.forEach(b -> System.out.println("Capacity -> " + b.getCapacity()));
        });
    }
}