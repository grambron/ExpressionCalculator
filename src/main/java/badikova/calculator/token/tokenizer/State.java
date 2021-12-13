package badikova.calculator.token.tokenizer;

public abstract class State {

    protected final Tokenizer tokenizer;

    protected State(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public abstract void process();

    public void collectTokens() {

    }

}
