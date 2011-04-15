public class Symbol {
   private String name;
   private Type t;
   private int lineNumber;

   public Symbol() { }
   public Symbol(String name, Type t) {
      this.name = name;
      this.t = t;
   }

   public Symbol(String name, Type t, int line) {
      this(name, t);
      this.lineNumber = line;
   }

   public String toString() {
      return getType() + " " + getName() + " on line " + getLine();
   }

   public String getName() { return name; }
   public Type getType() { return t; }
   public int getLine() { return lineNumber; }

   public void setName(String name) { this.name = name; }
   public void setType(Type t) { this.t = t; }
   public void setLine(int line) { this.lineNumber = line; }
}
