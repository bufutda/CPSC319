/**
 * HashTable data structure for storing strings
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since April, 2016
 */
public class HashTable {
    /**
     * The table
     */
    private TableEntry[] array;

    /**
     * The total number of entries in the table
     */
    private int entries;

    /**
     * Constructs a new HashTable
     * @constructor
     * @param size - the length of the table
     */
    public HashTable (int size) {
        array = new TableEntry[size];
        entries = 0;
    }

    /**
     * Search for a key in the table
     * @param code - the key to search for
     * @returns a FindResult object with the results of the search
     */
    public FindResult find (int code) {
        int probes = 0;
        int index = Math.abs(code) % array.length;
        int home = index;
        while (true) {
            probes++;
            if (array[index] == null) {
                return new FindResult(new TableEntry(Math.abs(code), null), probes, false);
            }
            if (array[index].getKey() == Math.abs(code)) {
                return new FindResult(array[index], probes, true);
            }
            if (index == array.length - 1) {
                index = 0;
            } else {
                index++;
            }
            if (index == home) {
                return new FindResult(new TableEntry(Math.abs(code), null), probes, false);
            }
        }
    }

    /**
     * Inserts a new string into the table
     * @throws RuntimeException when the table is full
     * @param word - the new string to insert
     */
    public void insert (String word) {
        int index = Math.abs(word.hashCode()) % array.length;
        int home = index;
        while (true) {
            if (array[index] == null || array[index] instanceof DeletedEntry) {
                array[index] = new TableEntry(Math.abs(word.hashCode()), word);
                entries++;
                return;
            }
            if (index == array.length - 1) {
                index = 0;
            } else {
                index++;
            }
            if (index == home) {
                throw new RuntimeException("Table is full");
            }
        }
    }

    /**
     * Remove a key from the table
     * @throws RuntimeException when the key is not in the table
     * @param code - the key to remove
     */
    public void delete (int code) {
        int index = Math.abs(code) % array.length;
        int home = index;
        while (true) {
            if (array[index] == null) {
                throw new RuntimeException("Deleting an entry that is not there");
            }
            if (array[index].getKey() == Math.abs(code)) {
                array[index] = new DeletedEntry();
                entries--;
            }
            if (index == array.length - 1) {
                index = 0;
            } else {
                index++;
            }
            if (index == home) {
                throw new RuntimeException("Deleting an entry that is not there");
            }
        }
    }

    /**
     * Getter function for the table length
     * @returns the table length
     */
    public int tableSize () {
        return array.length;
    }

    /**
     * Getter function for the table load factor
     * @returns the table load factor
     */
    public double loadFactor () {
        return entries / (double)array.length;
    }

    /**
     * Getter function for the table entries
     * @returns the table entries
     */
    public int entries () {
        return entries;
    }

    /**
     * Print a representation of the table to stdout
     */
    public void print () {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                System.out.println("[" + i + "]\t------------");
            } else if (array[i] instanceof DeletedEntry) {
                System.out.println("[" + i + "]\t" + "---DELETED--");
            } else {
                System.out.println("[" + i + "]\t" + array[i].getKey() + " -> " + array[i].getValue() + " (" + (array[i].getKey() % array.length) + ")");
            }
        }
    }
}
