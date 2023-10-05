import java.util.List;

public class GeneralOperations {
    private List<Student> students;
    private List<Faculty> faculties;
    private StudyField studyField;

    public GeneralOperations(List<Faculty> faculties) {
        this.students = students;
        this.faculties = faculties;
        this.studyField = studyField;
    }

    // Operation 1: Create a new faculty
    public void createFaculty(String name, String abbreviation, StudyField studyField) {
        Faculty newFaculty = new Faculty(name, abbreviation, null, studyField);
        faculties.add(newFaculty);
    }

    // Operation 2: Search faculty by student's unique identifier (email in this case)
    public Faculty findFacultyByStudentEmail(String email) {
        for (Faculty faculty : faculties) {
            for (Student student : faculty.getStudents()) {
                if (student.getEmail().equals(email)) {
                    return faculty;
                }
            }
        }
        return null; // Return null if no faculty found for the given email
    }

    // Operation 3: Display University faculties
    public void displayFaculties() {
        for (Faculty faculty : faculties) {
            System.out.println("Faculty Name: " + faculty.getName() +
                    ", Abbreviation: " + faculty.getAbbreviation() +
                    ", Study Field: " + faculty.getStudyField());
        }
    }

    // Operation 4: Display faculties belonging to a specific field
    public void displayFacultiesByField(StudyField field) {
        for (Faculty faculty : faculties) {
            if (faculty.getStudyField() == field) {
                System.out.println("Faculty Name: " + faculty.getName() +
                        ", Abbreviation: " + faculty.getAbbreviation());
            }
        }
    }

    public List<Student> getStudents() {
        return students;
    }
}

