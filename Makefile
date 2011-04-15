# The makefile for our compiler.
#
# @author Ben Sweedler
# @author Nat Welch

SOURCEDIR=src
CLASSDIR=classes

DEBUGFLAGS=
RUNFLAGS=

JAVAFLAGS=-d ${CLASSDIR} -Xlint:unchecked
ANTLRFLAGS=-lib ${CLASSDIR}

export CLASSPATH=.:./${SOURCEDIR}:./${CLASSDIR}:./antlr-3.3-complete.jar:./commons-cli-1.2.jar

JAVAC=javac ${JAVAFLAGS}

sources = $(wildcard ${SOURCEDIR}/*.java)
classes = $(sources:.java=.class)

all: ${CLASSDIR} antlr.generated $(classes)

%.class : %.java
	$(JAVAC) $<

${CLASSDIR}:
	mkdir ${CLASSDIR} -p
	@touch ${CLASSDIR}

antlr.generated: antlr.generated.evil antlr.generated.type antlr.generated.cfg
	@touch antlr.generated

antlr.generated.evil: ${SOURCEDIR}/Evil.g
	java org.antlr.Tool ${ANTLRFLAGS} src/Evil.g
	@touch antlr.generated.evil

antlr.generated.type: ${SOURCEDIR}/TypeCheck.g
	java org.antlr.Tool ${ANTLRFLAGS} ${DEBUGFLAGS} src/TypeCheck.g
	@touch antlr.generated.type

antlr.generated.cfg: ${SOURCEDIR}/CFG.g
	java org.antlr.Tool ${ANTLRFLAGS} ${DEBUGFLAGS} src/CFG.g
	@touch antlr.generated.cfg

run: all
	java Evil ${RUNFLAGS} tests/4.ev

test: all
	@./test.sh

debug:
	@make -s -B -e DEBUGFLAGS=-trace

run_debug: debug
	@make -s run -e RUNFLAGS=-d

ast: all
	java Evil --displayAST tests/1.ev

d: all
	java Evil -d tests/1.ev

help: all
	@java Evil -h

clean:
	@rm -rfv classes
	@rm -fv *.tokens
	@rm -fv antlr.generated*
	@rm -fv src/EvilLexer.java src/EvilParser.java src/TypeCheck.java src/CFG.java

