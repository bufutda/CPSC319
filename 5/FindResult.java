/**
 * Container for results of a find operation on a HashTable
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since April, 2016
 */
public class FindResult {
    /**
     * The HashTable entry
     */
    public TableEntry result;

    /**
     * The number of probes it took to get the result
     */
    public int probes;

    /**
     * Whether or not the result was in the table
     */
    public boolean found;

    /**
     * Constructs a new FindResult object
     * @constructor
     * @param r - the entry of the table
     * @param p - the number of probes it took
     * @param f - whether or not the result is valid
     */
    public FindResult(TableEntry r, int p, boolean f) {
        result = r;
        probes = p;
        found = f;
    }
}
