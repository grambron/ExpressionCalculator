package badikova.calculator.token;

import badikova.calculator.visitor.TokenVisitor;

public enum Operation implements Token {

    PLUS,
    MINUS,
    MUL,
    DIV;

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

}
