package com.assignment1.solvingalgebraicequation.dto;

import jakarta.validation.constraints.NotBlank;

public class StoreEquationRequest {

    @NotBlank(message = "Equation cannot be empty")
    private String equation;

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }
}
