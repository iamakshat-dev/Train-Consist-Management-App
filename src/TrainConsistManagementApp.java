import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;

import java.util.LinkedList;
import java.util.List;


import java.util.HashSet;
import java.util.Set;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("UC6 - Map Bogie to Capacity (HashMap)");
        System.out.println("=======================================\n");

        // Create HashMap for bogie-capacity mapping
        Map<String, Integer> bogieCapacity = new HashMap<>();

        // ---------------- INSERT DATA ----------------
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 56);
        bogieCapacity.put("First Class", 120);
        bogieCapacity.put("Cargo", 100); // load capacity example

        // ---------------- DISPLAY ----------------
        System.out.println("Bogie Capacity Details:");

        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\nUC6 bogie-capacity mapping completed...");
        System.out.println("UC5 - Preserve Insertion Order of Bogies");
        System.out.println("=======================================\n");

        // LinkedHashSet preserves order and ensures uniqueness
        Set<String> formation = new LinkedHashSet<>();

        // ---------------- ADD BOGIES ----------------
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        // Attempt duplicate insertion
        formation.add("Sleeper"); // ignored

        // ---------------- DISPLAY RESULT ----------------
        System.out.println("Final Train Formation:");
        System.out.println(formation);

        // ---------------- NOTE ----------------
        System.out.println("\nNote:");
        System.out.println("LinkedHashSet preserves insertion order and removes duplicates automatically.");

        System.out.println("\nUC5 formation setup completed...");
        System.out.println("\nUC4 ordered consist operations completed...");
        System.out.println("UC3 - Track Unique Bogie IDs");
        System.out.println("=======================================\n");

        // Create a Set to store unique bogie IDs
        Set<String> bogies = new HashSet<>();

        // ---------------- ADD IDs (including duplicates) ----------------
        bogies.add("BG101");
        bogies.add("BG102");
        bogies.add("BG103");
        bogies.add("BG104");

        // Duplicate entries (will be ignored automatically)
        bogies.add("BG101");
        bogies.add("BG102");

        // ---------------- DISPLAY RESULT ----------------
        System.out.println("Bogie IDs After Insertion:");
        System.out.println(bogies);

        // ---------------- NOTE ----------------
        System.out.println("\nNote:");
        System.out.println("Duplicates are automatically ignored by HashSet.");

        System.out.println("\nUC3 uniqueness validation completed...");
    }
}
