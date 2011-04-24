import java.util.*;

public class FuncType extends Type {
   private List<Symbol> params;
   private Type ret;
   private Map<String, Symbol> localMap;
   private Map<String, Symbol> paramMap;
   private List<Symbol> locals; // It's nice to have a list because iterating is easy.

   /** Getters and Setters */
   public List<Symbol> getParams() { return params; }
   public Type getReturn() { return ret; }

   public void setParams(List<Symbol> p) { this.params = p; }
   public void setReturn(Type t) { this.ret = t; }

   public void bindLocalMap(Map<String, Symbol> localMap) {
      Set<String> names = localMap.keySet();

      locals = new LinkedList<Symbol>();
      for (String name : names) {
         locals.add(localMap.get(name));
      }

      this.localMap = localMap;
   }

   public void bindParamMap(Map<String, Symbol> params) {
      this.paramMap = params;
   }

   public Map<String, Symbol> getLocalMap() { return this.localMap; }
   public Map<String, Symbol> getParamMap() { return this.paramMap; }
   public List<Symbol> getLocals() { return this.locals; };

}
