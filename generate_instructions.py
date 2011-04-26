#!/usr/bin/env python
#
# Instruction generator.
# @author Nat Welch
#
# This would be so much easier if I actually knew python or perl.

from datetime import datetime, date, time

instructions = [

# Arithmetic (MATHS)

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

# Boolean

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

# Comparison and Branching (Forks in the road)

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
  {
     'name': 'jumpi',
     'sources': [ 'Label' ],
     'dest': [ ],
  },

# Loads (Not the kind for her face)

   {
      'name': 'loadi',
      'sources': [ 'Immediate' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'loadai',
      'sources': [ 'Register', 'Field' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'loadglobal',
      'sources': [ 'ID' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'loadinargument',
      'sources': [ 'ID', 'Immediate' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'loadret',
      'sources': [ ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'computeformaladdress',
      'sources': [ 'ID', 'Immediate' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'restoreformal',
      'sources': [ 'ID', 'Immediate' ],
      'dest': [ ],
   },
   {
      'name': 'computeglobaladdress',
      'sources': [ 'ID' ],
      'dest': [ 'Register' ],
   },

# Stores (Target, 7-11, etc.)

   {
      'name': 'storeai',
      'sources': [ 'Register' ],
      'dest': [ 'Register', 'Field' ],
   },
   {
      'name': 'storeglobal',
      'sources': [ 'Register' ],
      'dest': [ 'ID' ],
   },
   {
      'name': 'storeinargument',
      'sources': [ 'Register' ],
      'dest': [ 'ID', 'Immediate' ],
   },
   {
      'name': 'storeoutargument',
      'sources': [ 'Register' ],
      'dest': [ 'ID', 'Immediate' ],
   },
   {
      'name': 'storeret',
      'sources': [ 'Register' ],
      'dest': [ ],
   },

# Invocation (Casting spells, raising demons)

   {
      'name': 'call',
      'sources': [ 'Label' ],
      'dest': [ ],
   },
   {
      'name': 'ret',
      'sources': [ ],
      'dest': [ ],
   },

# Allocation (Too serious to joke about)

   {
      'name': 'new',
      'sources': [ 'StructIdentifier' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'del',
      'sources': [ 'Register' ],
      'dest': [ ],
   },

# I/0 (That's a planet right?)

   {
      'name': 'print',
      'sources': [ 'Register' ],
      'dest': [ ],
   },
   {
      'name': 'println',
      'sources': [ 'Register' ],
      'dest': [ ],
   },
   {
      'name': 'read',
      'sources': [ 'Register' ],
      'dest': [ ],
   },

# Moves (for the dance floor)
   {
      'name': 'mov',
      'sources': [ 'Register' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'moveq',
      'sources': [ 'Immediate' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'movge',
      'sources': [ 'Immediate' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'movgt',
      'sources': [ 'Immediate' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'movle',
      'sources': [ 'Immediate' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'movlt',
      'sources': [ 'Immediate' ],
      'dest': [ 'Register' ],
   },
   {
      'name': 'movne',
      'sources': [ 'Immediate' ],
      'dest': [ 'Register' ],
   },
]

# Now that the instructions are defined, apply them to the template.
for instr in instructions:
   classname = instr['name'].capitalize() + "Instruction"
   filename = "src/" + classname + ".java"
   data = instr['sources'] + instr['dest']

   data = {
      'date': datetime.now().isoformat(' '),
      'classname': classname,
      'instr': instr['name'],
      'pattern': ' '.join(data),
      'count': len(data)
   }

   # Take the data we built, and apply it to the template below.
   txt = """import java.util.*;
import java.lang.*;

/**
 * Generated automatically by generate_instructions.py
 */
public class %(classname)s extends Instruction {
   public static Integer operandCount = %(count)d;
   public %(classname)s() { }

   public String toString() {
      return this.toILOC();
   }

   public String toILOC() {
      String classPattern = new String("%(pattern)s");
      String[] pattern = classPattern.split(" ");
      String ret = "%(instr)s ";
      int operandCount = this.getOperands().size();

      if ((operandCount != 0) && (operandCount != pattern.length)) {
         Evil.error(ret + ": Found " + operandCount + " operands, ILOC expecting " + pattern.length);
      }

      for (Operand r : this.getOperands()) {
         ret = ret + r + ", ";
      }

      ret = ret.trim();
      if (ret.lastIndexOf(",") == ret.length()-1)
         ret = ret.substring(0, ret.length()-1);

      for (int i = 0; i < this.getOperands().size(); i++) {
         Operand o = this.getOperands().get(i);
         String oper = "null";

         if (o != null) { oper = o.getClass().getName(); }

         if (!oper.equals(pattern[i])) {
            Evil.error(ret + ": ILOC expecting " + classPattern + ". Found " + oper);
         }
      }

      return ret;
   }
}
""" % data

   f = open(filename, 'w')
   f.write(txt)
   f.close()
