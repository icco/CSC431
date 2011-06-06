import java.util.*;
import java.lang.*;

public abstract class Instruction {
   public static Integer operandCount = 0;
   private ArrayList<Operand> operands = new ArrayList<Operand>();
   private ArrayList<Register> dests = new ArrayList<Register>();
   private ArrayList<Register> srcs = new ArrayList<Register>();

   protected ArrayList<String> sparcs = new ArrayList<String>();

   public String toString() {
      return "NULL INSTRUCTION.";
   }

   public String toILOC() {
      return "noop";
   }

   public ArrayList<Sparc> toSparc() {
      ArrayList<Sparc> instructions = new ArrayList<Sparc>();

      for (String instr : this.sparcs) {
         String cap = instr.toUpperCase();
         String classname = cap.substring(0,1) + instr.substring(1) + "Sparc";

         try {
            Class cls = Class.forName(classname);
            Sparc i = (Sparc)cls.newInstance();

            if (this.sparcs.size() == 1) {
               // Conditional moves need a %icc
               if (instr.length() > 3 && instr.substring(0,3).equals("mov")) {
                  i.addSource(new ConditionCodeRegister());
               }

               for (Operand o : this.getOperands()) {
                  // Don't write out %icc for cmp.
                  if (!(instr.equals("cmp") &&
                   o instanceof ConditionCodeRegister)) {
                     i.addOp(o);
                  }
               }
            }

            // To deal with jumpi basically.
            if (instr.equals("ba") && this.sparcs.size() <= 2) {
               for (Operand o : this.getOperands()) {
                  i.addSource(o);
               }
            }

            instructions.add(i);
         } catch (ClassNotFoundException e) {
            Evil.error("No such class " + classname
             + ": " + e.getMessage());
         } catch (InstantiationException e) {
            Evil.error("Could not instantiate object "
             + classname + ": " + e.getMessage());
         } catch (IllegalAccessException e) {
            Evil.error("Could not access constructor for object "
             + classname + ": " + e.getMessage());
         }
      }

      // Globals
      if (this instanceof StoreglobalInstruction) {
         Register r = new Register("%o1");
         r.addOffset(0);
         instructions.get(0).addOp(this.getOperands().get(1));
         instructions.get(0).addOp(r.copy());
         instructions.get(1).addOp(this.getOperands().get(0));
         instructions.get(1).addOp(r);
      }

      if (this instanceof LoadglobalInstruction) {
         Register r = new Register("%o1");
         r.addOffset(0);
         instructions.get(0).addOp(this.getOperands().get(0));
         instructions.get(0).addOp(r.copy());
         instructions.get(1).addOp(r);
         instructions.get(1).addOp(this.getOperands().get(1));
      }

      // Conditional Branches.
      String firstthree = this.getClass().getName().toLowerCase().substring(0,3);
      if (firstthree.equals("cbr")) {
         instructions.get(0).addOp(this.getOperands().get(1));
         instructions.get(2).addOp(this.getOperands().get(2));
      } else if (this instanceof CallInstruction) {
         instructions.get(0).addOp(this.getOperands().get(0));
      }

      return instructions;
   }

   public ArrayList<Register> getDestinations() {
      return this.dests;
   }

   public ArrayList<Register> getSources() {
      return this.srcs;
   }

   public ArrayList<Operand> getOperands() {
      return this.operands;
   }

   /**
    * Add a register to the source and operand list.
    */
   public void addSource(Register in) {
      this.srcs.add(in);
      this.operands.add(in);
   }

   /**
    * Add a register the the destination and operand list.
    */
   public void addDest(Register in) {
      this.dests.add(in);
      this.operands.add(in);
   }

   /**
    * The rest of the addX functions are wrappers around addOp.
    */
   public void addOp(Operand op) {
      this.operands.add(op);
   }

   public void addLabel(Label in) {
      this.addOp(in);
   }

   public void addLabel(String in) {
      this.addOp(new Label(in));
   }

   public void addID(String in) {
      this.addOp(new ID(in));
   }

   public void addField(Field in) {
      this.addOp(in);
   }

   public void addRegister(Register in) {
      this.addOp(in);
   }

   public void addImmediate(Immediate in) {
      this.addOp(in);
   }

   public void addImmediate(Integer in) {
      this.addOp(new Immediate(in));
   }

   /**
    * Instead of having a parent class for conditional moves.
    */
   public boolean isConditionalMove() {
      return this instanceof MoveqInstruction
       || this instanceof MovgeInstruction
       || this instanceof MovgtInstruction
       || this instanceof MovleInstruction
       || this instanceof MovleInstruction
       || this instanceof MovneInstruction;
   }

   /**
    * Instead of having a parent class for call-like functions.
    */
   public boolean isCall() {
      return this instanceof CallInstruction
       || this instanceof PrintInstruction
       || this instanceof PrintlnInstruction
       || this instanceof ReadInstruction
       || this instanceof NewInstruction
       || this instanceof MultInstruction
       || this instanceof DivInstruction;
   }

   public void transformRegisters(
    Map<Register, Register> allocations, Node func) {

      Register virtual, real;
      int ndx = 0;

      // Transform operands list.
      for (Operand op : this.operands) {
         if (op instanceof Register && !(op instanceof ConditionCodeRegister)) {
            virtual = (Register) op;

            real = allocations.get(virtual);

            if (real != null) {
               operands.set(ndx, real);
            } else {
               func.addSpill(virtual);
               Evil.warning(virtual + " marked as spilled");
            }
         }

         ndx++;
      }

      // Transform destination list.
      for (ndx = 0; ndx < dests.size(); ndx++) {
         virtual = dests.get(ndx);
         real = allocations.get(virtual);

         if (real != null) {
            dests.set(ndx, real);
         } else {
            Evil.warning(virtual + " marked as spilled");
            func.addSpill(virtual);
         }
      }

      // Transform sources list.
      for (ndx = 0; ndx < srcs.size(); ndx++) {
         virtual = srcs.get(ndx);
         real = allocations.get(virtual);

         if (real != null) {
            srcs.set(ndx, real);
         } else {
            Evil.warning(virtual + " marked as spilled");
            func.addSpill(virtual);
         }
      }
   }

   /**
    * In copy propagation we never want to transform a destination.
    * It was easier to write this function than use transformRegisters.
    */
   public void transformSources(Register src, Register betterSrc) {
      Map<Register, Register> temp = new HashMap<Register, Register>();
      int destSize = getDestinations().size();
      Register oldSource;

      // Transform sources list.
      for (int ndx = 0; ndx < srcs.size(); ndx++) {
         oldSource = srcs.get(ndx);

         if (oldSource.equals(src)) {
            srcs.set(ndx, betterSrc);
         }
      }

      // Transfor operands list but don't touch the destinations.
      for (int ndx = 0; ndx < this.operands.size() - destSize; ndx++) {
         Operand op = this.operands.get(ndx);

         if (op instanceof Register && !(op instanceof ConditionCodeRegister)) {
            oldSource = (Register) op;

            if (oldSource.equals(src)) {
               operands.set(ndx, betterSrc);
            }
         }
      }
   }
}
