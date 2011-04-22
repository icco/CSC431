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
   public SymbolTable symTable = new SymbolTable();
   private FuncType currentFunc;
}

verify
@init {
}
   : ^(PROGRAM types

   declarations {
      for (Symbol s : $declarations.symbols) {
         s.setScope(Symbol.Scope.GLOBAL);
      }
      symTable.bindDeclarations($declarations.symbols, true);
   }

   functions {
      if (symTable.getFunction("main") == null) {
         Evil.error("Function main is undeclared.", $PROGRAM.getLine());
      }
   }
   )
   ;
types @init { }
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
      if (struct == null)
         Evil.error("Struct " + $ID.getText() + " is undeclared", $ID.getLine());

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
   currentFunc = new FuncType();
}
   : ^(FUN ID parameters ^(RETTYPE return_type) declarations {
       for (Symbol param : $parameters.params) {
          param.setScope(Symbol.Scope.PARAM);
       }
       currentFunc.setParams($parameters.params);
       currentFunc.setReturn($return_type.t);

       // Bind parameters and locals.
       symTable.bindParameters(currentFunc);

       for (Symbol local : $declarations.symbols) {
          local.setScope(Symbol.Scope.LOCAL);
       }
       symTable.bindDeclarations($declarations.symbols);

       // Bind function itself to allow for recursion.
       $s = new Symbol();
       $s.setName($ID.getText());
       $s.setType(currentFunc);
       $s.setLine($ID.getLine());
       symTable.bindFunction($s);
    }

    statement_list {
       Boolean foundReturn = $statement_list.ret;

       if (!(currentFunc.getReturn() instanceof VoidType) && !foundReturn) {
          Evil.error("Missing return statment for function.", $ID.getLine());
       }

       // Save local/parameter symbols into function (for use during compiling).
       symTable.saveLocals(currentFunc);
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

statement_list returns [Boolean ret]
@init {
   $ret = false;
}
   : ^(STMTS (statement
   {
      $ret = $ret || $statement.ret;
   }) *)
   ;

statement returns [Boolean ret]
@init {
   $ret = false;
}
   : block { $ret = $block.ret; }
   | assignment
   | print
   | read
   | conditional { $ret = $conditional.ret; }
   | loop
   | delete
   | ret { $ret = true; }
   | i=invocation
   ;

block returns [Boolean ret]
@init {
}
   : ^(BLOCK statement_list { $ret = $statement_list.ret; })
   ;

assignment
@init {
}
   : ^(ASSIGN ex=expression lval=lvalue) {
      if (!$ex.t.equals($lval.t)) {
         Evil.error("Assignment lvalue type (" + $lval.t + ") doesn't match expresion(" + $ex.t + ").", $ASSIGN.getLine());
      }
   }
   ;

lvalue returns [Type t]
@init {
}
   : ID {
      Type s = symTable.getType($ID.getText());
      if (s == null)
         Evil.error("Reference to undeclared variable " + $ID.getText(), $ID.getLine());

      $t = s;
      }
   | ^(DOT lvalue_h ID) {
         if ($lvalue_h.t.is_struct()) {
            StructType struct = (StructType)$lvalue_h.t;
            String field = $ID.getText();

            $t = struct.getField(field);
            if ($t == null) {
               Evil.error("Trying to access undeclared field " + field
                + " in struct " + struct.getName(), $DOT.getLine());
            }
         } else {
            Evil.error("Trying to access field of a " + $lvalue_h.t + ".", $ID.getLine());
         }
      }
   ;

lvalue_h returns [Type t]
@init {
}
   : ID {
      Type s = symTable.getType($ID.getText());
      if (s == null)
         Evil.error("Reference to undeclared variable " + $ID.getText(), $ID.getLine());

      $t = s;
      }
   | ^(DOT l=lvalue_h ID) {
         if ($l.t.is_struct()) {
            StructType struct = (StructType)$l.t;
            String field = $ID.getText();

            $t = struct.getField(field);
            if ($t == null) {
               Evil.error("Trying to access undeclared field " + field
                + " in struct " + struct.getName(), $DOT.getLine());
            }
         } else {
            Evil.error("Trying to access field of a " + $l.t + ".", $ID.getLine());
         }
      }
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

conditional returns [Boolean ret]
@init {
}
   :  ^(IF e=expression tb=block (fb=block)?) {
      if (!$e.t.is_bool()) {
         Evil.error("Conditional in if must be a boolean.", $IF.getLine());
      }

      if ($fb.ret == null) {
         $ret = $tb.ret;
      } else {
         $ret = $tb.ret && $fb.ret;
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

ret
@init {
}
   : ^(RETURN (expression)?) {
      Type ret = new VoidType();
      Type funcRetType = currentFunc.getReturn();

      if ($expression.t != null) {
         ret = $expression.t;
      }

      if (!ret.equals(funcRetType)) {
         Evil.error("Return type " + ret
          + " does not match function return type " + funcRetType
          + ".", $RETURN.getLine());
      }
   }
   ;

invocation returns [Type t]
@init {
}
   : ^(INVOKE ID args=arguments) {
      FuncType ty = symTable.getFunction($ID.getText());
      if (ty == null) {
         Evil.error("Function " + $ID.getText() + "is undeclared", $ID.getLine());
      }

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
   : INTEGER { $t = new IntType(); }
   | TRUE { $t = new BoolType(); }
   | FALSE { $t = new BoolType(); }
   | ^(NEW ID) {
      StructType s = symTable.getStruct($ID.getText());
      if (s == null) {
         Evil.error("Struct " + $ID.getText() + " is undeclared", $ID.getLine());
      } else {
         $t = s.clone();
      }
    }
   | NULL { $t = new NullType(); }
   | ID {
      Type s = symTable.getType($ID.getText());
      if (s == null)
         Evil.error("Reference to undeclared variable " + $ID.getText(), $ID.getLine());

      $t = s;
   }
   | ^(DOT f=expression ID) {
      if ($f.t.is_struct()) {
         StructType struct = (StructType)$f.t;
         String field = $ID.getText();

         $t = struct.getField(field);
         if ($t == null) {
            Evil.error("Trying to access undeclared field " + field
             + " in struct " + struct.getName(), $DOT.getLine());
         }
      } else {
         Evil.error("Trying to access field of a " + $f.t + ".", $ID.getLine());
      }
   }
   | i=invocation { $t = $i.t; }
   | ^(u=unop f1=expression) {
      if ($f1.t != null) {
         if (!$u.t.checkValid($f1.t)) { Evil.error($u.t.toString(), $u.t.getLine()); }
      }

      $t = u.out();
   }
   | ^(u=binop f1=expression f2=expression) {
      // These must be the same, and the correct type for their operation.
      if ($f1.t != null && $f2.t != null) {
         if (!$u.t.checkValid($f1.t, $f2.t)) { Evil.error($u.t.toString(), $u.t.getLine()); }
         if (!$f1.t.equals($f2.t)) { Evil.error("Both objects in a boolean operation must be the same type.", $u.t.getLine()); }
      }

      $t = u.out();
   }
   ;

binop returns [OperatorType t]
@init {
}
   : e=(AND | OR) {
      t = new OperatorType();
      t.setBinary();
      t.setType("BoolType");
      t.setOutType("BoolType");
      t.setLine($e.getLine());
   }
   | e=(EQ | NE) {
      t = new OperatorType();
      t.setBinary();
      t.setType("Type");
      t.setOutType("BoolType");
      t.setLine($e.getLine());
   }
   | e=(LT | GT | LE | GE) {
      t = new OperatorType();
      t.setBinary();
      t.setType("IntType");
      t.setOutType("BoolType");
      t.setLine($e.getLine());
   }
   | e=(PLUS | MINUS | TIMES | DIVIDE) {
      t = new OperatorType();
      t.setBinary();
      t.setType("IntType");
      t.setOutType("IntType");
      t.setLine($e.getLine());
   }
   ;

unop returns [OperatorType t]
   : NOT {
      t = new OperatorType();
      t.setUnary();
      t.setType("BoolType");
      t.setOutType("BoolType");
      t.setLine($NOT.getLine());
   }
   | NEG {
      t = new OperatorType();
      t.setUnary();
      t.setType("IntType");
      t.setOutType("IntType");
      t.setLine($NEG.getLine());
   }
   ;
