package badikova.calculator.token.tokenizer;

import badikova.calculator.token.BraceToken;
import badikova.calculator.token.Operation;

public class StartState extends State {

    public StartState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    public StartState(Tokenizer tokenizer, char symbol) {
        super(tokenizer);
        processNonDigit(symbol);
    }

    @Override
    public void process() {
        while (tokenizer.hasNext()) {

            var symbol = tokenizer.next();

            if (Character.isDigit(symbol)) {
                var number = symbol - '0';
                tokenizer.setState(new NumberState(tokenizer, number));
                return;
            }

            processNonDigit(symbol);
        }
    }

    private void processNonDigit(char symbol) {
        if (Character.isWhitespace(symbol)) {
            return;
        }

        switch (symbol) {
            case '(' -> tokenizer.addToken(BraceToken.LEFT_BRACE);
            case ')' -> tokenizer.addToken(BraceToken.RIGHT_BRACE);
            case '+' -> tokenizer.addToken(Operation.PLUS);
            case '-' -> tokenizer.addToken(Operation.MINUS);
            case '/' -> tokenizer.addToken(Operation.DIV);
            case '*' -> tokenizer.addToken(Operation.MUL);
            default -> throw new IllegalStateException("Unexpected character: " + symbol);
        }
    }

}
