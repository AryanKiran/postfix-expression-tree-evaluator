package com.assignment1.solvingalgebraicequation.model;

import java.util.Map;

public abstract class ExpressionNode {
    public abstract double evaluate(Map<String, Double> variables);
}
