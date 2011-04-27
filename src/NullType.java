public class NullType extends StructType {
   public String toString() {
      return this.getClass().getName();
   }

   public boolean equals(Object other) {
      return other instanceof StructType;
   }
}
