package badikova.calculator.token.tokenizer;

import badikova.calculator.token.BraceToken;
import badikova.calculator.token.NumberToken;
import badikova.calculator.token.Operation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenizerTest {

    @Test
    void simpleExpressionTest() {
        var expression = "5 + 6";

        var tokenizer = new Tokenizer();

        var result = tokenizer.process(expression);

        assertEquals(
            List.of(
                    new NumberToken(5),
                    Operation.PLUS,
                    new NumberToken(6)
            ),
            result);
    }

    @Test
    void expressionTest() {
        var expression = "2-(5 + 6)";

        var tokenizer = new Tokenizer();

        var result = tokenizer.process(expression);

        assertEquals(
                List.of(
                        new NumberToken(2),
                        Operation.MINUS,
                        BraceToken.LEFT_BRACE,
                        new NumberToken(5),
                        Operation.PLUS,
                        new NumberToken(6),
                        BraceToken.RIGHT_BRACE
                ),
                result);
    }

}