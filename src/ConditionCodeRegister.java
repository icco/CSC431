public class ConditionCodeRegister extends Register {
   public String toString() {
      return toILOC();
   }

   public String toILOC() {
      return "%cc";
   }

   public String toSparc() {
      return "%icc";
   }
}
