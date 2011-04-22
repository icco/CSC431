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
   private GraphTable cfg = new GraphTable();
   private Node finalNode; // final node for the current function.
   public SymbolTable symTable;

   public String dump() {
      return cfg.toString();
   }
}

build
@init {
}
   : ^(PROGRAM types declarations functions)
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

var_decl
@init {
}
   : ^(DECL ^(TYPE type) ID)
   ;

type
@init {
}
   : INT
   | BOOL
   | ^(STRUCT ID)
   ;

declarations
   : ^(DECLS (declaration)*)
   ;

declaration
   : ^(DECLLIST ^(TYPE type) (ID)+)
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
      String name = $ID.getText();
      FuncType currentFunc = symTable.getFunction(name);
      symTable.loadLocals(currentFunc);

      start.setLabel(name);

      /* All paths from start end with the function's final node */
      finalNode = new Node();
      finalNode.setLabel(("." + $ID.getText() + "_final"));

   }
   parameters ^(RETTYPE return_type) declarations statement_list[start]) {
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

parameters
   : ^(PARAMS (var_decl)*)
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
      lr.addRegister(new Register());
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
      Symbol s = symTable.get(name);
      $r = new Register(); // TODO

      // Figure out if ID is local, parameter, or global.
      if (s.isLocal()) {

      } else if (s.isParam()) {

      } else if (s.isGlobal()) {

      }
   }
   | ^(DOT expression[current] ID) {
      /* TODO */
   }
   | invocation[current] { $r = $invocation.r; }
   | ^(unop[current] e=expression[current]) {
      // TODO
      // if not xori with 1
      // else if load -1 into a register and mult by register.
   }
   | ^(binop[current] f1=expression[current] f2=expression[current]) {
      $binop.inst.addRegister($f1.r);
      $binop.inst.addRegister($f2.r);
      $binop.inst.addRegister($r = new Register());
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
   : NOT
   | NEG
   ;
