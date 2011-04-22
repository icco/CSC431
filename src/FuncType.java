import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class FuncType extends Type {
   private List<Symbol> params;
   private Type ret;
   private Map<String, Symbol> localMap;
   private Map<String, Symbol> paramMap;

   /** Getters and Setters */
   public List<Symbol> getParams() { return params; }
   public Type getReturn() { return ret; }

   public void setParams(List<Symbol> p) { this.params = p; }
   public void setReturn(Type t) { this.ret = t; }

   public void bindLocalMap(Map<String, Symbol> locals) {
      this.localMap = locals;
   }

   public void bindParamMap(Map<String, Symbol> params) {
      this.paramMap = params;
   }

   public Map<String, Symbol> getLocalMap() { return localMap; }
   public Map<String, Symbol> getParamMap() { return paramMap; }
}
