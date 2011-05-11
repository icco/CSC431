import java.util.*;
import java.lang.*;

public abstract class Sparc {
   ArrayList<Operand> sources = new ArrayList<Operand>();
   ArrayList<Operand> dests = new ArrayList<Operand>();

   void addSource(Operand in) {
      this.sources.add(in);
   }

   public void addDest(Operand in) {
      this.dests.add(in);
   }

   public ArrayList<Operand> getOperands() {
      ArrayList<Operand> ret = new ArrayList<Operand>();
      ret.addAll(this.sources);
      ret.addAll(this.dests);

      return ret;
   }
}
