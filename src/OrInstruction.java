
/**
 * Generated automatically by generate_instructions.py
 */
public class OrInstruction extends Instruction {
   Register src0 = null;
   Register src1 = null;

   Register dest0 = null;

   public OrInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "or ";
   }
}
