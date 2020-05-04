package com.query.parser;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws ParseException {
        Map<String, String> data = new HashMap<>();
        data.put("age", "255");
        data.put("gender", "Male");
        data.put("past_order_amount", "10000");


        Expr expr = Util.parse(Util.tokenize("(age = '25' AND gender = 'Male') OR (past_order_amount = '10000')"));
        System.out.println(expr.evaluate(data)); // true

    }
}
