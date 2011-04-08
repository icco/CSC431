import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
   protected Map<String, Type> globals;
   protected Map<String, Type> locals;

   public SymbolTable() {
      globals = new HashMap<String, Type>(); 
      locals = new HashMap<String, Type>(); 
   }

   public boolean add(String name, Type t) {
      return add(name, t, false);
   }

   public boolean add(String name, Type t, boolean isGlobal) {
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

   public void addParameters(FuncType func) {
      locals.clear();

      for (FuncType.Parameter t : func.getParams()) {
         add(t.getName(), t.getType());
      }
   }
}
