FILES=Evil.java

DEBUGFLAGS=

export CLASSPATH=CLASSPATH:.:./antlr-3.3-complete.jar

Evil.class : antlr.generated ${FILES}
	javac *.java

antlr.generated: antlr.generated.evil antlr.generated.type
	touch antlr.generated

antlr.generated.evil : Evil.g
	java org.antlr.Tool Evil.g
	touch antlr.generated.evil

antlr.generated.type : TypeCheck.g
	java org.antlr.Tool ${DEBUGFLAGS} TypeCheck.g
	touch antlr.generated.type

run: Evil.class
	cat tests/basic.ev
	java Evil < tests/basic.ev

test: clean Evil.class
	@./test.sh

ast: Evil.class
	java Evil -displayAST < tests/1.ev

clean:
	@rm -fv *class
	@rm -fv *tokens
	@rm -fv antlr.generated*
	@rm -fv EvilLexer.java EvilParser.java TypeCheck.java

debug:
	@make -s -B -e DEBUGFLAGS=-trace

debug_run: debug run

