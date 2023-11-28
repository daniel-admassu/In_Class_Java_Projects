import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testToString() {
        Customer test = new Customer("1234", "Daniel", "Admassu",
                "da@gmail.com", 12.55);
        assertEquals(test.toString(),
                "customer(1234) [id = 1234, firstName = Daniel, lastName = Admassu, " +
                        "email = da@gmail.com, balance = 12.55]");
    }

    @Test
    void testEquals() {
        Customer test = new Customer("1234", "Daniel", "Admassu",
                "da@gmail.com", 12.55);
        Customer test2 = new Customer("1234", "Daniel", "Admassu",
                "da@gmail.com", 12.55);
        Customer test3 = new Customer("1235", "Daniel", "Admassu",
                "da@gmail.com", 12.55);
        assertTrue(test.equals(test2));
        assertFalse(test.equals(test3));

    }
}