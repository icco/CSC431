import java.util.*;

public class ColorNode implements Comparable<ColorNode> {
   private Set<ColorNode> edges;
   private Register vertex;
   private int color = -1; // -1 for color not set or a spill.
   private boolean real = false; // Node for virtual or real register.
 
   public ColorNode(Register r) {
      this.vertex = r;
      this.edges = new HashSet<ColorNode>();
   }

   public void addEdges(Collection<ColorNode> edges) {
      this.edges.addAll(edges);
   }

   public void addEdge(ColorNode edge) {
      if (edge != this) { /** HACK to avoid annoyingness. */
         this.edges.add(edge);
      }
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

   public boolean isReal() { return real; }
   public void setReal(boolean real) { this.real = real; }

   /** 
    * Hueristic for order to color. 
    * Pick non-virtual nodes last. 
    * Otherwise pick node with smallest edges.
    *
    * Best pick is "highest" when comparing.
    */
   public int compareTo(ColorNode other) {
      int edges1 = getNumberEdges();
      int edges2 = other.getNumberEdges();

      if (this.isReal() == other.isReal()) {
         if (edges1 == edges2) {
            return 0;
         } else if (edges1 > edges2) {
            return 1;
         } else {
            return -1;
         }
      } else {
         if (this.isReal()) {
            return -1;
         } else {
            return 1;
         }
      }
   }

   public String toString() {
      return vertex.toString() + ": " + getColor();
   }
}
