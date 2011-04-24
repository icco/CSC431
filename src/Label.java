/**
 * Opearand that's a wrapper around a String.
 */
public class Label implements Operand { 
   protected String name;

   public Label() { }
   public Label(String name) { 
      this.name = name; 
   }

   public String toString() {
      return toILOC();
   }

   public String toILOC() {
      return name;
   }
}
