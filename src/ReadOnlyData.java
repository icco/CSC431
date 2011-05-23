public class ReadOnlyData {
   private static final ReadOnlyData INSTANCE = new ReadOnlyData();
   private String data = "\n\n";

   // Private constructor prevents instantiation from other classes
   private ReadOnlyData() {

      // For printing and reading...
      this.addLine(".section\t\".rodata\"");
      this.addLine(".align 8");
      this.addLine(".LLC0:");
      this.addLine("\t.asciz  \"%d \"");
      this.addLine(".align 8");
      this.addLine(".LLC1:");
      this.addLine("\t.asciz  \"%d\\n\"");
      this.addLine(".align 8");
      this.addLine(".LLC2:");
      this.addLine("\t.asciz  \"%d\"");
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
