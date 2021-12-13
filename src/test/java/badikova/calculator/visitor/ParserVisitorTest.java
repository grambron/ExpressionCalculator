package badikova.calculator.visitor;

import badikova.calculator.token.BraceToken;
import badikova.calculator.token.NumberToken;
import badikova.calculator.token.Operation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserVisitorTest {

    @Test
    void parseSimpleExpressionTest() {
        var input = List.of(
                new NumberToken(2),
                Operation.MUL,
                BraceToken.LEFT_BRACE,
                new NumberToken(3),
                Operation.PLUS,
                new NumberToken(4),
                BraceToken.RIGHT_BRACE
        );

        var visitor = new ParserVisitor();
        var result = visitor.convert(input);

        var expected = List.of(
                new NumberToken(2),
                new NumberToken(3),
                new NumberToken(4),
                Operation.PLUS,
                Operation.MUL
        );

        assertEquals(expected, result);
    }

    @Test
    void anotherParseExpressionTest() {
        var input = List.of(
                new NumberToken(2),
                Operation.DIV,
                new NumberToken(1),
                Operation.MINUS,
                new NumberToken(4)
        );

        var visitor = new ParserVisitor();
        var result = visitor.convert(input);

        var expected = List.of(
                new NumberToken(2),
                new NumberToken(1),
                Operation.DIV,
                new NumberToken(4),
                Operation.MINUS
        );

        assertEquals(expected, result);
    }

}