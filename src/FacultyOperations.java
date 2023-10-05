import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacultyOperations {

    public static void createAndAssignStudent(Faculty faculty, Student student) {
        if (faculty.getStudents() == null) {
            faculty.setStudents(new ArrayList<>());
        }
        faculty.getStudents().add(student);
    }

    public static void graduateStudent(Faculty faculty, Student student) {
        List<Student> students = faculty.getStudents();
        if (students != null) {
            students.remove(student);
        }
    }

    public static void displayCurrentEnrolledStudents(Faculty faculty) {
        List<Student> students = faculty.getStudents();
        if (students != null) {
            System.out.println("Currently Enrolled Students in " + faculty.getName() + " Faculty:");
            for (Student student : students) {
                System.out.println("Student Name: " + student.getFirstName() + " " + student.getLastName() +
                        ", Email: " + student.getEmail());
            }
        } else {
            System.out.println("No students currently enrolled in " + faculty.getName() + " Faculty.");
        }
    }

    public static void displayGraduates(Faculty faculty, List<Student> allStudents) {
        System.out.println("Graduates from " + faculty.getName() + " Faculty:");
        for (Student student : allStudents) {
            if (!faculty.getStudents().contains(student)) {
                System.out.println("Student Name: " + student.getFirstName() + " " + student.getLastName() +
                        ", Email: " + student.getEmail());
            }
        }
    }

    public static boolean isStudentInFaculty(Faculty faculty, Student student) {
        return faculty.getStudents() != null && faculty.getStudents().contains(student);
    }

    public static void createAndAssignStudent(Faculty selectedFaculty, String firstName, String lastName, String studentEmail, Date date, Date date1) {

    }
}

