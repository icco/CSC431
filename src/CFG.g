/**
 * CFG Builder.
 * @author Ben Sweedler
 * @author Nat Welch
 */

tree grammar CFG;

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
   import java.util.List;
   import java.util.Set;
}

@members {
   // Creates a CFG mapping label names to boxes
   private GraphTable cfg = new GraphTable();
   private Node finalNode; // final node for the current function.
   public SymbolTable symTable;

   public String dump() {
      return cfg.toString();
   }

   private Register loadVar(String name, Node current) {
      Symbol var = symTable.get(name);
      Register tempReg = null;
      Instruction mov;

      if (var.isLocal()) {
         tempReg = var.getRegister();

      } else if (var.isParam()) {
         tempReg = new Register();

         mov = new LoadinargumentInstruction();
         mov.addID(var.getName());
         mov.addImmediate(var.getOffset());
         mov.addDest(tempReg);

         current.addInstr(mov);

      } else if (var.isGlobal()) {
         tempReg = new Register();

         mov = new LoadglobalInstruction();
         mov.addSource(new ID(var.getName()));
         mov.addDest(tempReg);

         current.addInstr(mov);
      }

      tempReg.setType(var.getType());

      return tempReg;
   }

   private Register loadFromMem(Register source, String field, Node current) {
      Instruction mov = new LoadaiInstruction();
      Register dest = new Register();
      StructType sourceType;
      Type destType;
      Field accessedField;

      sourceType = source.getType();

      destType = sourceType.getField(field);
      dest.setType(destType);

      accessedField = new Field(field, sourceType);

      mov.addSource(source);
      mov.addField(accessedField);
      mov.addDest(dest);
      current.addInstr(mov);

      return dest;
   }
}

build
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
   : ^(DECLS (declaration)*)
   ;

declaration
   : ^(DECLLIST ^(TYPE type) (ID)+)
   ;

functions
@init {
   // Set global offsets.
   Map<String, Symbol> globals = symTable.getGlobals();
   Set<String> globalNames = globals.keySet();
   int offset = 0;

   for (String name : globalNames) {
      globals.get(name).setOffset(offset++);
   }
}
   : ^(FUNCS function*)
   ;

function
@init {
   Node start = new Node();
}
   : ^(FUN ID {
      String name = $ID.getText();
      FuncType currentFunc = symTable.getFunction(name);
      int offset = 0;

      symTable.loadLocals(currentFunc);
      // Set up locals.
      for (Symbol local : currentFunc.getLocals()) {
         local.setRegister(new Register());
      }

      // Set up parameters.
      for (Symbol param : currentFunc.getParams()) {
         param.setOffset(offset++);
      }

      start.setLabel(name);

      /* All paths from start end with the function's final node */
      finalNode = new Node();
      finalNode.setLabel(("." + $ID.getText() + "_final"));
   }
   parameters ^(RETTYPE return_type) declarations statement_list[start]) {
      // TODO Loading parameters code.

      // TODO Statement list code.

      // Link last current block to final block.
      // (this only makes a difference for void funtions)
      $statement_list.exit.addChild(finalNode);
      finalNode.addParent($statement_list.exit);

      // Exiting code.
      cfg.put($ID.getText(), start);
   }
   ;

return_type
@init {
}
   : type
   | VOID
   ;

parameters
   : ^(PARAMS (var_decl)*)
   ;

statement_list[Node current] returns [Node exit]
@init {
   $exit = current; /* In case of any empty statement_list. */
}
   : ^(STMTS (statement[current] { $exit = current = $statement.exit;  })*)
   ;

statement[Node current] returns [Node exit]
@init {
   $exit = current;
}
   : block[current] { $exit = $block.exit; }
   | assignment[current]
   | print[current]
   | read[current]
   | conditional[current] { $exit = $conditional.exit; }
   | loop[current] { $exit = $loop.exit; }
   | delete[current]
   | ret[current] { $exit = $ret.exit; }
   | invocation[current]
   ;

