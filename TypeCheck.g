tree grammar TypeCheck;

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
}
   : ^(ASSIGN ex=expression lval=lvalue)
   {
      if ($ex.t != null && !$ex.t.equals($lval.t)) {
         Evil.error("Assignment lvalue type doesn't match expresion");
      }
   }
   ;

lvalue returns [Type t]
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

expression returns [Type t]
@init {
}
   : factor
   | ^(u=unop f1=factor)
   {
      if ($f1.t != null) {
         if (!$u.t.checkValid($f1.t)) { Evil.error($u.t.toString()); }
      }
   }
   | ^(u=binop f1=factor f2=factor)
   {
      // These must be the same, and the correct type for their operation.
      if ($f1.t != null && $f2.t != null) {
         if (!$u.t.checkValid($f1.t, $f2.t)) { Evil.error($u.t.toString()); }
         if (!$f1.t.equals($f2.t)) { Evil.error("Both objects in a boolean operation must be the same type."); }
      }
   }
   ;

binop returns [OperatorType t]
   : (AND | OR)
   {
      t = new OperatorType();
      t.setBinary();
      t.setType("BoolType");
   }
   | (EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE)
   {
      t = new OperatorType();
      t.setBinary();
      t.setType("IntType");
   }
   ;

unop returns [OperatorType t]
   : NOT
   {
      t = new OperatorType();
      t.setUnary();
      t.setType("BoolType");
   }
   | NEG
   {
      t = new OperatorType();
      t.setUnary();
      t.setType("IntType");
   }
   ;

factor returns [Type t]
@init {
}
   : INTEGER { $t = new IntType(); }
   | TRUE { $t = new BoolType(); }
   | FALSE { $t = new BoolType(); }
   | ^(NEW ID)
   | NULL { $t = new NullType(); }
   | ID
   | ^(DOT factor ID)
   | invocation
   ;
