package com.query.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrExpr implements Expr {
    private final List<Expr> children = new ArrayList<>();

    public OrExpr(TokenStream stream) throws ParseException {
        do {
            children.add(new AndExpr(stream));
        } while(stream.consumeIf(TokenType.OR) != null);
    }

    @Override
    public String toString() {
        return children.stream().map(Object::toString).collect(Collectors.joining(" OR "));
    }

    @Override
    public boolean evaluate(Map<String, String> data) {
        for(Expr child : children) {
            if(child.evaluate(data))
                return true;
        }
        return false;
    }
}