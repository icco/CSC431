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

   public boolean bind(Symbol s) {
      return bind(s, false);
   }

   public boolean bind(Symbol s, boolean isGlobal) {
      Map<String, Type> table = (isGlobal ? globals : locals);

      if (table.get(s.getName()) == null) {
         table.put(s.getName(), s.getType());

         System.err.println("Bound " 
          + (isGlobal ? "global" : "local") + " variable " + s);

      } else {
         Evil.error("Cannot redeclare " + s 
          + " as " + (isGlobal ? "global" : "local") 
          + " variable.");
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
         bind(s);
      }
   }

   public void bindDeclarations(List<Symbol> symbols) {
      bindDeclarations(symbols, false);
   }

   public void bindDeclarations(List<Symbol> symbols, boolean isGlobal) {
      for (Symbol s : symbols) {
         bind(s, isGlobal);
      }
   }
}
