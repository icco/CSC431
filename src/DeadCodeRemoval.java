import java.util.*;

public class DeadCodeRemoval {
   public static void deleteUselessInstructions(GraphTable gt) {
      System.out.println("Removing dead code.");

      for (Node n : gt.getAllNodes()) {
         Set<Register> liveset = new HashSet<Register>(n.getLiveSet());

         for (int i = n.instructions.size() - 1; i >= 0; i--) {
            //System.out.println(n.instructions.get(i) + " \t\t\t - destinations: " + n.instructions.get(i).getDestinations() + " Liveset: " + liveset);

            for (Register d : n.instructions.get(i).getDestinations()) {

               // We can only look at: add, sub, mult, div, loads.
               if (DeadCodeRemoval.isOptimizable(n.instructions.get(i))) {

                  // Check if destination in liveset, if not remove
                  if (!liveset.contains(d)) {
                     //System.out.println("Removing: " + n.instructions.get(i));
                     n.instructions.remove(i);
                  } else {
                     //System.out.println(d + " is in " + liveset);
                  }
               } else {
                  //System.out.println("Not valid: " + n.instructions.get(i));
               }
            }

            // Add all sources to live set.
            liveset.addAll(n.instructions.get(i).getSources());
         }
      }
   }

   private static boolean isOptimizable(Instruction instr) {
      return (
            instr instanceof AddInstruction
         || instr instanceof AddiInstruction
         || instr instanceof AndInstruction
         || instr instanceof DivInstruction
         || instr instanceof LoadaiInstruction
         || instr instanceof LoadglobalInstruction
         || instr instanceof LoadiInstruction
         || instr instanceof LoadinargumentInstruction
         || instr instanceof MovInstruction
         || instr instanceof MultInstruction
         || instr instanceof OrInstruction
         || instr instanceof SubInstruction
         || instr instanceof XoriInstruction
         );
   }
}
