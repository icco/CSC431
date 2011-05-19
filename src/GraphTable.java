import java.util.*;

/**
 * Extends a HashMap, only rewriting the toString function.
 */
public class GraphTable extends HashMap<String, Node> {
   public String toILOC() {
      String ret = "";
      List<Node> nodes = new LinkedList<Node>();

      for (String s : this.keySet()) {
         nodes.addAll(TopoSort.sort(this.get(s)));
      }

      for (Node n : nodes) {
         ret += n.toILOC();
      }

      return ret;
   }

   public String toSparc() {
      String ret = "";
      List<Node> nodes = new LinkedList<Node>();

      for (String s : this.keySet()) {
         // .align 4
         ret += "\t.align 4\n";

         // .global printList
         ret += "\t.global " + n.getLabel() + "\n";

         // .type    printList, #function
         ret += "\t.type " + n.getLabel() + ", #function\n";

         for (Node n : TopoSort.sort(this.get(s))) {
            ret += n.toSparc();
         }

         // .size    add, .-add
         ret += "\t.size " + n.getLabel() + ", .-" + n.getLabel() + "\n";
      }

      return ret;
   }
}
