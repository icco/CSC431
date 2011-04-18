
/**
 * Generated automatically by generate_instructions.py
 */
public class XoriInstruction extends Instruction {
   Immediate src0 = null;
   Register src1 = null;

   Register dest0 = null;

   public XoriInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "xori ";
   }
}
