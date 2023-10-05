import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private  String abbreviation;
    private List<Student> students;
    private StudyField studyField;
    private Faculty faculties;

    public Faculty (String name, String abbreviation, List<Student> students, StudyField studyField ){
    this.name = name;
    this.abbreviation = abbreviation;
    this.students = students;
    this.studyField = studyField;
  }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
    public void addStudent(Student student) {
        this.students.add(student);
    }
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public void add(Faculty faculty) {
        this.faculties.add(faculty);
    }


}
