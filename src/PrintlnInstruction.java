import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py
 */
public class PrintlnInstruction extends Instruction {
   public static Integer operandCount = 1;
   public PrintlnInstruction() { }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      String classPattern = new String("Register");
      String[] pattern = classPattern.split(" ");
      String ret = "println ";

      if (this.getOperands().size() != pattern.length) {
         Evil.error(ret + ": ILOC expecting " + pattern.length + " operators.");
      }

      for (Operand r : this.getOperands()) {
         ret = ret + r + ", ";
      }

      ret = ret.trim();
      if (ret.lastIndexOf(",") == ret.length()-1)
         ret = ret.substring(0, ret.length()-2);

      for (int i = 0; i < this.getOperands().size(); i++) {
         Operand o = this.getOperands().get(i);
         String oper = "null";

         if (o != null) {
            oper = o.getClass().getName();
         }
         if (!oper.equals(pattern[i])) {
            Evil.error(ret + ": ILOC expecting " + classPattern + ". Found " + oper);
         }
      }

      return ret;
   }
}
