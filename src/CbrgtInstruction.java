
/**
 * Generated automatically by generate_instructions.py
 */
public class CbrgtInstruction extends Instruction {
   ConditionCodeRegister src0 = null;
   Label src1 = null;
   Label src2 = null;


   public CbrgtInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "cbrgt ";
   }
}
