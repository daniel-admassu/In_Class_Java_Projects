import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCourseCount() throws FileNotFoundException {
        StudentManager testManger = new StudentManager();
        assertEquals(15, testManger.getCourseCount());
    }

    @Test
    void getStudentCount() throws FileNotFoundException {
        StudentManager testManger = new StudentManager();
        assertEquals(27, testManger.getStudentCount(1));
        assertEquals(29, testManger.getStudentCount(8));
    }

    @Test
    void testGetStudentCount() throws FileNotFoundException {
        StudentManager testManger = new StudentManager();
        assertEquals(403, testManger.getStudentCount());
    }

    @Test
    void testGetStudentCount1() throws FileNotFoundException {
        StudentManager testManger = new StudentManager();
        assertEquals(30, testManger.getStudentCount("CSC110d"));
        assertEquals(30, testManger.getStudentCount("CSC142c"));
        assertEquals(-1, testManger.getStudentCount("CSC145t"));
    }

    @Test
    void getCourseName() throws FileNotFoundException {
        StudentManager testManger = new StudentManager();
        assertEquals("CSC143c", testManger.getCourseName(14));
        assertEquals("CSC110h", testManger.getCourseName(7));
    }

    @Test
    void getStudent() throws FileNotFoundException {
        StudentManager testManger = new StudentManager();
        Student testStudent = new Student("292217", "Zamora", "Tammy","tzamora22@virginia.edu", "929-256-1434");
        assertEquals(testStudent, testManger.getStudent(2, 23));
    }

    @Test
    void getStudents() throws FileNotFoundException {
        StudentManager testManger = new StudentManager();

        Student[] testArray = new Student[testManger.getStudentCount(11)];
        for (int i = 0; i < testManger.getStudentCount(11); i++) {
            testArray[i]= testManger.getStudent(11, i);

        }
        assertEquals(testArray[0], testManger.getStudents(11)[0]);
        assertEquals(testArray[5], testManger.getStudents(11)[5]);
        assertEquals(testArray[12], testManger.getStudents(11)[12]);


    }

    @Test
    void findStudentCourse() throws FileNotFoundException {
        StudentManager testManger = new StudentManager();
        assertEquals(8, testManger.findStudentCourse("154558"));
        assertEquals(4, testManger.findStudentCourse("146286"));
        assertEquals(-1, testManger.findStudentCourse("00000"));
    }
}