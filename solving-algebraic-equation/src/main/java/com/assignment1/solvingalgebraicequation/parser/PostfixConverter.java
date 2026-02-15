package com.assignment1.solvingalgebraicequation.parser;

import java.util.*;

public class PostfixConverter {

    private static final Map<String, Integer> PRECEDENCE = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2,
            "^", 3
    );

    public List<String> convert(List<String> tokens) {

        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {

            if (!PRECEDENCE.containsKey(token) && 
                !token.equals("(") && 
                !token.equals(")")) {

                output.add(token);
            }

            else if (PRECEDENCE.containsKey(token)) {

                while (!stack.isEmpty() &&
                        PRECEDENCE.containsKey(stack.peek()) &&
                        PRECEDENCE.get(stack.peek()) >= PRECEDENCE.get(token)) {

                    output.add(stack.pop());
                }

                stack.push(token);
            }

            else if (token.equals("(")) {
                stack.push(token);
            }

            else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }
}
