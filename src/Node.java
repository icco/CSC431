import java.util.*;

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

   public Set<Register> getGenSet() { 
      if (gen == null) {
         createGenAndKill();
      }
      
      return gen; 
   }

   public Set<Register> getKillSet() { 
      if (kill == null) {
         createGenAndKill();
      }
      
      return kill; 
   }

   public Set<Register> getLiveSet() { 
      if (live == null) {
          // This needs to iterate, AND still might not be the right place to do this.
         createLive();
      }

      return live; 
   }

   /**
    * Creates gen and kill for node.
    */
   public void createGenAndKill() {
      gen = new HashSet<Register>();
      kill = new HashSet<Register>();

      for (Instruction instr : getInstr()) {
         for (Register src : instr.getSources()) {
            if (!kill.contains(src)) {
               gen.add(src);
            }
         }

         for (Register dest : instr.getActualDestinations()) {
            kill.add(dest);
         }
      }

      if (kill.contains(new ConditionCodeRegister())) {
         Evil.error("kill has it");
      }

      if (gen.contains(new ConditionCodeRegister())) {
         Evil.error("gen has it");
      }
   }

   public void createLive() {
      Set<Register> liveOut = new HashSet<Register>();
      Set<Register> immediate;
      Set<Register> nonImmediate;

      for (Node successor : children) {
         if (successor != this) {
            immediate = successor.getGenSet();
            nonImmediate = successor.getLiveSet();
            nonImmediate.removeAll(successor.getKillSet());

            liveOut.addAll(immediate);
            liveOut.addAll(nonImmediate);
         }
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

      // TODO: We need to add special code above actual functions. Do we know
      // which labels we made and which are real?
      if (this.getInstr().size() > 0) {
         ret = getLabel() + ":\n";

         for (Instruction i : this.getInstr()) {
            for (Sparc s : i.toSparc()) {
               ret += "\t" + s + "\n";
            }
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
