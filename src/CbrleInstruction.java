
/**
 * Generated automatically by generate_instructions.py
 */
public class CbrleInstruction extends Instruction {
   ConditionCodeRegister src0 = null;
   Label src1 = null;
   Label src2 = null;


   public CbrleInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "cbrle ";
   }
}
