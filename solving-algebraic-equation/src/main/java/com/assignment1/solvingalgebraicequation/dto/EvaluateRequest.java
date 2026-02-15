package com.assignment1.solvingalgebraicequation.dto;

import java.util.Map;

public class EvaluateRequest {

    private Map<String, Double> variables;

    public Map<String, Double> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Double> variables) {
        this.variables = variables;
    }
}
