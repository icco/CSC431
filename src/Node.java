import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

/**
 * A box representing a block I guess.
 */
public class Node implements Iterable<Node> {
   private static int labelCount = 0;

   protected ArrayList<Instruction> instructions;
   protected ArrayList<Node> parents;
   protected ArrayList<Node> children;
   private String label;

   private Set<Register> gen;
   private Set<Register> kill;
   private Set<Register> live;
      
   public Set<Register> getGenSet() { return gen; }
   public Set<Register> getKillSet() { return kill; }
   public Set<Register> getLiveSet() { return live; }

   public void createGenAndKill() {
      if (gen == null) {
         gen = new HashSet<Register>();
         kill = new HashSet<Register>();

         for (Instruction instr : getInstr()) {
            for (Register src : instr.getSources()) {
               if (!kill.contains(src)) {
                  gen.add(src);
               } 
            }

            for (Register dest : instr.getDestinations()) {
               kill.add(dest);
            }
         }

         for (Node successor : children) {
            successor.createGenAndKill();
         }
      }
   }

   public void createLive() {
      Set<Register> liveOut = new HashSet<Register>(); 
      Set<Register> immediate;
      Set<Register> nonImmediate;

      for (Node successor : children) {
         immediate = successor.getGenSet();
         nonImmediate = successor.getLiveOut();
         nonImmediate.removeAll(successor.getKillSet());

         liveOut.addAll(immediate);
         liveOut.addAll(nonImmediate);
      }

      this.live = liveOut;
   }


   public void addParent(Node parent) { this.parents.add(parent); }

   public void addChild(Node child) { this.children.add(child); }

   public String getLabel() {
      return this.label.replace(".", "").replace("_", "");
   }

   public void setLabel(String label) { this.label = label; }

   public Node() {
      instructions = new ArrayList<Instruction>();
      parents = new ArrayList<Node>();
      children = new ArrayList<Node>();
   }

   public Node(String label) {
      this();
      this.setLabel(Node.nextLabel(label)); // label
   }

   public ArrayList<Instruction> getInstr() {
      return this.instructions;
   }

   public void addInstr(Instruction i) {
      this.instructions.add(i);
   }

   public String toILOC() {
      String ret = "";

      if (this.getInstr().size() > 0) {
         ret = getLabel() + ":\n";

         for (Instruction i : this.getInstr()) {
            ret += "\t" + i.toILOC() + "\n";
         }
      }

      return ret;
   }

   public String toSparc() {
      String ret = "";

      if (this.getInstr().size() > 0) {
         ret = getLabel() + ":\n";

         for (Instruction i : this.getInstr()) {
            ret += "\t" + i.toSparc() + "\n";
         }
      }

      return ret;
   }

   public static String nextLabel() {
      return nextLabel("");
   }

   public static String nextLabel(String id) {
      return "." + id + labelCount++;
   }

   public Iterator<Node> iterator() {
      return children.iterator();
   }
}
