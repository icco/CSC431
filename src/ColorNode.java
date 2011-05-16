import java.util.*;

public class ColorNode implements Comparable<ColorNode> {
   private Set<ColorNode> edges;
   private Register vertex;
   private int color = -1; // -1 for color not set or a spill.

   public ColorNode(Register r) {
      this.vertex = r;
      this.edges = new HashSet<ColorNode>();
   }

   public void addEdges(Set<ColorNode> edges) {
      this.edges.addAll(edges);
   }

   public void addEdge(ColorNode edge) {
      this.edges.add(edge);
   }

   public int getNumberEdges() {
      return edges.size();
   }

   public Set<ColorNode> getEdges() {
      return edges;
   }

   public Register getVertex() {
      return vertex;
   }

   public int getColor() { return color; }
   public void setColor(int color) { this.color = color; }

   /** 
    * Hueristic for order to color. Vertices that should be picked
    * first are "greater" than other edges.
    */
   public int compareTo(ColorNode other) {
      int edges1 = getNumberEdges();
      int edges2 = other.getNumberEdges();

      if (edges1 == edges2) {
         return 0;
      } else if (edges1 > edges2) {
         return 1;
      } else {
         return -1;
      }
   }
}
