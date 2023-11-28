import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaggedLinkedListTest<E> extends LinkedList {

    @Test
    void getTagLetter() {
        TaggedLinkedList test = new TaggedLinkedList('A');
        assertEquals('A', test.getTagLetter());
    }
}