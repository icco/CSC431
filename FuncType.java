import java.util.List;

public class FuncType extends Type {
   private List<Type> params;
   private Type ret;

   public List<Type> getParams() {
      return params;
   }

   public Type getReturn() {
      return ret;
   }
}
