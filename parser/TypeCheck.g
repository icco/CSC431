tree grammar TypeCheck;

options
{
   tokenVocab=Evil;
   ASTLabelType=CommonTree;
}

@header
{
   import java.util.Map;
   import java.util.HashMap;
   import java.util.Vector;
   import java.util.Iterator;
}

verify
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
   : ^(DECLLIST ^(TYPE type) id_list)
   ;

id_list
@init {
}
   : ID (ID)*
   ;

functions
@init {
}
   : ^(FUNCS function*)
   ;

function
@init {
}
   : ^(FUN ID parameters ^(RETTYPE return_type) declarations statement_list)
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
   System.out.println("Starting an assignment");
}
   : ^(ASSIGN expression lvalue)
   ;

lvalue
@init {
   System.out.println("Starting an lvalue");
}
   :  ID ID*
   ;

print
@init {
   System.out.println("Starting a print");
}
   :  PRINT expression
   ;

read
@init {
   System.out.println("Starting a read");
}
   :  READ lvalue
   ;

conditional
@init {
   System.out.println("Starting a conditional");
}
   :  IF expression block (ELSE block)?
   ;

loop
@init {
   System.out.println("Starting a loop");
}
   : ^(WHILE expression block expression)
   ;

delete
@init {
   System.out.println("Starting a delete");
}
   : DELETE expression
   ;

ret
@init {
   System.out.println("Starting a return");
}
   : RETURN (expression)?
   ;

invocation
@init {
   System.out.println("Starting an invocation ");
}
   : ^(INVOKE ID arguments)
   ;

// From here on out, there be dragons.
expression
   : boolterm ((AND | OR) boolterm)*
   ;

boolterm
   : simple ((EQ | LT | GT | NE | LE | GE) simple)?
   ;

simple
   : term ((PLUS | MINUS) term)*
   ;

term
   : unary ((TIMES | DIVIDE) unary)*
   ;

unary
   : (factor (DOT ID)*)
   | ^(NOT (factor (DOT ID)*))
   | ^(NEG (factor (DOT ID)*))
   ;

factor
   :  expression
   | ^(INVOKE ID arguments)
   |  ID
   |  INTEGER
   |  TRUE
   |  FALSE
   |  NEW ID
   |  NULL
   ;

arguments
   : (^(ARGS expression+) | ARGS)
   ;

