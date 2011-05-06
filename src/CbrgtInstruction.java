import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py
 */
public class CbrgtInstruction extends Instruction {
   public static Integer operandCount = 3;
   public CbrgtInstruction() {
      super();sparcs.add("bg");sparcs.add("nop");
   }

   public String toString() {
      return this.toILOC();
   }

   public String toSparc() {
      return "cbrgt : " + this.sparcs.toString();
   }

   public String toILOC() {
      String classPattern = new String("ConditionCodeRegister Label Label");
      String[] pattern = classPattern.split(" ");
      String ret = "cbrgt ";
      int operandCount = this.getOperands().size();

      if ((operandCount != 0) && (operandCount != pattern.length)) {
         Evil.error(ret + ": Found " + operandCount + " operands, ILOC expecting " + pattern.length);
      }

      for (Operand r : this.getOperands()) {
         if (!r.toString().equals(""))
            ret = ret + r + ", ";
      }

      ret = ret.trim();
      if (ret.lastIndexOf(",") == ret.length()-1)
         ret = ret.substring(0, ret.length()-1);

      for (int i = 0; i < this.getOperands().size(); i++) {
         Operand o = this.getOperands().get(i);
         String oper = "null";

         if (o != null) { oper = o.getClass().getName(); }

         if (!oper.equals(pattern[i])) {
            Evil.error(ret + ": ILOC expecting " + classPattern + ". Found " + oper);
         }
      }

      return ret;
   }
}
