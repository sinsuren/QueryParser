package com.query.parser;

import java.text.ParseException;
import java.util.Map;

public class EqualsExpr implements Expr {
    private final String identifier, literal;

    public EqualsExpr(TokenStream stream) throws ParseException {
        Token token = stream.consumeIf(TokenType.IDENTIFIER);
        if(token != null) {
            this.identifier = token.data;
            //check the type
            stream.consume(TokenType.EQUALS);
            this.literal = stream.consume(TokenType.LITERAL).data;
        } else {
            this.literal = stream.consume(TokenType.LITERAL).data;
            stream.consume(TokenType.EQUALS);
            this.identifier = stream.consume(TokenType.IDENTIFIER).data;
        }
    }

    @Override
    public String toString() {
        return identifier+"='"+literal+"'";
    }

    @Override
    public boolean evaluate(Map<String, String> data) {
        return literal.equals(data.get(identifier));
    }
}