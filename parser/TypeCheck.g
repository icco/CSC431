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
   : ret
   ;

block
@init {
}
   : ^(BLOCK statement_list)
   ;

ret
   : RETURN (factor)?
   ;

factor
   :  ID
   | INTEGER
   |  TRUE
   |  FALSE
   |  NEW ID
   |  NULL
   ;