block[Node current] returns [Node exit]
@init {
}
   : ^(BLOCK statement_list[current]) {
      $exit = $statement_list.exit;
   }
   ;

assignment[Node current]
@init {
}
   : ^(ASSIGN expression[current] lvalue[current, $expression.r])
   ;

lvalue[Node current, Register storeThis]
@init {
}
   : ID {
      /* Store in local/global/parameter */
      Symbol var = symTable.get($ID.getText());
      Instruction mov = null;

      if (var.isLocal()) {
         mov = new MovInstruction();
         mov.addSource(storeThis);
         mov.addDest(var.getRegister());
      } else if (var.isParam()) {
         mov = new StoreinargumentInstruction();
         mov.addSource(storeThis);
         mov.addImmediate(var.getOffset());

      } else if (var.isGlobal()) {
         mov = new StoreglobalInstruction();
         mov.addSource(storeThis);
         mov.addID(var.getName());
      }

      current.addInstr(mov);
   }
   | ^(DOT lvalue_h[current] ID) {
      Instruction mov = new StoreaiInstruction();
      Register source = $lvalue_h.r;
      Field accessedField = new Field($ID.getText());

      accessedField.setType(source.getType());

      mov.addSource(storeThis);
      mov.addSource(source);
      mov.addField(accessedField);

      current.addInstr(mov);
   }
   ;

lvalue_h[Node current] returns [Register r]
@init {
   /**
    * Returning a symbol is kind of a hack.
    * The only information we need to return is a register and a type.
    */
}
   :  ID {
      $r = loadVar($ID.getText(), current);
   }
   | ^(DOT src=lvalue_h[current] ID) {
      $r = loadFromMem($src.r, $ID.getText(), current);
   }
   ;

print[Node current]
@init {
}
   :  ^(PRINT expression[current] (ENDL)?)
   ;

read[Node current]
@init {
   // Scanf into global. Lvalue deals with actual storage.
   Register readValue = new Register();
   Instruction l = new ReadInstruction();
   l.addDest(readValue);
   current.addInstr(l);
}
   :  ^(READ lvalue[current, readValue])
   ;

conditional[Node current] returns [Node exit]
@init {
   String ifLabel = Node.nextLabel("if");

   Node tStart = new Node();
   Node fStart = new Node();
   $exit = new Node();

   tStart.setLabel(ifLabel + "_then");
   fStart.setLabel(ifLabel + "_else");
   $exit.setLabel(ifLabel + "_after");
}
   :  ^(IF c=expression[current] {
         // add Branch instructions based on boolean in returned register.
         Instruction i = new CompiInstruction();
         Register cc = new ConditionCodeRegister();
         i.addRegister($c.r);
         i.addImmediate(1);
         i.addDest(cc);
         current.addInstr(i);

         i = new CbreqInstruction();
         i.addRegister(cc);
         i.addLabel(tStart.getLabel());
         i.addLabel(fStart.getLabel()); // TODO we should jump to exit if no else block.
         current.addInstr(i);
      } tb=block[tStart] (fb=block[fStart])?) {
         /* Add code for looking at expression and jumping */
         /* Link then block path */
         current.addChild(tStart);
         tStart.addParent(current);
         $tb.exit.addChild($exit);
         $exit.addParent($tb.exit);

         if ($fb.exit != null) {
            /* Link else block path */
            current.addChild(fStart);
            fStart.addParent(current);
            $exit.addParent($fb.exit);
            $fb.exit.addChild($exit);
         } else {
            current.addChild($exit);
            $exit.addParent(current);
         }
      }
   ;

