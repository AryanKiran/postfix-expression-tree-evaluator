package com.assignment1.solvingalgebraicequation.model;


public class Equation {

    private String id;
    private String infix;
    private ExpressionNode root;

    public Equation(String id, String infix, ExpressionNode root) {
        this.id = id;
        this.infix = infix;
        this.root = root;
    }

    public String getId() { return id; }
    public String getInfix() { return infix; }
    public ExpressionNode getRoot() { return root; }
}
