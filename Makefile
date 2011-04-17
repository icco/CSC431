# The makefile for our compiler.
#
# @author Ben Sweedler
# @author Nat Welch

SHELL := /bin/bash

SOURCEDIR=src
CLASSDIR=classes

DEBUGFLAGS=
RUNFLAGS=

JAVAFLAGS=-d ${CLASSDIR} -Xlint:unchecked
ANTLRFLAGS=-lib ${CLASSDIR}

export CLASSPATH=.:./${SOURCEDIR}:./${CLASSDIR}:./antlr-3.3-complete.jar:./commons-cli-1.2.jar

JAVAC=javac ${JAVAFLAGS}

sources = $(wildcard ${SOURCEDIR}/*.java)
iclasses = $(sources:.java=.class)
classes = $(subst ${SOURCEDIR},${CLASSDIR},$(iclasses))

all: ${CLASSDIR} antlr.generated instructions $(classes)

%.class: $(subst ${CLASSDIR},${SOURCEDIR},$(subst .class,.java,$@))
	$(JAVAC) $(subst ${CLASSDIR},${SOURCEDIR},$(subst .class,.java,$@))

${CLASSDIR}:
	mkdir ${CLASSDIR} -p
	@touch ${CLASSDIR}

antlr.generated: antlr.generated.evil antlr.generated.type antlr.generated.cfg
	@touch antlr.generated

antlr.generated.evil: ${SOURCEDIR}/Evil.g
	java org.antlr.Tool ${ANTLRFLAGS} ${DEBUGFLAGS} src/Evil.g
	@touch antlr.generated.evil

antlr.generated.type: ${SOURCEDIR}/TypeCheck.g
	java org.antlr.Tool ${ANTLRFLAGS} ${DEBUGFLAGS} src/TypeCheck.g
	@touch antlr.generated.type

antlr.generated.cfg: ${SOURCEDIR}/CFG.g
	java org.antlr.Tool ${ANTLRFLAGS} ${DEBUGFLAGS} src/CFG.g
	@touch antlr.generated.cfg

instructions: generate_instructions.py
	./generate_instructions.py

run: all
	java Evil -i ${RUNFLAGS} tests/4.ev

typetest: all
	@./typetest.sh

test: run

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
