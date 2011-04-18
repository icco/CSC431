
/**
 * Generated automatically by generate_instructions.py
 */
public class AddInstruction extends Instruction {
   Register src0 = null;
   Register src1 = null;

   Register dest0 = null;

   public AddInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "add ";
   }
}
