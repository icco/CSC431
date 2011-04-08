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

   declarations {
      symTable.bindDeclarations($declarations.symbols, true);
   }

   functions {
      // We're binding the function deeper in the tree,
      // but I'm leaving this here in case that ends up not working.
      // symTable.bindFunctions($functions.symbols, true);
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
   {
      // TODO: We need to build the actual struct to store here.
   }
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
   | ^(STRUCT ID) {
      // TODO
      // This is someone creating a new struct. So we need the name of the
      // struct they are creating, such as A or something.
      $t = new StructType();
   }
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
   FuncType fun = new FuncType();
}
   : ^(FUN ID parameters ^(RETTYPE return_type) declarations
    {
       fun.setParams($parameters.params);
       fun.setReturn($return_type.t);

       // Bind parameters and locals.
       symTable.clearLocals();
       symTable.bindParameters(fun);
       symTable.bindDeclarations($declarations.symbols);

       // Bind function itself to allow for recursion.
       $s = new Symbol();
       $s.setName($ID.getText());
       $s.setType(fun);
       $s.setLine($ID.getLine());
       symTable.bindFunction($s);
    }

    statement_list
    {
       Type returnStatement = $statement_list.t;

       if (!fun.getReturn().equals(returnStatement)) {
          Evil.error("Function " + $s.getName() + " has return type "
           + fun.getReturn() + " but is returning " + returnStatement);
       }
    }


    )
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

statement_list returns [Type t]
@init {
   $t = new VoidType();
}
   : ^(STMTS (statement
   {
      if ($statement.t != null) {
         $t = $statement.t;
      }
   }) *)
   ;

statement returns [Type t]
@init {
}
   : block { $t = $block.t; }
   | assignment
   | print
   | read
   | conditional
   | loop
   | delete
   | ret { $t = $ret.t; }
   | i=invocation { $t = $i.t; }
   ;

block returns [Type t]
@init {
}
   : ^(BLOCK statement_list { $t = $statement_list.t; })
   ;

assignment
@init {
}
   : ^(ASSIGN ex=expression lval=lvalue)
   {
      if (!$ex.t.equals($lval.t)) {
         Evil.error("Assignment lvalue type doesn't match expresion");
      }
   }
   ;

// TODO: This needs more work...
lvalue returns [Type t]
@init {
}
   : ID { $t = symTable.get($ID.getText()); }
   | ^(DOT lvalue_h ID)
   ;

lvalue_h returns [Type t]
@init {
}
   : ID { $t = symTable.get($ID.getText()); }
   | ^(DOT lvalue_h ID)
   ;

// TODO: This needs more work...
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

conditional returns [Type t]
@init {
}
   :  ^(IF e=expression tb=block (fb=block)?)
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

ret returns [Type t]
@init {
}
   : ^(RETURN (expression)?)
   {
      if ($expression.t != null) {
         $t = $expression.t;
      } else {
         $t = new VoidType();
      }
   }
   ;

invocation returns [Type t]
@init {
}
   : ^(INVOKE ID arguments)
   {
      Type ty = symTable.get($ID.getText());
      if (ty.is_func())
         t = ((FuncType)ty).getReturn();
      else
         Evil.error("You can only invoke functions.");
   }
   ;

arguments
@init {
}
   : ^(ARGS (expression)*)
   ;

expression returns [Type t]
@init {
}
   : f=factor { $t = $f.t; }
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
   | ^(NEW ID) { $t = new StructType(); }
   | NULL { $t = new NullType(); }
   | ID { $t = symTable.get($ID.getText()); }
   | ^(DOT f=factor ID) {
         if ($f.t.is_struct()) {
            $t = new NullType(); // Implement.
         } else {
            Evil.error("Trying to access field of a non-struct.");
         }
      }
   | i=invocation { $t = $i.t; }
   ;
