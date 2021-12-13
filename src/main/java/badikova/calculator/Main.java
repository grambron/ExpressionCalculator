package badikova.calculator;

import badikova.calculator.token.tokenizer.Tokenizer;
import badikova.calculator.visitor.CalcVisitor;
import badikova.calculator.visitor.ParserVisitor;
import badikova.calculator.visitor.PrintVisitor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            input = scanner.nextLine();
        }

        var tokens = new Tokenizer().process(input);
        var expression = new ParserVisitor().convert(tokens);

        new PrintVisitor(System.out).print(expression);

        var result = new CalcVisitor().calculate(expression);

        System.out.println();
        System.out.println("Expression = " + result);
    }

}
