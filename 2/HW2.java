import java.io.IOException;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HW2 {
    public static void main (String[] argv) {
        File in;
        PrintStream out = null;
        Scanner sc = null;

        try {
            in = new File(argv[0]);
            sc = new Scanner(in);
            out = new PrintStream(argv[1]);

            sc.useDelimiter(Pattern.compile("\n\n+"));
            while (sc.hasNext()) {
                String[] op = sc.next().split("\n");
                if (op.length != 3) {
                    System.err.println("Error: Invalid operation:\n" + String.join("\n", op));
                    out.println();
                } else {
                    try {
                        Polynomial a = new Polynomial(op[0]);
                        Polynomial b;
                        switch (op[1]) {
                            case "+":
                                b = new Polynomial(op[2]);
                                out.println(a.add(b).toString());
                                break;
                            case "-":
                                b = new Polynomial(op[2]);
                                out.println(a.subtract(b).toString());
                                break;
                            case "@":
                                out.println(a.evaluate(Integer.parseInt(op[2])));
                                break;
                            default:
                                System.err.println("Error: unsuported operation: " + op[1]);
                                out.println();
                        }
                    } catch (PLLException e) {
                        System.err.println("Error: " + e);
                        out.println();
                    } catch (NumberFormatException e) {
                        System.err.println("Error: " + e);
                        out.println();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e);
        } finally {
            if (sc != null) {
                sc.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
