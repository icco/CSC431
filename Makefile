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
classes = $(subst ${SOURCEDIR},${CLASSDIR},$(sources:.java=.class))

all: ${CLASSDIR} antlr.generated antlr.generated.inst $(classes)

%.class: $(sources)
	$(JAVAC) ${SOURCEDIR}/*.java

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

antlr.generated.walker : ${SOURCEDIR}/Walker.g
	java org.antlr.Tool ${ANTLRFLAGS} ${DEBUGFLAGS} src/Walker.g
	@touch antlr.generated.walker

# Technically not antlr, but looks nice.
antlr.generated.inst: generate_instructions.py
	bash -c "rm -f src/[A-Z]*Sparc.java"
	./generate_instructions.py
	@touch antlr.generated.inst

run: all
	echo 13 | ./build.sh
	@echo "ssh sparc03 , cd `pwd` , echo 13 | ./test.sh"

%.ev: all
	./ecc ${RUNFLAGS} $@

%.il: all
	./ecc -i ${RUNFLAGS} $(subst .il,.ev,$@)
	java -jar mochi.jar -r $@

typetest: all
	@./typetest.sh

.PHONY: test
test: tests/compat.ev

debug:
	@make -s -B -e DEBUGFLAGS=-trace

#run_debug: debug
#	@make -s run -e RUNFLAGS=-d

ast: all
	./ecc --displayAST tests/1.ev

d: all
	./ecc -d tests/1.ev

help: all
	@./ecc -h

clean:
	@rm -rfv classes
	@rm -fv *.tokens
	@rm -fv antlr.generated*
	@rm -fv src/EvilLexer.java src/EvilParser.java src/TypeCheck.java src/CFG.java src/Walker.java
	@rm -fv benchmarks/*/*.s
	@rm -fv benchmarks/*/output.{ev,c}
	@rm -fv a.out
