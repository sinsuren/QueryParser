package com.query.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final Pattern TOKENS =
            Pattern.compile("(\\s+)|(AND)|(OR)|(=)|(\\()|(\\))|(\\w+)|\'([^\']+)\'");

    public static Expr parse(TokenStream stream) throws ParseException {
        OrExpr expr = new OrExpr(stream);
        stream.consume(TokenType.EOF); // ensure that we parsed the whole input
        return expr;
    }

    public static TokenStream tokenize(String input) throws ParseException {
        Matcher matcher = TOKENS.matcher(input);
        List<Token> tokens = new ArrayList<>();
        int offset = 0;
        TokenType[] types = TokenType.values();
        while (offset != input.length()) {
            if (!matcher.find() || matcher.start() != offset) {
                throw new ParseException("Unexpected token at " + offset, offset);
            }
            for (int i = 0; i < types.length; i++) {
                if (matcher.group(i + 1) != null) {
                    if (types[i] != TokenType.WHITESPACE)
                        tokens.add(new Token(types[i], offset, matcher.group(i + 1)));
                    break;
                }
            }
            offset = matcher.end();
        }

        tokens.add(new Token(TokenType.EOF, input.length(), ""));
        return new TokenStream(tokens);
    }
}
