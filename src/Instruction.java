public class Instruction {
   static Integer r_counter = 1;

   public static String getNextRegister() {
      String ret = "r" + r_counter;

      r_counter = r_counter + 1;

      return ret;
   }
}
