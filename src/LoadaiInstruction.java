import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py.
 */
public class LoadaiInstruction extends Instruction {
   public static Integer operandCount = 3;
   public LoadaiInstruction() {
      super();sparcs.add("ldsw");
   }

   public ArrayList<Sparc> toSparc() {
      ArrayList<Sparc> instructions = new ArrayList<Sparc>();
      
      LdswSparc load = new LdswSparc();

      Register src = (Register) this.getOperands().get(0);
      Field member = (Field) this.getOperands().get(1);
      Register dest = this.getDestinations().get(0);

      src = new Register(src.id); // Make a copy before adding offset.
      src.addOffset(member.getOffset());

      load.addSource(src);
      load.addDest(dest);
      instructions.add(load);

      return instructions;
   }

   public String toString() { return this.toILOC(); }

   public String toILOC() {
      String classPattern = new String("Register Field Register");
      String[] pattern = classPattern.split(" ");
      String ret = "loadai ";
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
