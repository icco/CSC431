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
}

@members {
   // Creates a CFG mapping label names to boxes
   private static GraphTable cfg = new GraphTable();
   private static Node finalNode; // final node for the current function.

   public void dump() {
      // Verify that build has already been run?

      // Print out Graph.
      System.out.println(cfg);
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
@init {
}
   : ^(DECLS declaration*)
   ;

declaration
@init {
}
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
      start.setLabel($ID.getText());

      /* All paths from start end with the function's final node */
      finalNode = new Node($ID.getText() + "_final"); 
      
   } parameters ^(RETTYPE return_type) declarations statement_list[start]) 
   {
      // Store the function...

      // Loading paraters code. 

      // Statement list code.

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
@init {
   // Code for getting stored arguments?
}
   : ^(PARAMS var_decl*)
   ;

statement_list[Node current] returns [Node exit]
@init {
}
   : ^(STMTS (statement[current] { current = $statement.exit;   })*)
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
   : ^(ASSIGN expression[current] lvalue)
   ;

lvalue returns [Register r]
@init {
}
   :  ID { 
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
}
   :  ^(READ lvalue)
   ;

conditional[Node current] returns [Node exit]
@init {
   String ifLabel = Node.nextLabel("if");

   Node tStart = new Node();
   Node fStart = new Node();
   $exit = new Node();

   tStart.setLabel(ifLabel + "_then");
   fStart.setLabel(ifLabel + "_else");
   $exit.setLabel(ifLabel + "_final");
}
   :  ^(IF 
      expression[current] { 
         /* put expression code in current block */ 
         
      }
      tb=block[tStart] 
      (fb=block[fStart = new Node()])?)
      {
         /* Add code for looking at expression and jumping */
         /* Link then block path */ 
         current.addChild(tStart);
         $exit.addParent($tb.exit);

         if (fStart != null) {
            /* Link else block path */
            current.addChild(fStart);
            $exit.addParent($fb.exit);
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

   current.addChild($exit);
   current.addChild(loopNode);
   
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
   : ^(RETURN (expression[current])?)
   {
      // Put value in return register if expression is not null.

      // Link current block.
      current.addChild(finalNode);
      finalNode.addParent(current);

      $exit = new Node("after_ret"); // this node won't connect to anything.
   }
   ;

invocation[Node current]
@init {
}
   : ^(INVOKE ID arguments[current]) 
   ;

arguments[Node current]
@init {
}
   : ^(ARGS (expression[current])*)
   ;

expression[Node current]
@init {
}
   : factor[current]
   | ^(unop[current] factor[current])
   | ^(binop[current] factor[current] factor[current])
   ;

binop[Node current]
   : (AND | OR | EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE)
   ;

unop[Node current]
   : (NOT | NEG)
   ;

factor[Node current]
@init {
}
   : INTEGER
   | TRUE
   | FALSE
   | ^(NEW ID)
   | NULL
   | ID
   | ^(DOT factor[current] ID)
   | invocation[current]
   ;
