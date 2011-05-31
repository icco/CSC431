import java.util.*;

public class LocalValueNumbering {
   private Map<String, String> numberings;
   private Integer nextNumber = 0;

   public void optimize(Node block) {
      numberings = new HashMap<String, String>();

      for (Instruction instr : block.getInstr()) {
         


      }
   }

   /**
    * Get string representation of expression.
    * @returns non empty string when instruciton is 
    * arithmetic.
    * Otherwise returns an empty string.
    */
   private String getValueOf(Instruction instr) {
      if (isOptimizable(instr)) {


      }

      return "";
   }

   private boolean isOptimizable(Instruction instr) {
      return (instr instanceof AddInstruction
       || instr instanceof AddiInstruction
       || instr instanceof LoadiInstruction
       || instr instanceof DivInstruction
       || instr instanceof MultInstruction
       || instr instanceof SubInstruction
       || instr instanceof AndInstruction
       || instr instanceof OrInstruction
       || instr instanceof XoriInstruction
       || instr instanceof LoadiInstruction
       || instr instanceof MovInstruction);
   }

   /**
    * If a operand is already numbered, get that number.
    * Otherwise return a new number value and put
    * pair into numberings.
    */
   private String getValueOf(Operand op) {
      String opString = op.toString();
      String value =  numberings.get(opString);

      if (value == null) {
         numberings.put(opString, nextNumber.toString());

         return "" + nextNumber++;
      } else {
         return value;
      }
   }
}
