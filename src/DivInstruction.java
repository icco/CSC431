
/**
 * Generated automatically by generate_instructions.py
 */
public class DivInstruction extends Instruction {
   Register src0 = null;
   Register src1 = null;

   Register dest0 = null;

   public DivInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "div ";
   }
}
