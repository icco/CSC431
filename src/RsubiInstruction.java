
/**
 * Generated automatically by generate_instructions.py
 */
public class RsubiInstruction extends Instruction {
   Immediate src0 = null;
   Register src1 = null;

   Register dest0 = null;

   public RsubiInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "rsubi ";
   }
}
