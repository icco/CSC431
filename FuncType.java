import java.util.List;

public class FuncType extends Type {
   private List<Symbol> params;
   private Type ret;

   /** Getters and Setters */
   public List<Symbol> getParams() { return params; }
   public Type getReturn() { return ret; }

   public void setParams(List<Symbol> p) { this.params = p; }
   public void setReturn(Type t) { this.ret = t; }
}
