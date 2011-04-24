import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py
 */
public class DelInstruction extends Instruction {
   public static Integer operandCount = 1;
   public DelInstruction() { }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      String classPattern = new String("Register");
      String[] pattern = classPattern.split(" ");
      String ret = "del ";

      for (Operand r : this.getOperands()) {
         ret = ret + r + ", ";
      }

      ret = ret.trim();
      if (ret.lastIndexOf(",") == ret.length()-1)
         ret = ret.substring(0, ret.length()-2);

      for (int i = 0; i < this.getOperands().size(); i++) {
         if (this.getOperands().get(i).getClass().getName() != pattern[i]) {
            Evil.error(ret + ": ILOC expecting " + classPattern);
         }
      }

      return ret;
   }
}