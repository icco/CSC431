import java.util.*;

/**
 * Extends a HashMap, only rewriting the toString function.
 */
public class GraphTable extends HashMap<String, Node> {
   public List<Node> allNodes = new LinkedList<Node>();

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
         ret += "\t.global " + s + "\n";

         // .type    printList, #function
         ret += "\t.type " + s + ", #function\n";

         nodes = TopoSort.sort(this.get(s));
         allNodes.addAll(nodes);

         for (Node n : nodes) {
            ret += n.toSparc();
         }

         // .size    add, .-add
         ret += "\t.size " + s + ", .-" + s + "\n";
      }

      return ret;
   }
}
