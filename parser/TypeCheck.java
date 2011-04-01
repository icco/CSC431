// $ANTLR 3.3 Nov 30, 2010 12:50:56 TypeCheck.g 2011-04-01 15:50:34

   import java.util.Map;
   import java.util.HashMap;
   import java.util.Vector;
   import java.util.Iterator;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TypeCheck extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "STRUCT", "INT", "BOOL", "FUN", "VOID", "PRINT", "ENDL", "READ", "IF", "ELSE", "WHILE", "DELETE", "RETURN", "TRUE", "FALSE", "NEW", "NULL", "PROGRAM", "TYPES", "TYPE", "DECLS", "FUNCS", "DECL", "DECLLIST", "PARAMS", "RETTYPE", "BLOCK", "STMTS", "INVOKE", "ARGS", "NEG", "LBRACE", "RBRACE", "SEMI", "COMMA", "LPAREN", "RPAREN", "ASSIGN", "DOT", "AND", "OR", "EQ", "LT", "GT", "NE", "LE", "GE", "PLUS", "MINUS", "TIMES", "DIVIDE", "NOT", "ID", "INTEGER", "WS", "COMMENT"
    };
    public static final int EOF=-1;
    public static final int STRUCT=4;
    public static final int INT=5;
    public static final int BOOL=6;
    public static final int FUN=7;
    public static final int VOID=8;
    public static final int PRINT=9;
    public static final int ENDL=10;
    public static final int READ=11;
    public static final int IF=12;
    public static final int ELSE=13;
    public static final int WHILE=14;
    public static final int DELETE=15;
    public static final int RETURN=16;
    public static final int TRUE=17;
    public static final int FALSE=18;
    public static final int NEW=19;
    public static final int NULL=20;
    public static final int PROGRAM=21;
    public static final int TYPES=22;
    public static final int TYPE=23;
    public static final int DECLS=24;
    public static final int FUNCS=25;
    public static final int DECL=26;
    public static final int DECLLIST=27;
    public static final int PARAMS=28;
    public static final int RETTYPE=29;
    public static final int BLOCK=30;
    public static final int STMTS=31;
    public static final int INVOKE=32;
    public static final int ARGS=33;
    public static final int NEG=34;
    public static final int LBRACE=35;
    public static final int RBRACE=36;
    public static final int SEMI=37;
    public static final int COMMA=38;
    public static final int LPAREN=39;
    public static final int RPAREN=40;
    public static final int ASSIGN=41;
    public static final int DOT=42;
    public static final int AND=43;
    public static final int OR=44;
    public static final int EQ=45;
    public static final int LT=46;
    public static final int GT=47;
    public static final int NE=48;
    public static final int LE=49;
    public static final int GE=50;
    public static final int PLUS=51;
    public static final int MINUS=52;
    public static final int TIMES=53;
    public static final int DIVIDE=54;
    public static final int NOT=55;
    public static final int ID=56;
    public static final int INTEGER=57;
    public static final int WS=58;
    public static final int COMMENT=59;

    // delegates
    // delegators


        public TypeCheck(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public TypeCheck(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TypeCheck.tokenNames; }
    public String getGrammarFileName() { return "TypeCheck.g"; }



    // $ANTLR start "verify"
    // TypeCheck.g:17:1: verify : ^( PROGRAM types declarations functions ) ;
    public final void verify() throws RecognitionException {
        try {
            // TypeCheck.g:17:7: ( ^( PROGRAM types declarations functions ) )
            // TypeCheck.g:18:2: ^( PROGRAM types declarations functions )
            {
            match(input,PROGRAM,FOLLOW_PROGRAM_in_verify40); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_types_in_verify42);
            types();

            state._fsp--;

            pushFollow(FOLLOW_declarations_in_verify44);
            declarations();

            state._fsp--;

            pushFollow(FOLLOW_functions_in_verify46);
            functions();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "verify"


    // $ANTLR start "types"
    // TypeCheck.g:21:1: types : ^( TYPES ( types_declaration )* ) ;
    public final void types() throws RecognitionException {
         
           System.out.println("Starting types"); 

        try {
            // TypeCheck.g:25:4: ( ^( TYPES ( types_declaration )* ) )
            // TypeCheck.g:25:6: ^( TYPES ( types_declaration )* )
            {
            match(input,TYPES,FOLLOW_TYPES_in_types66); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // TypeCheck.g:25:14: ( types_declaration )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==STRUCT) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // TypeCheck.g:25:15: types_declaration
                	    {
                	    pushFollow(FOLLOW_types_declaration_in_types69);
                	    types_declaration();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop1;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "types"


    // $ANTLR start "types_declaration"
    // TypeCheck.g:28:1: types_declaration : ^( STRUCT ID nested_decl ) ;
    public final void types_declaration() throws RecognitionException {
         
           System.out.println("Starting type declaration"); 

        try {
            // TypeCheck.g:32:4: ( ^( STRUCT ID nested_decl ) )
            // TypeCheck.g:32:6: ^( STRUCT ID nested_decl )
            {
            match(input,STRUCT,FOLLOW_STRUCT_in_types_declaration90); 

            match(input, Token.DOWN, null); 
            match(input,ID,FOLLOW_ID_in_types_declaration92); 
            pushFollow(FOLLOW_nested_decl_in_types_declaration94);
            nested_decl();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "types_declaration"


    // $ANTLR start "nested_decl"
    // TypeCheck.g:35:1: nested_decl : ( decl )+ ;
    public final void nested_decl() throws RecognitionException {

           System.out.println("Starting nested declarations");

        try {
            // TypeCheck.g:39:4: ( ( decl )+ )
            // TypeCheck.g:39:6: ( decl )+
            {
            // TypeCheck.g:39:6: ( decl )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==DECL) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // TypeCheck.g:39:7: decl
            	    {
            	    pushFollow(FOLLOW_decl_in_nested_decl113);
            	    decl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "nested_decl"


    // $ANTLR start "decl"
    // TypeCheck.g:42:1: decl : ^( DECL type ID ) ;
    public final void decl() throws RecognitionException {

           System.out.println("Starting declaration");

        try {
            // TypeCheck.g:46:4: ( ^( DECL type ID ) )
            // TypeCheck.g:46:6: ^( DECL type ID )
            {
            match(input,DECL,FOLLOW_DECL_in_decl136); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_decl138);
            type();

            state._fsp--;

            match(input,ID,FOLLOW_ID_in_decl140); 

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "decl"


    // $ANTLR start "type"
    // TypeCheck.g:49:1: type : ( INT | BOOL | ^( STRUCT ID ) );
    public final void type() throws RecognitionException {

           System.out.println("Starting type");

        try {
            // TypeCheck.g:53:4: ( INT | BOOL | ^( STRUCT ID ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt3=1;
                }
                break;
            case BOOL:
                {
                alt3=2;
                }
                break;
            case STRUCT:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // TypeCheck.g:53:6: INT
                    {
                    match(input,INT,FOLLOW_INT_in_type161); 

                    }
                    break;
                case 2 :
                    // TypeCheck.g:54:6: BOOL
                    {
                    match(input,BOOL,FOLLOW_BOOL_in_type168); 

                    }
                    break;
                case 3 :
                    // TypeCheck.g:55:6: ^( STRUCT ID )
                    {
                    match(input,STRUCT,FOLLOW_STRUCT_in_type176); 

                    match(input, Token.DOWN, null); 
                    match(input,ID,FOLLOW_ID_in_type178); 

                    match(input, Token.UP, null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type"


    // $ANTLR start "declarations"
    // TypeCheck.g:58:1: declarations : ;
    public final void declarations() throws RecognitionException {
         
           System.out.println("Starting declarations");

        try {
            // TypeCheck.g:62:4: ()
            // TypeCheck.g:63:4: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declarations"


    // $ANTLR start "functions"
    // TypeCheck.g:65:1: functions : ;
    public final void functions() throws RecognitionException {
        try {
            // TypeCheck.g:65:10: ()
            // TypeCheck.g:67:1: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "functions"

    // Delegated rules


 

    public static final BitSet FOLLOW_PROGRAM_in_verify40 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_types_in_verify42 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_declarations_in_verify44 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_functions_in_verify46 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPES_in_types66 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_types_declaration_in_types69 = new BitSet(new long[]{0x0000000000000018L});
    public static final BitSet FOLLOW_STRUCT_in_types_declaration90 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_types_declaration92 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_nested_decl_in_types_declaration94 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_decl_in_nested_decl113 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_DECL_in_decl136 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_decl138 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_decl140 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INT_in_type161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_type168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_type176 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_type178 = new BitSet(new long[]{0x0000000000000008L});

}