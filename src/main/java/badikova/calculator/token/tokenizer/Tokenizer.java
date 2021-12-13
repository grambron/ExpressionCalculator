package badikova.calculator.token.tokenizer;

import badikova.calculator.token.Token;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private List<Token> expression;

    private State state;

    private int index;

    private String line;

    public List<Token> process(String line) {
        this.line = line;
        index = 0;
        expression = new ArrayList<>();
        state = new StartState(this);

        while (hasNext()) {
            state.process();
        }

        state.collectTokens();

        return expression;
    }

    void addToken(Token token) {
        expression.add(token);
    }

    void setState(State state) {
        this.state = state;
    }

    char next() {
        if (index >= line.length()) {
            throw new IllegalStateException("Index is out of bounds: " + index);
        }

        return line.charAt(index++);
    }

    boolean hasNext() {
        return index < line.length();
    }

}
