/**
 * The base for all of the Math type instructions. All of the subclasses of
 * this class should be generated.
 */
public class MathInstruction extends Instruction {
   public String reg1 = "r1";
   public String reg2 = "r2";
   public String reg3 = "r3";

   public MathInstruction(String r1, String r2, String r3) {
      this.reg1 = r1;
      this.reg2 = r2;
      this.reg3 = r3;

      instr = "maths";
   }

   public MathInstruction() {
      this.reg1 = Instruction.getNextRegister();
      this.reg2 = Instruction.getNextRegister();
      this.reg3 = Instruction.getNextRegister();

      instr = "maths";
   }

   public String toString() {
      return this.instr + " " + this.reg1 + ", " + this.reg2 + ", " + this.reg3;
   }
}
