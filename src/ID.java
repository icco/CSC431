public class ID implements Operand {
   public String id;

   public ID() {
      id = "sym";
   }

   public ID(String name) {
      id = name;
   }

   public String toString() {
      return toILOC();
   }

   public String toILOC() {
      return id;
   }
}
