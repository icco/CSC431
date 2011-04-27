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
      return symTable.toString() + cfg.toString();
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

   public void addBranchInstructions(Register comp, String tLabel,
    String fLabel, Node current) {
      Instruction i = new CompiInstruction();
      Register cc = new ConditionCodeRegister();
      i.addRegister(comp);
      i.addImmediate(1);
      i.addDest(cc);
      current.addInstr(i);

      i = new CbreqInstruction();
      i.addRegister(cc);
      i.addLabel(tLabel);
      i.addLabel(fLabel);
      current.addInstr(i);
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
   // Set global offsets. This isn't being used for ILOC though.
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

      // Add return statment incase of no explicit return.
      Instruction ret = new RetInstruction();
      $statement_list.exit.addInstr(ret);

      // Link last current block to final block.
      // (this only makes a difference for void funtions)

      // TODO Do we need final Node?
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
   :  ^(PRINT e=expression[current] (ENDL)?) {
      Instruction l = null;

      if ($ENDL != null) {
         l = new PrintlnInstruction();
      } else {
         l = new PrintInstruction();
      }

      l.addRegister($e.r);
      current.addInstr(l);
   }
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
   :  ^(IF c=expression[current] tb=block[tStart] (fb=block[fStart])?) {
         // Add branch from current block to true/false block.
         String tLabel = tStart.getLabel();
         String fLabel = fStart.getLabel();

         if ($fb.exit == null) {
            // Jump to exit if no else block.
            fLabel = $exit.getLabel();
         }

         addBranchInstructions($c.r, tLabel, fLabel, current);

         /* Add code for looking at expression and jumping */
         /* Link then block path */
         current.addChild(tStart);
         tStart.addParent(current);
         $tb.exit.addChild($exit);
         $exit.addParent($tb.exit);

         Instruction i = new JumpiInstruction();
         i.addLabel($exit.getLabel());
         $tb.exit.addInstr(i);

         if ($fb.exit != null) {
            /* Link else block path */
            current.addChild(fStart);
            fStart.addParent(current);
            $exit.addParent($fb.exit);
            $fb.exit.addChild($exit);

            i = new JumpiInstruction();
            i.addLabel($exit.getLabel());
            $fb.exit.addInstr(i);
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

   loopNode.addChild(loopNode); // Is this right?
   loopNode.addChild($exit);
}
   : ^(WHILE ex1=expression[current] {

      addBranchInstructions($ex1.r, loopNode.getLabel(), $exit.getLabel(), current);

   } block[loopNode] ex2=expression[loopNode]) {

      addBranchInstructions($ex2.r, loopNode.getLabel(), $exit.getLabel(), loopNode);

   }
   ;

delete[Node current]
@init {
}
   : ^(DELETE expression[current]) {
      Instruction l = new DelInstruction();
      l.addRegister($expression.r);
      current.addInstr(l);
   }
   ;

ret[Node current] returns [Node exit]
@init {
}
   : ^(RETURN (e=expression[current])?) {
      if ($e.r != null) {
         Instruction sr = new StoreretInstruction();
         sr.addDest($e.r); // Might need to be addRegister instead
         current.addInstr(sr);
      }

      Instruction r = new RetInstruction();
      current.addInstr(r);

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
      Instruction store, call, load;
      int offset = 0;

      $r = new Register();
      $r.setType(fun.getReturn());

      // Put arguemnts into out registers.
      for (Register arg : $arguments.args) {
         store = new StoreoutargumentInstruction();
         store.addSource(arg);
         // store.addID(fun.getParams().get(offset).getName());
         store.addImmediate(offset++);
         current.addInstr(store);
      }

      // Jump to ID.
      call = new CallInstruction();
      call.addLabel($ID.getText());
      current.addInstr(call);

      // If funcdtion is not Void, load return into r.
      if (!(fun.getReturn() instanceof VoidType)) {
         load = new LoadretInstruction();
         load.addDest($r);
         current.addInstr(load);
      }
   }
   ;

arguments[Node current] returns [List<Register> args]
@init {
   $args = new LinkedList<Register>();
}
   : ^(ARGS (expression[current] { args.add($expression.r); } )*)
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
      $r = new Register();
      StructType type = symTable.getStruct($ID.getText());
      Instruction l = new NewInstruction();

      $r.setType(type);
      l.addSource(new StructIdentifier(type));
      l.addDest($r);

      current.addInstr(l);
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
      x.addRegister($e.r);
      x.addImmediate(new Immediate(1));
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
