import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void constructorAndGets() {
        Student testStudent = new Student("00001", "Admassu", "Daniel", "dan@gmial.com", "1234567890");
        assertEquals("00001", testStudent.id());
        assertEquals("Admassu", testStudent.lastName());
        assertEquals("Daniel", testStudent.firstName());
        assertEquals("dan@gmial.com", testStudent.email());
        assertEquals("1234567890", testStudent.phone());
    }

    @Test
    void testToString(){
        Student testStudent = new Student("00001", "Admassu", "Daniel", "dan@gmial.com", "1234567890");
        assertFalse(testStudent.toString().isEmpty());
        assertEquals("Student[id=00001, lastName=Admassu, firstName=Daniel, email=dan@gmial.com, phone=1234567890]", testStudent.toString());
    }
    //-----------------------------------------
    //  Expectation test
    //-----------------------------------------
    @Test
    void constructorPreconditions(){
        assertThrows(IllegalArgumentException.class,
                () -> new Student(null, "admassu", "daniel","@gmial.com","1234567890"));
        assertThrows(IllegalArgumentException.class,
                () -> new Student("0000", "admassu", "daniel",null,"1234567890"));
    }

}

