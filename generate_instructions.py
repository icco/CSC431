#!/usr/bin/env python
#
# Instruction generator.
# @author Nat Welch
#
# This would be so much easier if I actually knew python or perl.

from datetime import datetime, date, time

# First we generate the Math instructions.
mathi = [ 'add', 'div', 'mult', 'sub' ]

for instr in mathi:
   classname = instr.capitalize() + "Instruction"
   filename = "src/" + classname + ".java"

   txt = """
public class %(classname)s extends MathInstruction {
   public static String instr = "%(instr)s";
}
   """ % {'date': datetime.now().isoformat(' '), 'classname': classname, 'instr': instr}

   f = open(filename, 'w')
   f.write(txt)
   f.close()
