import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Class to read input files and parse them
 * @author Mitchell Sawatzky
 * @since Mar 2016
 * @version 1.0
 */
public class HW4 {
    /**
     * The input Scanner
     */
    public static Scanner sc;

    /**
     * The query Scanner
     */
     public static Scanner qy;

    /**
     * The output PrintWriter
     */
    public static PrintWriter out;

    /**
     * The Dijkstra output printwriter
     */
    public static PrintWriter out2;


    /**
     * Program entry point
     * @param argv - the array of command line arguments
     */
    public static void main (String[] argv) throws InputLengthException, DuplicateEdgeException, UnexpectedTokenException {
        if (argv.length != 3 && argv.length != 4) {
            System.err.println("Usage: java HW3 <maze> <query> <output1> [output2]");
            System.exit(1);
        }
        try {
            // open files
            sc = new Scanner(new FileInputStream(argv[0]));
            qy = new Scanner(new FileInputStream(argv[1]));
            out = new PrintWriter(new FileOutputStream(argv[2]), true);
            if (argv.length == 4) {
                out2 = new PrintWriter(new FileOutputStream(argv[3]), true);
            }
            int lineNum = 0;
            int mazeSize = 0;
            AdjacencyList graph;
            //parse maze file
            try {
                String size = sc.nextLine().trim();
                lineNum++;
                // ensure line one is an integer parseable
                if (size.matches("^[0-9]+$")) {
                    mazeSize = Integer.parseInt(size);
                } else {
                    System.err.println("Invalid maze size number (" + size + ") at " + argv[0] + ":" + lineNum);
                    System.exit(1);
                }
            } catch (NoSuchElementException e) {
                System.err.println("Could not read mazeInput");
                System.err.println(e);
                System.exit(1);
            }
            if (mazeSize > 0) {
                graph = new AdjacencyList(mazeSize);
                // while the input file has stuff left
                System.out.println("Parsing Maze Input: " + argv[0]);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    lineNum++;
                    // if the line isn't empty
                    if (line != null && !line.trim().equals("")) {
                        try {
                            graph.parseAdjacency(line);
                        } catch (InputLengthException e) {
                            System.err.println("\nInputLengthException at " + argv[0] + ":" + lineNum + "\nLine must be in the form <fromVertex> <toVertex> <weight>, but is \"" + e.getMessage() + "\"");
                            System.exit(1);
                        } catch (DuplicateEdgeException e) {
                            System.err.println("\nDuplicateEdgeException at " + argv[0] + ":" + lineNum + "\n" + e.getMessage());
                            System.exit(1);
                        } catch (UnexpectedTokenException e) {
                            System.err.println("\nUnexpectedTokenException at " + argv[0] + ":" + lineNum + "\nUnexpected token: " + e.getMessage());
                            System.exit(1);
                        }
                    }
                }
                // while the query input has stuff left
                lineNum = 0;
                System.out.println("Parsing Query Input: " + argv[1]);
                while (qy.hasNextLine()) {
                    String line = qy.nextLine();
                    // split string by any amount of whitespace
                    String[] parts = line.trim().split("\\s+");
                    lineNum++;
                    if (parts.length != 2) {
                        System.err.println("InputLengthException at " + argv[1] + ":" + lineNum + "\nLine must be in the form <fromVertex> <toVertex>, but is \"" + line + "\"");
                        out.println("InputLengthException at " + argv[1] + ":" + lineNum + ":\tLine must be in the form <fromVertex> <toVertex>, but is \"" + line + "\"");
                        if (argv.length == 4) {
                            out2.println("InputLengthException at " + argv[1] + ":" + lineNum + ":\tLine must be in the form <fromVertex> <toVertex>, but is \"" + line + "\"");
                        }
                    } else {
                        // make sure each token is an integer parseable
                        if (parts[0].matches("^[0-9]+$")) {
                            if (parts[1].matches("^[0-9]+$")) {
                                out.println(graph.BFS(Integer.parseInt(parts[0]) - 1, Integer.parseInt(parts[1]) - 1));
                                if (argv.length == 4) {
                                    out2.println(graph.dij(Integer.parseInt(parts[0]) - 1, Integer.parseInt(parts[1]) - 1));
                                }
                            } else {
                                System.err.println("UnexpectedTokenException at " + argv[1] + ":" + lineNum + "\nUnexpected token: " + parts[1]);
                                out.println("UnexpectedTokenException at " + argv[1] + ":" + lineNum + ":  \"" + parts[1] + "\"");
                                if (argv.length == 4) {
                                    out2.println("UnexpectedTokenException at " + argv[1] + ":" + lineNum + ":  \"" + parts[1] + "\"");
                                }
                            }
                        } else {
                            System.err.println("UnexpectedTokenException at " + argv[1] + ":" + lineNum + "\nUnexpected token: " + parts[0]);
                            out.println("UnexpectedTokenException at " + argv[1] + ":" + lineNum + ":  \"" + parts[0] + "\"");
                            if (argv.length == 4) {
                                out.println("UnexpectedTokenException at " + argv[1] + ":" + lineNum + ":  \"" + parts[0] + "\"");
                            }
                        }
                    }
                }
            } else {
                System.err.println("Maze size must be bigger than 0");
                System.exit(1);
            }
            // close files
            sc.close();
            qy.close();
            out.close();
            if (argv.length == 4) {
                out2.close();
            }
        } catch (IOException e) {
            System.err.println("Cannot open file");
            System.err.println(e);
        }
    }
}
