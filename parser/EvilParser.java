// $ANTLR 3.3 Nov 30, 2010 12:50:56 Evil.g 2011-04-04 15:54:03

   /* package declaration here */


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class EvilParser extends Parser {
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


        public EvilParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public EvilParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return EvilParser.tokenNames; }
    public String getGrammarFileName() { return "Evil.g"; }


    public static class program_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "program"
    // Evil.g:101:1: program : t= types d= declarations f= functions EOF -> ^( PROGRAM $t $d $f) ;
    public final EvilParser.program_return program() throws RecognitionException {
        EvilParser.program_return retval = new EvilParser.program_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF1=null;
        EvilParser.types_return t = null;

        EvilParser.declarations_return d = null;

        EvilParser.functions_return f = null;


        Object EOF1_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_functions=new RewriteRuleSubtreeStream(adaptor,"rule functions");
        RewriteRuleSubtreeStream stream_types=new RewriteRuleSubtreeStream(adaptor,"rule types");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // Evil.g:102:4: (t= types d= declarations f= functions EOF -> ^( PROGRAM $t $d $f) )
            // Evil.g:102:7: t= types d= declarations f= functions EOF
            {
            pushFollow(FOLLOW_types_in_program1044);
            t=types();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_types.add(t.getTree());
            pushFollow(FOLLOW_declarations_in_program1048);
            d=declarations();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarations.add(d.getTree());
            pushFollow(FOLLOW_functions_in_program1052);
            f=functions();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_functions.add(f.getTree());
            EOF1=(Token)match(input,EOF,FOLLOW_EOF_in_program1054); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF1);



            // AST REWRITE
            // elements: t, f, d
            // token labels: 
            // rule labels: f, retval, d, t
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_f=new RewriteRuleSubtreeStream(adaptor,"rule f",f!=null?f.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);

            root_0 = (Object)adaptor.nil();
            // 103:7: -> ^( PROGRAM $t $d $f)
            {
                // Evil.g:103:10: ^( PROGRAM $t $d $f)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PROGRAM, "PROGRAM"), root_1);

                adaptor.addChild(root_1, stream_t.nextTree());
                adaptor.addChild(root_1, stream_d.nextTree());
                adaptor.addChild(root_1, stream_f.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "program"

    public static class types_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "types"
    // Evil.g:105:1: types : ( ( STRUCT ID LBRACE )=> types_sub -> ^( TYPES types_sub ) | -> TYPES );
    public final EvilParser.types_return types() throws RecognitionException {
        EvilParser.types_return retval = new EvilParser.types_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.types_sub_return types_sub2 = null;


        RewriteRuleSubtreeStream stream_types_sub=new RewriteRuleSubtreeStream(adaptor,"rule types_sub");
        try {
            // Evil.g:106:4: ( ( STRUCT ID LBRACE )=> types_sub -> ^( TYPES types_sub ) | -> TYPES )
            int alt1=2;
            switch ( input.LA(1) ) {
            case STRUCT:
                {
                int LA1_1 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
                }
                break;
            case INT:
                {
                int LA1_2 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 2, input);

                    throw nvae;
                }
                }
                break;
            case BOOL:
                {
                int LA1_3 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 3, input);

                    throw nvae;
                }
                }
                break;
            case FUN:
                {
                int LA1_4 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 4, input);

                    throw nvae;
                }
                }
                break;
            case EOF:
                {
                int LA1_5 = input.LA(2);

                if ( (synpred1_Evil()) ) {
                    alt1=1;
                }
                else if ( (true) ) {
                    alt1=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // Evil.g:106:7: ( STRUCT ID LBRACE )=> types_sub
                    {
                    pushFollow(FOLLOW_types_sub_in_types1100);
                    types_sub2=types_sub();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_types_sub.add(types_sub2.getTree());


                    // AST REWRITE
                    // elements: types_sub
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 106:39: -> ^( TYPES types_sub )
                    {
                        // Evil.g:106:42: ^( TYPES types_sub )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPES, "TYPES"), root_1);

                        adaptor.addChild(root_1, stream_types_sub.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // Evil.g:107:7: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 107:7: -> TYPES
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(TYPES, "TYPES"));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "types"

    public static class types_sub_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "types_sub"
    // Evil.g:109:1: types_sub : ( ( STRUCT ID LBRACE )=> type_declaration types_sub | );
    public final EvilParser.types_sub_return types_sub() throws RecognitionException {
        EvilParser.types_sub_return retval = new EvilParser.types_sub_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.type_declaration_return type_declaration3 = null;

        EvilParser.types_sub_return types_sub4 = null;



        try {
            // Evil.g:110:4: ( ( STRUCT ID LBRACE )=> type_declaration types_sub | )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==STRUCT) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==ID) ) {
                    int LA2_3 = input.LA(3);

                    if ( (LA2_3==LBRACE) && (synpred2_Evil())) {
                        alt2=1;
                    }
                    else if ( (LA2_3==ID) ) {
                        alt2=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA2_0==EOF||(LA2_0>=INT && LA2_0<=FUN)) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Evil.g:110:7: ( STRUCT ID LBRACE )=> type_declaration types_sub
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_type_declaration_in_types_sub1143);
                    type_declaration3=type_declaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type_declaration3.getTree());
                    pushFollow(FOLLOW_types_sub_in_types_sub1145);
                    types_sub4=types_sub();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, types_sub4.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:112:4: 
                    {
                    root_0 = (Object)adaptor.nil();

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "types_sub"

    public static class type_declaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type_declaration"
    // Evil.g:113:1: type_declaration : STRUCT ID LBRACE nested_decl RBRACE SEMI ;
    public final EvilParser.type_declaration_return type_declaration() throws RecognitionException {
        EvilParser.type_declaration_return retval = new EvilParser.type_declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRUCT5=null;
        Token ID6=null;
        Token LBRACE7=null;
        Token RBRACE9=null;
        Token SEMI10=null;
        EvilParser.nested_decl_return nested_decl8 = null;


        Object STRUCT5_tree=null;
        Object ID6_tree=null;
        Object LBRACE7_tree=null;
        Object RBRACE9_tree=null;
        Object SEMI10_tree=null;

        try {
            // Evil.g:114:4: ( STRUCT ID LBRACE nested_decl RBRACE SEMI )
            // Evil.g:114:7: STRUCT ID LBRACE nested_decl RBRACE SEMI
            {
            root_0 = (Object)adaptor.nil();

            STRUCT5=(Token)match(input,STRUCT,FOLLOW_STRUCT_in_type_declaration1165); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRUCT5_tree = (Object)adaptor.create(STRUCT5);
            root_0 = (Object)adaptor.becomeRoot(STRUCT5_tree, root_0);
            }
            ID6=(Token)match(input,ID,FOLLOW_ID_in_type_declaration1168); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID6_tree = (Object)adaptor.create(ID6);
            adaptor.addChild(root_0, ID6_tree);
            }
            LBRACE7=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_type_declaration1170); if (state.failed) return retval;
            pushFollow(FOLLOW_nested_decl_in_type_declaration1173);
            nested_decl8=nested_decl();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, nested_decl8.getTree());
            RBRACE9=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_type_declaration1175); if (state.failed) return retval;
            SEMI10=(Token)match(input,SEMI,FOLLOW_SEMI_in_type_declaration1178); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type_declaration"

    public static class nested_decl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "nested_decl"
    // Evil.g:116:1: nested_decl : ( decl SEMI )+ ;
    public final EvilParser.nested_decl_return nested_decl() throws RecognitionException {
        EvilParser.nested_decl_return retval = new EvilParser.nested_decl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMI12=null;
        EvilParser.decl_return decl11 = null;


        Object SEMI12_tree=null;

        try {
            // Evil.g:117:4: ( ( decl SEMI )+ )
            // Evil.g:117:7: ( decl SEMI )+
            {
            root_0 = (Object)adaptor.nil();

            // Evil.g:117:7: ( decl SEMI )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=STRUCT && LA3_0<=BOOL)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Evil.g:117:8: decl SEMI
            	    {
            	    pushFollow(FOLLOW_decl_in_nested_decl1195);
            	    decl11=decl();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, decl11.getTree());
            	    SEMI12=(Token)match(input,SEMI,FOLLOW_SEMI_in_nested_decl1197); if (state.failed) return retval;

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "nested_decl"

    public static class decl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decl"
    // Evil.g:119:1: decl : t= type i= ID -> ^( DECL ^( TYPE $t) $i) ;
    public final EvilParser.decl_return decl() throws RecognitionException {
        EvilParser.decl_return retval = new EvilParser.decl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token i=null;
        EvilParser.type_return t = null;


        Object i_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // Evil.g:120:4: (t= type i= ID -> ^( DECL ^( TYPE $t) $i) )
            // Evil.g:120:7: t= type i= ID
            {
            pushFollow(FOLLOW_type_in_decl1217);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(t.getTree());
            i=(Token)match(input,ID,FOLLOW_ID_in_decl1221); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(i);



            // AST REWRITE
            // elements: t, i
            // token labels: i
            // rule labels: retval, t
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_i=new RewriteRuleTokenStream(adaptor,"token i",i);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);

            root_0 = (Object)adaptor.nil();
            // 121:7: -> ^( DECL ^( TYPE $t) $i)
            {
                // Evil.g:121:10: ^( DECL ^( TYPE $t) $i)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECL, "DECL"), root_1);

                // Evil.g:121:17: ^( TYPE $t)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE, "TYPE"), root_2);

                adaptor.addChild(root_2, stream_t.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_i.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decl"

    public static class type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type"
    // Evil.g:123:1: type : ( INT | BOOL | STRUCT ID );
    public final EvilParser.type_return type() throws RecognitionException {
        EvilParser.type_return retval = new EvilParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INT13=null;
        Token BOOL14=null;
        Token STRUCT15=null;
        Token ID16=null;

        Object INT13_tree=null;
        Object BOOL14_tree=null;
        Object STRUCT15_tree=null;
        Object ID16_tree=null;

        try {
            // Evil.g:124:4: ( INT | BOOL | STRUCT ID )
            int alt4=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt4=1;
                }
                break;
            case BOOL:
                {
                alt4=2;
                }
                break;
            case STRUCT:
                {
                alt4=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Evil.g:124:7: INT
                    {
                    root_0 = (Object)adaptor.nil();

                    INT13=(Token)match(input,INT,FOLLOW_INT_in_type1258); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT13_tree = (Object)adaptor.create(INT13);
                    adaptor.addChild(root_0, INT13_tree);
                    }

                    }
                    break;
                case 2 :
                    // Evil.g:125:7: BOOL
                    {
                    root_0 = (Object)adaptor.nil();

                    BOOL14=(Token)match(input,BOOL,FOLLOW_BOOL_in_type1266); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOL14_tree = (Object)adaptor.create(BOOL14);
                    adaptor.addChild(root_0, BOOL14_tree);
                    }

                    }
                    break;
                case 3 :
                    // Evil.g:126:7: STRUCT ID
                    {
                    root_0 = (Object)adaptor.nil();

                    STRUCT15=(Token)match(input,STRUCT,FOLLOW_STRUCT_in_type1274); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRUCT15_tree = (Object)adaptor.create(STRUCT15);
                    root_0 = (Object)adaptor.becomeRoot(STRUCT15_tree, root_0);
                    }
                    ID16=(Token)match(input,ID,FOLLOW_ID_in_type1277); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID16_tree = (Object)adaptor.create(ID16);
                    adaptor.addChild(root_0, ID16_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type"

    public static class declarations_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declarations"
    // Evil.g:128:1: declarations : ( declaration )* -> ^( DECLS ( declaration )* ) ;
    public final EvilParser.declarations_return declarations() throws RecognitionException {
        EvilParser.declarations_return retval = new EvilParser.declarations_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.declaration_return declaration17 = null;


        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        try {
            // Evil.g:129:4: ( ( declaration )* -> ^( DECLS ( declaration )* ) )
            // Evil.g:129:7: ( declaration )*
            {
            // Evil.g:129:7: ( declaration )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=STRUCT && LA5_0<=BOOL)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Evil.g:129:8: declaration
            	    {
            	    pushFollow(FOLLOW_declaration_in_declarations1293);
            	    declaration17=declaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_declaration.add(declaration17.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);



            // AST REWRITE
            // elements: declaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 129:22: -> ^( DECLS ( declaration )* )
            {
                // Evil.g:129:25: ^( DECLS ( declaration )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLS, "DECLS"), root_1);

                // Evil.g:129:33: ( declaration )*
                while ( stream_declaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_declaration.nextTree());

                }
                stream_declaration.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declarations"

    public static class declaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declaration"
    // Evil.g:131:1: declaration : t= type ilist= id_list SEMI -> ^( DECLLIST ^( TYPE $t) $ilist) ;
    public final EvilParser.declaration_return declaration() throws RecognitionException {
        EvilParser.declaration_return retval = new EvilParser.declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMI18=null;
        EvilParser.type_return t = null;

        EvilParser.id_list_return ilist = null;


        Object SEMI18_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        RewriteRuleSubtreeStream stream_id_list=new RewriteRuleSubtreeStream(adaptor,"rule id_list");
        try {
            // Evil.g:132:4: (t= type ilist= id_list SEMI -> ^( DECLLIST ^( TYPE $t) $ilist) )
            // Evil.g:132:7: t= type ilist= id_list SEMI
            {
            pushFollow(FOLLOW_type_in_declaration1321);
            t=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(t.getTree());
            pushFollow(FOLLOW_id_list_in_declaration1325);
            ilist=id_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id_list.add(ilist.getTree());
            SEMI18=(Token)match(input,SEMI,FOLLOW_SEMI_in_declaration1327); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI18);



            // AST REWRITE
            // elements: ilist, t
            // token labels: 
            // rule labels: retval, t, ilist
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_t=new RewriteRuleSubtreeStream(adaptor,"rule t",t!=null?t.tree:null);
            RewriteRuleSubtreeStream stream_ilist=new RewriteRuleSubtreeStream(adaptor,"rule ilist",ilist!=null?ilist.tree:null);

            root_0 = (Object)adaptor.nil();
            // 133:7: -> ^( DECLLIST ^( TYPE $t) $ilist)
            {
                // Evil.g:133:10: ^( DECLLIST ^( TYPE $t) $ilist)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(DECLLIST, "DECLLIST"), root_1);

                // Evil.g:133:21: ^( TYPE $t)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE, "TYPE"), root_2);

                adaptor.addChild(root_2, stream_t.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_ilist.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declaration"

    public static class id_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "id_list"
    // Evil.g:135:1: id_list : ID ( COMMA ID )* ;
    public final EvilParser.id_list_return id_list() throws RecognitionException {
        EvilParser.id_list_return retval = new EvilParser.id_list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID19=null;
        Token COMMA20=null;
        Token ID21=null;

        Object ID19_tree=null;
        Object COMMA20_tree=null;
        Object ID21_tree=null;

        try {
            // Evil.g:136:4: ( ID ( COMMA ID )* )
            // Evil.g:136:7: ID ( COMMA ID )*
            {
            root_0 = (Object)adaptor.nil();

            ID19=(Token)match(input,ID,FOLLOW_ID_in_id_list1364); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID19_tree = (Object)adaptor.create(ID19);
            adaptor.addChild(root_0, ID19_tree);
            }
            // Evil.g:136:10: ( COMMA ID )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==COMMA) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Evil.g:136:11: COMMA ID
            	    {
            	    COMMA20=(Token)match(input,COMMA,FOLLOW_COMMA_in_id_list1367); if (state.failed) return retval;
            	    ID21=(Token)match(input,ID,FOLLOW_ID_in_id_list1370); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    ID21_tree = (Object)adaptor.create(ID21);
            	    adaptor.addChild(root_0, ID21_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "id_list"

    public static class functions_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "functions"
    // Evil.g:138:1: functions : ( function )* -> ^( FUNCS ( function )* ) ;
    public final EvilParser.functions_return functions() throws RecognitionException {
        EvilParser.functions_return retval = new EvilParser.functions_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.function_return function22 = null;


        RewriteRuleSubtreeStream stream_function=new RewriteRuleSubtreeStream(adaptor,"rule function");
        try {
            // Evil.g:139:4: ( ( function )* -> ^( FUNCS ( function )* ) )
            // Evil.g:139:7: ( function )*
            {
            // Evil.g:139:7: ( function )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==FUN) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // Evil.g:139:7: function
            	    {
            	    pushFollow(FOLLOW_function_in_functions1387);
            	    function22=function();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_function.add(function22.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);



            // AST REWRITE
            // elements: function
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 139:17: -> ^( FUNCS ( function )* )
            {
                // Evil.g:139:20: ^( FUNCS ( function )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNCS, "FUNCS"), root_1);

                // Evil.g:139:28: ( function )*
                while ( stream_function.hasNext() ) {
                    adaptor.addChild(root_1, stream_function.nextTree());

                }
                stream_function.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "functions"

    public static class function_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function"
    // Evil.g:141:1: function : FUN id= ID p= parameters r= return_type LBRACE d= declarations s= statement_list RBRACE -> ^( FUN $id $p ^( RETTYPE $r) $d $s) ;
    public final EvilParser.function_return function() throws RecognitionException {
        EvilParser.function_return retval = new EvilParser.function_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        Token FUN23=null;
        Token LBRACE24=null;
        Token RBRACE25=null;
        EvilParser.parameters_return p = null;

        EvilParser.return_type_return r = null;

        EvilParser.declarations_return d = null;

        EvilParser.statement_list_return s = null;


        Object id_tree=null;
        Object FUN23_tree=null;
        Object LBRACE24_tree=null;
        Object RBRACE25_tree=null;
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_FUN=new RewriteRuleTokenStream(adaptor,"token FUN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_statement_list=new RewriteRuleSubtreeStream(adaptor,"rule statement_list");
        RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
        RewriteRuleSubtreeStream stream_return_type=new RewriteRuleSubtreeStream(adaptor,"rule return_type");
        RewriteRuleSubtreeStream stream_declarations=new RewriteRuleSubtreeStream(adaptor,"rule declarations");
        try {
            // Evil.g:142:4: ( FUN id= ID p= parameters r= return_type LBRACE d= declarations s= statement_list RBRACE -> ^( FUN $id $p ^( RETTYPE $r) $d $s) )
            // Evil.g:142:7: FUN id= ID p= parameters r= return_type LBRACE d= declarations s= statement_list RBRACE
            {
            FUN23=(Token)match(input,FUN,FOLLOW_FUN_in_function1412); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_FUN.add(FUN23);

            id=(Token)match(input,ID,FOLLOW_ID_in_function1416); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            pushFollow(FOLLOW_parameters_in_function1420);
            p=parameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_parameters.add(p.getTree());
            pushFollow(FOLLOW_return_type_in_function1424);
            r=return_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_return_type.add(r.getTree());
            LBRACE24=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_function1426); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE24);

            pushFollow(FOLLOW_declarations_in_function1430);
            d=declarations();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declarations.add(d.getTree());
            pushFollow(FOLLOW_statement_list_in_function1434);
            s=statement_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statement_list.add(s.getTree());
            RBRACE25=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_function1436); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE25);



            // AST REWRITE
            // elements: d, r, s, p, id, FUN
            // token labels: id
            // rule labels: retval, d, s, r, p
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.tree:null);
            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);
            RewriteRuleSubtreeStream stream_r=new RewriteRuleSubtreeStream(adaptor,"rule r",r!=null?r.tree:null);
            RewriteRuleSubtreeStream stream_p=new RewriteRuleSubtreeStream(adaptor,"rule p",p!=null?p.tree:null);

            root_0 = (Object)adaptor.nil();
            // 143:7: -> ^( FUN $id $p ^( RETTYPE $r) $d $s)
            {
                // Evil.g:143:10: ^( FUN $id $p ^( RETTYPE $r) $d $s)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_FUN.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextNode());
                adaptor.addChild(root_1, stream_p.nextTree());
                // Evil.g:143:23: ^( RETTYPE $r)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(RETTYPE, "RETTYPE"), root_2);

                adaptor.addChild(root_2, stream_r.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_d.nextTree());
                adaptor.addChild(root_1, stream_s.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function"

    public static class parameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameters"
    // Evil.g:145:1: parameters : LPAREN ( decl ( COMMA decl )* )? RPAREN -> ^( PARAMS ( decl )* ) ;
    public final EvilParser.parameters_return parameters() throws RecognitionException {
        EvilParser.parameters_return retval = new EvilParser.parameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN26=null;
        Token COMMA28=null;
        Token RPAREN30=null;
        EvilParser.decl_return decl27 = null;

        EvilParser.decl_return decl29 = null;


        Object LPAREN26_tree=null;
        Object COMMA28_tree=null;
        Object RPAREN30_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_decl=new RewriteRuleSubtreeStream(adaptor,"rule decl");
        try {
            // Evil.g:146:4: ( LPAREN ( decl ( COMMA decl )* )? RPAREN -> ^( PARAMS ( decl )* ) )
            // Evil.g:146:7: LPAREN ( decl ( COMMA decl )* )? RPAREN
            {
            LPAREN26=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_parameters1482); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN26);

            // Evil.g:146:14: ( decl ( COMMA decl )* )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=STRUCT && LA9_0<=BOOL)) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Evil.g:146:15: decl ( COMMA decl )*
                    {
                    pushFollow(FOLLOW_decl_in_parameters1485);
                    decl27=decl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_decl.add(decl27.getTree());
                    // Evil.g:146:20: ( COMMA decl )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==COMMA) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // Evil.g:146:21: COMMA decl
                    	    {
                    	    COMMA28=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters1488); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA28);

                    	    pushFollow(FOLLOW_decl_in_parameters1490);
                    	    decl29=decl();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_decl.add(decl29.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    }
                    break;

            }

            RPAREN30=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_parameters1496); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN30);



            // AST REWRITE
            // elements: decl
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 147:7: -> ^( PARAMS ( decl )* )
            {
                // Evil.g:147:10: ^( PARAMS ( decl )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PARAMS, "PARAMS"), root_1);

                // Evil.g:147:19: ( decl )*
                while ( stream_decl.hasNext() ) {
                    adaptor.addChild(root_1, stream_decl.nextTree());

                }
                stream_decl.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parameters"

    public static class return_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "return_type"
    // Evil.g:149:1: return_type : ( type | VOID );
    public final EvilParser.return_type_return return_type() throws RecognitionException {
        EvilParser.return_type_return retval = new EvilParser.return_type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VOID32=null;
        EvilParser.type_return type31 = null;


        Object VOID32_tree=null;

        try {
            // Evil.g:150:4: ( type | VOID )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=STRUCT && LA10_0<=BOOL)) ) {
                alt10=1;
            }
            else if ( (LA10_0==VOID) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // Evil.g:150:7: type
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_type_in_return_type1526);
                    type31=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type31.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:151:7: VOID
                    {
                    root_0 = (Object)adaptor.nil();

                    VOID32=(Token)match(input,VOID,FOLLOW_VOID_in_return_type1534); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VOID32_tree = (Object)adaptor.create(VOID32);
                    adaptor.addChild(root_0, VOID32_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "return_type"

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // Evil.g:153:1: statement : ( block | ( lvalue ASSIGN )=> assignment | print | read | conditional | loop | delete | ret | ( ID LPAREN )=> invocation );
    public final EvilParser.statement_return statement() throws RecognitionException {
        EvilParser.statement_return retval = new EvilParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.block_return block33 = null;

        EvilParser.assignment_return assignment34 = null;

        EvilParser.print_return print35 = null;

        EvilParser.read_return read36 = null;

        EvilParser.conditional_return conditional37 = null;

        EvilParser.loop_return loop38 = null;

        EvilParser.delete_return delete39 = null;

        EvilParser.ret_return ret40 = null;

        EvilParser.invocation_return invocation41 = null;



        try {
            // Evil.g:154:4: ( block | ( lvalue ASSIGN )=> assignment | print | read | conditional | loop | delete | ret | ( ID LPAREN )=> invocation )
            int alt11=9;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // Evil.g:154:7: block
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_block_in_statement1549);
                    block33=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block33.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:155:7: ( lvalue ASSIGN )=> assignment
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_assignment_in_statement1565);
                    assignment34=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignment34.getTree());

                    }
                    break;
                case 3 :
                    // Evil.g:156:7: print
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_print_in_statement1573);
                    print35=print();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, print35.getTree());

                    }
                    break;
                case 4 :
                    // Evil.g:157:7: read
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_read_in_statement1581);
                    read36=read();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, read36.getTree());

                    }
                    break;
                case 5 :
                    // Evil.g:158:7: conditional
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_conditional_in_statement1589);
                    conditional37=conditional();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditional37.getTree());

                    }
                    break;
                case 6 :
                    // Evil.g:159:7: loop
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_loop_in_statement1597);
                    loop38=loop();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, loop38.getTree());

                    }
                    break;
                case 7 :
                    // Evil.g:160:7: delete
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_delete_in_statement1605);
                    delete39=delete();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, delete39.getTree());

                    }
                    break;
                case 8 :
                    // Evil.g:161:7: ret
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ret_in_statement1613);
                    ret40=ret();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ret40.getTree());

                    }
                    break;
                case 9 :
                    // Evil.g:162:7: ( ID LPAREN )=> invocation
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_invocation_in_statement1629);
                    invocation41=invocation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, invocation41.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class block_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // Evil.g:164:1: block : LBRACE s= statement_list RBRACE -> ^( BLOCK $s) ;
    public final EvilParser.block_return block() throws RecognitionException {
        EvilParser.block_return retval = new EvilParser.block_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACE42=null;
        Token RBRACE43=null;
        EvilParser.statement_list_return s = null;


        Object LBRACE42_tree=null;
        Object RBRACE43_tree=null;
        RewriteRuleTokenStream stream_RBRACE=new RewriteRuleTokenStream(adaptor,"token RBRACE");
        RewriteRuleTokenStream stream_LBRACE=new RewriteRuleTokenStream(adaptor,"token LBRACE");
        RewriteRuleSubtreeStream stream_statement_list=new RewriteRuleSubtreeStream(adaptor,"rule statement_list");
        try {
            // Evil.g:165:4: ( LBRACE s= statement_list RBRACE -> ^( BLOCK $s) )
            // Evil.g:165:7: LBRACE s= statement_list RBRACE
            {
            LBRACE42=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_block1644); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACE.add(LBRACE42);

            pushFollow(FOLLOW_statement_list_in_block1648);
            s=statement_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statement_list.add(s.getTree());
            RBRACE43=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_block1650); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACE.add(RBRACE43);



            // AST REWRITE
            // elements: s
            // token labels: 
            // rule labels: retval, s
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

            root_0 = (Object)adaptor.nil();
            // 166:7: -> ^( BLOCK $s)
            {
                // Evil.g:166:10: ^( BLOCK $s)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BLOCK, "BLOCK"), root_1);

                adaptor.addChild(root_1, stream_s.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class statement_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement_list"
    // Evil.g:168:1: statement_list : ( statement )* -> ^( STMTS ( statement )* ) ;
    public final EvilParser.statement_list_return statement_list() throws RecognitionException {
        EvilParser.statement_list_return retval = new EvilParser.statement_list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        EvilParser.statement_return statement44 = null;


        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // Evil.g:169:4: ( ( statement )* -> ^( STMTS ( statement )* ) )
            // Evil.g:169:7: ( statement )*
            {
            // Evil.g:169:7: ( statement )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==PRINT||(LA12_0>=READ && LA12_0<=IF)||(LA12_0>=WHILE && LA12_0<=RETURN)||LA12_0==LBRACE||LA12_0==ID) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // Evil.g:169:8: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_statement_list1681);
            	    statement44=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement44.getTree());

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);



            // AST REWRITE
            // elements: statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 169:20: -> ^( STMTS ( statement )* )
            {
                // Evil.g:169:23: ^( STMTS ( statement )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STMTS, "STMTS"), root_1);

                // Evil.g:169:31: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_1, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement_list"

    public static class assignment_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignment"
    // Evil.g:171:1: assignment : l= lvalue ASSIGN e= expression SEMI -> ^( ASSIGN $e $l) ;
    public final EvilParser.assignment_return assignment() throws RecognitionException {
        EvilParser.assignment_return retval = new EvilParser.assignment_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ASSIGN45=null;
        Token SEMI46=null;
        EvilParser.lvalue_return l = null;

        EvilParser.expression_return e = null;


        Object ASSIGN45_tree=null;
        Object SEMI46_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_lvalue=new RewriteRuleSubtreeStream(adaptor,"rule lvalue");
        try {
            // Evil.g:172:4: (l= lvalue ASSIGN e= expression SEMI -> ^( ASSIGN $e $l) )
            // Evil.g:172:7: l= lvalue ASSIGN e= expression SEMI
            {
            pushFollow(FOLLOW_lvalue_in_assignment1709);
            l=lvalue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_lvalue.add(l.getTree());
            ASSIGN45=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignment1711); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN45);

            pushFollow(FOLLOW_expression_in_assignment1715);
            e=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(e.getTree());
            SEMI46=(Token)match(input,SEMI,FOLLOW_SEMI_in_assignment1717); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI46);



            // AST REWRITE
            // elements: e, l, ASSIGN
            // token labels: 
            // rule labels: retval, e, l
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);
            RewriteRuleSubtreeStream stream_l=new RewriteRuleSubtreeStream(adaptor,"rule l",l!=null?l.tree:null);

            root_0 = (Object)adaptor.nil();
            // 173:7: -> ^( ASSIGN $e $l)
            {
                // Evil.g:173:10: ^( ASSIGN $e $l)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_ASSIGN.nextNode(), root_1);

                adaptor.addChild(root_1, stream_e.nextTree());
                adaptor.addChild(root_1, stream_l.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "assignment"

    public static class print_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "print"
    // Evil.g:175:1: print : PRINT expression ( ENDL )? SEMI ;
    public final EvilParser.print_return print() throws RecognitionException {
        EvilParser.print_return retval = new EvilParser.print_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PRINT47=null;
        Token ENDL49=null;
        Token SEMI50=null;
        EvilParser.expression_return expression48 = null;


        Object PRINT47_tree=null;
        Object ENDL49_tree=null;
        Object SEMI50_tree=null;

        try {
            // Evil.g:176:4: ( PRINT expression ( ENDL )? SEMI )
            // Evil.g:176:7: PRINT expression ( ENDL )? SEMI
            {
            root_0 = (Object)adaptor.nil();

            PRINT47=(Token)match(input,PRINT,FOLLOW_PRINT_in_print1750); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PRINT47_tree = (Object)adaptor.create(PRINT47);
            root_0 = (Object)adaptor.becomeRoot(PRINT47_tree, root_0);
            }
            pushFollow(FOLLOW_expression_in_print1753);
            expression48=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression48.getTree());
            // Evil.g:176:25: ( ENDL )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==ENDL) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Evil.g:176:26: ENDL
                    {
                    ENDL49=(Token)match(input,ENDL,FOLLOW_ENDL_in_print1756); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ENDL49_tree = (Object)adaptor.create(ENDL49);
                    adaptor.addChild(root_0, ENDL49_tree);
                    }

                    }
                    break;

            }

            SEMI50=(Token)match(input,SEMI,FOLLOW_SEMI_in_print1760); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "print"

    public static class read_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "read"
    // Evil.g:178:1: read : READ lvalue SEMI ;
    public final EvilParser.read_return read() throws RecognitionException {
        EvilParser.read_return retval = new EvilParser.read_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token READ51=null;
        Token SEMI53=null;
        EvilParser.lvalue_return lvalue52 = null;


        Object READ51_tree=null;
        Object SEMI53_tree=null;

        try {
            // Evil.g:179:4: ( READ lvalue SEMI )
            // Evil.g:179:7: READ lvalue SEMI
            {
            root_0 = (Object)adaptor.nil();

            READ51=(Token)match(input,READ,FOLLOW_READ_in_read1776); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            READ51_tree = (Object)adaptor.create(READ51);
            root_0 = (Object)adaptor.becomeRoot(READ51_tree, root_0);
            }
            pushFollow(FOLLOW_lvalue_in_read1779);
            lvalue52=lvalue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, lvalue52.getTree());
            SEMI53=(Token)match(input,SEMI,FOLLOW_SEMI_in_read1781); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "read"

    public static class conditional_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "conditional"
    // Evil.g:181:1: conditional : IF LPAREN expression RPAREN block ( ELSE block )? ;
    public final EvilParser.conditional_return conditional() throws RecognitionException {
        EvilParser.conditional_return retval = new EvilParser.conditional_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IF54=null;
        Token LPAREN55=null;
        Token RPAREN57=null;
        Token ELSE59=null;
        EvilParser.expression_return expression56 = null;

        EvilParser.block_return block58 = null;

        EvilParser.block_return block60 = null;


        Object IF54_tree=null;
        Object LPAREN55_tree=null;
        Object RPAREN57_tree=null;
        Object ELSE59_tree=null;

        try {
            // Evil.g:182:4: ( IF LPAREN expression RPAREN block ( ELSE block )? )
            // Evil.g:182:7: IF LPAREN expression RPAREN block ( ELSE block )?
            {
            root_0 = (Object)adaptor.nil();

            IF54=(Token)match(input,IF,FOLLOW_IF_in_conditional1797); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF54_tree = (Object)adaptor.create(IF54);
            root_0 = (Object)adaptor.becomeRoot(IF54_tree, root_0);
            }
            LPAREN55=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_conditional1800); if (state.failed) return retval;
            pushFollow(FOLLOW_expression_in_conditional1803);
            expression56=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression56.getTree());
            RPAREN57=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_conditional1805); if (state.failed) return retval;
            pushFollow(FOLLOW_block_in_conditional1808);
            block58=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block58.getTree());
            // Evil.g:182:44: ( ELSE block )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==ELSE) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // Evil.g:182:45: ELSE block
                    {
                    ELSE59=(Token)match(input,ELSE,FOLLOW_ELSE_in_conditional1811); if (state.failed) return retval;
                    pushFollow(FOLLOW_block_in_conditional1814);
                    block60=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block60.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "conditional"

    public static class loop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "loop"
    // Evil.g:184:1: loop : WHILE LPAREN e= expression RPAREN b= block -> ^( WHILE $e $b $e) ;
    public final EvilParser.loop_return loop() throws RecognitionException {
        EvilParser.loop_return retval = new EvilParser.loop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WHILE61=null;
        Token LPAREN62=null;
        Token RPAREN63=null;
        EvilParser.expression_return e = null;

        EvilParser.block_return b = null;


        Object WHILE61_tree=null;
        Object LPAREN62_tree=null;
        Object RPAREN63_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_WHILE=new RewriteRuleTokenStream(adaptor,"token WHILE");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // Evil.g:185:4: ( WHILE LPAREN e= expression RPAREN b= block -> ^( WHILE $e $b $e) )
            // Evil.g:185:7: WHILE LPAREN e= expression RPAREN b= block
            {
            WHILE61=(Token)match(input,WHILE,FOLLOW_WHILE_in_loop1831); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_WHILE.add(WHILE61);

            LPAREN62=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_loop1833); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN62);

            pushFollow(FOLLOW_expression_in_loop1837);
            e=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(e.getTree());
            RPAREN63=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_loop1839); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN63);

            pushFollow(FOLLOW_block_in_loop1843);
            b=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(b.getTree());


            // AST REWRITE
            // elements: WHILE, e, b, e
            // token labels: 
            // rule labels: retval, e, b
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e=new RewriteRuleSubtreeStream(adaptor,"rule e",e!=null?e.tree:null);
            RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"rule b",b!=null?b.tree:null);

            root_0 = (Object)adaptor.nil();
            // 186:7: -> ^( WHILE $e $b $e)
            {
                // Evil.g:186:10: ^( WHILE $e $b $e)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_WHILE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_e.nextTree());
                adaptor.addChild(root_1, stream_b.nextTree());
                adaptor.addChild(root_1, stream_e.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "loop"

    public static class delete_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "delete"
    // Evil.g:188:1: delete : DELETE expression SEMI ;
    public final EvilParser.delete_return delete() throws RecognitionException {
        EvilParser.delete_return retval = new EvilParser.delete_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DELETE64=null;
        Token SEMI66=null;
        EvilParser.expression_return expression65 = null;


        Object DELETE64_tree=null;
        Object SEMI66_tree=null;

        try {
            // Evil.g:189:4: ( DELETE expression SEMI )
            // Evil.g:189:7: DELETE expression SEMI
            {
            root_0 = (Object)adaptor.nil();

            DELETE64=(Token)match(input,DELETE,FOLLOW_DELETE_in_delete1879); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DELETE64_tree = (Object)adaptor.create(DELETE64);
            root_0 = (Object)adaptor.becomeRoot(DELETE64_tree, root_0);
            }
            pushFollow(FOLLOW_expression_in_delete1882);
            expression65=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression65.getTree());
            SEMI66=(Token)match(input,SEMI,FOLLOW_SEMI_in_delete1884); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "delete"

    public static class ret_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ret"
    // Evil.g:191:1: ret : RETURN ( expression )? SEMI ;
    public final EvilParser.ret_return ret() throws RecognitionException {
        EvilParser.ret_return retval = new EvilParser.ret_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token RETURN67=null;
        Token SEMI69=null;
        EvilParser.expression_return expression68 = null;


        Object RETURN67_tree=null;
        Object SEMI69_tree=null;

        try {
            // Evil.g:192:4: ( RETURN ( expression )? SEMI )
            // Evil.g:192:7: RETURN ( expression )? SEMI
            {
            root_0 = (Object)adaptor.nil();

            RETURN67=(Token)match(input,RETURN,FOLLOW_RETURN_in_ret1900); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RETURN67_tree = (Object)adaptor.create(RETURN67);
            root_0 = (Object)adaptor.becomeRoot(RETURN67_tree, root_0);
            }
            // Evil.g:192:15: ( expression )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=TRUE && LA15_0<=NULL)||LA15_0==LPAREN||LA15_0==MINUS||(LA15_0>=NOT && LA15_0<=INTEGER)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // Evil.g:192:16: expression
                    {
                    pushFollow(FOLLOW_expression_in_ret1904);
                    expression68=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression68.getTree());

                    }
                    break;

            }

            SEMI69=(Token)match(input,SEMI,FOLLOW_SEMI_in_ret1908); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ret"

    public static class invocation_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "invocation"
    // Evil.g:194:1: invocation : id= ID a= arguments SEMI -> ^( INVOKE $id $a) ;
    public final EvilParser.invocation_return invocation() throws RecognitionException {
        EvilParser.invocation_return retval = new EvilParser.invocation_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        Token SEMI70=null;
        EvilParser.arguments_return a = null;


        Object id_tree=null;
        Object SEMI70_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_arguments=new RewriteRuleSubtreeStream(adaptor,"rule arguments");
        try {
            // Evil.g:195:4: (id= ID a= arguments SEMI -> ^( INVOKE $id $a) )
            // Evil.g:195:7: id= ID a= arguments SEMI
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_invocation1926); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            pushFollow(FOLLOW_arguments_in_invocation1930);
            a=arguments();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_arguments.add(a.getTree());
            SEMI70=(Token)match(input,SEMI,FOLLOW_SEMI_in_invocation1932); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(SEMI70);



            // AST REWRITE
            // elements: a, id
            // token labels: id
            // rule labels: retval, a
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

            root_0 = (Object)adaptor.nil();
            // 196:7: -> ^( INVOKE $id $a)
            {
                // Evil.g:196:10: ^( INVOKE $id $a)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INVOKE, "INVOKE"), root_1);

                adaptor.addChild(root_1, stream_id.nextNode());
                adaptor.addChild(root_1, stream_a.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "invocation"

    public static class lvalue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lvalue"
    // Evil.g:198:1: lvalue : ID ( DOT ID )* ;
    public final EvilParser.lvalue_return lvalue() throws RecognitionException {
        EvilParser.lvalue_return retval = new EvilParser.lvalue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID71=null;
        Token DOT72=null;
        Token ID73=null;

        Object ID71_tree=null;
        Object DOT72_tree=null;
        Object ID73_tree=null;

        try {
            // Evil.g:199:4: ( ID ( DOT ID )* )
            // Evil.g:199:7: ID ( DOT ID )*
            {
            root_0 = (Object)adaptor.nil();

            ID71=(Token)match(input,ID,FOLLOW_ID_in_lvalue1965); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID71_tree = (Object)adaptor.create(ID71);
            adaptor.addChild(root_0, ID71_tree);
            }
            // Evil.g:199:10: ( DOT ID )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==DOT) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // Evil.g:199:11: DOT ID
            	    {
            	    DOT72=(Token)match(input,DOT,FOLLOW_DOT_in_lvalue1968); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOT72_tree = (Object)adaptor.create(DOT72);
            	    root_0 = (Object)adaptor.becomeRoot(DOT72_tree, root_0);
            	    }
            	    ID73=(Token)match(input,ID,FOLLOW_ID_in_lvalue1971); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    ID73_tree = (Object)adaptor.create(ID73);
            	    adaptor.addChild(root_0, ID73_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lvalue"

    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // Evil.g:201:1: expression : boolterm ( ( AND | OR ) boolterm )* ;
    public final EvilParser.expression_return expression() throws RecognitionException {
        EvilParser.expression_return retval = new EvilParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AND75=null;
        Token OR76=null;
        EvilParser.boolterm_return boolterm74 = null;

        EvilParser.boolterm_return boolterm77 = null;


        Object AND75_tree=null;
        Object OR76_tree=null;

        try {
            // Evil.g:202:4: ( boolterm ( ( AND | OR ) boolterm )* )
            // Evil.g:202:7: boolterm ( ( AND | OR ) boolterm )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_boolterm_in_expression1988);
            boolterm74=boolterm();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm74.getTree());
            // Evil.g:202:16: ( ( AND | OR ) boolterm )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=AND && LA18_0<=OR)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // Evil.g:202:17: ( AND | OR ) boolterm
            	    {
            	    // Evil.g:202:17: ( AND | OR )
            	    int alt17=2;
            	    int LA17_0 = input.LA(1);

            	    if ( (LA17_0==AND) ) {
            	        alt17=1;
            	    }
            	    else if ( (LA17_0==OR) ) {
            	        alt17=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 17, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt17) {
            	        case 1 :
            	            // Evil.g:202:18: AND
            	            {
            	            AND75=(Token)match(input,AND,FOLLOW_AND_in_expression1992); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            AND75_tree = (Object)adaptor.create(AND75);
            	            root_0 = (Object)adaptor.becomeRoot(AND75_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Evil.g:202:25: OR
            	            {
            	            OR76=(Token)match(input,OR,FOLLOW_OR_in_expression1997); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            OR76_tree = (Object)adaptor.create(OR76);
            	            root_0 = (Object)adaptor.becomeRoot(OR76_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_boolterm_in_expression2001);
            	    boolterm77=boolterm();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm77.getTree());

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class boolterm_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "boolterm"
    // Evil.g:204:1: boolterm : simple ( ( EQ | LT | GT | NE | LE | GE ) simple )? ;
    public final EvilParser.boolterm_return boolterm() throws RecognitionException {
        EvilParser.boolterm_return retval = new EvilParser.boolterm_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ79=null;
        Token LT80=null;
        Token GT81=null;
        Token NE82=null;
        Token LE83=null;
        Token GE84=null;
        EvilParser.simple_return simple78 = null;

        EvilParser.simple_return simple85 = null;


        Object EQ79_tree=null;
        Object LT80_tree=null;
        Object GT81_tree=null;
        Object NE82_tree=null;
        Object LE83_tree=null;
        Object GE84_tree=null;

        try {
            // Evil.g:205:4: ( simple ( ( EQ | LT | GT | NE | LE | GE ) simple )? )
            // Evil.g:205:7: simple ( ( EQ | LT | GT | NE | LE | GE ) simple )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_simple_in_boolterm2018);
            simple78=simple();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, simple78.getTree());
            // Evil.g:205:14: ( ( EQ | LT | GT | NE | LE | GE ) simple )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=EQ && LA20_0<=GE)) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // Evil.g:205:15: ( EQ | LT | GT | NE | LE | GE ) simple
                    {
                    // Evil.g:205:15: ( EQ | LT | GT | NE | LE | GE )
                    int alt19=6;
                    switch ( input.LA(1) ) {
                    case EQ:
                        {
                        alt19=1;
                        }
                        break;
                    case LT:
                        {
                        alt19=2;
                        }
                        break;
                    case GT:
                        {
                        alt19=3;
                        }
                        break;
                    case NE:
                        {
                        alt19=4;
                        }
                        break;
                    case LE:
                        {
                        alt19=5;
                        }
                        break;
                    case GE:
                        {
                        alt19=6;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 19, 0, input);

                        throw nvae;
                    }

                    switch (alt19) {
                        case 1 :
                            // Evil.g:205:16: EQ
                            {
                            EQ79=(Token)match(input,EQ,FOLLOW_EQ_in_boolterm2022); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            EQ79_tree = (Object)adaptor.create(EQ79);
                            root_0 = (Object)adaptor.becomeRoot(EQ79_tree, root_0);
                            }

                            }
                            break;
                        case 2 :
                            // Evil.g:205:22: LT
                            {
                            LT80=(Token)match(input,LT,FOLLOW_LT_in_boolterm2027); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            LT80_tree = (Object)adaptor.create(LT80);
                            root_0 = (Object)adaptor.becomeRoot(LT80_tree, root_0);
                            }

                            }
                            break;
                        case 3 :
                            // Evil.g:205:28: GT
                            {
                            GT81=(Token)match(input,GT,FOLLOW_GT_in_boolterm2032); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            GT81_tree = (Object)adaptor.create(GT81);
                            root_0 = (Object)adaptor.becomeRoot(GT81_tree, root_0);
                            }

                            }
                            break;
                        case 4 :
                            // Evil.g:205:34: NE
                            {
                            NE82=(Token)match(input,NE,FOLLOW_NE_in_boolterm2037); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            NE82_tree = (Object)adaptor.create(NE82);
                            root_0 = (Object)adaptor.becomeRoot(NE82_tree, root_0);
                            }

                            }
                            break;
                        case 5 :
                            // Evil.g:205:40: LE
                            {
                            LE83=(Token)match(input,LE,FOLLOW_LE_in_boolterm2042); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            LE83_tree = (Object)adaptor.create(LE83);
                            root_0 = (Object)adaptor.becomeRoot(LE83_tree, root_0);
                            }

                            }
                            break;
                        case 6 :
                            // Evil.g:205:46: GE
                            {
                            GE84=(Token)match(input,GE,FOLLOW_GE_in_boolterm2047); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            GE84_tree = (Object)adaptor.create(GE84);
                            root_0 = (Object)adaptor.becomeRoot(GE84_tree, root_0);
                            }

                            }
                            break;

                    }

                    pushFollow(FOLLOW_simple_in_boolterm2051);
                    simple85=simple();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple85.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "boolterm"

    public static class simple_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "simple"
    // Evil.g:207:1: simple : term ( ( PLUS | MINUS ) term )* ;
    public final EvilParser.simple_return simple() throws RecognitionException {
        EvilParser.simple_return retval = new EvilParser.simple_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PLUS87=null;
        Token MINUS88=null;
        EvilParser.term_return term86 = null;

        EvilParser.term_return term89 = null;


        Object PLUS87_tree=null;
        Object MINUS88_tree=null;

        try {
            // Evil.g:208:4: ( term ( ( PLUS | MINUS ) term )* )
            // Evil.g:208:7: term ( ( PLUS | MINUS ) term )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_term_in_simple2068);
            term86=term();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, term86.getTree());
            // Evil.g:208:12: ( ( PLUS | MINUS ) term )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=PLUS && LA22_0<=MINUS)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // Evil.g:208:13: ( PLUS | MINUS ) term
            	    {
            	    // Evil.g:208:13: ( PLUS | MINUS )
            	    int alt21=2;
            	    int LA21_0 = input.LA(1);

            	    if ( (LA21_0==PLUS) ) {
            	        alt21=1;
            	    }
            	    else if ( (LA21_0==MINUS) ) {
            	        alt21=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 21, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt21) {
            	        case 1 :
            	            // Evil.g:208:14: PLUS
            	            {
            	            PLUS87=(Token)match(input,PLUS,FOLLOW_PLUS_in_simple2072); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            PLUS87_tree = (Object)adaptor.create(PLUS87);
            	            root_0 = (Object)adaptor.becomeRoot(PLUS87_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Evil.g:208:22: MINUS
            	            {
            	            MINUS88=(Token)match(input,MINUS,FOLLOW_MINUS_in_simple2077); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            MINUS88_tree = (Object)adaptor.create(MINUS88);
            	            root_0 = (Object)adaptor.becomeRoot(MINUS88_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_term_in_simple2081);
            	    term89=term();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, term89.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "simple"

    public static class term_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "term"
    // Evil.g:210:1: term : unary ( ( TIMES | DIVIDE ) unary )* ;
    public final EvilParser.term_return term() throws RecognitionException {
        EvilParser.term_return retval = new EvilParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TIMES91=null;
        Token DIVIDE92=null;
        EvilParser.unary_return unary90 = null;

        EvilParser.unary_return unary93 = null;


        Object TIMES91_tree=null;
        Object DIVIDE92_tree=null;

        try {
            // Evil.g:211:4: ( unary ( ( TIMES | DIVIDE ) unary )* )
            // Evil.g:211:7: unary ( ( TIMES | DIVIDE ) unary )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unary_in_term2098);
            unary90=unary();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unary90.getTree());
            // Evil.g:211:13: ( ( TIMES | DIVIDE ) unary )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=TIMES && LA24_0<=DIVIDE)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // Evil.g:211:14: ( TIMES | DIVIDE ) unary
            	    {
            	    // Evil.g:211:14: ( TIMES | DIVIDE )
            	    int alt23=2;
            	    int LA23_0 = input.LA(1);

            	    if ( (LA23_0==TIMES) ) {
            	        alt23=1;
            	    }
            	    else if ( (LA23_0==DIVIDE) ) {
            	        alt23=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 23, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt23) {
            	        case 1 :
            	            // Evil.g:211:15: TIMES
            	            {
            	            TIMES91=(Token)match(input,TIMES,FOLLOW_TIMES_in_term2102); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            TIMES91_tree = (Object)adaptor.create(TIMES91);
            	            root_0 = (Object)adaptor.becomeRoot(TIMES91_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Evil.g:211:24: DIVIDE
            	            {
            	            DIVIDE92=(Token)match(input,DIVIDE,FOLLOW_DIVIDE_in_term2107); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            DIVIDE92_tree = (Object)adaptor.create(DIVIDE92);
            	            root_0 = (Object)adaptor.becomeRoot(DIVIDE92_tree, root_0);
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_unary_in_term2111);
            	    unary93=unary();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unary93.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "term"

    public static class unary_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unary"
    // Evil.g:213:1: unary : ( NOT odd_not | MINUS odd_neg | selector );
    public final EvilParser.unary_return unary() throws RecognitionException {
        EvilParser.unary_return retval = new EvilParser.unary_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NOT94=null;
        Token MINUS96=null;
        EvilParser.odd_not_return odd_not95 = null;

        EvilParser.odd_neg_return odd_neg97 = null;

        EvilParser.selector_return selector98 = null;


        Object NOT94_tree=null;
        Object MINUS96_tree=null;

        try {
            // Evil.g:214:4: ( NOT odd_not | MINUS odd_neg | selector )
            int alt25=3;
            switch ( input.LA(1) ) {
            case NOT:
                {
                alt25=1;
                }
                break;
            case MINUS:
                {
                alt25=2;
                }
                break;
            case TRUE:
            case FALSE:
            case NEW:
            case NULL:
            case LPAREN:
            case ID:
            case INTEGER:
                {
                alt25=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // Evil.g:214:7: NOT odd_not
                    {
                    root_0 = (Object)adaptor.nil();

                    NOT94=(Token)match(input,NOT,FOLLOW_NOT_in_unary2128); if (state.failed) return retval;
                    pushFollow(FOLLOW_odd_not_in_unary2131);
                    odd_not95=odd_not();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_not95.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:215:7: MINUS odd_neg
                    {
                    root_0 = (Object)adaptor.nil();

                    MINUS96=(Token)match(input,MINUS,FOLLOW_MINUS_in_unary2139); if (state.failed) return retval;
                    pushFollow(FOLLOW_odd_neg_in_unary2142);
                    odd_neg97=odd_neg();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_neg97.getTree());

                    }
                    break;
                case 3 :
                    // Evil.g:216:7: selector
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_selector_in_unary2150);
                    selector98=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, selector98.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "unary"

    public static class odd_not_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "odd_not"
    // Evil.g:218:1: odd_not : ( NOT even_not | s= selector -> ^( NOT $s) );
    public final EvilParser.odd_not_return odd_not() throws RecognitionException {
        EvilParser.odd_not_return retval = new EvilParser.odd_not_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NOT99=null;
        EvilParser.selector_return s = null;

        EvilParser.even_not_return even_not100 = null;


        Object NOT99_tree=null;
        RewriteRuleSubtreeStream stream_selector=new RewriteRuleSubtreeStream(adaptor,"rule selector");
        try {
            // Evil.g:219:4: ( NOT even_not | s= selector -> ^( NOT $s) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NOT) ) {
                alt26=1;
            }
            else if ( ((LA26_0>=TRUE && LA26_0<=NULL)||LA26_0==LPAREN||(LA26_0>=ID && LA26_0<=INTEGER)) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // Evil.g:219:7: NOT even_not
                    {
                    root_0 = (Object)adaptor.nil();

                    NOT99=(Token)match(input,NOT,FOLLOW_NOT_in_odd_not2165); if (state.failed) return retval;
                    pushFollow(FOLLOW_even_not_in_odd_not2168);
                    even_not100=even_not();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, even_not100.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:220:7: s= selector
                    {
                    pushFollow(FOLLOW_selector_in_odd_not2178);
                    s=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_selector.add(s.getTree());


                    // AST REWRITE
                    // elements: s
                    // token labels: 
                    // rule labels: retval, s
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 221:7: -> ^( NOT $s)
                    {
                        // Evil.g:221:10: ^( NOT $s)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NOT, "NOT"), root_1);

                        adaptor.addChild(root_1, stream_s.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "odd_not"

    public static class even_not_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "even_not"
    // Evil.g:223:1: even_not : ( NOT odd_not | selector );
    public final EvilParser.even_not_return even_not() throws RecognitionException {
        EvilParser.even_not_return retval = new EvilParser.even_not_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NOT101=null;
        EvilParser.odd_not_return odd_not102 = null;

        EvilParser.selector_return selector103 = null;


        Object NOT101_tree=null;

        try {
            // Evil.g:224:4: ( NOT odd_not | selector )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==NOT) ) {
                alt27=1;
            }
            else if ( ((LA27_0>=TRUE && LA27_0<=NULL)||LA27_0==LPAREN||(LA27_0>=ID && LA27_0<=INTEGER)) ) {
                alt27=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // Evil.g:224:7: NOT odd_not
                    {
                    root_0 = (Object)adaptor.nil();

                    NOT101=(Token)match(input,NOT,FOLLOW_NOT_in_even_not2208); if (state.failed) return retval;
                    pushFollow(FOLLOW_odd_not_in_even_not2211);
                    odd_not102=odd_not();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_not102.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:225:7: selector
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_selector_in_even_not2219);
                    selector103=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, selector103.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "even_not"

    public static class odd_neg_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "odd_neg"
    // Evil.g:227:1: odd_neg : ( MINUS even_neg | s= selector -> ^( NEG $s) );
    public final EvilParser.odd_neg_return odd_neg() throws RecognitionException {
        EvilParser.odd_neg_return retval = new EvilParser.odd_neg_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS104=null;
        EvilParser.selector_return s = null;

        EvilParser.even_neg_return even_neg105 = null;


        Object MINUS104_tree=null;
        RewriteRuleSubtreeStream stream_selector=new RewriteRuleSubtreeStream(adaptor,"rule selector");
        try {
            // Evil.g:228:4: ( MINUS even_neg | s= selector -> ^( NEG $s) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==MINUS) ) {
                alt28=1;
            }
            else if ( ((LA28_0>=TRUE && LA28_0<=NULL)||LA28_0==LPAREN||(LA28_0>=ID && LA28_0<=INTEGER)) ) {
                alt28=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // Evil.g:228:7: MINUS even_neg
                    {
                    root_0 = (Object)adaptor.nil();

                    MINUS104=(Token)match(input,MINUS,FOLLOW_MINUS_in_odd_neg2234); if (state.failed) return retval;
                    pushFollow(FOLLOW_even_neg_in_odd_neg2237);
                    even_neg105=even_neg();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, even_neg105.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:229:7: s= selector
                    {
                    pushFollow(FOLLOW_selector_in_odd_neg2247);
                    s=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_selector.add(s.getTree());


                    // AST REWRITE
                    // elements: s
                    // token labels: 
                    // rule labels: retval, s
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_s=new RewriteRuleSubtreeStream(adaptor,"rule s",s!=null?s.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 230:7: -> ^( NEG $s)
                    {
                        // Evil.g:230:10: ^( NEG $s)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NEG, "NEG"), root_1);

                        adaptor.addChild(root_1, stream_s.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "odd_neg"

    public static class even_neg_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "even_neg"
    // Evil.g:232:1: even_neg : ( MINUS odd_neg | selector );
    public final EvilParser.even_neg_return even_neg() throws RecognitionException {
        EvilParser.even_neg_return retval = new EvilParser.even_neg_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS106=null;
        EvilParser.odd_neg_return odd_neg107 = null;

        EvilParser.selector_return selector108 = null;


        Object MINUS106_tree=null;

        try {
            // Evil.g:233:4: ( MINUS odd_neg | selector )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==MINUS) ) {
                alt29=1;
            }
            else if ( ((LA29_0>=TRUE && LA29_0<=NULL)||LA29_0==LPAREN||(LA29_0>=ID && LA29_0<=INTEGER)) ) {
                alt29=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // Evil.g:233:7: MINUS odd_neg
                    {
                    root_0 = (Object)adaptor.nil();

                    MINUS106=(Token)match(input,MINUS,FOLLOW_MINUS_in_even_neg2277); if (state.failed) return retval;
                    pushFollow(FOLLOW_odd_neg_in_even_neg2280);
                    odd_neg107=odd_neg();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, odd_neg107.getTree());

                    }
                    break;
                case 2 :
                    // Evil.g:234:7: selector
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_selector_in_even_neg2288);
                    selector108=selector();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, selector108.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "even_neg"

    public static class selector_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "selector"
    // Evil.g:236:1: selector : factor ( DOT ID )* ;
    public final EvilParser.selector_return selector() throws RecognitionException {
        EvilParser.selector_return retval = new EvilParser.selector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOT110=null;
        Token ID111=null;
        EvilParser.factor_return factor109 = null;


        Object DOT110_tree=null;
        Object ID111_tree=null;

        try {
            // Evil.g:237:4: ( factor ( DOT ID )* )
            // Evil.g:237:7: factor ( DOT ID )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_factor_in_selector2303);
            factor109=factor();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, factor109.getTree());
            // Evil.g:237:14: ( DOT ID )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==DOT) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // Evil.g:237:15: DOT ID
            	    {
            	    DOT110=(Token)match(input,DOT,FOLLOW_DOT_in_selector2306); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOT110_tree = (Object)adaptor.create(DOT110);
            	    root_0 = (Object)adaptor.becomeRoot(DOT110_tree, root_0);
            	    }
            	    ID111=(Token)match(input,ID,FOLLOW_ID_in_selector2309); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    ID111_tree = (Object)adaptor.create(ID111);
            	    adaptor.addChild(root_0, ID111_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "selector"

    public static class factor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "factor"
    // Evil.g:239:1: factor : ( LPAREN expression RPAREN | id= ID a= arguments -> ^( INVOKE $id $a) | ID | INTEGER | TRUE | FALSE | NEW ID | NULL );
    public final EvilParser.factor_return factor() throws RecognitionException {
        EvilParser.factor_return retval = new EvilParser.factor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        Token LPAREN112=null;
        Token RPAREN114=null;
        Token ID115=null;
        Token INTEGER116=null;
        Token TRUE117=null;
        Token FALSE118=null;
        Token NEW119=null;
        Token ID120=null;
        Token NULL121=null;
        EvilParser.arguments_return a = null;

        EvilParser.expression_return expression113 = null;


        Object id_tree=null;
        Object LPAREN112_tree=null;
        Object RPAREN114_tree=null;
        Object ID115_tree=null;
        Object INTEGER116_tree=null;
        Object TRUE117_tree=null;
        Object FALSE118_tree=null;
        Object NEW119_tree=null;
        Object ID120_tree=null;
        Object NULL121_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_arguments=new RewriteRuleSubtreeStream(adaptor,"rule arguments");
        try {
            // Evil.g:240:4: ( LPAREN expression RPAREN | id= ID a= arguments -> ^( INVOKE $id $a) | ID | INTEGER | TRUE | FALSE | NEW ID | NULL )
            int alt31=8;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // Evil.g:240:7: LPAREN expression RPAREN
                    {
                    root_0 = (Object)adaptor.nil();

                    LPAREN112=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_factor2326); if (state.failed) return retval;
                    pushFollow(FOLLOW_expression_in_factor2329);
                    expression113=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression113.getTree());
                    RPAREN114=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_factor2331); if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // Evil.g:241:7: id= ID a= arguments
                    {
                    id=(Token)match(input,ID,FOLLOW_ID_in_factor2342); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(id);

                    pushFollow(FOLLOW_arguments_in_factor2346);
                    a=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_arguments.add(a.getTree());


                    // AST REWRITE
                    // elements: id, a
                    // token labels: id
                    // rule labels: retval, a
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_a=new RewriteRuleSubtreeStream(adaptor,"rule a",a!=null?a.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 242:7: -> ^( INVOKE $id $a)
                    {
                        // Evil.g:242:10: ^( INVOKE $id $a)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(INVOKE, "INVOKE"), root_1);

                        adaptor.addChild(root_1, stream_id.nextNode());
                        adaptor.addChild(root_1, stream_a.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // Evil.g:243:7: ID
                    {
                    root_0 = (Object)adaptor.nil();

                    ID115=(Token)match(input,ID,FOLLOW_ID_in_factor2372); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID115_tree = (Object)adaptor.create(ID115);
                    adaptor.addChild(root_0, ID115_tree);
                    }

                    }
                    break;
                case 4 :
                    // Evil.g:244:7: INTEGER
                    {
                    root_0 = (Object)adaptor.nil();

                    INTEGER116=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_factor2380); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INTEGER116_tree = (Object)adaptor.create(INTEGER116);
                    adaptor.addChild(root_0, INTEGER116_tree);
                    }

                    }
                    break;
                case 5 :
                    // Evil.g:245:7: TRUE
                    {
                    root_0 = (Object)adaptor.nil();

                    TRUE117=(Token)match(input,TRUE,FOLLOW_TRUE_in_factor2388); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE117_tree = (Object)adaptor.create(TRUE117);
                    adaptor.addChild(root_0, TRUE117_tree);
                    }

                    }
                    break;
                case 6 :
                    // Evil.g:246:7: FALSE
                    {
                    root_0 = (Object)adaptor.nil();

                    FALSE118=(Token)match(input,FALSE,FOLLOW_FALSE_in_factor2396); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE118_tree = (Object)adaptor.create(FALSE118);
                    adaptor.addChild(root_0, FALSE118_tree);
                    }

                    }
                    break;
                case 7 :
                    // Evil.g:247:7: NEW ID
                    {
                    root_0 = (Object)adaptor.nil();

                    NEW119=(Token)match(input,NEW,FOLLOW_NEW_in_factor2404); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NEW119_tree = (Object)adaptor.create(NEW119);
                    root_0 = (Object)adaptor.becomeRoot(NEW119_tree, root_0);
                    }
                    ID120=(Token)match(input,ID,FOLLOW_ID_in_factor2407); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID120_tree = (Object)adaptor.create(ID120);
                    adaptor.addChild(root_0, ID120_tree);
                    }

                    }
                    break;
                case 8 :
                    // Evil.g:248:7: NULL
                    {
                    root_0 = (Object)adaptor.nil();

                    NULL121=(Token)match(input,NULL,FOLLOW_NULL_in_factor2415); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NULL121_tree = (Object)adaptor.create(NULL121);
                    adaptor.addChild(root_0, NULL121_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "factor"

    public static class arguments_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arguments"
    // Evil.g:250:1: arguments : LPAREN arg_list RPAREN ;
    public final EvilParser.arguments_return arguments() throws RecognitionException {
        EvilParser.arguments_return retval = new EvilParser.arguments_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN122=null;
        Token RPAREN124=null;
        EvilParser.arg_list_return arg_list123 = null;


        Object LPAREN122_tree=null;
        Object RPAREN124_tree=null;

        try {
            // Evil.g:251:4: ( LPAREN arg_list RPAREN )
            // Evil.g:251:7: LPAREN arg_list RPAREN
            {
            root_0 = (Object)adaptor.nil();

            LPAREN122=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_arguments2430); if (state.failed) return retval;
            pushFollow(FOLLOW_arg_list_in_arguments2433);
            arg_list123=arg_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, arg_list123.getTree());
            RPAREN124=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_arguments2435); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arguments"

    public static class arg_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arg_list"
    // Evil.g:253:1: arg_list : ( expression ( COMMA expression )* -> ^( ARGS ( expression )+ ) | -> ARGS );
    public final EvilParser.arg_list_return arg_list() throws RecognitionException {
        EvilParser.arg_list_return retval = new EvilParser.arg_list_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA126=null;
        EvilParser.expression_return expression125 = null;

        EvilParser.expression_return expression127 = null;


        Object COMMA126_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // Evil.g:254:4: ( expression ( COMMA expression )* -> ^( ARGS ( expression )+ ) | -> ARGS )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=TRUE && LA33_0<=NULL)||LA33_0==LPAREN||LA33_0==MINUS||(LA33_0>=NOT && LA33_0<=INTEGER)) ) {
                alt33=1;
            }
            else if ( (LA33_0==RPAREN) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // Evil.g:254:7: expression ( COMMA expression )*
                    {
                    pushFollow(FOLLOW_expression_in_arg_list2451);
                    expression125=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression125.getTree());
                    // Evil.g:254:18: ( COMMA expression )*
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==COMMA) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // Evil.g:254:19: COMMA expression
                    	    {
                    	    COMMA126=(Token)match(input,COMMA,FOLLOW_COMMA_in_arg_list2454); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA126);

                    	    pushFollow(FOLLOW_expression_in_arg_list2456);
                    	    expression127=expression();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_expression.add(expression127.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop32;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 255:7: -> ^( ARGS ( expression )+ )
                    {
                        // Evil.g:255:10: ^( ARGS ( expression )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGS, "ARGS"), root_1);

                        if ( !(stream_expression.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                        }
                        stream_expression.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // Evil.g:257:7: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 257:7: -> ARGS
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(ARGS, "ARGS"));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arg_list"

    // $ANTLR start synpred1_Evil
    public final void synpred1_Evil_fragment() throws RecognitionException {   
        // Evil.g:106:7: ( STRUCT ID LBRACE )
        // Evil.g:106:8: STRUCT ID LBRACE
        {
        match(input,STRUCT,FOLLOW_STRUCT_in_synpred1_Evil1091); if (state.failed) return ;
        match(input,ID,FOLLOW_ID_in_synpred1_Evil1093); if (state.failed) return ;
        match(input,LBRACE,FOLLOW_LBRACE_in_synpred1_Evil1095); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_Evil

    // $ANTLR start synpred2_Evil
    public final void synpred2_Evil_fragment() throws RecognitionException {   
        // Evil.g:110:7: ( STRUCT ID LBRACE )
        // Evil.g:110:8: STRUCT ID LBRACE
        {
        match(input,STRUCT,FOLLOW_STRUCT_in_synpred2_Evil1134); if (state.failed) return ;
        match(input,ID,FOLLOW_ID_in_synpred2_Evil1136); if (state.failed) return ;
        match(input,LBRACE,FOLLOW_LBRACE_in_synpred2_Evil1138); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_Evil

    // $ANTLR start synpred3_Evil
    public final void synpred3_Evil_fragment() throws RecognitionException {   
        // Evil.g:155:7: ( lvalue ASSIGN )
        // Evil.g:155:8: lvalue ASSIGN
        {
        pushFollow(FOLLOW_lvalue_in_synpred3_Evil1558);
        lvalue();

        state._fsp--;
        if (state.failed) return ;
        match(input,ASSIGN,FOLLOW_ASSIGN_in_synpred3_Evil1560); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_Evil

    // $ANTLR start synpred4_Evil
    public final void synpred4_Evil_fragment() throws RecognitionException {   
        // Evil.g:162:7: ( ID LPAREN )
        // Evil.g:162:8: ID LPAREN
        {
        match(input,ID,FOLLOW_ID_in_synpred4_Evil1622); if (state.failed) return ;
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred4_Evil1624); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_Evil

    // Delegated rules

    public final boolean synpred4_Evil() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_Evil_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_Evil() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Evil_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_Evil() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_Evil_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_Evil() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_Evil_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA31 dfa31 = new DFA31(this);
    static final String DFA11_eotS =
        "\14\uffff";
    static final String DFA11_eofS =
        "\14\uffff";
    static final String DFA11_minS =
        "\1\11\1\uffff\1\47\11\uffff";
    static final String DFA11_maxS =
        "\1\70\1\uffff\1\52\11\uffff";
    static final String DFA11_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\1\4\1\5\1\6\1\7\1\10\2\2\1\11";
    static final String DFA11_specialS =
        "\2\uffff\1\0\11\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\3\1\uffff\1\4\1\5\1\uffff\1\6\1\7\1\10\22\uffff\1\1\24\uffff"+
            "\1\2",
            "",
            "\1\13\1\uffff\1\12\1\11",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "153:1: statement : ( block | ( lvalue ASSIGN )=> assignment | print | read | conditional | loop | delete | ret | ( ID LPAREN )=> invocation );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA11_2 = input.LA(1);

                         
                        int index11_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA11_2==DOT) && (synpred3_Evil())) {s = 9;}

                        else if ( (LA11_2==ASSIGN) && (synpred3_Evil())) {s = 10;}

                        else if ( (LA11_2==LPAREN) && (synpred4_Evil())) {s = 11;}

                         
                        input.seek(index11_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 11, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA31_eotS =
        "\12\uffff";
    static final String DFA31_eofS =
        "\12\uffff";
    static final String DFA31_minS =
        "\1\21\1\uffff\1\12\7\uffff";
    static final String DFA31_maxS =
        "\1\71\1\uffff\1\66\7\uffff";
    static final String DFA31_acceptS =
        "\1\uffff\1\1\1\uffff\1\4\1\5\1\6\1\7\1\10\1\2\1\3";
    static final String DFA31_specialS =
        "\12\uffff}>";
    static final String[] DFA31_transitionS = {
            "\1\4\1\5\1\6\1\7\22\uffff\1\1\20\uffff\1\2\1\3",
            "",
            "\1\11\32\uffff\2\11\1\10\1\11\1\uffff\15\11",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "239:1: factor : ( LPAREN expression RPAREN | id= ID a= arguments -> ^( INVOKE $id $a) | ID | INTEGER | TRUE | FALSE | NEW ID | NULL );";
        }
    }
 

    public static final BitSet FOLLOW_types_in_program1044 = new BitSet(new long[]{0x00000000000000F0L});
    public static final BitSet FOLLOW_declarations_in_program1048 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_functions_in_program1052 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_program1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_types_sub_in_types1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_declaration_in_types_sub1143 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_types_sub_in_types_sub1145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_type_declaration1165 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_type_declaration1168 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACE_in_type_declaration1170 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_nested_decl_in_type_declaration1173 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACE_in_type_declaration1175 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_type_declaration1178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decl_in_nested_decl1195 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_nested_decl1197 = new BitSet(new long[]{0x0000000000000072L});
    public static final BitSet FOLLOW_type_in_decl1217 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_decl1221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_type1258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_type1266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_type1274 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_type1277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_declarations1293 = new BitSet(new long[]{0x0000000000000072L});
    public static final BitSet FOLLOW_type_in_declaration1321 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_id_list_in_declaration1325 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_declaration1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_id_list1364 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_COMMA_in_id_list1367 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_id_list1370 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_function_in_functions1387 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_FUN_in_function1412 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_function1416 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_parameters_in_function1420 = new BitSet(new long[]{0x0000000000000170L});
    public static final BitSet FOLLOW_return_type_in_function1424 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACE_in_function1426 = new BitSet(new long[]{0x010000180001DA70L});
    public static final BitSet FOLLOW_declarations_in_function1430 = new BitSet(new long[]{0x010000180001DA00L});
    public static final BitSet FOLLOW_statement_list_in_function1434 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACE_in_function1436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parameters1482 = new BitSet(new long[]{0x0000010000000070L});
    public static final BitSet FOLLOW_decl_in_parameters1485 = new BitSet(new long[]{0x0000014000000000L});
    public static final BitSet FOLLOW_COMMA_in_parameters1488 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_decl_in_parameters1490 = new BitSet(new long[]{0x0000014000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parameters1496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_return_type1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_return_type1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement1565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_in_statement1573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_read_in_statement1581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditional_in_statement1589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loop_in_statement1597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_delete_in_statement1605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ret_in_statement1613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_invocation_in_statement1629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_block1644 = new BitSet(new long[]{0x010000180001DA00L});
    public static final BitSet FOLLOW_statement_list_in_block1648 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RBRACE_in_block1650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statement_list1681 = new BitSet(new long[]{0x010000080001DA02L});
    public static final BitSet FOLLOW_lvalue_in_assignment1709 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_assignment1711 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_assignment1715 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_assignment1717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_print1750 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_print1753 = new BitSet(new long[]{0x0000002000000400L});
    public static final BitSet FOLLOW_ENDL_in_print1756 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_print1760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_READ_in_read1776 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_lvalue_in_read1779 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_read1781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_conditional1797 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_LPAREN_in_conditional1800 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_conditional1803 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_RPAREN_in_conditional1805 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_block_in_conditional1808 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_ELSE_in_conditional1811 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_block_in_conditional1814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_loop1831 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_LPAREN_in_loop1833 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_loop1837 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_RPAREN_in_loop1839 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_block_in_loop1843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DELETE_in_delete1879 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_delete1882 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_delete1884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_ret1900 = new BitSet(new long[]{0x039000A0001E0000L});
    public static final BitSet FOLLOW_expression_in_ret1904 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_ret1908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_invocation1926 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_arguments_in_invocation1930 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_SEMI_in_invocation1932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_lvalue1965 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_DOT_in_lvalue1968 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_lvalue1971 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_boolterm_in_expression1988 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_AND_in_expression1992 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_OR_in_expression1997 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_boolterm_in_expression2001 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_simple_in_boolterm2018 = new BitSet(new long[]{0x0007E00000000002L});
    public static final BitSet FOLLOW_EQ_in_boolterm2022 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_LT_in_boolterm2027 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_GT_in_boolterm2032 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_NE_in_boolterm2037 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_LE_in_boolterm2042 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_GE_in_boolterm2047 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_simple_in_boolterm2051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_simple2068 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_PLUS_in_simple2072 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_MINUS_in_simple2077 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_term_in_simple2081 = new BitSet(new long[]{0x0018000000000002L});
    public static final BitSet FOLLOW_unary_in_term2098 = new BitSet(new long[]{0x0060000000000002L});
    public static final BitSet FOLLOW_TIMES_in_term2102 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_DIVIDE_in_term2107 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_unary_in_term2111 = new BitSet(new long[]{0x0060000000000002L});
    public static final BitSet FOLLOW_NOT_in_unary2128 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_odd_not_in_unary2131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_unary2139 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_odd_neg_in_unary2142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_unary2150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_odd_not2165 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_even_not_in_odd_not2168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_odd_not2178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_even_not2208 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_odd_not_in_even_not2211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_even_not2219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_odd_neg2234 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_even_neg_in_odd_neg2237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_odd_neg2247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_even_neg2277 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_odd_neg_in_even_neg2280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_even_neg2288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_selector2303 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_DOT_in_selector2306 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_selector2309 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_LPAREN_in_factor2326 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_factor2329 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_RPAREN_in_factor2331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_factor2342 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_arguments_in_factor2346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_factor2372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_factor2380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_factor2388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_factor2396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEW_in_factor2404 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_factor2407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_factor2415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_arguments2430 = new BitSet(new long[]{0x03900180001E0000L});
    public static final BitSet FOLLOW_arg_list_in_arguments2433 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_RPAREN_in_arguments2435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_arg_list2451 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_COMMA_in_arg_list2454 = new BitSet(new long[]{0x03900080001E0000L});
    public static final BitSet FOLLOW_expression_in_arg_list2456 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_STRUCT_in_synpred1_Evil1091 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_synpred1_Evil1093 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACE_in_synpred1_Evil1095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCT_in_synpred2_Evil1134 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_synpred2_Evil1136 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LBRACE_in_synpred2_Evil1138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lvalue_in_synpred3_Evil1558 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_synpred3_Evil1560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred4_Evil1622 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred4_Evil1624 = new BitSet(new long[]{0x0000000000000002L});

}