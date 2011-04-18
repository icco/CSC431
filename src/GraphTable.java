import java.util.*;

/**
 * Extends a HashMap, only rewriting the toString function.
 */
public class GraphTable extends HashMap<String, Node> {
   // Eventually we want to write a topological sort based toString.

   public String toString() {
      String ret = "";
      Set<String> keys = keySet();
      List<Node> nodes = new LinkedList<Node>();

      for (String s : keys) {
         nodes.addAll(TopoSort.sort(get(s)));
      }

      for (Node n : nodes) {
         ret += n;
      }

      return ret;
   }
}
