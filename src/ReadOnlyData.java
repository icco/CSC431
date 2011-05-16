public class ReadOnlyData {
   private static final ReadOnlyData INSTANCE = new ReadOnlyData();
   private String data = "\n\n";

   // Private constructor prevents instantiation from other classes
   private ReadOnlyData() {
      this.addLine(".section\t\".rodata\"");
      this.addLine(".align 8");
   }

   public void addLine(String line) {
      this.data += line + "\n";
   }

   public static ReadOnlyData getInstance() {
      return INSTANCE;
   }

   public String toString() {
      return this.data;
   }

}

