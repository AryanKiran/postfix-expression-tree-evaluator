package com.assignment1.solvingalgebraicequation.parser;


import com.assignment1.solvingalgebraicequation.model.ExpressionNode;
import com.assignment1.solvingalgebraicequation.model.OperandNode;
import com.assignment1.solvingalgebraicequation.model.OperatorNode;

import java.util.List;
import java.util.Stack;

public class ExpressionTreeBuilder {

    public ExpressionNode build(List<String> postfix) {

        Stack<ExpressionNode> stack = new Stack<>();

        for (String token : postfix) {

            if (isOperator(token)) {

                ExpressionNode right = stack.pop();
                ExpressionNode left = stack.pop();

                stack.push(new OperatorNode(token, left, right));
            }
            else {
                stack.push(new OperandNode(token));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return "+-*/^".contains(token);
    }
}
