public class Register implements Operand {
   public static int regCounter = 0;
   public int id = 0;

   public Register() {
      regCounter = regCounter + 1;
      this.id = regCounter;
   }

   public String toString() {
      return "r" + this.id;
   }
}
