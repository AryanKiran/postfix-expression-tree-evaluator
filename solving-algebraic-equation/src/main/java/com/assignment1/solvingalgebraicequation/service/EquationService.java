package com.assignment1.solvingalgebraicequation.service;

import com.assignment1.solvingalgebraicequation.model.Equation;
import com.assignment1.solvingalgebraicequation.model.ExpressionNode;
import com.assignment1.solvingalgebraicequation.parser.ExpressionTreeBuilder;
import com.assignment1.solvingalgebraicequation.parser.PostfixConverter;
import com.assignment1.solvingalgebraicequation.parser.Tokenizer;

import com.assignment1.solvingalgebraicequation.repository.EquationRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquationService {

    private final EquationRepository repository;
    private final Tokenizer tokenizer = new Tokenizer();
    private final PostfixConverter postfixConverter = new PostfixConverter();
    private final ExpressionTreeBuilder treeBuilder = new ExpressionTreeBuilder();

    public EquationService(EquationRepository repository) {
        this.repository = repository;
    }

    public String storeEquation(String equationString) {

        List<String> tokens = tokenizer.tokenize(equationString);
        List<String> postfix = postfixConverter.convert(tokens);
        ExpressionNode root = treeBuilder.build(postfix);

        String id = UUID.randomUUID().toString();

        Equation equation = new Equation(id, equationString, root);
        repository.save(equation);

        return id;
    }

    public List<Map<String, String>> getAllEquations() {

        List<Map<String, String>> response = new ArrayList<>();

        for (Equation eq : repository.findAll()) {
            Map<String, String> map = new HashMap<>();
            map.put("equationId", eq.getId());
            map.put("equation", eq.getInfix());
            response.add(map);
        }

        return response;
    }

    public Map<String, Object> evaluate(String id, Map<String, Double> variables) {

        Equation equation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equation not found"));

        double result = equation.getRoot().evaluate(variables);

        Map<String, Object> response = new HashMap<>();
        response.put("equationId", id);
        response.put("equation", equation.getInfix());
        response.put("variables", variables);
        response.put("result", result);

        return response;
    }
}
