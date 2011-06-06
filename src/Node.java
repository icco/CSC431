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
   protected Boolean function = false;
   public Integer callArg = 6;

   private Set<Register> gen;
   private Set<Register> kill;
   private Set<Register> live = new HashSet<Register>();

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

   /**
    * Recompute the live set.
    * @returns true if the live set did not change.
    */
   public boolean redoLiveSet() {
      Set<Register> oldLiveSet = new HashSet<Register>(live);

      createLive();

      /*
      System.out.print(getLabel() + "-> ");
      for (Node n : this.children) {
         System.out.print(n.getLabel() + ", ");
      }
      System.out.println();
      System.out.println(live);
      */

      return oldLiveSet.equals(live);
   }

   public Set<Register> getLiveSet() {
      return live;
   }

   public Boolean isFunction() {
      return this.function;
   }

   public Integer getMaxCallArguments() {
      Integer ret = 0;

      if (this.isFunction()) {
         for (Node n : this.children) {
            ret = Math.max(n.callArg, this.callArg);
         }
      }

      return Math.max(6, ret);
   }

   public void addCall(Integer argCount) {
      this.callArg = Math.max(argCount, this.callArg);
   }

   public Integer getLocalCount() {
      return 26;
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

         for (Register dest : instr.getDestinations()) {
            kill.add(dest);
         }
      }
   }

   public void createLive() {
      Set<Register> liveOut = new HashSet<Register>();
      Set<Register> immediate;
      Set<Register> nonImmediate;

      for (Node successor : children) {
         immediate = successor.getGenSet();
         nonImmediate =
          new HashSet<Register>(successor.getLiveSet());
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
         ret = this.getLabel() + ":\n";

         // If we are a function, we need to tell the stack how much space to
         // save for calls.
         if (this.isFunction()) {
            ret += "\t!#PROLOGUE# 0\n";
            ret += "\tsave %sp, " + this.getStackSize() + ", %sp\n";
            ret += "\t!#PROLOGUE# 1\n";
         }

         for (Instruction i : this.getInstr()) {

            // Transform here
            for (Register r : s.getSources()) {
               // if r in spills
               // r = new register w/ offset
            }

            for (Register r : s.getDestinations()) {
               // if r in spills
               // r = new register w/ offset
               // create store but don't add
            }

            //check for spill registers
            // insert loads
            for (Sparc s : i.toSparc()) {



               ret += "\t" + s + "\n";

            }
            // Print out stores
         }
      }

      return ret;
   }

   // Returns amount of space based on number of calls.
   // TODO: Figure out how to make correct
   public int getStackSize() {
      int ret = (96 + (this.getLocalCount() * 4) + (this.getMaxCallArguments() * 4));

      while ((ret % 8) != 0) {
         ret += 1;
      }

      return (-1) * ret;
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
