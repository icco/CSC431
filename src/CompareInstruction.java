/**
 * The base for all Comparator instructions. All of the subclasses of this
 * class should be generated.
 */
public class CompareInstruction extends Instruction {
   public String reg1 = "cc";
   public String reg2 = "i1";
   public String reg3 = "i2";

   public CompareInstruction(String r1, String r2, String r3) {
      if (r1 != null)
         this.reg1 = r1;

      if (r2 != null)
         this.reg2 = r2;

      if (r3 != null)
         this.reg3 = r3;

      this.instr = "compy";
   }

   public CompareInstruction() {
      this(null, null, null);
   }

   public String toString() {
      return this.instr + " " + this.reg1 + ", " + this.reg2 + ", " + this.reg3;
   }
}
