import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py.
 */
public class StoreaiInstruction extends Instruction {
   public static Integer operandCount = 3;
   public StoreaiInstruction() {
      super();sparcs.add("st");
   }

   public String toString() { return this.toILOC(); }

   public ArrayList<Sparc> toSparc() {
      ArrayList<Sparc> instructions = new ArrayList<Sparc>();
      
      StSparc store = new StSparc();

      Register src = this.getSources().get(0);
      Field member = (Field) this.getOperands().get(2);
      Register address = this.getSources().get(1);

      address = address.copy();
      address.addOffset(member.getOffset());

      store.addSource(src);
      store.addSource(address);
      instructions.add(store);

      return instructions;
   }

   public String toILOC() {
      String classPattern = new String("Register Register Field");
      String[] pattern = classPattern.split(" ");
      String ret = "storeai ";
      int operandCount = this.getOperands().size();

      if ((operandCount != 0) && (operandCount != pattern.length)) {
         Evil.error(ret + ": Found " + operandCount + " operands, ILOC expecting " + pattern.length);
      }

      for (Operand r : this.getOperands()) {
         if (!r.toString().equals(""))
            ret = ret + r.toILOC() + ", ";
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
