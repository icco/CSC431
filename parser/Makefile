# The makefile for our compiler.
#
# @author Ben Sweedler
# @author Nat Welch

FILES=Evil.java
DEBUGFLAGS=

all: Evil.class

# We define the classpath in this shell file.
classpath:
	./classpath.sh

Evil.class: classpath antlr.generated ${FILES}
	javac *.java

antlr.generated: antlr.generated.evil antlr.generated.type
	touch antlr.generated

antlr.generated.evil: Evil.g
	java org.antlr.Tool Evil.g
	touch antlr.generated.evil

antlr.generated.type: TypeCheck.g
	java org.antlr.Tool ${DEBUGFLAGS} TypeCheck.g
	touch antlr.generated.type

run: Evil.class
	cat tests/1.ev
	java Evil < tests/1.ev

test: Evil.class
	@./test.sh

debug:
	@make -s -B -e DEBUGFLAGS=-trace

run_debug: debug run

ast: Evil.class
	java Evil --displayAST < tests/1.ev

help: Evil.class
	@java Evil -h

clean:
	@rm -fv *class
	@rm -fv *tokens
	@rm -fv antlr.generated*
	@rm -fv EvilLexer.java EvilParser.java TypeCheck.java

