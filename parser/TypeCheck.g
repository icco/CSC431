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
   System.out.println("Init");
}
   : ^(PROGRAM types declarations functions)
   ;

types
@init {
   System.out.println("Starting types");
}
   : ^(TYPES (types_declaration)*)
   ;

types_declaration
@init {
   System.out.println("Starting type declaration");
}
   : ^(STRUCT ID nested_decl)
   ;

nested_decl
@init {
   System.out.println("Starting nested declarations");
}
   : (decl)+
   ;

decl
@init {
   System.out.println("Starting decl");
}
   : ^(DECL ^(TYPE type) ID)
   ;

type
@init {
   System.out.println("Starting type");
}
   : INT
   | BOOL
   | ^(STRUCT ID)
   ;

declarations
@init {
   System.out.println("Starting declarations");
}
   : ^(DECLS declaration*)
   ;

declaration
@init {
   System.out.println("Starting declaration");
}
   : ^(DECLLIST ^(TYPE type) id_list)
   ;

id_list
@init {
   System.out.println("Starting id_list");
}
   : ID (ID)*
   ;

functions
@init {
   System.out.println("Starting functions");
}
   : ^(FUNCS function*)
   ;

function
@init {
   System.out.println("Starting a function");
}
   : ^(FUN ID parameters ^(RETTYPE return_type) declarations statement_list)
   ;

return_type
@init {
   System.out.println("Starting return type");
}
   : type
   | VOID
   ;

parameters
@init {
   System.out.println("Starting parameters");
}
   : ^(PARAMS decl*)
   ;

statement_list
@init {
   System.out.println("Starting statements");
}
   : ^(STMTS statement*)
   ;

statement
@init {
   System.out.println("Starting a statement");
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
   System.out.println("Starting a block");
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
   : selector
   | ^(NOT selector)
   | ^(NEG selector)
   ;

selector
   :  factor (ID)*
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
   :  arg_list
   ;

arg_list
   :  ^(ARGS expression+)
   | ARGS
   ;
