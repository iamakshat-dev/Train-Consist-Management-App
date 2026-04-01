import java.util.LinkedHashSet;
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
    }
}