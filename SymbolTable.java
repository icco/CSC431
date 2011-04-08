import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SymbolTable {
   protected Map<String, Type> globals;
   protected Map<String, Type> locals;

   public SymbolTable() {
      globals = new HashMap<String, Type>(); 
      locals = new HashMap<String, Type>(); 
   }

   public boolean bind(String name, Type t) {
      return bind(name, t, false);
   }

   public boolean bind(String name, Type t, boolean isGlobal) {
      Map<String, Type> table = (isGlobal ? globals : locals);

      if (table.get(name) == null) {
         table.put(name, t);
      } else {
         Evil.error("Cannot redeclare " + name 
          + " as " + (isGlobal ? "global" : "local") 
          + " variable");
      }

      return true;
   }

   public Type get(String name) {
      if (locals.get(name) != null) {
         return locals.get(name);
      }

      if (globals.get(name) != null) {
         return globals.get(name);
      }

      Evil.error("Reference to undeclared symbol " + name);

      return null;
   }

   public void clearLocals() {
      locals.clear();
   }

   public void bindParameters(FuncType func) {
      for (Symbol s : func.getParams()) {
         bind(s.getName(), s.getType());
      }
   }

   public void bindDeclarations(List<Symbol> symbols) {
      bindDeclarations(symbols, false);
   }

   public void bindDeclarations(List<Symbol> symbols, boolean isGlobal) {
      for (Symbol s : symbols) {
         bind(s.getName(), s.getType(), isGlobal);
      }
   }
}
