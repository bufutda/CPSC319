/**
 * Specialized TableEntry for when elements need to be deleted
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since April, 2016
 */
public class DeletedEntry extends TableEntry {
    /**
     * Constructs a new DeletedEntry object.
     * @constructor
     */
    public DeletedEntry () {
        super(-1, null);
    }
}
