public class Immediate implements Operand {
   public Integer value = null;

   public Immediate() { }

   public Immediate(Integer in) {
      this.setValue(in);
   }

   public Integer getValue() {
      return this.value;
   }

   public void setValue(Integer in) { this.value = in; }

   public String toString() {
      return this.getValue().toString();
   }

   public String toSparc() {
      return this.toString();
   }

   public String toILOC() {
      return this.toString();
   }
}
