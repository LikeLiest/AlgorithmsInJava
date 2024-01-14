package AlgorithmsInJava.Algorithms.Calculator;

import java.util.*;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        var calculator = new Calculator();

        calculator.calculate("        12*5 -    36 / 3");  // 48
        calculator.calculate(" 12+ 50/ 5    -3");  // 19
//        calculator.calculate("20 * (45 + 5) / 10");  // 100
//        calculator.calculate("(0 - 1+ (45 + 5) * 2) /33 ");  // 3
        calculator.calculate("0/1");  // 0
        calculator.calculate("2+2*2*2");  // 16
        calculator.calculate("015");  // 15

    }

    static class Calculator {
        private final Lexer lexer = new Lexer();
        private final  PostfixConverter postfixConverter = new PostfixConverter();
        private final StackMachine stackMachine = new StackMachine();

        public int calculate(String expression) {
            List<Token> tokens = lexer.getTokens(expression);
            var postfixExpression = postfixConverter.convertToPostfix(tokens);
            var result = stackMachine.evaluate(postfixExpression);

            out.println(result);
            return result;
        }

    }

    static class StackMachine {
        public int evaluate(List<Token> postfixExpression) {
            Deque<Integer> valueStack = new LinkedList<>();

            for (Token token : postfixExpression) {
                if (token instanceof NumberToken number) {
                    valueStack.push(number.value());
                } else if (token instanceof BinaryOperationToken operation) {
                    var right = valueStack.pop();
                    var left = valueStack.pop();

                    var result = switch (operation.operationType()) {
                        case PLUS -> left + right;
                        case MINUS -> left - right;
                        case MULTIPLY -> left * right;
                        case DIVIDE -> {
                            if (right == 0) {
                                throw  new RuntimeException("Divide by zero");
                            }
                        //  Возвращает результат из лямбды
                            yield left/right;
                        }
                    };

                    valueStack.push(result);
                }
            }
            return valueStack.pop();
        }
    }
    static class Lexer {
        private final static String DELIMITERS = " +-*/()";

    /*  StringTokenizer может быть полезным,
    *   особенно если вам нужно разбивать строки с использованием нескольких символов в качестве разделителей
    *   и вы хотите более простой способ указания нескольких разделителей.
    */
        public List<Token> getTokens(String source) {
//                                              Строка      Делитель        Указать на то, что разделители будут считаться токенами
            var tokenizer = new StringTokenizer(source,     DELIMITERS, true);
            List<Token> tokens = new ArrayList<>();

            while (tokenizer.hasMoreTokens()) {
                var token = tokenizer.nextToken();

                if (token.isBlank()){
                    continue;
                } else if (isNumber(token)) {
                    tokens.add(new NumberToken(Integer.parseInt(token)));
                    continue;
                }
                tokens.add(
                        switch (token) {
                            case "+" -> new BinaryOperationToken(OperationType.PLUS);
                            case "-" -> new BinaryOperationToken(OperationType.MINUS);
                            case "*" -> new BinaryOperationToken(OperationType.MULTIPLY);
                            case "/" -> new BinaryOperationToken(OperationType.DIVIDE);
                            case "(" -> new OtherToken(TokenType.CLOSE_BRACKET);
                            case ")" -> new OtherToken(TokenType.OPEN_BRACKET);
                            default -> throw new RuntimeException("Unexpected token: " + token);
                        }
                );
            }
            return tokens;
        }


        private boolean isNumber(String token) {
            for (int i = 0; i < token.length() ; i++) {
//                isDigit - проверяет, является символ числом
                if (!Character.isDigit(token.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

    }

    static class PostfixConverter {
        //        3 + 4 -> 3 4 +
        //        (5 - 6) * 7 -> 5 6 - 7 *

        public List<Token> convertToPostfix(List<Token> source) {
            List<Token> postfixExpression = new ArrayList<>();
            Deque<Token> operationStack = new LinkedList<>();

            for (Token token : source) {
                switch (token.type()) {
                    case NUMBER -> postfixExpression.add(token);
                    case OPEN_BRACKET -> operationStack.push(token);
                    case CLOSE_BRACKET -> {
                        while (!operationStack.isEmpty() && operationStack.peek().type() != TokenType.OPEN_BRACKET) {
                            postfixExpression.add(operationStack.pop());
                        }
                        operationStack.pop(); // открывающая скобка
                    }

                    /*
                    * peek() используется для просмотра, но не извлечения, элемента с начала очереди.
                    * Этот метод возвращает первый элемент в очереди (головной элемент) без его удаления.
                    *  Если очередь пуста, метод возвращает значение null.
                    */

                    case BINARY_OPERATION -> {
                        while (!operationStack.isEmpty() && getPriority(operationStack.peek()) >= getPriority(token)) {
                            postfixExpression.add(operationStack.pop());
                        }
                        operationStack.push(token);
                    }
                }
            }
            while (!operationStack.isEmpty()) {
                postfixExpression.add(operationStack.pop());
            }
            return postfixExpression;
        }
        private int getPriority(Token token) {
            if (token instanceof  BinaryOperationToken operation) {
                return switch (operation.operationType()) {
                    case PLUS,MINUS -> 1;
                    case MULTIPLY, DIVIDE -> 2;
                };
            }
            return 0;
        }
    }



    interface Token {
        TokenType type();
    }

    record BinaryOperationToken(OperationType operationType) implements Token {

        @Override
        public TokenType type() {
            return TokenType.BINARY_OPERATION;
        }
    }

//    OtherToken будем использовать для '()'

    record OtherToken(TokenType type) implements Token {}

    record NumberToken(Integer value) implements Token {
        @Override
        public TokenType type() {
            return TokenType.NUMBER;
        }
    }

    enum OperationType {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE
    }

    enum TokenType {
        BINARY_OPERATION,
        NUMBER,
        OPEN_BRACKET,
        CLOSE_BRACKET
    }

}
