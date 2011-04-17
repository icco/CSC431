public class Register {
   public static int regCounter = 0;
   public int id;

   public Register() {
      id = regCounter++;
   }
}
