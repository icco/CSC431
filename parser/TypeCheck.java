// $ANTLR 3.3 Nov 30, 2010 12:50:56 TypeCheck.g 2011-04-04 15:58:04

   import java.util.Map;
   import java.util.HashMap;
   import java.util.Vector;
   import java.util.Iterator;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
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
    // TypeCheck.g:18:1: verify : ^( PROGRAM types declarations functions ) ;
    public final void verify() throws RecognitionException {


        try {
            // TypeCheck.g:21:4: ( ^( PROGRAM types declarations functions ) )
            // TypeCheck.g:21:6: ^( PROGRAM types declarations functions )
            {
            match(input,PROGRAM,FOLLOW_PROGRAM_in_verify56); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_types_in_verify58);
            types();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_declarations_in_verify60);
            declarations();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_functions_in_verify62);
            functions();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;

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
    // TypeCheck.g:24:1: types : ^( TYPES ( types_declaration )* ) ;
    public final void types() throws RecognitionException {


        try {
            // TypeCheck.g:27:4: ( ^( TYPES ( types_declaration )* ) )
            // TypeCheck.g:27:6: ^( TYPES ( types_declaration )* )
            {
            match(input,TYPES,FOLLOW_TYPES_in_types84); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                // TypeCheck.g:27:14: ( types_declaration )*
                loop1:
                do {
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==STRUCT) ) {
                        alt1=1;
                    }


                    switch (alt1) {
                	case 1 :
                	    // TypeCheck.g:27:15: types_declaration
                	    {
                	    pushFollow(FOLLOW_types_declaration_in_types87);
                	    types_declaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop1;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return ;
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
    // TypeCheck.g:30:1: types_declaration : ^( STRUCT ID ( var_decl )+ ) ;
    public final void types_declaration() throws RecognitionException {


        try {
            // TypeCheck.g:33:4: ( ^( STRUCT ID ( var_decl )+ ) )
            // TypeCheck.g:33:6: ^( STRUCT ID ( var_decl )+ )
            {
            match(input,STRUCT,FOLLOW_STRUCT_in_types_declaration111); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            match(input,ID,FOLLOW_ID_in_types_declaration113); if (state.failed) return ;
            // TypeCheck.g:33:18: ( var_decl )+
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
            	    // TypeCheck.g:33:19: var_decl
            	    {
            	    pushFollow(FOLLOW_var_decl_in_types_declaration116);
            	    var_decl();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return ;

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


    // $ANTLR start "var_decl"
    // TypeCheck.g:36:1: var_decl : ^( DECL ^( TYPE type ) ID ) ;
    public final void var_decl() throws RecognitionException {


        try {
            // TypeCheck.g:39:4: ( ^( DECL ^( TYPE type ) ID ) )
            // TypeCheck.g:39:6: ^( DECL ^( TYPE type ) ID )
            {
            match(input,DECL,FOLLOW_DECL_in_var_decl140); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            match(input,TYPE,FOLLOW_TYPE_in_var_decl143); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_type_in_var_decl145);
            type();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;
            match(input,ID,FOLLOW_ID_in_var_decl148); if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "var_decl"


    // $ANTLR start "type"
    // TypeCheck.g:42:1: type : ( INT | BOOL | ^( STRUCT ID ) );
    public final void type() throws RecognitionException {


        try {
            // TypeCheck.g:45:4: ( INT | BOOL | ^( STRUCT ID ) )
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
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // TypeCheck.g:45:6: INT
                    {
                    match(input,INT,FOLLOW_INT_in_type169); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // TypeCheck.g:46:6: BOOL
                    {
                    match(input,BOOL,FOLLOW_BOOL_in_type176); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // TypeCheck.g:47:6: ^( STRUCT ID )
                    {
                    match(input,STRUCT,FOLLOW_STRUCT_in_type184); if (state.failed) return ;

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    match(input,ID,FOLLOW_ID_in_type186); if (state.failed) return ;

                    match(input, Token.UP, null); if (state.failed) return ;

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
    // TypeCheck.g:50:1: declarations : ^( DECLS ( declaration )* ) ;
    public final void declarations() throws RecognitionException {


        try {
            // TypeCheck.g:53:4: ( ^( DECLS ( declaration )* ) )
            // TypeCheck.g:53:6: ^( DECLS ( declaration )* )
            {
            match(input,DECLS,FOLLOW_DECLS_in_declarations208); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                // TypeCheck.g:53:14: ( declaration )*
                loop4:
                do {
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==DECLLIST) ) {
                        alt4=1;
                    }


                    switch (alt4) {
                	case 1 :
                	    // TypeCheck.g:0:0: declaration
                	    {
                	    pushFollow(FOLLOW_declaration_in_declarations210);
                	    declaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop4;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return ;
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
    // $ANTLR end "declarations"


    // $ANTLR start "declaration"
    // TypeCheck.g:56:1: declaration : ^( DECLLIST ^( TYPE type ) ( ID )+ ) ;
    public final void declaration() throws RecognitionException {


        try {
            // TypeCheck.g:59:4: ( ^( DECLLIST ^( TYPE type ) ( ID )+ ) )
            // TypeCheck.g:59:6: ^( DECLLIST ^( TYPE type ) ( ID )+ )
            {
            match(input,DECLLIST,FOLLOW_DECLLIST_in_declaration233); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            match(input,TYPE,FOLLOW_TYPE_in_declaration236); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_type_in_declaration238);
            type();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;
            // TypeCheck.g:59:30: ( ID )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==ID) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // TypeCheck.g:59:31: ID
            	    {
            	    match(input,ID,FOLLOW_ID_in_declaration242); if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
            	    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "declaration"


    // $ANTLR start "functions"
    // TypeCheck.g:62:1: functions : ^( FUNCS ( function )* ) ;
    public final void functions() throws RecognitionException {


        try {
            // TypeCheck.g:65:4: ( ^( FUNCS ( function )* ) )
            // TypeCheck.g:65:6: ^( FUNCS ( function )* )
            {
            match(input,FUNCS,FOLLOW_FUNCS_in_functions266); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                // TypeCheck.g:65:14: ( function )*
                loop6:
                do {
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==FUN) ) {
                        alt6=1;
                    }


                    switch (alt6) {
                	case 1 :
                	    // TypeCheck.g:0:0: function
                	    {
                	    pushFollow(FOLLOW_function_in_functions268);
                	    function();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop6;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return ;
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
    // $ANTLR end "functions"


    // $ANTLR start "function"
    // TypeCheck.g:68:1: function : ^( FUN ID parameters ^( RETTYPE return_type ) declarations statement_list ) ;
    public final void function() throws RecognitionException {


        try {
            // TypeCheck.g:71:4: ( ^( FUN ID parameters ^( RETTYPE return_type ) declarations statement_list ) )
            // TypeCheck.g:71:6: ^( FUN ID parameters ^( RETTYPE return_type ) declarations statement_list )
            {
            match(input,FUN,FOLLOW_FUN_in_function291); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            match(input,ID,FOLLOW_ID_in_function293); if (state.failed) return ;
            pushFollow(FOLLOW_parameters_in_function295);
            parameters();

            state._fsp--;
            if (state.failed) return ;
            match(input,RETTYPE,FOLLOW_RETTYPE_in_function298); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_return_type_in_function300);
            return_type();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;
            pushFollow(FOLLOW_declarations_in_function303);
            declarations();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_statement_list_in_function305);
            statement_list();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "function"


    // $ANTLR start "return_type"
    // TypeCheck.g:74:1: return_type : ( type | VOID );
    public final void return_type() throws RecognitionException {


        try {
            // TypeCheck.g:77:4: ( type | VOID )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=STRUCT && LA7_0<=BOOL)) ) {
                alt7=1;
            }
            else if ( (LA7_0==VOID) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // TypeCheck.g:77:6: type
                    {
                    pushFollow(FOLLOW_type_in_return_type326);
                    type();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // TypeCheck.g:78:6: VOID
                    {
                    match(input,VOID,FOLLOW_VOID_in_return_type333); if (state.failed) return ;

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
    // $ANTLR end "return_type"


    // $ANTLR start "parameters"
    // TypeCheck.g:81:1: parameters : ^( PARAMS ( var_decl )* ) ;
    public final void parameters() throws RecognitionException {


        try {
            // TypeCheck.g:84:4: ( ^( PARAMS ( var_decl )* ) )
            // TypeCheck.g:84:6: ^( PARAMS ( var_decl )* )
            {
            match(input,PARAMS,FOLLOW_PARAMS_in_parameters354); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                // TypeCheck.g:84:15: ( var_decl )*
                loop8:
                do {
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==DECL) ) {
                        alt8=1;
                    }


                    switch (alt8) {
                	case 1 :
                	    // TypeCheck.g:0:0: var_decl
                	    {
                	    pushFollow(FOLLOW_var_decl_in_parameters356);
                	    var_decl();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop8;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return ;
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
    // $ANTLR end "parameters"


    // $ANTLR start "statement_list"
    // TypeCheck.g:87:1: statement_list : ^( STMTS ( statement )* ) ;
    public final void statement_list() throws RecognitionException {


        try {
            // TypeCheck.g:90:4: ( ^( STMTS ( statement )* ) )
            // TypeCheck.g:90:6: ^( STMTS ( statement )* )
            {
            match(input,STMTS,FOLLOW_STMTS_in_statement_list379); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                // TypeCheck.g:90:14: ( statement )*
                loop9:
                do {
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==PRINT||(LA9_0>=READ && LA9_0<=IF)||(LA9_0>=WHILE && LA9_0<=RETURN)||LA9_0==BLOCK||LA9_0==INVOKE||LA9_0==ASSIGN) ) {
                        alt9=1;
                    }


                    switch (alt9) {
                	case 1 :
                	    // TypeCheck.g:0:0: statement
                	    {
                	    pushFollow(FOLLOW_statement_in_statement_list381);
                	    statement();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop9;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return ;
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
    // $ANTLR end "statement_list"


    // $ANTLR start "statement"
    // TypeCheck.g:93:1: statement : ( block | assignment | print | read | conditional | loop | delete | ret | invocation );
    public final void statement() throws RecognitionException {


        try {
            // TypeCheck.g:96:4: ( block | assignment | print | read | conditional | loop | delete | ret | invocation )
            int alt10=9;
            switch ( input.LA(1) ) {
            case BLOCK:
                {
                alt10=1;
                }
                break;
            case ASSIGN:
                {
                alt10=2;
                }
                break;
            case PRINT:
                {
                alt10=3;
                }
                break;
            case READ:
                {
                alt10=4;
                }
                break;
            case IF:
                {
                alt10=5;
                }
                break;
            case WHILE:
                {
                alt10=6;
                }
                break;
            case DELETE:
                {
                alt10=7;
                }
                break;
            case RETURN:
                {
                alt10=8;
                }
                break;
            case INVOKE:
                {
                alt10=9;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // TypeCheck.g:96:6: block
                    {
                    pushFollow(FOLLOW_block_in_statement403);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // TypeCheck.g:97:6: assignment
                    {
                    pushFollow(FOLLOW_assignment_in_statement410);
                    assignment();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // TypeCheck.g:98:6: print
                    {
                    pushFollow(FOLLOW_print_in_statement417);
                    print();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // TypeCheck.g:99:6: read
                    {
                    pushFollow(FOLLOW_read_in_statement424);
                    read();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // TypeCheck.g:100:6: conditional
                    {
                    pushFollow(FOLLOW_conditional_in_statement431);
                    conditional();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // TypeCheck.g:101:6: loop
                    {
                    pushFollow(FOLLOW_loop_in_statement438);
                    loop();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // TypeCheck.g:102:6: delete
                    {
                    pushFollow(FOLLOW_delete_in_statement445);
                    delete();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // TypeCheck.g:103:6: ret
                    {
                    pushFollow(FOLLOW_ret_in_statement452);
                    ret();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // TypeCheck.g:104:6: invocation
                    {
                    pushFollow(FOLLOW_invocation_in_statement459);
                    invocation();

                    state._fsp--;
                    if (state.failed) return ;

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
    // $ANTLR end "statement"


    // $ANTLR start "block"
    // TypeCheck.g:107:1: block : ^( BLOCK statement_list ) ;
    public final void block() throws RecognitionException {


        try {
            // TypeCheck.g:110:4: ( ^( BLOCK statement_list ) )
            // TypeCheck.g:110:6: ^( BLOCK statement_list )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block480); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_statement_list_in_block482);
            statement_list();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "block"


    // $ANTLR start "assignment"
    // TypeCheck.g:113:1: assignment : ^( ASSIGN expression lvalue ) ;
    public final void assignment() throws RecognitionException {


        try {
            // TypeCheck.g:116:4: ( ^( ASSIGN expression lvalue ) )
            // TypeCheck.g:116:6: ^( ASSIGN expression lvalue )
            {
            match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment504); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_assignment506);
            expression();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_lvalue_in_assignment508);
            lvalue();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "assignment"


    // $ANTLR start "lvalue"
    // TypeCheck.g:119:1: lvalue : ( ID | ^( DOT lvalue_h ID ) );
    public final void lvalue() throws RecognitionException {


        try {
            // TypeCheck.g:122:4: ( ID | ^( DOT lvalue_h ID ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==ID) ) {
                alt11=1;
            }
            else if ( (LA11_0==DOT) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // TypeCheck.g:122:7: ID
                    {
                    match(input,ID,FOLLOW_ID_in_lvalue530); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // TypeCheck.g:123:6: ^( DOT lvalue_h ID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_lvalue539); if (state.failed) return ;

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    pushFollow(FOLLOW_lvalue_h_in_lvalue541);
                    lvalue_h();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,ID,FOLLOW_ID_in_lvalue543); if (state.failed) return ;

                    match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "lvalue"


    // $ANTLR start "lvalue_h"
    // TypeCheck.g:126:1: lvalue_h : ( ID | ^( DOT lvalue_h ID ) );
    public final void lvalue_h() throws RecognitionException {


        try {
            // TypeCheck.g:129:4: ( ID | ^( DOT lvalue_h ID ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ID) ) {
                alt12=1;
            }
            else if ( (LA12_0==DOT) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // TypeCheck.g:129:7: ID
                    {
                    match(input,ID,FOLLOW_ID_in_lvalue_h565); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // TypeCheck.g:130:6: ^( DOT lvalue_h ID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_lvalue_h574); if (state.failed) return ;

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    pushFollow(FOLLOW_lvalue_h_in_lvalue_h576);
                    lvalue_h();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,ID,FOLLOW_ID_in_lvalue_h578); if (state.failed) return ;

                    match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "lvalue_h"


    // $ANTLR start "print"
    // TypeCheck.g:133:1: print : ^( PRINT expression ( ENDL )? ) ;
    public final void print() throws RecognitionException {


        try {
            // TypeCheck.g:136:4: ( ^( PRINT expression ( ENDL )? ) )
            // TypeCheck.g:136:7: ^( PRINT expression ( ENDL )? )
            {
            match(input,PRINT,FOLLOW_PRINT_in_print602); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_print604);
            expression();

            state._fsp--;
            if (state.failed) return ;
            // TypeCheck.g:136:26: ( ENDL )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==ENDL) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // TypeCheck.g:136:27: ENDL
                    {
                    match(input,ENDL,FOLLOW_ENDL_in_print607); if (state.failed) return ;

                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "print"


    // $ANTLR start "read"
    // TypeCheck.g:139:1: read : ^( READ lvalue ) ;
    public final void read() throws RecognitionException {


        try {
            // TypeCheck.g:142:4: ( ^( READ lvalue ) )
            // TypeCheck.g:142:7: ^( READ lvalue )
            {
            match(input,READ,FOLLOW_READ_in_read632); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_lvalue_in_read634);
            lvalue();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "read"


    // $ANTLR start "conditional"
    // TypeCheck.g:145:1: conditional : ^( IF expression block ( block )? ) ;
    public final void conditional() throws RecognitionException {


        try {
            // TypeCheck.g:148:4: ( ^( IF expression block ( block )? ) )
            // TypeCheck.g:148:7: ^( IF expression block ( block )? )
            {
            match(input,IF,FOLLOW_IF_in_conditional657); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_conditional659);
            expression();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_block_in_conditional661);
            block();

            state._fsp--;
            if (state.failed) return ;
            // TypeCheck.g:148:29: ( block )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==BLOCK) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // TypeCheck.g:148:30: block
                    {
                    pushFollow(FOLLOW_block_in_conditional664);
                    block();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "conditional"


    // $ANTLR start "loop"
    // TypeCheck.g:151:1: loop : ^( WHILE expression block expression ) ;
    public final void loop() throws RecognitionException {


        try {
            // TypeCheck.g:154:4: ( ^( WHILE expression block expression ) )
            // TypeCheck.g:154:6: ^( WHILE expression block expression )
            {
            match(input,WHILE,FOLLOW_WHILE_in_loop688); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_loop690);
            expression();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_block_in_loop692);
            block();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_loop694);
            expression();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "loop"


    // $ANTLR start "delete"
    // TypeCheck.g:157:1: delete : ^( DELETE expression ) ;
    public final void delete() throws RecognitionException {


        try {
            // TypeCheck.g:160:4: ( ^( DELETE expression ) )
            // TypeCheck.g:160:6: ^( DELETE expression )
            {
            match(input,DELETE,FOLLOW_DELETE_in_delete716); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_delete718);
            expression();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "delete"


    // $ANTLR start "ret"
    // TypeCheck.g:163:1: ret : ^( RETURN ( expression )? ) ;
    public final void ret() throws RecognitionException {


        try {
            // TypeCheck.g:166:4: ( ^( RETURN ( expression )? ) )
            // TypeCheck.g:166:6: ^( RETURN ( expression )? )
            {
            match(input,RETURN,FOLLOW_RETURN_in_ret740); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                // TypeCheck.g:166:15: ( expression )?
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=TRUE && LA15_0<=NULL)||LA15_0==INVOKE||LA15_0==NEG||(LA15_0>=DOT && LA15_0<=INTEGER)) ) {
                    alt15=1;
                }
                switch (alt15) {
                    case 1 :
                        // TypeCheck.g:166:16: expression
                        {
                        pushFollow(FOLLOW_expression_in_ret743);
                        expression();

                        state._fsp--;
                        if (state.failed) return ;

                        }
                        break;

                }


                match(input, Token.UP, null); if (state.failed) return ;
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
    // $ANTLR end "ret"


    // $ANTLR start "invocation"
    // TypeCheck.g:169:1: invocation : ^( INVOKE ID arguments ) ;
    public final void invocation() throws RecognitionException {


        try {
            // TypeCheck.g:172:4: ( ^( INVOKE ID arguments ) )
            // TypeCheck.g:172:6: ^( INVOKE ID arguments )
            {
            match(input,INVOKE,FOLLOW_INVOKE_in_invocation767); if (state.failed) return ;

            match(input, Token.DOWN, null); if (state.failed) return ;
            match(input,ID,FOLLOW_ID_in_invocation769); if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_invocation771);
            arguments();

            state._fsp--;
            if (state.failed) return ;

            match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "invocation"


    // $ANTLR start "arguments"
    // TypeCheck.g:175:1: arguments : ^( ARGS ( expression )* ) ;
    public final void arguments() throws RecognitionException {


        try {
            // TypeCheck.g:178:4: ( ^( ARGS ( expression )* ) )
            // TypeCheck.g:178:6: ^( ARGS ( expression )* )
            {
            match(input,ARGS,FOLLOW_ARGS_in_arguments793); if (state.failed) return ;

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); if (state.failed) return ;
                // TypeCheck.g:178:13: ( expression )*
                loop16:
                do {
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0>=TRUE && LA16_0<=NULL)||LA16_0==INVOKE||LA16_0==NEG||(LA16_0>=DOT && LA16_0<=INTEGER)) ) {
                        alt16=1;
                    }


                    switch (alt16) {
                	case 1 :
                	    // TypeCheck.g:178:14: expression
                	    {
                	    pushFollow(FOLLOW_expression_in_arguments796);
                	    expression();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop16;
                    }
                } while (true);


                match(input, Token.UP, null); if (state.failed) return ;
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
    // $ANTLR end "arguments"


    // $ANTLR start "expression"
    // TypeCheck.g:182:1: expression : ( factor | ^( unop factor ) | ^( binop factor factor ) );
    public final void expression() throws RecognitionException {


        try {
            // TypeCheck.g:185:4: ( factor | ^( unop factor ) | ^( binop factor factor ) )
            int alt17=3;
            switch ( input.LA(1) ) {
            case TRUE:
            case FALSE:
            case NEW:
            case NULL:
            case INVOKE:
            case DOT:
            case ID:
            case INTEGER:
                {
                alt17=1;
                }
                break;
            case NEG:
            case NOT:
                {
                alt17=2;
                }
                break;
            case AND:
            case OR:
            case EQ:
            case LT:
            case GT:
            case NE:
            case LE:
            case GE:
            case PLUS:
            case MINUS:
            case TIMES:
            case DIVIDE:
                {
                alt17=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // TypeCheck.g:185:6: factor
                    {
                    pushFollow(FOLLOW_factor_in_expression820);
                    factor();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // TypeCheck.g:186:6: ^( unop factor )
                    {
                    pushFollow(FOLLOW_unop_in_expression828);
                    unop();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    pushFollow(FOLLOW_factor_in_expression830);
                    factor();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input, Token.UP, null); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // TypeCheck.g:187:6: ^( binop factor factor )
                    {
                    pushFollow(FOLLOW_binop_in_expression839);
                    binop();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    pushFollow(FOLLOW_factor_in_expression841);
                    factor();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_factor_in_expression843);
                    factor();

                    state._fsp--;
                    if (state.failed) return ;

                    match(input, Token.UP, null); if (state.failed) return ;

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
    // $ANTLR end "expression"


    // $ANTLR start "binop"
    // TypeCheck.g:190:1: binop : ( AND | OR | EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE ) ;
    public final void binop() throws RecognitionException {
        try {
            // TypeCheck.g:191:4: ( ( AND | OR | EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE ) )
            // TypeCheck.g:191:6: ( AND | OR | EQ | LT | GT | NE | LE | GE | PLUS | MINUS | TIMES | DIVIDE )
            {
            if ( (input.LA(1)>=AND && input.LA(1)<=DIVIDE) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "binop"


    // $ANTLR start "unop"
    // TypeCheck.g:194:1: unop : ( NOT | NEG ) ;
    public final void unop() throws RecognitionException {
        try {
            // TypeCheck.g:195:4: ( ( NOT | NEG ) )
            // TypeCheck.g:195:6: ( NOT | NEG )
            {
            if ( input.LA(1)==NEG||input.LA(1)==NOT ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
    // $ANTLR end "unop"


    // $ANTLR start "factor"
    // TypeCheck.g:198:1: factor : ( INTEGER | TRUE | FALSE | ^( NEW ID ) | NULL | ID | ^( DOT factor ID ) | invocation );
    public final void factor() throws RecognitionException {


        try {
            // TypeCheck.g:201:4: ( INTEGER | TRUE | FALSE | ^( NEW ID ) | NULL | ID | ^( DOT factor ID ) | invocation )
            int alt18=8;
            switch ( input.LA(1) ) {
            case INTEGER:
                {
                alt18=1;
                }
                break;
            case TRUE:
                {
                alt18=2;
                }
                break;
            case FALSE:
                {
                alt18=3;
                }
                break;
            case NEW:
                {
                alt18=4;
                }
                break;
            case NULL:
                {
                alt18=5;
                }
                break;
            case ID:
                {
                alt18=6;
                }
                break;
            case DOT:
                {
                alt18=7;
                }
                break;
            case INVOKE:
                {
                alt18=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // TypeCheck.g:201:6: INTEGER
                    {
                    match(input,INTEGER,FOLLOW_INTEGER_in_factor947); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // TypeCheck.g:202:6: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_factor954); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // TypeCheck.g:203:6: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_factor961); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // TypeCheck.g:204:6: ^( NEW ID )
                    {
                    match(input,NEW,FOLLOW_NEW_in_factor969); if (state.failed) return ;

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    match(input,ID,FOLLOW_ID_in_factor971); if (state.failed) return ;

                    match(input, Token.UP, null); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // TypeCheck.g:205:6: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_factor979); if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // TypeCheck.g:206:6: ID
                    {
                    match(input,ID,FOLLOW_ID_in_factor986); if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // TypeCheck.g:207:6: ^( DOT factor ID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_factor994); if (state.failed) return ;

                    match(input, Token.DOWN, null); if (state.failed) return ;
                    pushFollow(FOLLOW_factor_in_factor996);
                    factor();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,ID,FOLLOW_ID_in_factor998); if (state.failed) return ;

                    match(input, Token.UP, null); if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // TypeCheck.g:208:6: invocation
                    {
                    pushFollow(FOLLOW_invocation_in_factor1006);
                    invocation();

                    state._fsp--;
                    if (state.failed) return ;

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
    // $ANTLR end "factor"

    // Delegated rules


 

    public static final BitSet FOLLOW_PROGRAM_in_verify56 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_types_in_verify58 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_declarations_in_verify60 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_functions_in_verify62 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TYPES_in_types84 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_types_declaration_in_types87 = new BitSet(new long[]{0x0000000000000018L});
    public static final BitSet FOLLOW_STRUCT_in_types_declaration111 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_types_declaration113 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_var_decl_in_types_declaration116 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_DECL_in_var_decl140 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_var_decl143 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_var_decl145 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_var_decl148 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INT_in_type169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_type176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_type184 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_type186 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DECLS_in_declarations208 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declaration_in_declarations210 = new BitSet(new long[]{0x0000000008000008L});
    public static final BitSet FOLLOW_DECLLIST_in_declaration233 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TYPE_in_declaration236 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_declaration238 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_declaration242 = new BitSet(new long[]{0x0100000000000008L});
    public static final BitSet FOLLOW_FUNCS_in_functions266 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_function_in_functions268 = new BitSet(new long[]{0x0000000000000088L});
    public static final BitSet FOLLOW_FUN_in_function291 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_function293 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_parameters_in_function295 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_RETTYPE_in_function298 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_return_type_in_function300 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_declarations_in_function303 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_statement_list_in_function305 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_type_in_return_type326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_return_type333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARAMS_in_parameters354 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_var_decl_in_parameters356 = new BitSet(new long[]{0x0000000004000008L});
    public static final BitSet FOLLOW_STMTS_in_statement_list379 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_statement_list381 = new BitSet(new long[]{0x000002014001DA08L});
    public static final BitSet FOLLOW_block_in_statement403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_in_statement417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_read_in_statement424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_in_statement431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_in_statement438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_delete_in_statement445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ret_in_statement452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_invocation_in_statement459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block480 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_list_in_block482 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSIGN_in_assignment504 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_assignment506 = new BitSet(new long[]{0x0100040000000000L});
    public static final BitSet FOLLOW_lvalue_in_assignment508 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_lvalue530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lvalue539 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_lvalue_h_in_lvalue541 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_lvalue543 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_lvalue_h565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_lvalue_h574 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_lvalue_h_in_lvalue_h576 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_lvalue_h578 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PRINT_in_print602 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_print604 = new BitSet(new long[]{0x0000000000000408L});
    public static final BitSet FOLLOW_ENDL_in_print607 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_READ_in_read632 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_lvalue_in_read634 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_conditional657 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_conditional659 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_block_in_conditional661 = new BitSet(new long[]{0x0000000040000008L});
    public static final BitSet FOLLOW_block_in_conditional664 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_loop688 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_loop690 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_block_in_loop692 = new BitSet(new long[]{0x03FFFE05401FDA08L});
    public static final BitSet FOLLOW_expression_in_loop694 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DELETE_in_delete716 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_delete718 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RETURN_in_ret740 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_ret743 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INVOKE_in_invocation767 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_invocation769 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_arguments_in_invocation771 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARGS_in_arguments793 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_arguments796 = new BitSet(new long[]{0x03FFFE05401FDA08L});
    public static final BitSet FOLLOW_factor_in_expression820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unop_in_expression828 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_factor_in_expression830 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_binop_in_expression839 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_factor_in_expression841 = new BitSet(new long[]{0x03000601401FDA08L});
    public static final BitSet FOLLOW_factor_in_expression843 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_binop860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_unop921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_factor947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_factor954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_factor961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_factor969 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_factor971 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NULL_in_factor979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_factor986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_factor994 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_factor_in_factor996 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_factor998 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_invocation_in_factor1006 = new BitSet(new long[]{0x0000000000000002L});

}