/**
 * Type checker.
 *
 * @author Nat Welch
 * @author Ben Sweedler
 */

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
   private static String currentFunc;
}

verify
@init {
}
   : ^(PROGRAM types

   declarations {
      symTable.bindDeclarations($declarations.symbols, true);
   }

   functions {
      symTable.getFunction("main");
   }
   )
   ;

types
@init {
}
   : ^(TYPES (types_declaration)*)
   ;

types_declaration returns [StructType struct]
@init {
   $struct = new StructType();
}
   : ^(STRUCT ID {
         String name = $ID.getText();
         Symbol s = new Symbol(name, $struct, $ID.getLine());

         $struct.setName(name);
         symTable.bindStruct(s);
      }
      (var_decl { $struct.addField($var_decl.s); })+)
   ;

var_decl returns [Symbol s]
@init {
}
   : ^(DECL ^(TYPE type) ID) {
      $s = new Symbol($ID.getText(), $type.t, $ID.getLine());
   }
   ;

type returns [Type t]
@init {
}
   : INT { $t = new IntType(); }
   | BOOL { $t = new BoolType(); }
   | ^(STRUCT ID) {
      StructType struct = symTable.getStruct($ID.getText());
      $t = struct.clone();
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
   : ^(DECLLIST ^(TYPE type) (ID {
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
   : ^(FUN ID parameters ^(RETTYPE return_type) declarations {
       fun.setParams($parameters.params);
       fun.setReturn($return_type.t);

       // Bind parameters and locals.
       symTable.clearLocals();
       symTable.bindParameters(fun);
       symTable.bindDeclarations($declarations.symbols);

       // Bind function itself to allow for recursion.
       $s = new Symbol();
       $s.setName($ID.getText());
       currentFunc = $ID.getText();
       $s.setType(fun);
       $s.setLine($ID.getLine());
       symTable.bindFunction($s);
    }

    statement_list {
       Type returnStatement = $statement_list.t;

       if (returnStatement == null ||
        !returnStatement.equals(fun.getReturn())) {
          Evil.error("There are some branches that don't return correctly.", $ID.getLine());
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
      $t = $statement.t; /** Works unless code is allowed after return statment */
   }) *)
   ;

statement returns [Type t]
@init {
}
   : block { $t = $block.t; }
   | assignment
   | print
   | read
   | conditional { $t = $conditional.t; }
   | loop { $t = $loop.t; }
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
   : ^(ASSIGN ex=expression lval=lvalue) {
      if (!$ex.t.equals($lval.t)) {
         Evil.error("Assignment lvalue type doesn't match expresion", $ASSIGN.getLine());
      }
   }
   ;

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

print
@init {
}
   : ^(PRINT expression (ENDL)?)
   ;

read
@init {
}
   : ^(READ lvalue)
   ;

conditional returns [Type t]
@init {
}
   :  ^(IF e=expression tb=block (fb=block)?) {
      if (!$e.t.is_bool()) {
         Evil.error("Conditional in if must be a boolean.", $IF.getLine());
      }

      if ($fb.t == null) {
         $t = $tb.t;
      } else if ($tb.t.equals($fb.t)) {
         $t = $tb.t;
      } else {
         $t = null;
         Evil.error("There are some branches that don't return correctly");
      }
   }
   ;

loop returns [Type t]
@init {
}
   : ^(WHILE e1=expression block e2=expression) {
      if (!$e1.t.is_bool()) { Evil.error("Conditional in while must be a boolean.", $WHILE.getLine()); }
      if (!$e2.t.is_bool()) { Evil.error("Conditional in while must be a boolean.", $WHILE.getLine()); }
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
   : ^(RETURN (expression)?) {
      Type ret = new VoidType();
      Type funcRetType = symTable.getFunction(currentFunc).getReturn();

      if ($expression.t != null) {
         ret = $expression.t;
      }

      if (!ret.equals(funcRetType)) {
         Evil.error("Return type " + ret + " does not match function return type " + funcRetType  + ".", $RETURN.getLine());
      } else {
         $t = ret;
      }
   }
   ;

invocation returns [Type t]
@init {
}
   : ^(INVOKE ID args=arguments) {
      FuncType ty = symTable.getFunction($ID.getText());

      // Make sure args == parameters
      if ($args.types.size() == ty.getParams().size()) {
         for (int ndx = 0; ndx < ty.getParams().size(); ndx++) {
            Type p = ty.getParams().get(ndx).getType();
            Type a = $args.types.get(ndx);

            if (!p.equals(a)) {
               Evil.error("Mismatched type in call to function " + $ID.getText()
                + ", argument " + (ndx + 1) + " should be " + p + " but is " + a
                + ".", $ID.getLine());
            }
         }
      } else {
         Evil.error("Call to " + $ID.getText() + " has "
          + $args.types.size() + " arguments, but needs "
          + ty.getParams().size() + ".", $ID.getLine());
      }

      if (ty.is_func()) {
         t = ty.getReturn();
      }
   }
   ;

arguments returns [List<Type> types]
@init {
   $types = new LinkedList<Type>();
}
   : ^(ARGS (ex=expression { $types.add($ex.t); })*)
   ;

expression returns [Type t]
@init {
}
   : f=factor { $t = $f.t; }
   | ^(u=unop f1=factor) {
      if ($f1.t != null) {
         if (!$u.t.checkValid($f1.t)) { Evil.error($u.t.toString()); }
      }

      $t = u.out();
   }
   | ^(u=binop f1=factor f2=factor) {
      // These must be the same, and the correct type for their operation.
      if ($f1.t != null && $f2.t != null) {
         if (!$u.t.checkValid($f1.t, $f2.t)) { Evil.error($u.t.toString()); }
         if (!$f1.t.equals($f2.t)) { Evil.error("Both objects in a boolean operation must be the same type."); }
      }

      $t = u.out();
   }
   ;

binop returns [OperatorType t]
   : (AND | OR) {
      t = new OperatorType();
      t.setBinary();
      t.setType("BoolType");
      t.setOutType("BoolType");
   }
   | (EQ | LT | GT | NE | LE | GE) {
      t = new OperatorType();
      t.setBinary();
      t.setType("IntType");
      t.setOutType("BoolType");
   }
   | (PLUS | MINUS | TIMES | DIVIDE) {
      t = new OperatorType();
      t.setBinary();
      t.setType("IntType");
      t.setOutType("IntType");
   }
   ;

unop returns [OperatorType t]
   : NOT {
      t = new OperatorType();
      t.setUnary();
      t.setType("BoolType");
      t.setOutType("BoolType");
   }
   | NEG {
      t = new OperatorType();
      t.setUnary();
      t.setType("IntType");
      t.setOutType("IntType");
   }
   ;

factor returns [Type t]
@init {
}
   : INTEGER { $t = new IntType(); }
   | TRUE { $t = new BoolType(); }
   | FALSE { $t = new BoolType(); }
   | ^(NEW ID) {
      $t = symTable.getStruct($ID.getText()).clone();
    }
   | NULL { $t = new NullType(); }
   | ID { $t = symTable.get($ID.getText()); }
   | ^(DOT f=factor ID) {
         if ($f.t.is_struct()) {
            StructType struct = (StructType)$f.t;
            $t = struct.getField($ID.getText());
         } else {
            Evil.error("Trying to access field of a " + $f.t + ".", $ID.getLine());
         }
      }
   | i=invocation { $t = $i.t; }
   ;
