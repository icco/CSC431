
/**
 * Generated automatically by generate_instructions.py
 */
public class CompInstruction extends Instruction {
   Register src0 = null;
   Register src1 = null;

   ConditionCodeRegister dest0 = null;

   public CompInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "comp ";
   }
}
