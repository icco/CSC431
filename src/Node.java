
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A box representing a block I guess.
 */
public class Node implements Iterable<Node> {
   private static int labelCount = 0;

   protected ArrayList<Instruction> instructions;
   protected ArrayList<Node> parents;
   protected ArrayList<Node> children;
   private String label;

   public void addParent(Node parent) { parents.add(parent); }
   public void addChild(Node child) { children.add(child); }

   public String getLabel() { return label; }
   public void setLabel(String label) { this.label = label; }
   public List<Node> getChildren() { return children; }

   public Node() { 
      instructions = new ArrayList<Instruction>(); 
      parents = new ArrayList<Node>(); 
      children = new ArrayList<Node>();
   }

   public Node(String label) { 
      this();
      setLabel(nextLabel(label)); // label
   }

   public static String nextLabel() {
      return nextLabel("");
   }

   public static String nextLabel(String id) {
      return "." + id + labelCount++;
   }

   public String toString() {
      String ret = getLabel() + "\n";

      if (children.isEmpty()) {
         ret += "----\n";
      } else {
         for (Node n : children) {
            ret += n;
         }
      }

      return ret;
   }

   public Iterator<Node> iterator() {
      return children.iterator();
   }
}
