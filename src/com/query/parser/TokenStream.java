package com.query.parser;

import java.text.ParseException;
import java.util.List;

public class TokenStream {
    final List<Token> tokens;
    int offset = 0;

    public TokenStream(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token consume(TokenType type) throws ParseException {
        Token token = tokens.get(offset++);
        if (token.type != type) {
            throw new ParseException("Unexpected token at " + token.start
                    + ": " + token + " (was looking for " + type + ")",
                    token.start);
        }
        return token;
    }

    public Token consumeIf(TokenType type) {
        Token token = tokens.get(offset);
        if (token.type == type) {
            offset++;
            return token;
        }
        return null;
    }

    @Override
    public String toString() {
        return tokens.toString();
    }
}
