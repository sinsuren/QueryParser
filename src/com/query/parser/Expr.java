package com.query.parser;

import java.util.Map;

public interface Expr {
    boolean evaluate(Map<String, String> data);
}
