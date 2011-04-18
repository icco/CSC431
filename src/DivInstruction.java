
import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py
 */
public class DivInstruction extends Instruction {
   ArrayList<Operand> sources = new ArrayList<Operand>();
   ArrayList<Operand> dests   = new ArrayList<Operand>();

   public DivInstruction() {
   }

   public String toString() {
      return this.toILOC();
   }

   public void addSource(Operand in) {
      this.sources.add(in);
   }

   public void addDest(Operand in) {
      this.dests.add(in);
   }

   public String toILOC() {
      String ret = "div ";
      for (Operand r : sources) {
         ret = ret + r + ", ";
      }

      for (Operand r : dests) {
         ret = ret + r + ", ";
      }

      ret = ret.trim();
      if (ret.lastIndexOf(",") == ret.length()-1)
         ret = ret.substring(0, ret.length()-2);

      return ret;
   }
}
