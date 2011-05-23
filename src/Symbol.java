public class Symbol {
   public static enum Scope {LOCAL, PARAM, GLOBAL};

   private String name;
   private Type t;
   private int lineNumber;
   private Scope scopeLevel;

   private Register r;  // used for locals.
   private Integer offset; // used for globals and parameters.

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

   public void setScope(Scope s) { this.scopeLevel = s; }
   public boolean isLocal() { return this.scopeLevel == Scope.LOCAL; }
   public boolean isParam() { return this.scopeLevel == Scope.PARAM; }
   public boolean isGlobal() { return this.scopeLevel == Scope.GLOBAL; }

   public void setRegister(Register reg) { this.r = reg; }
   public Register getRegister() { return this.r; }
   public void setOffset(Integer offset) { this.offset = offset; }
   public Integer getOffset() { return this.offset; }
}
