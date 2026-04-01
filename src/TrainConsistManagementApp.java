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
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("UC4 - Maintain Ordered Bogie Consist");
        System.out.println("=======================================\n");

        // Create a LinkedList to represent train consist
        List<String> trainConsist = new LinkedList<>();

        // ---------------- INITIAL ADDITION ----------------
        trainConsist.add("Engine");
        trainConsist.add("Sleeper");
        trainConsist.add("AC");
        trainConsist.add("Cargo");
        trainConsist.add("Guard");

        System.out.println("Initial Train Consist:");
        System.out.println(trainConsist);

        // ---------------- INSERT AT POSITION ----------------
        // Insert "Pantry Car" at position 2 (index 2)
        trainConsist.add(2, "Pantry Car");

        System.out.println("\nAfter Inserting 'Pantry Car' at position 2:");
        System.out.println(trainConsist);

        // ---------------- REMOVE FIRST & LAST ----------------
        ((LinkedList<String>) trainConsist).removeFirst();
        ((LinkedList<String>) trainConsist).removeLast();

        System.out.println("\nAfter Removing First and Last Bogie:");
        System.out.println(trainConsist);

        System.out.println("\nUC4 ordered consist operations completed...");
    }
}