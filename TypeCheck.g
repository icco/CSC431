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
   :  ^(IF e=expression block (block)?)
   {
      if (!$e.t.is_bool()) { Evil.error("Conditional in if must be a boolean."); }
   }
   ;

loop
@init {
}
   : ^(WHILE e1=expression block e2=expression)
   {
      if (!$e1.t.is_bool()) { Evil.error("Conditional in while must be a boolean."); }
      if (!$e2.t.is_bool()) { Evil.error("Conditional in while must be a boolean."); }
   }
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

invocation returns [Type t]
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

      t = u.out();
   }
   | ^(u=binop f1=factor f2=factor)
   {
      // These must be the same, and the correct type for their operation.
      if ($f1.t != null && $f2.t != null) {
         if (!$u.t.checkValid($f1.t, $f2.t)) { Evil.error($u.t.toString()); }
         if (!$f1.t.equals($f2.t)) { Evil.error("Both objects in a boolean operation must be the same type."); }
      }

      t = u.out();
   }
   ;

binop returns [OperatorType t]
   : (AND | OR)
   {
      t = new OperatorType();
      t.setBinary();
      t.setType("BoolType");
      t.setOutType("BoolType");
   }
   | (EQ | LT | GT | NE | LE | GE) 
   {
      t = new OperatorType();
      t.setBinary();
      t.setType("IntType");
      t.setOutType("BoolType");
   }
   | (PLUS | MINUS | TIMES | DIVIDE)
   {
      t = new OperatorType();
      t.setBinary();
      t.setType("IntType");
      t.setOutType("IntType");
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
   | ^(NEW ID) { $t = new NullType(); }
   | NULL { $t = new NullType(); }
   | ID { $t = new NullType(); }
   | ^(DOT factor ID) { $t = new NullType(); }
   | invocation
   ;
