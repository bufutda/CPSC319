/**
 * Container for entries in a HashTable
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since April, 2016
 */
public class TableEntry {
    /**
     * The hash key
     */
    private int key;

    /**
     * The hash data
     */
    private String value;

    /**
     * Constructs a new TableEntry object
     * @constructor
     * @param key - the key of the data
     * @param value - the data
     */
    public TableEntry (int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Getter function for the key
     * @returns the key
     */
    public int getKey () {
        return key;
    }

    /**
     * Getter function for the value
     * @returns the value
     */
    public String getValue () {
        return value;
    }
}
