/**
 * CFG Builder.
 * @author Ben Sweedler
 * @author Nat Welch
 */

tree grammar CFG;

options {
   tokenVocab=Evil;
   ASTLabelType=CommonTree;
   backtrack=true;
}

@header {
   import java.util.Map;
   import java.util.HashMap;
   import java.util.Vector;
   import java.util.Iterator;
   import java.util.LinkedList;
   import java.util.List;
}

@members {
   // Creates a CFG mapping label names to boxes
   private static GraphTable cfg = new GraphTable();
   private static Node finalNode; // final node for the current function.
   private static Map<String, Register> localRegisters = new HashMap<String, Register>();
   private static Map<String, Integer> paramOffsets = new HashMap<String, Integer>();
   private static Map<String, Register> globalRegisters = new HashMap<String, Register>();

   public void dump() {
      // Verify that build has already been run?

      // Print out Graph.
      // TODO: We are supposed to write to a file, not stdout.
      System.out.println(cfg);
   }
}

build
@init {
}
   : ^(PROGRAM types declarations {
      globalRegisters.clear();

      for (String s : $declarations.names) {
         globalRegisters.put(s, new Register()); /* TODO: where do we put globals?? */
      }
   } functions)
   ;

types
@init {
}
   : ^(TYPES (types_declaration)*)
   ;

types_declaration
@init {
}
   : ^(STRUCT ID (var_decl)+)
   ;

var_decl returns [String name]
@init {
}
   : ^(DECL ^(TYPE type) ID) { $name = $ID.getText(); }
   ;

type
@init {
}
   : INT
   | BOOL
   | ^(STRUCT ID)
   ;

declarations returns [List<String> names]
@init {
   $names = new LinkedList<String>();
}
   : ^(DECLS (declaration { $names.addAll($declaration.names); })*)
   ;

declaration returns [List<String> names]
@init {
   $names = new LinkedList<String>();
}
   : ^(DECLLIST ^(TYPE type) (ID { $names.add($ID.getText()); })+)
   ;

functions
@init {
}
   : ^(FUNCS function*)
   ;

function
@init {
   Node start = new Node();
}
   : ^(FUN ID {
      start.setLabel($ID.getText());

      /* All paths from start end with the function's final node */
      finalNode = new Node();
      finalNode.setLabel(("." + $ID.getText() + "_final"));

   } 
   parameters {
      paramOffsets.clear();
      int offset = 0;

      for (String s : $parameters.names) {
         paramOffsets.put(s, offset++);
      }
   }
   ^(RETTYPE return_type) declarations {
      localRegisters.clear();

      for (String s : $declarations.names) {
         localRegisters.put(s, new Register());
      }
   }
   statement_list[start]) {
      // Loading parameters code.

      // Statement list code.

      // Link last current block to final block.
      // (this only makes a difference for void funtions)
      $statement_list.exit.addChild(finalNode);
      finalNode.addParent($statement_list.exit);

      // Exiting code.
      cfg.put($ID.getText(), start);
   }
   ;

return_type
@init {
}
   : type
   | VOID
   ;

parameters returns [List<String> names]
@init {
   $names = new LinkedList<String>();
}
   : ^(PARAMS (var_decl { $names.add($var_decl.name); } )*)
   ;

statement_list[Node current] returns [Node exit]
@init {
   $exit = current; /* In case of any empty statement_list. */
}
   : ^(STMTS (statement[current] { $exit = current = $statement.exit;  })*)
   ;

statement[Node current] returns [Node exit]
@init {
   $exit = current;
}
   : block[current] { $exit = $block.exit; }
   | assignment[current]
   | print[current]
   | read[current]
   | conditional[current] { $exit = $conditional.exit; }
   | loop[current] { $exit = $loop.exit; }
   | delete[current]
   | ret[current] { $exit = $ret.exit; }
   | invocation[current]
   ;

block[Node current] returns [Node exit]
@init {
}
   : ^(BLOCK statement_list[current])
   {
      $exit = $statement_list.exit;
   }
   ;

assignment[Node current]
@init {
}
   : ^(ASSIGN expression[current] lvalue[$expression.r])
   ;

lvalue[Register store] returns [Register r]
@init {
}
   : ID {
      /* store in local/global/parameter */

   }
   | ^(DOT lvalue_h ID) {
    /**
     * Store in heap:
     * lvalue_h rules gets register with memory address.
     * ID is offset to store at
     */
   }
   ;

lvalue_h returns [Register r]
@init {
}
   :  ID {
      /* Load from global/local */
   }
   | ^(DOT lvalue_h ID) {
     /* lvalue_h rule gets register with memory address.
      * ID is offset to store at
      */
   }
   ;

print[Node current]
@init {
}
   :  ^(PRINT expression[current] (ENDL)?)
   ;

