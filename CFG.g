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
   private static GraphTable cfg = new GraphTable(100);

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
}
   : ^(FUN ID parameters ^(RETTYPE return_type) declarations statement_list) {

      // Store the function...
      Node n = new Node();
      cfg.put($ID.getText(), n);
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
}
   : ^(PARAMS var_decl*)
   ;

statement_list
@init {
}
   : ^(STMTS statement*)
   ;

statement
@init {
}
   : block
   | assignment
   | print
   | read
   | conditional
   | loop
   | delete
   | ret
   | invocation
   ;

block
@init {
}
   : ^(BLOCK statement_list)
   ;

assignment
@init {
}
   : ^(ASSIGN expression lvalue)
   ;

lvalue
@init {
}
   :  ID
   | ^(DOT lvalue_h ID)
   ;

lvalue_h
@init {
}
   :  ID
   | ^(DOT lvalue_h ID)
   ;

print
@init {
}
   :  ^(PRINT expression (ENDL)?)
   ;

read
@init {
}
   :  ^(READ lvalue)
   ;

conditional
@init {
}
   :  ^(IF expression block (block)?)
   ;

loop
@init {
}
   : ^(WHILE expression block expression)
   ;

delete
@init {
}
   : ^(DELETE expression)
   ;

ret
@init {
}
   : ^(RETURN (expression)?)
   ;

invocation
@init {
}
   : ^(INVOKE ID arguments)
   ;

arguments
@init {
}
   : ^(ARGS (expression)*)
   ;

expression
@init {
}
   : factor
   | ^(unop factor)
   | ^(binop factor factor)
   ;

binop
   : (AND | OR | EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE)
   ;

unop
   : (NOT | NEG)
   ;

factor
@init {
}
   : INTEGER
   | TRUE
   | FALSE
   | ^(NEW ID)
   | NULL
   | ID
   | ^(DOT factor ID)
   | invocation
   ;
