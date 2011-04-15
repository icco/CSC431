# The makefile for our compiler.
#
# @author Ben Sweedler
# @author Nat Welch

SOURCEDIR=src
CLASSDIR=classes

DEBUGFLAGS=
RUNFLAGS=

JAVAFLAGS=-d classes -Xlint:unchecked
ANTLRFLAGS=-lib ${CLASSDIR}

TYPEFILES=Type.java BoolType.java FuncType.java IntType.java NullType.java OperatorType.java StructType.java Symbol.java VoidType.java
TYPEOBJECTS=${TYPEFILES:%.java=${SOURCEDIR}/%.java}
TYPECLASSES=${TYPEOBJECTS:${SOURCEDIR}/%.java=${CLASSDIR}/%.class}

EVILFILES=FunctionTree.java DottedTree.java SymbolTable.java TypeCheck.java
EVILOBJECTS=${EVILFILES:%.java=${SOURCEDIR}/%.java}
EVILCLASSES=${EVILOBJECTS:${SOURCEDIR}/%.java=${CLASSDIR}/%.class}

export CLASSPATH=.:./src:./${CLASSDIR}:./antlr-3.3-complete.jar:./commons-cli-1.2.jar

all: ${CLASSDIR}/Evil.class 

${CLASSDIR}:
	mkdir ${CLASSDIR} -p
	@touch ${CLASSDIR}

# default rule of .java files to depend on their .class file.
${CLASSDIR}/%.class: ${CLASSDIR} ${SOURCEDIR}/%.java
	javac ${JAVAFLAGS} ${SOURCEDIR}/$*.java

${CLASSDIR}/Evil.class: ${TYPECLASSES} antlr.generated ${EVILCLASSES} 

antlr.generated: antlr.generated.evil antlr.generated.type
	@touch antlr.generated

antlr.generated.evil: src/Evil.g
	java org.antlr.Tool ${ANTLRFLAGS} src/Evil.g
	@touch antlr.generated.evil

antlr.generated.type: src/TypeCheck.g
	java org.antlr.Tool ${ANTLRFLAGS} ${DEBUGFLAGS} src/TypeCheck.g 
	@touch antlr.generated.type

run: all
	java Evil ${RUNFLAGS} tests/4.ev

test: all
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
	@rm -rfv classes
	@rm -fv *.tokens
	@rm -fv antlr.generated*
	@rm -fv src/EvilLexer.java src/EvilParser.java src/TypeCheck.java

