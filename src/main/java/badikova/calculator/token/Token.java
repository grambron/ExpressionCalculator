package badikova.calculator.token;

import badikova.calculator.visitor.TokenVisitor;

public sealed interface Token permits BraceToken, NumberToken, Operation {

    void accept(TokenVisitor visitor);

}