loop[Node current] returns [Node exit]
@init {
   Node loopNode = new Node("loop");

   $exit = new Node();
   $exit.setLabel(loopNode.getLabel() + "_done");

   // Link the nodes.
   $exit.addParent(current);
   $exit.addParent(loopNode);

   current.addChild(loopNode);
   current.addChild($exit);

   loopNode.addChild(loopNode); // TODO Is this right?
   loopNode.addChild($exit);
}
   : ^(WHILE expression[current] {
      // Add code to check if we should start looping or not.

   } block[loopNode] expression[loopNode]) {
      // Add code to loopNode that checks if we should loop again.
   }
   ;

delete[Node current]
@init {
}
   // TODO Figure out what register to actually delete.
   : ^(DELETE expression[current]) {
      Instruction l = new DelInstruction();
      l.addRegister(new Register());
      current.addInstr(l);
   }
   ;

ret[Node current] returns [Node exit]
@init {
}
   : ^(RETURN (expression[current])?) {
      Instruction lr = new LoadretInstruction();
      lr.addDest(new Register()); // Might need to be addRegister instead
      current.addInstr(lr);

      // TODO Put value in return register if expression is not null.

      // Link current block.
      current.addChild(finalNode);
      finalNode.addParent(current);

      $exit = new Node("after_ret"); // this node doesn't have a parent.
   }
   ;

invocation[Node current] returns [Register r]
@init {
}
   : ^(INVOKE ID arguments[current])
   {
      FuncType fun = symTable.getFunction($ID.getText());

      $r = new Register();
      $r.setType(fun.getReturn());

      /* Do a jump to ID, then load from return adress into r */
   }
   ;

arguments[Node current]
@init {
}
   : ^(ARGS (expression[current])*)
   ;

