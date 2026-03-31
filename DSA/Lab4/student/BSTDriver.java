import java.util.Random;

import student.BST;
import student.Flight;

public class BSTDriver {
    public static void main(String[] args) {
    BST tree = new BST();   
    
    // 1. Use fixed values for predictability
    int[] times = {50, 30, 70, 20, 40, 60, 80, 90, 100};
    for (int t : times) {
        tree.insert(new Flight(t, "FN" + t, "123", "AVL", "CLT"));
    }

    // 2. Verify Min/Max initially
    System.out.println("Initial Min (Expected 20): " + tree.min().flight.time);
    System.out.println("Initial Max (Expected 80): " + tree.max().flight.time);

    // 3. Test Deletion of node with TWO children (Case 3)
    System.out.println("\nDeleting 30 (two children)...");
    tree.delete(30);
    
    // If your successor logic is correct, the new "min" of that subtree 
    // or the node itself should be replaced correctly.
    // Check if the tree still functions:
    System.out.println("Post-delete Min (Expected 20): " + tree.min().flight.time);
    
    // 4. Test Deleting the root
    System.out.println("\nDeleting Root (50)...");
    tree.delete(50);

    System.out.println("Current Min (Expected ??): " + tree.min().flight.time);
    System.out.println("Current Max (Expected ??): " + tree.max().flight.time);
    // 5. Test Deleting something that doesn't exist
    System.out.println("\nDeleting 999 (non-existent)...");
    tree.delete(999);

    }
}
