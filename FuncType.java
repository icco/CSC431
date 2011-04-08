import java.util.List;

public class FuncType extends Type {
   public static class Parameter {
      Type t;
      String name;

      Parameter(Type t, String name) {
         this.t = t;
         this.name = name;
      }

      public Type getType() { return t; }
      public String getName() { return name; }
   }

   private List<Parameter> params;
   private Type ret;

   public List<Parameter> getParams() {
      return params;
   }

   public Type getReturn() {
      return ret;
   }
}
