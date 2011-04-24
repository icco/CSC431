import java.util.*;
import java.lang.*;

public class Instruction {
   public static Integer operandCount = 0;
   private ArrayList<Operand> operands = new ArrayList<Operand>();
   private ArrayList<Register> dests = new ArrayList<Register>();

   public String toString() {
      return "NULL INSTRUCTION.";
   }

   public ArrayList<Register> getSources() {
      ArrayList<Register> ret = new ArrayList<Register>();

      for (Operand o : this.operands) {
         if (o instanceof Register)
            ret.add((Register)o);
      }

      return ret;
   }

   public ArrayList<Register> getDestinations() {
      return this.dests;
   }

   public ArrayList<Operand> getOperands() {
      ArrayList<Operand> ret = new ArrayList<Operand>();
      ret.addAll(this.operands);
      ret.addAll(this.dests);

      return ret;
   }

   void addSource(Operand in) {
      this.operands.add(in);
   }

   void addDest(Register in) {
      this.sources.add(in);
   }

   public void addLabel(Label in) {
      this.addSource(in);
   }

   public void addRegister(Register in) {
      this.addSource(in);
   }

   public void addDest(Register in) {
      this.dests.add(in);
   }

   public void addImmediate(Immediate in) {
      this.addSource(in);
   }

   public void addImmediate(Integer in) {
      this.addSource(new Immediate(in));
   }
}
