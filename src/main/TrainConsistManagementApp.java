package main;

import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

public class TrainConsistManagementApp {

    // ================= UC14 =================
    public static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    // ================= UC15 =================
    public static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) {
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

        public GoodsBogie(String type) {
            this.type = type;
        }

        public String getType() { return type; }
        public String getCargo() { return cargo; }

        // UC15: Safe cargo assignment
        public void assignCargo(String cargo) {
            try {
                if (type.equalsIgnoreCase("Rectangular") &&
                        cargo.equalsIgnoreCase("Petroleum")) {

                    throw new CargoSafetyException(
                            "Petroleum cannot be assigned to Rectangular bogie"
                    );
                }

                this.cargo = cargo;

            } catch (CargoSafetyException e) {
                System.out.println("Error: " + e.getMessage());

            } finally {
                System.out.println("Cargo assignment attempt completed.");
            }
        }
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
                                || "Petroleum".equalsIgnoreCase(b.getCargo())
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
}