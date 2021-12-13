package badikova.calculator.visitor;

import badikova.calculator.token.BraceToken;
import badikova.calculator.token.NumberToken;
import badikova.calculator.token.Operation;

public interface TokenVisitor {

    void visit(NumberToken token);

    void visit(BraceToken token);

    void visit(Operation token);

}
