public class Main {
    public static void main(String[] args) throws Exception {
        String folderPath = "C:\\Users\\dglig\\IdeaProjects\\lab_1\\lab_OOP3\\TestFolder";
        ProgramLoop monitor = new ProgramLoop(folderPath);
        while (true) {
            System.out.println("Actions:");
            System.out.println("1. commit");
            System.out.println("2. info <filename>");
            System.out.println("3. status\n");

            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String[] choice = scanner.nextLine().split(" ", 2);

            switch (choice[0]) {
                case "commit":
                    monitor.commit();
                    System.out.println("Snapshot created.");
                    break;
                case "info":
                    if (choice.length != 2) {
                        System.out.println("Invalid input. Usage: info <filename>");
                    } else {
                        monitor.info(choice[1]);
                    }
                    break;
                case "status":
                    monitor.status();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

