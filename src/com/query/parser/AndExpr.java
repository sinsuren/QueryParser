package com.query.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AndExpr implements Expr {
    private final List<Expr> children = new ArrayList<>();

    public AndExpr(TokenStream stream) throws ParseException {
        do {
            children.add(new SubExpr(stream));
        } while(stream.consumeIf(TokenType.AND) != null);
    }

    @Override
    public String toString() {
        return children.stream().map(Object::toString).collect(Collectors.joining(" AND "));
    }

    @Override
    public boolean evaluate(Map<String, String> data) {
        for(Expr child : children) {
            if(!child.evaluate(data))
                return false;
        }
        return true;
    }
}
