
/**
 * Generated automatically by generate_instructions.py
 */
public class SubInstruction extends Instruction {
   Register src0 = null;
   Register src1 = null;

   Register dest0 = null;

   public SubInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "sub ";
   }
}
