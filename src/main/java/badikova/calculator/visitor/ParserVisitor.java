package badikova.calculator.visitor;

import badikova.calculator.token.BraceToken;
import badikova.calculator.token.NumberToken;
import badikova.calculator.token.Operation;
import badikova.calculator.token.Token;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ParserVisitor implements TokenVisitor{

    private final List<Token> expression = new ArrayList<>();

    private final Deque<Token> stack = new ArrayDeque<>();

    @Override
    public void visit(NumberToken token) {
        expression.add(token);
    }

    @Override
    public void visit(BraceToken token) {
        switch (token) {

            case RIGHT_BRACE -> {
                while (!stack.isEmpty() && stack.peek() != BraceToken.LEFT_BRACE) {
                    var element = stack.pop();
                    expression.add(element);
                }

                stack.pop();
            }

            case LEFT_BRACE -> stack.push(token);
        }
    }

    @Override
    public void visit(Operation token) {
        while (!stack.isEmpty()) {
            var top = stack.peek();

            if (!(top instanceof Operation operation)) {
                break;
            }

            if (isAddSub(operation) && isMulDiv(token)) {
                break;
            }

            expression.add(stack.pop());
        }

        stack.push(token);
    }

    public List<Token> convert(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }

        while (!stack.isEmpty()) {
            var current = stack.pop();

            if (!(current instanceof BraceToken)) {
                expression.add(current);
            }
        }

        return expression;
    }

    private boolean isAddSub(Operation operation) {
        return operation == Operation.PLUS || operation == Operation.MINUS;
    }


    private boolean isMulDiv(Operation operation) {
        return operation == Operation.MUL || operation == Operation.DIV;
    }

}
