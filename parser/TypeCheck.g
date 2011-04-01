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

verify:
 ^(PROGRAM types declarations functions)
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
   System.out.println("Starting declaration");
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
   :
   ;

functions:

;
