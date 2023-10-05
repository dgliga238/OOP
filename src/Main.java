import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Faculty> faculties = new ArrayList<>();
        GeneralOperations generalOperations = new GeneralOperations(faculties);



        System.out.println("Welcome to the TUM Board Management System!");

        int choice;
        do {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create a new faculty");
            System.out.println("2. Search faculty by student email");
            System.out.println("3. Display faculties by field");
            System.out.println("4. Add a student to a faculty");
            System.out.println("5. Graduate a student from a faculty");
            System.out.println("6. Display current enrolled students");
            System.out.println("7. Display graduates");
            System.out.println("8. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Create a new faculty
                    System.out.println("Enter faculty name:");
                    String facultyName = scanner.nextLine();

                    System.out.println("Enter faculty abbreviation:");
                    String facultyAbbreviation = scanner.nextLine();

                    System.out.println("Choose study field:");
                    System.out.println("1. MECHANICAL_ENGINEERING");
                    System.out.println("2. SOFTWARE_ENGINEERING");
                    System.out.println("3. FOOD_TECHNOLOGY");
                    System.out.println("4. URBANISM_ARCHITECTURE");
                    System.out.println("5. VETERINARY_MEDICINE");
                    StudyField studyField = StudyField.values()[scanner.nextInt() - 1];

                    generalOperations.createFaculty(facultyName, facultyAbbreviation, studyField);
                    System.out.println("New faculty created successfully!");
                    break;

                case 2:
                    // Search faculty by student email
                    System.out.println("Enter student email:");
                    scanner.nextLine(); // Consume newline
                    String email = scanner.nextLine();
                    generalOperations.findFacultyByStudentEmail(email);
                    break;

                case 3:
                    // Display faculties by field
                    System.out.println("Choose study field:");
                    System.out.println("1. MECHANICAL_ENGINEERING");
                    System.out.println("2. SOFTWARE_ENGINEERING");
                    System.out.println("3. FOOD_TECHNOLOGY");
                    System.out.println("4. URBANISM_ARCHITECTURE");
                    System.out.println("5. VETERINARY_MEDICINE");
                    StudyField field = StudyField.values()[scanner.nextInt() - 1];
                    generalOperations.displayFacultiesByField(field);
                    break;

                case 4:
                    // Add a student to a faculty
                    System.out.println("Enter faculty name:");
                    scanner.nextLine(); // Consume newline
                    String facultyToAddStudent = scanner.nextLine();

                    Faculty selectedFaculty = null;
                    for (Faculty faculty : faculties) {
                        if (faculty.getName().equals(facultyToAddStudent)) {
                            selectedFaculty = faculty;
                            break;
                        }
                    }

                    if (selectedFaculty != null) {
                        System.out.println("Enter student first name:");
                        String firstName = scanner.nextLine();
                        System.out.println("Enter student last name:");
                        String lastName = scanner.nextLine();
                        System.out.println("Enter student email:");
                        String studentEmail = scanner.nextLine();
                        // You can input enrollment date and date of birth similarly

                        // Create a new Student object
                        Student newStudent = new Student(firstName, lastName, studentEmail, new Date(), new Date());

                        // Assign the student to the faculty using the existing method
                        FacultyOperations.createAndAssignStudent(selectedFaculty, newStudent);

                        System.out.println("Student added to the faculty successfully!");
                    } else {
                        System.out.println("Faculty not found.");
                    }
                    break;

                case 5:
                    // Graduate a student from a faculty
                    System.out.println("Enter faculty name:");
                    scanner.nextLine(); // Consume newline
                    String facultyToGraduateStudent = scanner.nextLine();

                    Faculty facultyToGraduateFrom = null;
                    for (Faculty faculty : faculties) {
                        if (faculty.getName().equals(facultyToGraduateStudent)) {
                            facultyToGraduateFrom = faculty;
                            break;
                        }
                    }

                    if (facultyToGraduateFrom != null) {
                        System.out.println("Enter student email:");
                        String studentEmail = scanner.nextLine();

                        Student studentToGraduate = null;
                        for (Student student : facultyToGraduateFrom.getStudents()) {
                            if (student.getEmail().equals(studentEmail)) {
                                studentToGraduate = student;
                                break;
                            }
                        }

                        if (studentToGraduate != null) {
                            FacultyOperations.graduateStudent(facultyToGraduateFrom, studentToGraduate);
                            System.out.println("Student graduated from the faculty successfully!");
                        } else {
                            System.out.println("Student not found in the specified faculty.");
                        }
                    } else {
                        System.out.println("Faculty not found.");
                    }
                    break;

                case 6:
                    // Display current enrolled students
                    System.out.println("Enter faculty name:");
                    scanner.nextLine(); // Consume newline
                    String facultyToDisplayEnrolledStudents = scanner.nextLine();

                    Faculty facultyToDisplayEnrolledStudentsFrom = null;
                    for (Faculty faculty : faculties) {
                        if (faculty.getName().equals(facultyToDisplayEnrolledStudents)) {
                            facultyToDisplayEnrolledStudentsFrom = faculty;
                            break;
                        }
                    }

                    if (facultyToDisplayEnrolledStudentsFrom != null) {
                        FacultyOperations.displayCurrentEnrolledStudents(facultyToDisplayEnrolledStudentsFrom);
                    } else {
                        System.out.println("Faculty not found.");
                    }
                    break;

                case 7:
                    // Display graduates
                    System.out.println("Enter faculty name:");
                    scanner.nextLine(); // Consume newline
                    String facultyToDisplayGraduates = scanner.nextLine();

                    Faculty facultyToDisplayGraduatesFrom = null;
                    for (Faculty faculty : faculties) {
                        if (faculty.getName().equals(facultyToDisplayGraduates)) {
                            facultyToDisplayGraduatesFrom = faculty;
                            break;
                        }
                    }

                    if (facultyToDisplayGraduatesFrom != null) {
                        FacultyOperations.displayGraduates(facultyToDisplayGraduatesFrom, generalOperations.getStudents());
                    } else {
                        System.out.println("Faculty not found.");
                    }
                    break;

                case 8:
                    // Exit the program
                    System.out.println("Exiting the TUM Board Management System. Goodbye!");
                    break;

            }
        }while (choice != 8);
    }
}