expression[Node current] returns [Register r]
@init {
}
   : INTEGER {
      // Load immediate into register.
      $r = new Register();
      Instruction l = new LoadiInstruction();
      l.addImmediate(new Integer($INTEGER.getText()));
      l.addDest($r);
      current.addInstr(l);
   }
   | TRUE {
      // Load immediate 1 into register.
      $r = new Register();
      Instruction l = new LoadiInstruction();
      l.addImmediate(1);
      l.addDest($r);
      current.addInstr(l);
   }
   | FALSE {
     // Load immediate 0 into register.
      $r = new Register();
      Instruction l = new LoadiInstruction();
      l.addImmediate(0);
      l.addDest($r);
      current.addInstr(l);
   }
   | ^(NEW ID) {
      // TODO: Figure out to use field names, or how much data to use.
      $r = new Register();
      Instruction l = new NewInstruction();
      l.addDest($r);
      l.addImmediate(9001); // TODO: CHANGE ME!
   }
   | NULL {
      // Load immediate 0 into register.
      $r = new Register();
      Instruction l = new LoadiInstruction();
      l.addImmediate(0);
      l.addDest($r);
      current.addInstr(l);
   }
   | ID {
      $r = loadVar($ID.getText(), current);
   }
   | ^(DOT src=expression[current] ID) {
      $r = loadFromMem($src.r, $ID.getText(), current);
   }
   | invocation[current] { $r = $invocation.r; }
   | ^(NOT e=expression[current]) {
      // xor with 1 to flop a boolean
      Instruction x = new XoriInstruction();
      $r = new Register();
      x.addImmediate(new Immediate(1));
      x.addRegister($e.r);
      x.addDest($r);

      current.addInstr(x);
   }
   | ^(NEG e=expression[current]) {
      // load -1 into a register and mult by register.
      Instruction l = new LoadiInstruction();
      Register rt = new Register();
      Register ri = new Register();
      l.addImmediate(-1);
      l.addDest(ri);
      current.addInstr(l);

      Instruction m = new MultInstruction();
      m.addRegister($e.r);
      m.addRegister(ri);
      m.addDest(rt);
      current.addInstr(m);
      $r = rt;
   }
   | ^(AND f1=expression[current] f2=expression[current]) {
      Instruction inst = new AndInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest($r = new Register());
      current.addInstr(inst);
   }
   | ^(OR f1=expression[current] f2=expression[current]) {
      Instruction inst = new OrInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest($r = new Register());
      current.addInstr(inst);
   }
   | ^(PLUS f1=expression[current] f2=expression[current]) {
      Instruction inst = new AddInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest($r = new Register());
      current.addInstr(inst);
   }
   | ^(MINUS f1=expression[current] f2=expression[current]) {
      Instruction inst = new SubInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest($r = new Register());
      current.addInstr(inst);
   }
   | ^(TIMES f1=expression[current] f2=expression[current]) {
      Instruction inst = new MultInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest($r = new Register());
      current.addInstr(inst);
   }
   | ^(DIVIDE f1=expression[current] f2=expression[current]) {
      Instruction inst = new DivInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest($r = new Register());
      current.addInstr(inst);
   }
   | ^(EQ f1=expression[current] f2=expression[current]) {
      Instruction inst = new CompInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest(new ConditionCodeRegister());
      current.addInstr(inst);

      $r = new Register();

      inst = new LoadiInstruction();
      inst.addImmediate(0);
      inst.addDest($r);
      current.addInstr(inst);

      // If true toggle $r to 1.
      inst = new MoveqInstruction();
      inst.addImmediate(1);
      inst.addDest($r);
      current.addInstr(inst);
   }
   | ^(LT f1=expression[current] f2=expression[current]) {
      Instruction inst = new CompInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest(new ConditionCodeRegister());
      current.addInstr(inst);

      $r = new Register();

      inst = new LoadiInstruction();
      inst.addImmediate(0);
      inst.addDest($r);
      current.addInstr(inst);

      // If true toggle $r to 1.
      inst = new MovltInstruction();
      inst.addImmediate(1);
      inst.addDest($r);
      current.addInstr(inst);
   }
   | ^(GT f1=expression[current] f2=expression[current]) {
      Instruction inst = new CompInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest(new ConditionCodeRegister());
      current.addInstr(inst);

      $r = new Register();

      inst = new LoadiInstruction();
      inst.addImmediate(0);
      inst.addDest($r);
      current.addInstr(inst);

      // If true toggle $r to 1.
      inst = new MovgtInstruction();
      inst.addImmediate(1);
      inst.addDest($r);
      current.addInstr(inst);
   }
   | ^(NE f1=expression[current] f2=expression[current]) {
      Instruction inst = new CompInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest(new ConditionCodeRegister());
      current.addInstr(inst);

      $r = new Register();

      inst = new LoadiInstruction();
      inst.addImmediate(0);
      inst.addDest($r);
      current.addInstr(inst);

      // If true toggle $r to 1.
      inst = new MovneInstruction();
      inst.addImmediate(1);
      inst.addDest($r);
      current.addInstr(inst);
   }
   | ^(LE f1=expression[current] f2=expression[current]) {
      Instruction inst = new CompInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest(new ConditionCodeRegister());
      current.addInstr(inst);

      $r = new Register();

      inst = new LoadiInstruction();
      inst.addImmediate(0);
      inst.addDest($r);
      current.addInstr(inst);

      // If true toggle $r to 1.
      inst = new MovleInstruction();
      inst.addImmediate(1);
      inst.addDest($r);
      current.addInstr(inst);
   }
   | ^(GE f1=expression[current] f2=expression[current]) {
      Instruction inst = new CompInstruction();
      inst.addRegister($f1.r);
      inst.addRegister($f2.r);
      inst.addDest(new ConditionCodeRegister());
      current.addInstr(inst);

      $r = new Register();

      inst = new LoadiInstruction();
      inst.addImmediate(0);
      inst.addDest($r);
      current.addInstr(inst);

      // If true toggle $r to 1.
      inst = new MovgeInstruction();
      inst.addImmediate(1);
      inst.addDest($r);
      current.addInstr(inst);
   }
   ;
