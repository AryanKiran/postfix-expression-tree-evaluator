package com.assignment1.solvingalgebraicequation.model;

import java.util.Map;

public class OperatorNode extends ExpressionNode {

    private final String operator;
    private final ExpressionNode left;
    private final ExpressionNode right;

    public OperatorNode(String operator, ExpressionNode left, ExpressionNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate(Map<String, Double> variables) {

        double l = left.evaluate(variables);
        double r = right.evaluate(variables);

        return switch (operator) {
            case "+" -> l + r;
            case "-" -> l - r;
            case "*" -> l * r;
            case "/" -> {
                if (r == 0) throw new ArithmeticException("Division by zero");
                yield l / r;
            }
            case "^" -> Math.pow(l, r);
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }

    public String getOperator() {
        return operator;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
    }
}
