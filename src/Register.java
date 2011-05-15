public class Register implements Operand {
   public static int regCounter = 0;
   public String id = "";
   public StructType type;

   public Register() {
      regCounter = regCounter + 1;
      this.id = "r" + regCounter;
   }

   public Register(String in) {
      this.id = in;
   }

   public StructType getType() { 
      return this.type; 
   }

   public void setType(Type type) {
      // We only keep track of StructTypes.
      if (type instanceof StructType) {
         this.type = (StructType) type;
      }
   }

   public String toString() {
      return this.id;
   }

   public boolean equals(Object other) {
      if (other instanceof Register) {
         return toString().equals(other.toString());
      } 

      return false;
   }

   public int hashCode() {
      return toString().hashCode();
   }
}
