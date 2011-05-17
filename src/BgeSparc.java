import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py.
 */
public class BgeSparc extends Sparc {
   public String toString() {
      String ret = "bge ";

      for (Operand r : this.getOperands()) {
         if (!r.toString().equals(""))
            ret = ret + r + ", ";
      }

      ret = ret.trim();
      if (ret.lastIndexOf(",") == ret.length()-1)
         ret = ret.substring(0, ret.length()-1);

      return ret;
   }
}
