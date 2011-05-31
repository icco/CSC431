import java.util.*;

public class DeadCodeRemoval {
   public static void deleteUselessInstructions(GraphTable gt) {
      System.out.println("Removing dead code.");

      for (Node n : gt.getAllNodes()) {
         Set<Register> liveset = new HashSet<Register>(n.getLiveSet());

         for (int i = n.instructions.size() - 1; i >= 0; i--) {
            // We can only look at: add, sub, mult, div, loads.
            if (this.isOptimizable(i)) {

               // Check if destination in liveset, if not remove
               for (Register d : n.instructions.get(i).getDestinations()) {
                  if (!liveset.contains(d)) {
                     System.out.println("Removing: " + n.instructions.get(i));
                     n.instructions.remove(i);
                  }
               }

               // Add all sources to live set.
               for (Instruction i : n.instructions) {
                  liveset.addAll(i.getSources());
               }
            }
         }
      }
   }
}
