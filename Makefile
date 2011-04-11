# The makefile for our compiler.
#
# @author Ben Sweedler
# @author Nat Welch

FILES=BoolType.java DottedTree.java Evil.java VoidType.java FunctionTree.java FuncType.java IntType.java StructType.java Symbol.java SymbolTable.java Type.java OperatorType.java
DEBUGFLAGS=
RUNFLAGS=
JAVAFLAGS=-Xlint:unchecked

TYPECHECKOBJECTS=Type.java BoolType.java IntType.java FuncType.java StructType.java SymbolTable.java Symbol.java NullType.java

export CLASSPATH=.:./antlr-3.3-complete.jar:./commons-cli-1.2.jar

all: Evil.class

Evil.class: antlr.generated ${FILES} ${TYPECHECKOBJECTS}
	javac ${JAVAFLAGS} *.java

antlr.generated: antlr.generated.evil antlr.generated.type
	@touch antlr.generated

antlr.generated.evil: Evil.g
	java org.antlr.Tool Evil.g
	@touch antlr.generated.evil

antlr.generated.type: TypeCheck.g
	java org.antlr.Tool ${DEBUGFLAGS} TypeCheck.g
	@touch antlr.generated.type

run: Evil.class
	java Evil ${RUNFLAGS} < tests/2.ev

test: Evil.class
	@./test.sh

debug:
	@make -s -B -e DEBUGFLAGS=-trace

run_debug: debug 
	@make -s run -e RUNFLAGS=-d

ast: Evil.class
	java Evil --displayAST tests/1.ev

d: Evil.class
	java Evil -d tests/1.ev

help: Evil.class
	@java Evil -h

clean:
	@rm -fv *class
	@rm -fv *tokens
	@rm -fv antlr.generated*
	@rm -fv EvilLexer.java EvilParser.java TypeCheck.java

