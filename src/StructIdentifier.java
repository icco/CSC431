public class StructIdentifier extends StructType implements Operand {
   StructIdentifier(StructType t) {
      this.name = t.name;
      this.memberOrder = t.memberOrder;
      this.members = t.members;
   }

   public String toString() {
      return this.toILOC(); 
   }

   /* TODO: Implement... */
   public String toSparc() {
      return this.toILOC(); 
   }

   public String toILOC() {
      String def = "";

      def += getName() + ", ["; 

      for (int ndx = 0; ndx < memberOrder.size(); ndx++) {
         def += memberOrder.get(ndx);

         if (ndx != memberOrder.size() - 1) {
            def += ", ";
         }
      }

      def += "]";

      return def;
   }
}
