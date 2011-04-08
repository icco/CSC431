public class Symbol {
   private String name;
   private Type t;

   public Symbol() { }
   public Symbol(String name, Type t) {
      this.name = name;
      this.t = t;
   }

   public String getName() { return name; }
   public Type getType() { return t; }

   public void setName(String name) { this.name = name; }
   public void setType(Type t) { this.t = t; }
}
