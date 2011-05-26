import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py.
 */
public class NewInstruction extends Instruction {
   public static Integer operandCount = 2;
   public NewInstruction() {
      super();sparcs.add("mov");sparcs.add("call");sparcs.add("nop");sparcs.add("mov");
   }

   public ArrayList<Sparc> toSparc() {
      ArrayList<Sparc> instructions = new ArrayList<Sparc>();
      Sparc i;

      // mov sizeof(Struct), %o1
      i = new MovSparc();
      i.addOp(this.getOperands().get(0));
      i.addDest(new Register("%o1"));
      instructions.add(i);

      // call malloc
      i = new CallSparc();
      i.addOp(new Label("malloc"));
      instructions.add(i);

      // nop
      i = new NopSparc();
      instructions.add(i);

      // mov %o0 %dest
      i = new MovSparc();
      i.addSource(new Register("%o0"));
      i.addDest(this.getDestinations().get(0));

      return instructions;
   }

   public String toString() { return this.toILOC(); }

   public String toILOC() {
      String classPattern = new String("StructIdentifier Register");
      String[] pattern = classPattern.split(" ");
      String ret = "new ";
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
