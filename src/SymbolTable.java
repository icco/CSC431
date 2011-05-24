import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class SymbolTable {
   protected Map<String, Symbol> globals;
   protected Map<String, Symbol> locals;
   protected Map<String, Symbol> structs;
   protected Map<String, Symbol> functions;
   protected Map<String, Symbol> parameters;

   public SymbolTable() {
      globals = new HashMap<String, Symbol>();
      locals = new HashMap<String, Symbol>();
      structs = new HashMap<String, Symbol>();
      functions = new HashMap<String, Symbol>();
      parameters = new HashMap<String, Symbol>();
   }

   public String getTableName(Map table) {
      if (table == globals) return "global";
      if (table == locals) return "local";
      if (table == structs) return "structs";
      if (table == functions) return "functions";
      if (table == parameters) return "parameters";
      return "";
   }

   public void bind(Symbol s) {
      bind(s, false);
   }

   public void bind(Symbol s, boolean isGlobal) {
      if (isGlobal) {
         bind(s, globals);
      } else {
         bind (s, locals);
      }
   }

   public void bindStruct(Symbol s) {
      bind(s, structs);
   }

   public void bindFunction(Symbol fun) {
      bind(fun, functions);
   }

   public void bind(Symbol s, Map<String, Symbol> table) {
      if (table.get(s.getName()) == null) {

         table.put(s.getName(), s);
         Evil.debug("Bound " + s + " in " + getTableName(table) + " table");

      } else {
         Evil.error("Cannot redeclare " + s + " as " + getTableName(table), s.getLine());
      }
   }

   public Type getType(String name) {
      Symbol s = get(name);

      if (s != null)
         return s.getType();
      else
         return null;
   }

   public Symbol get(String name) {
      if (locals.get(name) != null) {
         return locals.get(name);
      }

      if (parameters.get(name) != null) {
         return parameters.get(name);
      }

      if (globals.get(name) != null) {
         return globals.get(name);
      }

      return null;
   }

   public StructType getStruct(String name) {
      Symbol s = structs.get(name);
      StructType sType = null;

      if (s != null) {
         sType = (StructType)s.getType();
      }

      return sType;
   }

   public FuncType getFunction(String name) {
      Symbol s = functions.get(name);
      FuncType fun = null;

      if (s != null) {
         fun = (FuncType)s.getType();
      }

      return fun;
   }

   public void saveLocals(FuncType fun) {
      fun.bindLocalMap(locals);
      fun.bindParamMap(parameters);

      locals = new HashMap<String, Symbol>();
      parameters = new HashMap<String, Symbol>();
   }

   public void loadLocals(FuncType fun) {
      locals = fun.getLocalMap();
      parameters = fun.getParamMap();
   }

   public void bindParameters(FuncType func) {
      for (Symbol s : func.getParams()) {
         bindParam(s);
      }
   }

   public void bindParam(Symbol s) {
      bind(s, parameters);
   }

   public void bindDeclarations(List<Symbol> symbols) {
      bindDeclarations(symbols, false);
   }

   public void bindDeclarations(List<Symbol> symbols, boolean isGlobal) {
      for (Symbol s : symbols) {
         bind(s, isGlobal);
      }
   }

   public void bindStructs(List<Symbol> symbols) {
      for (Symbol s : symbols) {
         bindStruct(s);
      }
   }

   public void bindFunctions(List<Symbol> symbols) {
      for (Symbol s : symbols) {
         bindFunction(s);
      }
   }

   // The only table that needs a getter.
   public Map<String, Symbol> getGlobals() {
      return globals;
   }

   public String toString() {
      return toILOC();
   }

   public String toILOC() {
      String functionDefs = "", localDefs = "";
      Set<String> functionNames, localNames;
      FuncType fun;

      functionNames = functions.keySet();
      for (String fName : functionNames) {
         functionDefs += "@function " + fName + "\n";

         localNames = getFunction(fName).getLocalMap().keySet();

         for (String lName : localNames) {
            localDefs += "@local " + fName + ":" + lName + "\n";
         }
      }

      return functionDefs + "\n" + localDefs + "\n";
   }

   public String toSparc() {
      String ret = "\n";

      for (String gname : this.getGlobals().keySet()) {
         ret += "\t.common " + gname + ",4,4\n";
      }

      return ret;
   }
}
