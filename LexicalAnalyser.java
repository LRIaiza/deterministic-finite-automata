import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class LexicalAnalyser {
    public static List<Token> analyse(final String input) throws NumberException, ExpressionException {
        final List<Token> calculation = new ArrayList<>();
        StringBuilder currentTokenStr = new StringBuilder();
        int state = 0;

        for (int inputIndex = 0; inputIndex <input.length(); inputIndex++) { //iterate through the input
            char currentChar = input.charAt(inputIndex);
            switch (state) {
                case 0:
                    switch (currentChar) {
                        case ' ': break;
                        case '0':
                            currentTokenStr.append(currentChar);
                            state = 3; break;
                        case '1','2','3','4','5','6','7','8','9':
                            currentTokenStr.append(currentChar);
                            state = 1; break;
                        case '.':
                            throw new NumberException();
                        default:
                            throw new ExpressionException();
                    }
                    break;

                case 1:
                    switch (currentChar) {
                        case '+', '-', '/', '*' -> {
                            // Left part -> the number
                            if(!currentTokenStr.toString().equals("")) {
                                double currentTokenValue = parseDouble(currentTokenStr.toString());
                                currentTokenStr = new StringBuilder();
                                Token newToken = new Token(currentTokenValue);
                                calculation.add(newToken);
                            }
                            // The current operator
                            calculation.add(new Token(Token.typeOf(currentChar)));
                            state = 2;
                        }
                        case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> currentTokenStr.append(currentChar);
                        case ' ' -> {
                            // Left part -> the number
                            if(!currentTokenStr.toString().equals("")) {
                                double currentTokenValue = parseDouble(currentTokenStr.toString());
                                currentTokenStr = new StringBuilder();
                                Token newToken = new Token(currentTokenValue);
                                calculation.add(newToken);
                            }
                            // The current operator
                            state = 5;
                        }
                        case '.' -> throw new NumberException();
                        default -> throw new ExpressionException();
                    }
                    break;

                case 2:
                    switch (currentChar) {
                        case '0' -> {
                            currentTokenStr.append(currentChar);
                            state = 3;
                        }
                        case '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                            currentTokenStr.append(currentChar);
                            state = 1;
                        }
                        case ' ' -> state = 6;
                        case '.' -> throw new NumberException();
                        // if u encounter another operator, throw error (part of default expression exception)
                        default -> throw new ExpressionException();
                    }
                    break;

                case 3:
                    switch (currentChar) {
                        case '+', '-', '/', '*' -> {
                            // Left part -> the number
                            if (!currentTokenStr.toString().equals("")) {
                                double currentTokenValue = parseDouble(currentTokenStr.toString());
                                currentTokenStr = new StringBuilder();
                                Token newToken = new Token(currentTokenValue);
                                calculation.add(newToken);
                            }
                            // The current operator
                            calculation.add(new Token(Token.typeOf(currentChar)));
                            state = 2;
                        }
                        case '.' -> {
                            currentTokenStr.append(".");
                            state = 4;
                        }
                        case ' ' -> {
                            // Left part -> the number
                        if (!currentTokenStr.toString().equals("")) {
                            double currentTokenValue = parseDouble(currentTokenStr.toString());
                            currentTokenStr = new StringBuilder();
                            Token newToken = new Token(currentTokenValue);
                            calculation.add(newToken);

                            }
                            // The current operator
                        state = 5;
                        }
                        case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> throw new NumberException();
                        default -> throw new ExpressionException();
                    }
                    break;

                case 4:
                    switch (currentChar) {
                        case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                            currentTokenStr.append(currentChar);
                            state = 1;
                        }
                        default -> throw new ExpressionException();
                    }
                    break;

                case 5:
                    switch (currentChar) {
                        case '+', '-', '/', '*':
                            calculation.add(new Token(Token.typeOf(currentChar)));
                            state = 2; break;
                        case ' ' : break;
                        default:
                            throw new ExpressionException();
                    }
                    break;

                case 6:
                    switch (currentChar) {
                        case '0':
                            currentTokenStr.append(currentChar);
                            state = 3; break;
                        case '1','2','3','4','5','6','7','8','9':
                            currentTokenStr.append(currentChar);
                            state = 1; break;
                        case ' ' : break;
                        default:
                            throw new ExpressionException();
                    }
                    break;
                }
            }

        if (state == 4) {
            throw new NumberException();
        }
        else if (state == 2 || state == 6) {
            throw new ExpressionException();
        }
        if(!currentTokenStr.toString().equals("")) {
            double currentTokenValue = parseDouble(currentTokenStr.toString());
            currentTokenStr = new StringBuilder();
            Token newToken = new Token(currentTokenValue);
            calculation.add(newToken);
        }
        return calculation;
    }
}

