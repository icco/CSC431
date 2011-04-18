
/**
 * Generated automatically by generate_instructions.py
 */
public class CompiInstruction extends Instruction {
   Immediate src0 = null;
   Register src1 = null;

   ConditionCodeRegister dest0 = null;

   public CompiInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "compi ";
   }
}
