import java.util.LinkedHashSet;
import java.util.Set;

import java.util.LinkedList;
import java.util.List;

/**
 * ================================================================
 * MAIN CLASS - UseCase4TrainConsistMgmt
 * ================================================================
 *
 * Use Case 4: Maintain Ordered Bogie Consist
 *
 * Description:
 * Models real-world train chaining using LinkedList.
 * Supports ordered insertion, positional insertion,
 * and efficient removal from both ends.
 *
 * Concepts:
 * - LinkedList (Doubly Linked List)
 * - add(), add(index, element)
 * - removeFirst(), removeLast()
 * - Order Preservation
 *
 * @author Developer
 * @version 4.0
 */
import java.util.HashSet;
import java.util.Set;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=======================================");
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