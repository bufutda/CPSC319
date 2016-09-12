import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Class to read an input file, parse it, and output to an output file
 * @author Mitchell Sawatzky
 * @since Mar 2016
 * @version 1.0
 */
public class HW3 {
    /**
     * The input Scanner
     */
    public static Scanner sc;

    /**
     * The output PrintWriter
     */
    public static PrintWriter out;

    /**
     * Program entry point
     * @param argv - the array of command line arguments
     */
    public static void main (String[] argv) {
        if (argv.length != 2) {
            System.err.println("Usage: java HW2 <input> <output>");
            System.exit(1);
        }
        try {
            // open files
            sc = new Scanner(new FileInputStream(argv[0]));
            out = new PrintWriter(new FileOutputStream(argv[1]), true);
            int lineNum = 0;
            // while the input file has stuff left
            while (sc.hasNextLine()) {
                String exp = sc.nextLine();
                lineNum++;
                boolean parsed = false;
                // if the line isn't empty
                if (exp != null && !exp.trim().equals("")) {
                    while (sc.hasNextLine()) {
                        String operator = sc.nextLine();
                        lineNum++;
                        // if the line isn't empty
                        if (operator != null && !operator.trim().equals("")) {
                            try {
                                switch (operator.trim()) {
                                    case "i":
                                        out.println(ExpressionParser.expressionToString(new BinaryExpressionTree(exp)));
                                        break;
                                    case "=":
                                        out.println(ExpressionParser.evaluateExpression(new BinaryExpressionTree(exp)));
                                        break;
                                    case "<":
                                        out.println(ExpressionParser.toPrefix(new BinaryExpressionTree(exp)));
                                        break;
                                    case ">":
                                        out.println(ExpressionParser.toPostfix(new BinaryExpressionTree(exp)));
                                        break;
                                    default:
                                        // expression followed by an expression
                                        System.err.println(new Exception("Expected an operator, found \"" + operator.trim() + "\" (" + argv[0] + ": " + lineNum + ")"));
                                        out.println("*** expected an operator ***");
                                        break;
                                }
                            } catch (InvalidExpressionException e) {
                                System.err.print("(" + argv[0] + ": " + (lineNum - 1) + ") ");
                                System.err.println(e);
                                out.println("*** syntax error in expression ***");
                            }
                            parsed = true;
                            break;
                        }
                    }
                    if (!parsed) {
                        // expression followed by EOF
                        System.err.println(new Exception("Expected an operator (" + argv[0] + ": " + lineNum + ")"));
                        out.println("*** expected an operator ***");
                    }
                }
            }
            // close files
            sc.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Cannot open file");
            System.err.println(e);
        }
    }
}
