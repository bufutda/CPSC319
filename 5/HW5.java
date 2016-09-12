import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;

/**
 * Class to read input files and parse them
 * @author Mitchell Sawatzky
 * @since April 2016
 * @version 1.0
 */
public class HW5 {
    /**
     * Program entry point
     * @param argv - the array of command line arguments
     */
    public static void main (String[] argv) {
        boolean minim = false;
        if (argv.length == 3) {
            minim = true;
        } else if (argv.length != 2) {
            System.err.println("Usage: java HW4 <input> <LF>");
            System.exit(1);
        }
        // validate load factor
        try {
            final double LF = Double.parseDouble(argv[1]);
            Scanner sc;
            int lineNum;
            HashTable ht;
            int tableLength;
            int rollingProbes;
            int maxProbe;
            if (LF <= 0.0 || LF >= 1.0) {
                // use the exception handling for invalid load factors
                throw new NumberFormatException(argv[1]);
            }
            try {
                // open files
                sc = new Scanner(new FileInputStream(argv[0]));
                lineNum = 0;

                // obtain word count
                sc.useDelimiter("\\s+");
                while (sc.hasNext()) {
                    sc.next();
                    lineNum++;
                }
                sc.close();
                tableLength = Prime.nextPrime((int)Math.ceil(lineNum / LF));
                ht = new HashTable(tableLength);
                sc = new Scanner(new FileInputStream(argv[0]));
                sc.useDelimiter("\\s+");
                while (sc.hasNext()) {
                    ht.insert(sc.next());
                }
                sc.close();

                sc = new Scanner(new FileInputStream(argv[0]));
                sc.useDelimiter("\\s+");
                rollingProbes = 0;
                maxProbe = 0;
                while (sc.hasNext()) {
                    FindResult res = ht.find(sc.next().hashCode());
                    if (!res.found) {
                        throw new RuntimeException("Element in hash table was not in hash table");
                    }
                    if (res.probes > maxProbe) {
                        maxProbe = res.probes;
                    }
                    rollingProbes += res.probes;
                }
                sc.close();

                if (minim) {
                    System.out.println((rollingProbes / (double)lineNum) + "\t" + ht.loadFactor() + "\t" + (maxProbe / (double)ht.tableSize()) + "\t" + ht.loadFactor());
                } else {
                    System.out.println("    Table Size: " + ht.tableSize());
                    System.out.println("    Load Factor: " + Math.round(ht.loadFactor() * 1000000.0) / 1000000.0);
                    System.out.println("    Average Probes: " + Math.round((rollingProbes / (double)lineNum) * 1000000.0) / 1000000.0);
                    System.out.println("    Cmax|: " + Math.round((maxProbe / (double)ht.tableSize()) * 1000000.0) / 1000000.0);
                }
            } catch (IOException e) {
                System.err.println("Cannot open file: " + argv[0]);
                System.err.println(e);
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Load factor must fall in (0, 1)");
            System.exit(1);
        }
    }
}
