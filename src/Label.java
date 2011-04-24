/**
 * Opearand that's a wrapper around a String.
 */
public class Label implements Operand { 
   String name;

   public Label() { }
   public Label(String name) { this.name = name; }
   public String toString() { return name; }
}
