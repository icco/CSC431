import java.util.*;

/**
 * Extends a HashMap, only rewriting the toString function.
 */
public class GraphTable extends HashMap<String, Node> {
   // Eventually we want to write a topological sort based toString.

   public String toString() {
      String ret = "";
      ArrayList<String> printed = new ArrayList<String>();

      for (String s : this.keySet()) {
         Node n = this.get(s);
         ret += s + ":\n";
         for (Instruction i : n.getInstr()) {
            ret += "\t" + i + "\n";
         }

         for (Node nc : n.getChildren()) {
            if (printed.indexOf(nc.getLabel()) < 0) {
               ret += nc + ":\n";
               for (Instruction i : nc.getInstr()) {
                  ret += "\t" + i + "\n";
               }

               printed.add(nc.getLabel());
            }
         }
      }

      return ret;
   }
}
