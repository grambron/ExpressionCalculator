package badikova.calculator.visitor;

import badikova.calculator.token.BraceToken;
import badikova.calculator.token.NumberToken;
import badikova.calculator.token.Operation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CalcVisitorTest {

    @Test
    void simpleConversionTest() {
        var input = List.of(
                new NumberToken(2),
                new NumberToken(1),
                Operation.DIV,
                new NumberToken(4),
                Operation.MINUS
        );

        var result = new CalcVisitor().calculate(input);

        assertEquals(-2, result);
    }

    @Test
    void anotherParseExpressionTest() {
        var input = List.of(
                new NumberToken(2),
                new NumberToken(3),
                new NumberToken(4),
                Operation.PLUS,
                Operation.MUL
        );

        var result = new CalcVisitor().calculate(input);

        assertEquals(14, result);
    }

}