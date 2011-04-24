import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py
 */
public class CbrgeInstruction extends Instruction {
   public CbrgeInstruction() { }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      String ret = "cbrge ";
      for (Operand r : this.sources) {
         ret = ret + r + ", ";
      }

      ret = ret.trim();
      if (ret.lastIndexOf(",") == ret.length()-1)
         ret = ret.substring(0, ret.length()-2);

      return ret;
   }
}