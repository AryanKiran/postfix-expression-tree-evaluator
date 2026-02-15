package com.assignment1.solvingalgebraicequation.parser;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public List<String> tokenize(String expression) {

        List<String> tokens = new ArrayList<>();
        expression = expression.replaceAll("\\s+", "");

        StringBuilder current = new StringBuilder();

        for (char c : expression.toCharArray()) {

            if (Character.isLetterOrDigit(c) || c == '.') {
                current.append(c);
            } else {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }

        if (current.length() > 0) {
            tokens.add(current.toString());
        }

        return tokens;
    }
}
