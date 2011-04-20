public class Register implements Operand {
   public static int regCounter = 0;
   public int id;

   public Register() {
      id = regCounter++;
   }

   public String toString() {
      return "r" + id;
   }
}
