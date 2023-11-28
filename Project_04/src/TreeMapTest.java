import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TreeMapTest {


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sizeAndClear() {
        TreeMap<Integer,Character> testTree = new TreeMap<>();
        for (int i = 0; i < 11; i++) {
            testTree.put(i,(char)i);
        }
        int size = testTree.size();
        assertEquals(size,11);
        testTree.clear();
        int sizeAfter = testTree.size();
        assertEquals(sizeAfter,0);
    }


    @Test
    void getAndPutAndContainsKey() {
        TreeMap<String,String> testTree = new TreeMap<>();
        testTree.put("1","fragile");
        testTree.put("3","far");
        testTree.put("2","nonvolatile");
        testTree.put("1","longevity");
        assertEquals("longevity",testTree.get("1"));
        assertEquals("far",testTree.get("3"));
        assertSame(true,testTree.containsKey("2"));
        assertFalse(testTree.containsKey("8"));
    }
    @Test
    void toKeyArray() {
        TreeMap<Integer,String> testTree = new TreeMap<>();
        testTree.put(1,"fragile");
        testTree.put(3,"far");
        testTree.put(2,"nonvolatile");
        testTree.put(4,"longevity");
        Integer[] array = new Integer[7];
        Arrays.fill(array, 7);
        testTree.toKeyArray(array);
        assertTrue(testTree.containsKey(array[0]));
        assertTrue(testTree.containsKey(array[3]));
        assertNull(array[4]);
        assertEquals(array[5],7);
        Integer[] array2 = new Integer[2];
        testTree.toKeyArray(array2);
        assertEquals(5,testTree.toKeyArray(array2).length);
        assertEquals(4,testTree.toKeyArray(array2)[3]);
    }
}