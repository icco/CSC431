public class ID implements Operand {
   public String id;

   public ID() {
      this.id = "sym";
   }

   public ID(String name) {
      this.id = name;
   }

   public String toString() {
      return this.id.toString();
   }

   public String toILOC() {
      return this.toString();
   }

   public String toSparc() {
      return this.toString();
   }
}
