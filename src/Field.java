public class Field extends ID {
   private StructType type;

   public Field() { }
   public Field(String id) { 
      this.id = id; 
   }

   public Field(String id, StructType type) { 
      this.id = id; 
      this.type = type;
   }

   public void setType(StructType type) { this.type = type; }
   public StructType getType() { return this.type; }

   public Integer getOffset() {
      return type.getOffset(id);
   }

   public String toString() { 
      return this.toILOC();
   }
}
