#!/usr/bin/env python
#
# Instruction generator.
# @author Nat Welch
#
# This would be so much easier if I actually knew python or perl.

from datetime import datetime, date, time

instructions = [
   {
      'name': 'add',
      'sources': [ 'Register', 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'addi',
      'sources': [ 'Immediate', 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'div',
      'sources': [ 'Register', 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'mult',
      'sources': [ 'Register', 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'sub',
      'sources': [ 'Register', 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'rsubi',
      'sources': [ 'Immediate', 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'and',
      'sources': [ 'Register', 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'or',
      'sources': [ 'Register', 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'xori',
      'sources': [ 'Immediate', 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'compi',
      'sources': [ 'Immediate', 'Register' ],
      'dest': [ 'ConditionCodeRegister' ],
   },
   {
      'name': 'comp',
      'sources': [ 'Register', 'Register' ],
      'dest': [ 'ConditionCodeRegister' ],
   },
   {
      'name': 'comp',
      'sources': [ 'Register', 'Register' ],
      'dest': [ 'ConditionCodeRegister' ],
   },
   {
      'name': 'cbreq',
      'sources': [ 'ConditionCodeRegister', 'Label', 'Label' ],
      'dest': [ ],
   },
   {
      'name': 'cbrge',
      'sources': [ 'ConditionCodeRegister', 'Label', 'Label' ],
      'dest': [ ],
   },
   {
      'name': 'cbrgt',
      'sources': [ 'ConditionCodeRegister', 'Label', 'Label' ],
      'dest': [ ],
   },
   {
      'name': 'cbrle',
      'sources': [ 'ConditionCodeRegister', 'Label', 'Label' ],
      'dest': [ ],
   },
   {
      'name': 'cbrlt',
      'sources': [ 'ConditionCodeRegister', 'Label', 'Label' ],
      'dest': [ ],
   },
   {
      'name': 'cbrne',
      'sources': [ 'ConditionCodeRegister', 'Label', 'Label' ],
      'dest': [ ],
   },
]

# Now that the instructions are defined, apply them to the template.
for instr in instructions:
   classname = instr['name'].capitalize() + "Instruction"
   filename = "src/" + classname + ".java"

   sources = ""
   counter = 0;
   for src in instr['sources']:
      var = "src%(c)d" % {'c':counter}
      counter += 1
      sources += "   %(classname)s %(varname)s = null;\n" % {'classname': src, 'varname': var}

   counter = 0;
   dests = ""
   for dest in instr['dest']:
      var = "dest%(c)d" % {'c':counter}
      counter += 1
      dests += "   %(classname)s %(varname)s = null;\n" % {'classname': dest, 'varname': var}

   data = {
      'date': datetime.now().isoformat(' '),
      'classname': classname,
      'instr': instr['name'],
      'sources': sources,
      'dests': dests,
      'toILOC': '"%(i)s "' % {'i': instr['name']}
   }

   # Take the data we built, and apply it to the template below.
   txt = """
/**
 * Generated automatically by generate_instructions.py
 */
public class %(classname)s extends Instruction {
%(sources)s
%(dests)s
   public %(classname)s() {
   }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      return %(toILOC)s;
   }
}
""" % data

   f = open(filename, 'w')
   f.write(txt)
   f.close()
