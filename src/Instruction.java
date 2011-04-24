import java.util.*;
import java.lang.*;

public class Instruction {
   ArrayList<Operand> sources = new ArrayList<Operand>();

   public String toString() {
      return "NULL INSTRUCTION.";
   }

   void addSource(Operand in) {
      this.sources.add(in);
   }

   public void addLabel(Label in) {
      this.addSource(in);
   }

   public void addRegister(Register in) {
      this.addSource(in);
   }

   public void addImmediate(Immediate in) {
      this.addSource(in);
   }

   public void addImmediate(Integer in) {
      this.addSource(new Immediate(in));
   }
}
