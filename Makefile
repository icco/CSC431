# The makefile for our compiler.
#
# @author Ben Sweedler
# @author Nat Welch

DEBUGFLAGS=
RUNFLAGS=
JAVAFLAGS=-Xlint:unchecked

TYPEOBJECTS=Type.java BoolType.java VoidType.java FuncType.java IntType.java StructType.java Symbol.java Type.java OperatorType.java NullType.java
EVILOBJECTS=DottedTree.java FunctionTree.java TypeCheck.java SymbolTable.java

TYPECLASSES=${TYPEOBJECTS:.java=.class}
EVILCLASSES=${EVILOBJECTS:.java=.class}

export CLASSPATH=.:./antlr-3.3-complete.jar:./commons-cli-1.2.jar

# default rule of .java files to depend on their .class file.
.SUFFIXES: .java .class
.java.class:
	javac ${JAVAFLAGS} $*.java

all: Evil.class

Evil.class: ${TYPECLASSES} antlr.generated ${EVILCLASSES}

antlr.generated: antlr.generated.evil antlr.generated.type
	@touch antlr.generated

antlr.generated.evil: Evil.g
	java org.antlr.Tool Evil.g
	@touch antlr.generated.evil

antlr.generated.type: TypeCheck.g
	java org.antlr.Tool ${DEBUGFLAGS} TypeCheck.g
	@touch antlr.generated.type

TypeCheck.class: TypeCheck.java

run: Evil.class
	java Evil ${RUNFLAGS} tests/4.ev

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

