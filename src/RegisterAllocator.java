import java.util.*;

/**
 * Maps virtual registers to real registers.
 *
 * How to use:
 *   pass list of "real" registers using setReal().
 *   add each node to graph using addNode().
 *   get mappings using colorGraph().
 */
public class RegisterAllocator {
   /** 
    * graph is just an easy way to access the vertices. 
    * All of the connections in the graph are stored in the ColorNode objects.
    */
   private Map<Register, ColorNode> graph;

   /** Registers to consider real, colors will eventually map to these. */
   private List<Register> real;

   static private final int kColors = 25;

   public RegisterAllocator() {
      graph = new HashMap<Register, ColorNode>();
   }

   /**
    * Fill the graph with all the registers from a block of code.
    */
   public void addNode(Node block) {
      List<Register> srcs;
      List<Register> dests;
      Set<Register> liveSet = 
       new HashSet<Register>(block.getLiveSet());
    
      for (Instruction instr : block.getInstr()) {
         srcs = instr.getSources();
         dests = instr.getDestinations();

         for (Register dest : dests) {
            liveSet.remove(dest);
            addEdges(dest, liveSet);
         }
            
         for (Register src : srcs) {
            liveSet.add(src); 
         }
      }
   }

   /**
    * Used by addNode when creating graph.
    */
   private void addEdges(Register one, Set<Register> many) {
      Set<ColorNode> edges = new HashSet<ColorNode>();
      ColorNode vertex, edge;

      // Make sure all edges have a ColorNode and are in the graph.
      for (Register edgeRegister : many) {
         edge = graph.get(edgeRegister);
         if (edge == null) {
            edge = new ColorNode(edgeRegister);
            graph.put(edgeRegister, edge);
         }

         edges.add(edge);
      }

      // Make sure vertex has a ColorNode and is in the graph.
      vertex = graph.get(one);
      if (vertex == null) {
         vertex = new ColorNode(one);
         graph.put(one, vertex);
      }

      // Make sure edges are connected to vertex in graph.
      vertex.addEdges(edges);
      for (ColorNode node : edges) {
         node.addEdge(vertex);
      }
   }

   public void colorGraph() {
      Stack<ColorNode> popped;
      
      popped = deconstructGraph();
      reconstructGraph(popped);


   }

   /** Get the ColorNode with the best hueristic for reducing spills. */
   private ColorNode getNextFromGraph() {
      ColorNode best = null, vertex;
      Set<Register> keys = graph.keySet();

      for (Register key : keys) {
         vertex = graph.get(key);

         if (best == null || vertex.compareTo(best) == 1) {
            best = vertex;
         }
      }

      return best;
   }
   
   /**
    * Convert graph into a stack of nodes, where the
    * order of the stack is the order to color.
    */
   private Stack<ColorNode> deconstructGraph() {
      Stack<ColorNode> rStack = new Stack<ColorNode>();
      ColorNode next;

      while (!graph.isEmpty()) {
         next = getNextFromGraph();
         rStack.push(next);
         graph.remove(next.getVertex());
      }
         
      return rStack;
   }

   /**
    * Reconstruct graph, and assign colors to the nodes.
    */
   private void reconstructGraph(Stack<ColorNode> rStack) {
      ColorNode vertex;

      while (!rStack.empty()) {
         vertex = rStack.pop();

         graph.put(vertex.getVertex(), vertex);

         // Color node based on neighbors that are back in graph.
         boolean goodColor = true;
         for (int color = 0; color < kColors; color++) {
            for (ColorNode edge : vertex.getEdges()) {
               // Is the edge in the graph already?
               if (graph.get(edge.getVertex()) != null) {
                  // Then make sure we don't choose it's color.
                  if (color == edge.getColor()) {
                     goodColor = false;
                  }
               }
            }

            if (goodColor) {
               vertex.setColor(color);
            }
         }
      }
   }
}
