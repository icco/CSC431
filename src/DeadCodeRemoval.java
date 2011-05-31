import java.util.*;

public class DeadCodeRemoval {
   public static void deleteUselessInstructions(GraphTable gt) {
      System.out.println("Removing dead code.");

      for (Node n : gt.getAllNodes()) {
         Set<Register> liveset = new HashSet<Register>(n.getLiveSet());

         for (int i = n.instructions.size() - 1; i >= 0; i--) {

            // Check if destination in liveset, if not remove
            for (Register d : n.instructions.get(i).getDestinations()) {
               if (!liveset.contains(d)) {

                  // We can only look at: add, sub, mult, div, loads.
                  if (DeadCodeRemoval.isOptimizable(n.instructions.get(i))) {
                     System.out.println("Removing: " + n.instructions.get(i));
                     n.instructions.remove(i);
                  }
               }
            }

            // Add all sources to live set.
            for (Instruction ist : n.instructions) {
               liveset.addAll(ist.getSources());
            }
         }
      }
   }

   private static boolean isOptimizable(Instruction instr) {
      return (
            instr instanceof AddInstruction
         || instr instanceof AddiInstruction
         || instr instanceof LoadiInstruction
         || instr instanceof DivInstruction
         || instr instanceof MultInstruction
         || instr instanceof SubInstruction
         || instr instanceof AndInstruction
         || instr instanceof OrInstruction
         || instr instanceof XoriInstruction
         || instr instanceof LoadiInstruction
         );
   }
}
