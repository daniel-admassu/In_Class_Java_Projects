/**
 * holds all the attributes a student has.
 * @param id the id number of a student.
 * @param lastName the last name of a student
 * @param firstName the first name of a student
 * @param email the email of a student
 * @param phone the phone number of a student
 */

public record Student  (String id, String lastName, String firstName, String email, String phone)
        implements Comparable<Student> {



    public Student{
        if(id==null){
        throw new IllegalArgumentException("id must not be null");
        }
        if(lastName==null){
        throw new IllegalArgumentException("last name must not be null");
        }
        if(firstName==null){
        throw new IllegalArgumentException("first name must not be null");
        }
        if(email==null){
        throw new IllegalArgumentException("email must not be null");
        }
        if(phone==null){
        throw new IllegalArgumentException("phone must not be null");
        }
        }


    @Override
    public int compareTo(Student other){
        return this.lastName.compareTo(other.lastName);
    }
}

