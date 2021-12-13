package badikova.calculator.visitor;

import badikova.calculator.token.BraceToken;
import badikova.calculator.token.NumberToken;
import badikova.calculator.token.Operation;
import badikova.calculator.token.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.function.IntBinaryOperator;


public class CalcVisitor implements TokenVisitor {

    private final Deque<Integer> stack = new ArrayDeque<>();

    @Override
    public void visit(NumberToken token) {
        stack.push(token.value());
    }

    @Override
    public void visit(BraceToken token) {
        throw new IllegalStateException("Braces are not allowed");
    }

    @Override
    public void visit(Operation token) {
        switch (token) {
            case PLUS -> reduceStack(Integer::sum);
            case MINUS -> reduceStack((a, b) -> a - b);
            case MUL -> reduceStack((a, b) -> a * b);
            case DIV -> reduceStack((a, b) -> a / b);
        }
    }

    public int calculate(List<Token> expression) {
        for (Token token : expression) {
            token.accept(this);
        }

        return stack.pop();
    }

    private void reduceStack(IntBinaryOperator operator) {
        if (stack.size() < 2) {
            throw new IllegalStateException("Illegal notation");
        }

        int a = stack.pop();
        int b = stack.pop();
        int result = operator.applyAsInt(b, a);

        stack.push(result);
    }

}

