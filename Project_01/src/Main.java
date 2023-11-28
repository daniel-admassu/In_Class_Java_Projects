import java.io.FileNotFoundException;


public class Main {
    /**
     * Runnable entry point for the code
     * @param args Command-line parameters; unused
     * @throws FileNotFoundException throws a helpful message when a file is not found
     * prints to console few of the out for some methods
     */
    public static void main(String[] args) throws FileNotFoundException {

        StudentManager sm = new StudentManager();

        System.out.println(sm.getStudent(14, 26));
        System.out.println(sm.getCourseName(3));
        System.out.println(sm.getStudentCount());
        System.out.println(sm.getStudentCount(2));
        for (int i = 0; i < sm.getStudentCount(1); i++) {
            System.out.println(sm.getStudents(1)[i]);
        }

    }
}
