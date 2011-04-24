public class Field implements Operand {
   private StructType type;
   protected String name;

   public Field() { }
   public Field(String name) { 
      this.name = name; 
   }

   public void setType(StructType type) { this.type = type; }
   public StructType getType() { return this.type; }

   public String toString() { 
      return this.toILOC();
   }

   public String toILOC() {
      return "@" + name; 
   }
}
