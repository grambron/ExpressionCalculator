package badikova.calculator.visitor;

import badikova.calculator.token.BraceToken;
import badikova.calculator.token.NumberToken;
import badikova.calculator.token.Operation;
import badikova.calculator.token.Token;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PrintVisitor implements TokenVisitor {

    private final List<String> result = new ArrayList<>();

    private final OutputStream outputStream;

    public PrintVisitor(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void visit(NumberToken token) {
        result.add("Number(" + token.value() + ")");
    }

    @Override
    public void visit(BraceToken token) {
        switch (token) {
            case LEFT_BRACE -> result.add("LEFT");
            case RIGHT_BRACE -> result.add("RIGHT");
        }
    }

    @Override
    public void visit(Operation token) {
        result.add(token.name());
    }

    public void print(List<Token> expression) {
        result.clear();

        for (Token token : expression) {
            token.accept(this);
        }

        try {
            outputStream.write(String.join(" ", result).getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
