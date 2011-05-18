import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py.
 */
public class AddSparc extends Sparc {
   public String toString() {
      String ret = "add ";

      for (Operand r : this.getOperands()) {
         if (!r.toString().equals(""))
            ret = ret + r.toSparc() + ", ";
      }

      ret = ret.trim();
      if (ret.lastIndexOf(",") == ret.length()-1)
         ret = ret.substring(0, ret.length()-1);

      return ret;
   }
}
