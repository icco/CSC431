FILES=Evil.java

export CLASSPATH=CLASSPATH:.:./antlr-3.3-complete.jar

Evil.class : antlr.generated ${FILES}
	javac *.java

antlr.generated: antlr.generated.evil antlr.generated.type
	touch antlr.generated

antlr.generated.evil : Evil.g
	java org.antlr.Tool Evil.g
	touch antlr.generated.evil

antlr.generated.type : TypeCheck.g
	java org.antlr.Tool TypeCheck.g
	touch antlr.generated.type

run: Evil.class
	java Evil

test: Evil.class
	@./test.sh

ast: Evil.class
	java Evil -displayAST < tests/1.ev

clean:
	rm -fv *class
	rm -fv *tokens
	rm -fv antlr.generated*
