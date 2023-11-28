import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class StudentManager implements StudentManagerInterface  {


    /**  an array with all the course names   */
    private final String[] courses;
    /**  an array of arrays with the outer corresponding with the course names
     * list of students names in the class in each course   */
    private final Student[][] clas;

    /**
     * reads the file creates an appropriate amount of array to hold courses and students in each course
     * @throws FileNotFoundException throws a helpful message when a file is not found
     */
    public StudentManager() throws FileNotFoundException {

        File file = new File("Courses");
        File file2 = new File("Students");
        Scanner input = new Scanner(file);
        Scanner input2 = new Scanner(file2);

        String courseAmount = input.nextLine();
        String line1 = input.next();
        String line2 = input.nextLine();
        String line1S = input2.nextLine();
        int x = Integer.parseInt(courseAmount);
        courses = new String[x];
        clas = new Student[x][];
        int i = 0;

        while (input2.hasNextLine()) {

            String word = input.next();
            String[] parts = word.split(",");
            String course = parts[0];
            String studentsNumber = parts[1];
            int y = Integer.parseInt(studentsNumber);
            courses[i] = course;
            clas[i] = new Student[y];

            for (int j = 0; j < y; j++) {
                String studentt = input2.nextLine();
                String[] studentInfo = studentt.split(",", 6);
                String studentId = studentInfo[1];
                String studentLastn = studentInfo[2];
                String studentFirstn = studentInfo[3];
                String studentEmail = studentInfo[4];
                String studentPhone = studentInfo[5];
                Student person = new Student(studentId, studentLastn, studentFirstn, studentEmail, studentPhone);

                clas[i][j] = person;
            }
            i++;

        }
        for (int j = 0; j < courses.length; j++) {

            Arrays.sort(clas[j]);
        }


    }

    @Override
    public int getCourseCount() {

        return courses.length;
    }

    @Override
    public int getStudentCount(int courseIndex) {
        return clas[courseIndex].length;
    }

    @Override
    public int getStudentCount() {
        int allStudents = 0;
        for (int i = 0; i < courses.length; i++) {
            allStudents+=clas[i].length;
        }
        return allStudents;
    }

    @Override
    public int getStudentCount(String courseName) {
        for (int i = 0; i < courses.length; i++) {
            if (courseName.equalsIgnoreCase(courses[i])){
                return clas[i].length;
            }

        }
        return -1;
    }

    @Override
    public String getCourseName(int courseIndex) {

        return courses[courseIndex];
    }

    @Override
    public Student getStudent(int courseIndex, int studentIndex) {
        return clas[courseIndex][studentIndex];
    }

    @Override
    public Student[] getStudents(int courseIndex) {
        Student[] copyOfStudents = new Student[clas[courseIndex].length];

        System.arraycopy(clas[courseIndex], 0, copyOfStudents, 0, clas[courseIndex].length);
        return copyOfStudents;
    }

    @Override
    public int findStudentCourse(String id) {
        for (int i = 0; i < courses.length; i++) {
            for (int j = 0; j < clas[i].length; j++) {
                if (id.equals(clas[i][j].id())){
                    return i;
                }
            }
        }
        return -1;
    }
}

