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
   import java.util.LinkedList;
}

@members {
   private static SymbolTable symTable = new SymbolTable();
}

verify
@init {
}
   : ^(PROGRAM types 
   
   declarations 
   {
      symTable.bindDeclarations($declarations.symbols, true);
   }
      
   functions
   {
      symTable.bindDeclarations($functions.symbols, true);
   }
   )
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

var_decl returns [Symbol s]
@init {
}
   : ^(DECL ^(TYPE type) ID)
   {
      $s = new Symbol($ID.getText(), $type.t); 
      $s.setLine($ID.getLine());
   }
   ;

type returns [Type t]
@init {
}
   : INT { $t = new IntType(); }
   | BOOL { $t = new BoolType(); }
   | ^(STRUCT ID) { $t = new StructType(); /** TODO */  }
   ;

declarations returns [List<Symbol> symbols]
@init {
   $symbols = new LinkedList<Symbol>();
}
   : ^(DECLS (declaration { $symbols.addAll($declaration.symbols); })*)
   ;

declaration returns [List<Symbol> symbols]
@init {
   $symbols = new LinkedList<Symbol>();
}
   : ^(DECLLIST ^(TYPE type) (ID 
   { 
      String name = $ID.getText();
      Type t = $type.t;
      Symbol s = new Symbol(name, t);
      s.setLine($ID.getLine());

      $symbols.add(s); 
   })+)
   ;

functions returns [List<Symbol> symbols]
@init {
   $symbols = new LinkedList<Symbol>();
}
   : ^(FUNCS (function { $symbols.add($function.s); })*)
   ;

function returns [Symbol s]
@init {
}
   : ^(FUN ID parameters ^(RETTYPE return_type) declarations 
    {
       FuncType fun = new FuncType();
       
       fun.setParams($parameters.params);
       fun.setReturn($return_type.t);

       symTable.clearLocals();
       symTable.bindParameters(fun);
       symTable.bindDeclarations($declarations.symbols);

       $s = new Symbol();
       $s.setName($ID.getText());
       $s.setType(fun);
       $s.setLine($ID.getLine());
    }
       statement_list)
   ;

return_type returns [Type t]
@init {
}
   : type { $t = $type.t; }
   | VOID { $t = new VoidType(); }
   ;

parameters returns [List<Symbol> params]
@init {
   $params = new LinkedList<Symbol>();
}
   : ^(PARAMS (var_decl { $params.add($var_decl.s); })*)
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
   | NULL
   | ID
   | ^(DOT factor ID)
   | invocation
   ;
