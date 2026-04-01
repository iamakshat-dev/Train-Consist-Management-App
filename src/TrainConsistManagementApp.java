import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;

import java.util.LinkedList;
import java.util.List;


import java.util.HashSet;
import java.util.Set;

public class TrainConsistManagementApp {

    // ---------------- BOGIE CLASS ----------------
    static class Bogie {
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

    // ---------------- FILTER METHOD USING STREAM ----------------
    public static List<Bogie> filterByCapacity(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > threshold) // STRICT >
                .collect(Collectors.toList());
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("UC8 - Filter Passenger Bogies (Streams)");
        System.out.println("=======================================\n");

        // Create bogie list
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        // Display original list
        System.out.println("Original Bogies:");
        bogies.forEach(System.out::println);

        // Apply filter
        int threshold = 60;
        List<Bogie> filtered = filterByCapacity(bogies, threshold);

        // Display filtered result
        System.out.println("\nFiltered Bogies (Capacity > " + threshold + "):");
        filtered.forEach(System.out::println);

        // Verify original list unchanged
        System.out.println("\nOriginal List After Filtering (Unchanged):");
        bogies.forEach(System.out::println);

        System.out.println("\nUC8 filtering completed...");
    }
}
