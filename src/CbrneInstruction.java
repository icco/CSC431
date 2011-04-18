
/**
 * Generated automatically by generate_instructions.py
 */
public class CbrneInstruction extends Instruction {
   ConditionCodeRegister src0 = null;
   Label src1 = null;
   Label src2 = null;


   public CbrneInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return "cbrne ";
   }
}
