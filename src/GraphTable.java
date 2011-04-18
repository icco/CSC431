import java.util.*;

/**
 * Extends a HashMap, only rewriting the toString function.
 */
public class GraphTable extends HashMap<String, Node> {
   public String toString() {
      String ret = "";
      List<Node> nodes = new LinkedList<Node>();

      for (String s : this.keySet()) {
         nodes.addAll(TopoSort.sort(this.get(s)));
      }

      for (Node n : nodes) {
         ret += n;
      }

      return ret;
   }
}
