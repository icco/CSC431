import java.util.*;

/**
 * Used for TopoSorting a Node and it's children with sort() function.
 * I was going to make this nice and generic but eh.
 */
public class TopoSort {
   private static TreeMap<Integer, Node> positions = new TreeMap<Integer, Node>();
   private static Set<Node> visited = new HashSet<Node>();

   public static List<Node> sort(Node top) {
      List<Node> sorted = new LinkedList<Node>();

      markWithDFS(top, 0);

      // Add positions to sorted list, in reverse direction.
      TreeSet<Integer> keys = new TreeSet<Integer>(positions.keySet());
      for (Iterator<Integer> itr = keys.descendingIterator(); itr.hasNext();) {
         sorted.add(positions.get(itr.next()));
      }

      visited.clear();
      positions.clear();

      return sorted;
   }

   public static int markWithDFS(Node current, int pos) {
      if (visited.add(current)) {
         for (Node child : current) {
            pos = markWithDFS(child, pos);      
         }

         positions.put(pos, current); // done with children, mark node
         return pos + 1;

      } else {
         return pos; // already visited this node.
      }
   }
}