read[Node current]
@init {
   // Scanf into global.
   // move global into new register.
   Register readValue = new Register();
}
   :  ^(READ lvalue[readValue])
   ;

conditional[Node current] returns [Node exit]
@init {
   String ifLabel = Node.nextLabel("if");

   Node tStart = new Node();
   Node fStart = new Node();
   $exit = new Node();

   tStart.setLabel(ifLabel + "_then");
   fStart.setLabel(ifLabel + "_else");
   $exit.setLabel(ifLabel + "_after");
}
   :  ^(IF
      expression[current] {
         /* put expression code in current block */

      }
      tb=block[tStart]
      (fb=block[fStart])?)
      {
         /* Add code for looking at expression and jumping */
         /* Link then block path */
         current.addChild(tStart);
         tStart.addParent(current);
         $tb.exit.addChild($exit);
         $exit.addParent($tb.exit);

         if ($fb.exit != null) {
            /* Link else block path */
            current.addChild(fStart);
            fStart.addParent(current);
            $exit.addParent($fb.exit);
            $fb.exit.addChild($exit);
         } else {
            current.addChild($exit);
            $exit.addParent(current);
         }

      }
   ;

loop[Node current] returns [Node exit]
@init {
   Node loopNode = new Node("loop");

   $exit = new Node();
   $exit.setLabel(loopNode.getLabel() + "_done");

   // Link the nodes.
   $exit.addParent(current);
   $exit.addParent(loopNode);

   current.addChild(loopNode);
   current.addChild($exit);

   loopNode.addChild($exit);
}
   : ^(WHILE expression[current] {
      // Add code to check if we should start looping or not.

   } block[loopNode] expression[loopNode])
   {
      // Add code to loopNode that checks if we should loop again.
   }
   ;

delete[Node current]
@init {
}
   : ^(DELETE expression[current])
   ;

ret[Node current] returns [Node exit]
@init {
}
   : ^(RETURN (expression[current])?) {
      Instruction lr = new LoadretInstruction();
      lr.addSource(new Register());
      current.addInstr(lr);

      // Put value in return register if expression is not null.

      // Link current block.
      current.addChild(finalNode);
      finalNode.addParent(current);

      $exit = new Node("after_ret"); // this node doesn't have a parent.
   }
   ;

invocation[Node current] returns [Register r]
@init {
}
   : ^(INVOKE ID arguments[current]) 
   {
      /* Do a jump to ID, then load from return adress into r */
   }
   ;

arguments[Node current]
@init {
}
   : ^(ARGS (expression[current])*)
   ;

expression[Node current] returns [Register r]
@init {
}
   : factor[current] { $r = $factor.r; }
   | ^(unop[current] factor[current]) { 
      $unop.inst.addSource($factor.r);
      $unop.inst.addDest($r = new Register());
      current.addInstr($unop.inst);
   }
   | ^(binop[current] f1=factor[current] f2=factor[current]) {
      $binop.inst.addSource($f1.r);
      $binop.inst.addSource($f2.r);
      $binop.inst.addDest($r = new Register());
      current.addInstr($binop.inst);
   }
   ;

binop[Node current] returns [Instruction inst]
   : AND { $inst = new AndInstruction(); }
   | OR { $inst = new OrInstruction(); }
   | EQ { $inst = new CbreqInstruction(); }
   | LT { $inst = new CbrltInstruction(); }
   | GT { $inst = new CbrgtInstruction(); }
   | NE { $inst = new CbrneInstruction(); }
   | LE { $inst = new CbrleInstruction(); }
   | GE { $inst = new CbrgeInstruction(); }
   | PLUS { $inst = new AddInstruction(); }
   | MINUS { $inst = new SubInstruction(); }
   | TIMES { $inst = new MultInstruction(); }
   | DIVIDE { $inst = new DivInstruction(); }
   ;

unop[Node current] returns [Instruction inst]
   : NOT /* TODO */
   | NEG /* TODO */
   ;

factor[Node current] returns [Register r]
@init {
}
   : INTEGER {
      $r = new Register();
      // Load immediate into register.
   }
   | TRUE {
      $r = new Register();
      // Load immediate 1 into register. 
   }
   | FALSE {
      $r = new Register();
     // Load immediate 0 into register.
   }
   | ^(NEW ID) {
      $r = new Register();
      // How do we malloc?
   }
   | NULL {
      $r = new Register();
      // Load immediate 0 into register.
   }
   | ID {
      String name = $ID.getText();
      Integer offset;

      // Figure out if ID is local, parameter, or global.
      $r = localRegisters.get(name);
      if ($r != null) {
         offset = paramOffsets.get(name);

         if ($r != null) {
            $r = new Register();
            // TODO: load from stack, use r as destination register.
         } else {
            $r = globalRegisters.get(name);
         }
      }
   }
   | ^(DOT factor[current] ID) {
      /* TODO */
   }
   | invocation[current] { $r = $invocation.r; }
   ;
