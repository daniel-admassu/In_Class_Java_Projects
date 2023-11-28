/**
 * a subclass of linked list with an addition of filed tag which is a character.
 */
public class TaggedLinkedList<E> extends LinkedList implements TaggedLinkedListInterface {

    /** A charcter to help sort the customers in to the right list */
    private final char tag;

    public TaggedLinkedList(char tag) {
        this.tag = tag;
    }

    @Override
    public char getTagLetter() {
        return tag;
    }
}
