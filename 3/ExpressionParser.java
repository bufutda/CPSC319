/**
 * Container for methods relating to expression parsing using BinaryExpressionTrees as sources
 * @author Mitchell Sawatzky
 * @since Mar 2016
 * @version 1.0
 */
public class ExpressionParser {
    /**
     * Evaluates a node
     * @param n - the node to evaluate
     * @returns the value of the node
     */
    private static int evaluateNode (Node n) throws InvalidExpressionException {
        Node left = n.getLeftNode();
        Node right = n.getRightNode();
        if (left == null && right == null) {
            return Integer.parseInt(n.getData());
        }
        switch (n.getData()) {
            case "+":
                return evaluateNode(left) + evaluateNode(right);
            case "-":
                return evaluateNode(left) - evaluateNode(right);
            case "*":
                return evaluateNode(left) * evaluateNode(right);
            case "/":
                return evaluateNode(left) / evaluateNode(right);
            default:
                throw new InvalidExpressionException("Unexpected operator \"" + n.getData() + "\"", "[ INTERNAL ]");
        }
    }

    /**
     * Converts a node to a string
     * @param n - the node to convert
     * @returns the string representation of the node
     */
    private static String nodeToString (Node n) {
        Node left = n.getLeftNode();
        Node right = n.getRightNode();
        if (left == null && right == null) {
            return n.getData();
        }
        return "( " + nodeToString(left) + " " + n.getData() + " " + nodeToString(right) + " )";
    }

    /**
     * Determines if an operator a has a larger precedence than operator b
     * @param a - an operator
     * @param b - an operator
     * @param rev - whether or not to parse for RPN or NPN
     * @returns true if operator a has a higher precendece than b, false otherwise
     */
    private static boolean precedence (String a, String b, boolean rev) {
        // if a is + or |
        if (a.matches("\\+|\\-") || (!rev && a.equals("(")) || (rev && a.equals(")"))) {
            return false;
        }
        return true;
    }

    /**
     * Evaluates the expression in exp
     * @throws InvalidExpressionException when it encounters an unexcpected operator.
     * This should never happen if the BinaryExpressionTree was generated with the BinaryExpressionTree class.
     * @param exp - the tree to evaluate
     * @returns the integer value of the expression
     */
    public static int evaluateExpression (BinaryExpressionTree exp) throws InvalidExpressionException {
        return evaluateNode(exp.getRoot());
    }

    /**
     * Converts a BinaryExpressionTree to a string
     * @param exp - the tree to convert
     * @returns the string representation of the expression
     */
    public static String expressionToString (BinaryExpressionTree exp) {
        return nodeToString(exp.getRoot());
    }

    /**
     * Converts an expression to postfix (RPN) using a shunting algorithm
     * @throws InvalidExpressionException when it encounters an invalid token
     * @param exp - the expression to parse
     * @returns a string representation of the expression in RPN
     */
    public static String toPostfix (BinaryExpressionTree exp) throws InvalidExpressionException {
        String[] tokens = BinaryExpressionTree.tokenize(exp.getOriginal(), true);
        ArrayQueue q = new ArrayQueue(tokens.length);
        ArrayStack s = new ArrayStack(tokens.length);
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] != null) {
                // if the token is any amount of digits, preceded by 0 or 1 "-" character
                if (tokens[i].matches("-?[0-9]+")) {
                    q.enqueue(tokens[i]);
                // if the token is +, -, *, or /
                } else if (tokens[i].matches("\\+|\\-|\\*|\\/")) {
                    while (s.peek() != null && s.peek() != ")" && precedence(s.peek(), tokens[i], false)) {
                        q.enqueue(s.pop());
                    }
                    s.push(tokens[i]);
                } else if (tokens[i].equals("(")) {
                    s.push(tokens[i]);
                } else if (tokens[i].equals(")")) {
                    while (s.peek() != null && !s.peek().equals("(")) {
                        q.enqueue(s.pop());
                    }
                    s.pop();
                }
            }
        }
        while (!s.isEmpty()) {
            q.enqueue(s.pop());
        }
        String postfix = "";
        while (!q.isEmpty()) {
            postfix += " " + q.dequeue();
        }
        return postfix.trim();
    }

    /**
     * Converts an expression to prefix (NPN) using a shunting algorithm
     * @throws InvalidExpressionException when it encounters an invalid token
     * @param exp - the expression to parse
     * @returns a string representation of the expression in NPN
     */
    public static String toPrefix (BinaryExpressionTree exp) throws InvalidExpressionException {
        String[] tokens = BinaryExpressionTree.tokenize(exp.getOriginal(), true);
        ArrayQueue q = new ArrayQueue(tokens.length);
        ArrayStack s = new ArrayStack(tokens.length);
        ArrayStack revStack = new ArrayStack(tokens.length);
        for (int i = tokens.length - 1; i >= 0; i--) {
            if (tokens[i] != null) {
                // if the token is any amount of digits, preceded by 0 or 1 "-" character
                if (tokens[i].matches("-?[0-9]+")) {
                    q.enqueue(tokens[i]);
                // if the token is +, -, *, or /
                } else if (tokens[i].matches("\\+|\\-|\\*|\\/")) {
                    while (s.peek() != null && s.peek() != "(" && precedence(s.peek(), tokens[i], true)) {
                        q.enqueue(s.pop());
                    }
                    s.push(tokens[i]);
                } else if (tokens[i].equals(")")) {
                    s.push(tokens[i]);
                } else if (tokens[i].equals("(")) {
                    while (s.peek() != null && !s.peek().equals(")")) {
                        q.enqueue(s.pop());
                    }
                    s.pop();
                }
            }
        }
        while (!s.isEmpty()) {
            q.enqueue(s.pop());
        }
        while (!q.isEmpty()) {
            revStack.push(q.dequeue());
        }
        String prefix = "";
        while (!revStack.isEmpty()) {
            prefix += " " + revStack.pop();
        }
        return prefix.trim();
    }
}
