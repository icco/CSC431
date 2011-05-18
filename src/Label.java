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
      return this.name;
   }

   public String toILOC() {
      return this.toString();
   }

   public String toSparc() {
      return this.toString();
   }
}
