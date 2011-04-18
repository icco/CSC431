
/**
 * Generated automatically by generate_instructions.py
 */
public class AndInstruction extends Instruction {
   Register src0 = null;
   Register src1 = null;

   Register dest0 = null;

   public AndInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "and ";
   }
}
