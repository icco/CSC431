import java.util.ArrayList;

public class SparcRegisters {
   public static ArrayList<Register> globals;
   public static ArrayList<Register> locals;
   public static ArrayList<Register> inputs;
   public static ArrayList<Register> outputs;
   public static ArrayList<Register> all;

   public static void setupRegisters() {
      globals = new ArrayList<Register>();
      locals = new ArrayList<Register>();
      inputs = new ArrayList<Register>();
      outputs = new ArrayList<Register>();
      all = new ArrayList<Register>();

      // %g0 is always 0, can't write to it.
      // 1 2 3 used for spills only.
      //globals.add(new Register("%g1"));
      //globals.add(new Register("%g2"));
      //globals.add(new Register("%g3"));
      globals.add(new Register("%g4"));
      globals.add(new Register("%g5"));
      // %g6 and %g7 are used by the system so don't use them.

      locals.add(new Register("%l0"));
      locals.add(new Register("%l1"));
      locals.add(new Register("%l2"));
      locals.add(new Register("%l3"));
      locals.add(new Register("%l4"));
      locals.add(new Register("%l5"));
      locals.add(new Register("%l6"));
      locals.add(new Register("%l7"));

      inputs.add(new Register("%i0")); // Used for return values.
      inputs.add(new Register("%i1"));
      inputs.add(new Register("%i2"));
      inputs.add(new Register("%i3"));
      inputs.add(new Register("%i4"));
      inputs.add(new Register("%i5"));

      outputs.add(new Register("%o0")); // Used for return values.
      outputs.add(new Register("%o1"));
      outputs.add(new Register("%o2"));
      outputs.add(new Register("%o3"));
      outputs.add(new Register("%o4"));
      outputs.add(new Register("%o5"));

      all.addAll(globals);
      all.addAll(locals);
      all.addAll(inputs);
      all.addAll(outputs);
   }
}
