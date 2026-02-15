package com.assignment1.solvingalgebraicequation.model;

import java.util.Map;

public class OperandNode extends ExpressionNode {

    private final String value;

    public OperandNode(String value) {
        this.value = value;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {

        // pure number
        if (value.matches("\\d+(\\.\\d+)?")) {
            return Double.parseDouble(value);
        }

        // coefficient variable like 3x
        if (value.matches("\\d+(\\.\\d+)?[a-zA-Z]+")) {
            String number = value.replaceAll("[a-zA-Z]", "");
            String variable = value.replaceAll("\\d+(\\.\\d+)?", "");

            if (!variables.containsKey(variable)) {
                throw new RuntimeException("Missing variable: " + variable);
            }

            return Double.parseDouble(number) * variables.get(variable);
        }

        // pure variable
        if (!variables.containsKey(value)) {
            throw new RuntimeException("Missing variable: " + value);
        }

        return variables.get(value);
    }

    public String getValue() {
        return value;
    }
}
