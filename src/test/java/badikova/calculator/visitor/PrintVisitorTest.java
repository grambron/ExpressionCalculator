package badikova.calculator.visitor;

import badikova.calculator.token.BraceToken;
import badikova.calculator.token.NumberToken;
import badikova.calculator.token.Operation;
import badikova.calculator.token.Token;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintVisitorTest {

    @Test
    void printExpressionTest() {
        var input = List.of(
                new NumberToken(2),
                Operation.MUL,
                BraceToken.LEFT_BRACE,
                new NumberToken(3),
                Operation.PLUS,
                new NumberToken(4),
                BraceToken.RIGHT_BRACE
        );

        var output = getOutput(input);

        assertEquals("Number(2) MUL LEFT Number(3) PLUS Number(4) RIGHT", output);
    }

    @Test
    void anotherExpressionTest() {
        var input = List.of(
                new NumberToken(2),
                Operation.DIV,
                new NumberToken(1),
                Operation.MINUS,
                new NumberToken(4)
        );

        var output = getOutput(input);

        assertEquals("Number(2) DIV Number(1) MINUS Number(4)", output);
    }

    private String getOutput(List<Token> expression) {
        var byteArrayStream = new ByteArrayOutputStream();
        new PrintVisitor(byteArrayStream).print(expression);

        return byteArrayStream.toString();
    }

}