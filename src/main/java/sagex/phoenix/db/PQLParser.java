/* Generated By:JavaCC: Do not edit this line. PQLParser.java */
package sagex.phoenix.db;

import java.io.Reader;
import java.io.StringReader;

import org.apache.log4j.Logger;

import sagex.phoenix.vfs.filters.IResourceFilter;

public class PQLParser implements PQLParserConstants {
    private PQL query = new PQL();
    private static Logger log = Logger.getLogger(PQLParser.class);

    public PQLParser(String s) {
        this((Reader) (new StringReader(logQuery(s))));
    }

    private static String logQuery(String q) {
        log.info("Parsing PQL Query: " + q);
        return q;
    }

    public PQL getQuery() {
        return query;
    }

    public IResourceFilter getFilter() {
        return query.getFilter();
    }

    /**
     * Top level
     */
    final public void parse() throws ParseException {
        expression();
        jj_consume_token(0);
    }

    /**
     * An expression is defined to be a queryTerm followed by zero or more query
     * terms joined by either an AND or an OR. If two query terms are joined
     * with AND then both conditions must be met. If two query terms are joined
     * with an OR, then one of the two conditions must be met.
     */
    final public void expression() throws ParseException {
        queryTerm();
        label_1:
        while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case AND:
                case OR:
                    ;
                    break;
                default:
                    jj_la1[0] = jj_gen;
                    break label_1;
            }
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case AND:
                    jj_consume_token(AND);
                    query.and();
                    break;
                case OR:
                    jj_consume_token(OR);
                    query.or();
                    break;
                default:
                    jj_la1[1] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
            queryTerm();
        }
    }

    /**
     * Query terms may consist of a parenthetically separated expression or may
     * be a query criteria of the form queryName = something or queryName <>
     * something.
     */
    final public void queryTerm() throws ParseException {
        Token lhs, op, rhs;
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case STRING:
                lhs = jj_consume_token(STRING);
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case EQUALS:
                        op = jj_consume_token(EQUALS);
                        break;
                    case NOTEQUAL:
                        op = jj_consume_token(NOTEQUAL);
                        break;
                    case LESSTHAN:
                        op = jj_consume_token(LESSTHAN);
                        break;
                    case GREATERTHAN:
                        op = jj_consume_token(GREATERTHAN);
                        break;
                    case CONTAINS:
                        op = jj_consume_token(CONTAINS);
                        break;
                    case IS_NOT_NULL:
                        op = jj_consume_token(IS_NOT_NULL);
                        break;
                    case IS_NULL:
                        op = jj_consume_token(IS_NULL);
                        break;
                    default:
                        jj_la1[2] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case STRING:
                        rhs = jj_consume_token(STRING);
                        break;
                    case QUOTED_STRING:
                        rhs = jj_consume_token(QUOTED_STRING);
                        rhs.image = rhs.image.substring(1, rhs.image.length() - 1);
                        break;
                    case SQUOTED_STRING:
                        rhs = jj_consume_token(SQUOTED_STRING);
                        rhs.image = rhs.image.substring(1, rhs.image.length() - 1);
                        break;
                    default:
                        jj_la1[3] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
                query.field(lhs.image, op.image, rhs.image);
                break;
            case LPAREN:
                jj_consume_token(LPAREN);
                query.begingroup();
                expression();
                jj_consume_token(RPAREN);
                query.endgroup();
                break;
            default:
                jj_la1[4] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /**
     * Generated Token Manager.
     */
    public PQLParserTokenManager token_source;
    SimpleCharStream jj_input_stream;
    /**
     * Current token.
     */
    public Token token;
    /**
     * Next token.
     */
    public Token jj_nt;
    private int jj_ntk;
    private int jj_gen;
    final private int[] jj_la1 = new int[5];
    static private int[] jj_la1_0;

    static {
        jj_la1_init_0();
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{0x60, 0x60, 0xfe00, 0x70000, 0x10080,};
    }

    /**
     * Constructor with InputStream.
     */
    public PQLParser(java.io.InputStream stream) {
        this(stream, null);
    }

    /**
     * Constructor with InputStream and supplied encoding
     */
    public PQLParser(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source = new PQLParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++)
            jj_la1[i] = -1;
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream) {
        ReInit(stream, null);
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream.ReInit(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++)
            jj_la1[i] = -1;
    }

    /**
     * Constructor.
     */
    public PQLParser(java.io.Reader stream) {
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new PQLParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++)
            jj_la1[i] = -1;
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.Reader stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++)
            jj_la1[i] = -1;
    }

    /**
     * Constructor with generated Token Manager.
     */
    public PQLParser(PQLParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++)
            jj_la1[i] = -1;
    }

    /**
     * Reinitialise.
     */
    public void ReInit(PQLParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 5; i++)
            jj_la1[i] = -1;
    }

    private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null)
            token = token.next;
        else
            token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        if (token.kind == kind) {
            jj_gen++;
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
    }

    /**
     * Get the next Token.
     */
    final public Token getNextToken() {
        if (token.next != null)
            token = token.next;
        else
            token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        jj_gen++;
        return token;
    }

    /**
     * Get the specific Token.
     */
    final public Token getToken(int index) {
        Token t = token;
        for (int i = 0; i < index; i++) {
            if (t.next != null)
                t = t.next;
            else
                t = t.next = token_source.getNextToken();
        }
        return t;
    }

    private int jj_ntk() {
        if ((jj_nt = token.next) == null)
            return (jj_ntk = (token.next = token_source.getNextToken()).kind);
        else
            return (jj_ntk = jj_nt.kind);
    }

    private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
    private int[] jj_expentry;
    private int jj_kind = -1;

    /**
     * Generate ParseException.
     */
    public ParseException generateParseException() {
        jj_expentries.clear();
        boolean[] la1tokens = new boolean[19];
        if (jj_kind >= 0) {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for (int i = 0; i < 5; i++) {
            if (jj_la1[i] == jj_gen) {
                for (int j = 0; j < 32; j++) {
                    if ((jj_la1_0[i] & (1 << j)) != 0) {
                        la1tokens[j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 19; i++) {
            if (la1tokens[i]) {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.add(jj_expentry);
            }
        }
        int[][] exptokseq = new int[jj_expentries.size()][];
        for (int i = 0; i < jj_expentries.size(); i++) {
            exptokseq[i] = jj_expentries.get(i);
        }
        return new ParseException(token, exptokseq, tokenImage);
    }

    /**
     * Enable tracing.
     */
    final public void enable_tracing() {
    }

    /**
     * Disable tracing.
     */
    final public void disable_tracing() {
    }

}
