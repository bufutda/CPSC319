import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Binary Tree to store a parsed expression
 * @author Mitchell Sawatzky
 * @since Mar 2016
 * @version 1.0
 */
public class BinaryExpressionTree {
    /**
     * The root node
     */
    private Node root;

    /**
     * The index in the expression string that the parser is at
     */
    private int index;

    /**
     * The current amount of parenthesis to expect on the way up
     */
    private int parenCount;

    /**
     * An array of tokens representing the expression
     */
    private String[] expressArray;

    /**
     * The original string input expression
     */
    private String original;

    /**
     * Converts a string to an array of tokens
     * @throws InvalidExpressionException when it encounters an invalid token
     * @param expr - the string to parse into tokens
     * @param handleNeg - whether or not to parse negative integers as numbers or as separate tokens
     */
    public static String[] tokenize (String expr, boolean handleNeg) throws InvalidExpressionException {
        // regular expression that matches on any number of consecutive numbers, "+", "-", "*", "/", "(", or ")"
        Pattern expressionRegex = Pattern.compile("[0-9]+|\\+|\\-|\\*|\\/|\\(|\\)");
        // Collect all the substrings that do not fall into the above pattern (these will be illegal tokens)
        String[] notMatched = expr.split("[0-9]+|\\+|\\-|\\*|\\/|\\(|\\)");
        String[] tokens = new String[expr.length()];
        // Collect all the tokens matched by the regular expression
        Matcher m = expressionRegex.matcher(expr);
        int i = 0;
        for (String s : notMatched) {
            if (!s.trim().equals("")) {
                throw new InvalidExpressionException("Unexpected token \"" + s.trim() + "\"", expr);
            }
        }
        while (m.find()) {
            tokens[i++] = m.group();
        }
        String[] res = new String[i];
        for (i = 0; i < res.length; i++) {
            // if negative numbers are to be parsed, combine adjacent numbers and negative signs, and leave an element as null
            if (handleNeg && tokens[i].equals("-") && (i == 0 || tokens[i-1].matches("\\(|\\)|\\*|\\/|\\+|\\-")) && i < tokens.length-1) {
                res[i] = tokens[i] + tokens[i+1];
                i++;
            } else {
                res[i] = tokens[i];
            }
        }
        return res;
    }

    /**
     * Constructs a new tree based on an input string
     * @throws InvalidExpressionException when the input string is not a valid expression
     * @constructor
     * @param expr - the input expression string in infix notation
     */
    public BinaryExpressionTree (String expr) throws InvalidExpressionException {
        int i = 0;
        original = expr;
        index = 0;
        parenCount = 0;

        expressArray = tokenize(expr, false);

        // start parsing
        root = expression();

        // didn't finish parsing
        if (index != expressArray.length) {
            throw new InvalidExpressionException("Unexpected token: \"" + nextToken(false) + "\"", expr);
        }

        // parenthesis count is still non-zero
        if (parenCount != 0) {
            throw new InvalidExpressionException("Parenthesis mismatch", expr);
        }
    }

    /**
     * Parses the input string (at the current index) as an expression
     * @throws InvalidExpressionException when it encounters a close-parenthesis without a previous open-parenthesis
     * @returns a node containing the expression
     */
    public Node expression () throws InvalidExpressionException {
        // <Term> [<<+|-> <Term>> ...]
        String operator;
        Node left, right;
        left = term();
        while (true) {
            if (nextToken(false).equals(")")) {
                nextToken(true);
                if (parenCount > 0) {
                    parenCount--;
                    break;
                } else {
                    throw new InvalidExpressionException("Parenthesis unmatched", original);
                }
            } else {
                if (nextToken(false).equals("+") || nextToken(false).equals("-")) {
                    operator = nextToken(true);
                    right = term();
                    left = new Node(operator, left, right);
                } else {
                    break;
                }
            }
        }
        return left;
    }

    /**
     * Parses the input string at the current index as a term
     * @throws InvalidExpressionException when factor needs to bubble an exception up
     */
    public Node term () throws InvalidExpressionException {
        // <Factor> [<</|*> <Factor>> ...]
        String operator;
        Node left, right;
        left = factor();
        while (true) {
            if (nextToken(false).equals("*") || nextToken(false).equals("/")) {
                operator = nextToken(true);
                right = factor();
                left = new Node(operator, left, right);
            } else {
                break;
            }
        };
        return left;
    }

    /**
     * Parses the input string at the current index as a factor
     * @throws InvalidExpressionException when the string at this index is not a numbers, a negative factor, or an expression
     */
    public Node factor () throws InvalidExpressionException{
        // <Integer | -<Factor> | <(<Expr>)>>
        String next = nextToken(true);
        if (next.matches("[0-9]+")) {
            return new Node(next);
        } else if (next.equals("-")) {
            Node n = factor();
            return new Node("*", new Node("-1"), n);
        } else if (next.equals("(")) {
            parenCount++;
            return expression();
        } else {
            throw new InvalidExpressionException("Factor level is not num, -factor, or (expr)", original);
        }
    }

    /**
     * Retrieves the next token and updates the index
     * @return an empty string if there are no more tokens, or the next token
     */
    public String nextToken(boolean move) {
        if (index >= expressArray.length) {
            return "";
        }
        if (move) {
            return expressArray[index++];
        } else {
            return expressArray[index];
        }
    }

    /**
     * Getter function for the BET root node
     * @returns the root node
     */
    public Node getRoot () {
        return root;
    }

    /**
     * Getter function for the original input string
     * @returns the input string
     */
    public String getOriginal () {
        return original;
    }
}
