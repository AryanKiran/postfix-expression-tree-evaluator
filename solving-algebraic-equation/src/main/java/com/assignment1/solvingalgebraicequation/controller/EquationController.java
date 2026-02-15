package com.assignment1.solvingalgebraicequation.controller;
import com.assignment1.solvingalgebraicequation.dto.EvaluateRequest;
import com.assignment1.solvingalgebraicequation.dto.StoreEquationRequest;
import com.assignment1.solvingalgebraicequation.service.EquationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/equations")
public class EquationController {

    private final EquationService service;

    public EquationController(EquationService service) {
        this.service = service;
    }

    @PostMapping("/store")
    public ResponseEntity<?> store(@Valid @RequestBody StoreEquationRequest request) {

        String id = service.storeEquation(request.getEquation());

        return ResponseEntity.ok(
                Map.of(
                        "message", "Equation stored successfully",
                        "equationId", id
                )
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                Map.of("equations", service.getAllEquations())
        );
    }

    @PostMapping("/{id}/evaluate")
    public ResponseEntity<?> evaluate(
            @PathVariable String id,
            @RequestBody EvaluateRequest request) {

        return ResponseEntity.ok(
                service.evaluate(id, request.getVariables())
        );
    }
}
