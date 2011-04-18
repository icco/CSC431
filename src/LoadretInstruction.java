
public class LoadretInstruction extends Instruction {
   public String reg1 = "r1";

   public LoadretInstruction(String r1) {
      super();
      this.instr = "loadret";

      if (r1 != null)
         this.reg1 = r1;
   }

   public LoadretInstruction() {
      this(Instruction.getNextRegister());
   }

   public String toString() {
      return this.instr + " " + this.reg1;
   }
}
