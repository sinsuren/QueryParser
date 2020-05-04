package com.query.parser;

import java.text.ParseException;
import java.util.Map;

public class SubExpr implements Expr {
    private final Expr child;

    public SubExpr(TokenStream stream) throws ParseException {
        if (stream.consumeIf(TokenType.LEFT_PAREN) != null) {
            child = new OrExpr(stream);
            stream.consume(TokenType.RIGHT_PAREN);
        } else {
            child = new EqualsExpr(stream);
        }
    }

    @Override
    public String toString() {
        return "(" + child + ")";
    }

    @Override
    public boolean evaluate(Map<String, String> data) {
        return child.evaluate(data);
    }
}
