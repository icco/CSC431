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

            // TODO: Need to add code here to deal with Immediates > 13 bits.
            if (this.sparcs.size() == 1) {
               for (Operand o : this.getOperands()) {
                  i.addOp(o);
               }
            } else {
               // Alright, Those were the simple instructions.
               // These are the hard ones.

               // Branches.
               if (instr.equals("be") ||
                     instr.equals("bl") ||
                     instr.equals("bg") ||
                     instr.equals("ba") ||
                     instr.equals("bne") ||
                     instr.equals("ble") ||
                     instr.equals("bge")
                  ) {
                     for (Operand o : this.getOperands()) {
                        i.addOp(o);
                     }
                 }
            }

            instructions.add(i);
         } catch (Exception e) {
            Evil.error("You're doing it wrong: " + e.getMessage());
         }
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
      if (in instanceof ConditionCodeRegister) {
         System.out.println(this + " has it!");
      }
      this.srcs.add(in);
      this.operands.add(in);
   }

   /**
    * Add a register the the destination and operand list.
    */
   public void addDest(Register in) {
      if (in instanceof ConditionCodeRegister) {
         System.out.println(this + " has it!");
      }
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

   public void transformRegisters(Map<Register, Register> allocations) {
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
               Evil.warning("No mapping for register " + virtual + ".");
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
            Evil.warning("No mapping for register " + virtual + ".");
         }
      }

      // Transform sources list.
      for (ndx = 0; ndx < srcs.size(); ndx++) {
         virtual = srcs.get(ndx);
         real = allocations.get(virtual);

         if (real != null) {
            srcs.set(ndx, real);
         } else {
            Evil.warning("No mapping for register " + virtual + ".");
         }
      }

   }
}
