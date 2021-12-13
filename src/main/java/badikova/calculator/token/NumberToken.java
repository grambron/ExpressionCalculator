package badikova.calculator.token;

import badikova.calculator.visitor.TokenVisitor;

public record NumberToken(int value) implements Token {

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

}
