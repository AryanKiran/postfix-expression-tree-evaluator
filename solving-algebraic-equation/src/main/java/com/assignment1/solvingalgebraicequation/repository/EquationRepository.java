package com.assignment1.solvingalgebraicequation.repository;

import com.assignment1.solvingalgebraicequation.model.Equation;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EquationRepository {

    private final Map<String, Equation> storage = new ConcurrentHashMap<>();

    public void save(Equation equation) {
        storage.put(equation.getId(), equation);
    }

    public Optional<Equation> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Equation> findAll() {
        return new ArrayList<>(storage.values());
    }
}
