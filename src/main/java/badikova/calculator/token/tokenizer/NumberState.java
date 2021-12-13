package badikova.calculator.token.tokenizer;

import badikova.calculator.token.NumberToken;

public class NumberState extends State {

    private int number;

    public NumberState(Tokenizer tokenizer, int number) {
        super(tokenizer);
        this.number = number;
    }

    @Override
    public void process() {
        while (tokenizer.hasNext()) {
            var symbol = tokenizer.next();

            if (!Character.isDigit(symbol)) {
                tokenizer.addToken(new NumberToken(number));
                tokenizer.setState(new StartState(tokenizer, symbol));
                return;
            }

            number = number * 10 + (symbol - '0');
        }
        tokenizer.addToken(new NumberToken(number));
    }

    @Override
    public void collectTokens() {
        tokenizer.addToken(new NumberToken(number));
    }
}
