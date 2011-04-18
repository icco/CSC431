import java.util.*;
import java.lang.*;

public class Instruction {
   ArrayList<Operand> sources = new ArrayList<Operand>();
   ArrayList<Operand> dests   = new ArrayList<Operand>();

   public String toString() {
      return "NULL INSTRUCTION.";
   }

   public void addSource(Operand in) {
      this.sources.add(in);
   }

   public void addDest(Operand in) {
      this.dests.add(in);
   }
}
