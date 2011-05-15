public class Immediate implements Operand {
   public Integer value = null;

   public Immediate() { }

   public Immediate(Integer in) {
      this.setValue(in);
   }

   public Integer getValue() {
      return this.value;
   }

   public void setValue(Integer in) {
      this.value = in;

      if (this.value > 4095 || this.value < -4095) {
         Evil.warning("You are using an immediate larger than 13 bits, which is not allowed!");
      }
   }

   public String toString() {
      return this.getValue().toString();
   }
}
