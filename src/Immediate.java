public class Immediate implements Operand {
   public static int counter = 0;
   public int id;

   public Immediate() {
      id = counter++;
   }

   public String toString() {
      return "i" + id;
   }
}
