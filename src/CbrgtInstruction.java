import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py
 */
public class CbrgtInstruction extends Instruction {
   public CbrgtInstruction() { }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      String classPattern = new String("ConditionCodeRegister Label Label");
      String[] pattern = classPattern.split(" ");
      String ret = "cbrgt ";

      for (Operand r : this.sources) {
         ret = ret + r + ", ";
      }

      ret = ret.trim();
      if (ret.lastIndexOf(",") == ret.length()-1)
         ret = ret.substring(0, ret.length()-2);

      for (int i = 0; i < this.sources.size(); i++) {
         if (this.sources.get(i).getClass().getName() != pattern[i]) {
            Evil.error(ret + ": ILOC expecting " + classPattern);
         }
      }

      return ret;
   }
}