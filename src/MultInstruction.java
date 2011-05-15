import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py
 */
public class MultInstruction extends Instruction {
   public static Integer operandCount = 3;
   public MultInstruction() {
      super();
   }

   public String toString() {
      return this.toILOC();
   }

   // TODO: Moves need to use input / output registers.
   public ArrayList<Sparc> toSparc() {
      System.out.println("Mult");
      ArrayList<Sparc> instructions = new ArrayList<Sparc>();
      Sparc i;

      //mov  9, %o0   ; operand one
      i = new MovSparc();
      i.addSource(this.getSources().get(0));
      instructions.add(i);

      //mov  7, %o1   ; operand two
      i = new MovSparc();
      i.addSource(this.getSources().get(1));
      instructions.add(i);

      //call .mul
      i = new CallSparc();
      i.addSource(new Label(".mul"));
      instructions.add(i);

      //nop
      instructions.add(new NopSparc());

      //mov  %o0, %l0 ; result
      i = new MovSparc();
      i.addDest(this.getOperands().get(2));
      instructions.add(i);

      return instructions;
   }


   public String toILOC() {
      String classPattern = new String("Register Register Register");
      String[] pattern = classPattern.split(" ");
      String ret = "mult ";
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
