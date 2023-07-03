import java.util.Scanner;

public class StudentManagementSystem {

    private static final int MAX_STUDENTS = 100; // Maximum number of students
    private static final int MAX_SUBJECTS = 2;
    private static final String[] studentIds = new String[MAX_STUDENTS]; // Array to store student IDs
    private static final String[] studentNames = new String[MAX_STUDENTS]; // Array to store student names
    private static final int[][] studentMarks = new int[MAX_STUDENTS][2]; // Array to store student marks
    private static int studentCount = 0; // Current number of students

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("|                      WELCOME TO GDSE MANAGEMENT SYSTEM                     |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("[1]  Add New Student\t\t\t[2]  Add New Student With Marks");
            System.out.println("[3]  Add Marks\t\t\t\t[4]  Update Student Details");
            System.out.println("[5]  Update Marks\t\t\t[6]  Delete Student");
            System.out.println("[7]  Print Student Details\t\t[8]  Print Student Marks");
            System.out.println("[9]  Best in Programming Fundamentals\t[10] Best in Database Management System");
            System.out.println();
            System.out.print("Enter an option to continue: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            clearConsole();

            switch (option) {
                case 1:
                    addNewStudent(scanner);
                    break;
                case 2:
                    addNewStudentWithMarks(scanner);
                    break;
                case 3:
                    addMarks(scanner);
                    break;
                case 4:
                    updateStudentDetails(scanner);
                    break;
                case 5:
                    updateMarks(scanner);
                    break;
                case 6:
                    deleteStudent(scanner);
                    break;
                case 7:
                    printStudentDetails(scanner);
                    break;
                case 8:
                    printStudentRanks(scanner);
                    break;
                case 9:
                    bestInPRF(scanner);
                    break;
                case 10:
                    bestInDBMS(scanner);
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            System.out.println();
        }
    }

    // --------------------------- (1) Add New Student ---------------------------

    private static void addNewStudent(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                               ADD NEW STUDENT                              |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        String studentId;
        while (true) {
            System.out.print("Enter Student ID: ");
            studentId = scanner.nextLine();

            if (containsStudentId(studentId)) {
                System.out.println("Student ID already exists. Please enter a different Student ID.");
            } else {
                break;
            }
        }

        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        studentIds[studentCount] = studentId;
        studentNames[studentCount] = studentName;
        studentCount++;

        System.out.println("New student added successfully.");

        while (true) {
            System.out.print("Do you want to add another student? (Y/N): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y")) {
                clearConsole();
                addNewStudent(scanner);
                return;
            } else if (choice.equalsIgnoreCase("N")) {
                System.out.println("Returning to the main menu.");
                clearConsole();
                return;
            } else {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
        }
    }


    // --------------------------- (2) Add New Student with Marks ---------------------------

    private static void addNewStudentWithMarks(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                      ADD NEW STUDENT WITH MARKS                           |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        String studentId;
        while (true) {
            System.out.print("Enter Student ID: ");
            studentId = scanner.nextLine();

            if (containsStudentId(studentId)) {
                System.out.println("Student ID already exists. Please enter a different Student ID.");
            } else {
                break;
            }
        }

        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        int[] marks = new int[2];
        String[] subjects = { "PRF", "DBMS" };

        for (int i = 0; i < marks.length; i++) {
            while (true) {
                System.out.print("Enter " + subjects[i] + " Marks: ");
                int mark = scanner.nextInt();
                scanner.nextLine(); 

                if (mark < 0 || mark > 100) {
                    System.out.println("Invalid " + subjects[i] + " marks. Marks should be within the range of 0-100.");
                } else {
                    marks[i] = mark;
                    break;
                }
            }
        }

        studentIds[studentCount] = studentId;
        studentNames[studentCount] = studentName;
        studentMarks[studentCount] = marks;
        studentCount++;

        System.out.println("New student with marks added successfully.");

        while (true) {
            System.out.print("Do you want to add another student with marks? (Y/N): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y")) {
                clearConsole();
                addNewStudentWithMarks(scanner); 
                return; 
            } else if (choice.equalsIgnoreCase("N")) {
                System.out.println("Returning to the main menu.");
                clearConsole();
                return; 
            } else {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
        }
    }

    private static boolean containsStudentId(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                return true;
            }
        }
        return false;
    }


    // --------------------------- (3) Add Marks ---------------------------

    private static void addMarks(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                                  ADD MARKS                                 |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        String studentId;
        boolean continueSearch = true;

        while (continueSearch) {
            System.out.print("Enter Student ID: ");
            studentId = scanner.nextLine();

            int studentIndex = -1;

            for (int i = 0; i < studentCount; i++) {
                if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                    studentIndex = i;
                    break;
                }
            }

            if (studentIndex == -1) {
                System.out.println("Invalid Student ID.");
                System.out.print("Continue search? (Y/N): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Returning to the main menu.");
                    clearConsole();
                    return;
                }
            } else if (hasAssignedMarks(studentIndex)) {
                System.out.println("Marks have already been assigned for this student.");
                System.out.print("Continue search? (Y/N): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Returning to the main menu.");
                    clearConsole();
                    return;
                }
            } else {
                String studentName = studentNames[studentIndex];
                System.out.println("Student Name: " + studentName);

                int[] marks = new int[2];
                String[] subjects = { "PRF", "DBMS" };

                for (int i = 0; i < marks.length; i++) {
                    while (true) {
                        System.out.print("Enter " + subjects[i] + " Marks: ");
                        int mark = scanner.nextInt();
                        scanner.nextLine(); 

                        if (mark < 0 || mark > 100) {
                            System.out.println(
                                    "Invalid " + subjects[i] + " marks. Marks should be within the range of 0-100.");
                        } else {
                            marks[i] = mark;
                            break;
                        }
                    }
                }

                studentMarks[studentIndex] = marks;

                System.out.println("Marks added successfully.");

                while (true) {
                    System.out.print("Do you want to add marks for another student? (Y/N): ");
                    String choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase("Y")) {
                        clearConsole();
                        addMarks(scanner); 
                        return; 
                    } else if (choice.equalsIgnoreCase("N")) {
                        System.out.println("Returning to the main menu.");
                        clearConsole();
                        return; 
                    } else {
                        System.out.println("Invalid choice. Please enter Y or N.");
                    }
                }
            }
        }
    }

    private static boolean hasAssignedMarks(int studentIndex) {
        for (int mark : studentMarks[studentIndex]) {
            if (mark != 0) {
                return true;
            }
        }
        return false;
    }


    // --------------------------- (4) Update Student Details ---------------------------

    private static void updateStudentDetails(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                           UPDATE STUDENT DETAILS                           |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        String studentId;
        boolean continueSearch = true;

        while (continueSearch) {
            System.out.print("Enter Student ID: ");
            studentId = scanner.nextLine();

            int studentIndex = -1;

            for (int i = 0; i < studentCount; i++) {
                if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                    studentIndex = i;
                    break;
                }
            }

            if (studentIndex == -1) {
                System.out.println("Invalid Student ID.");
                System.out.print("Continue search? (Y/N): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Returning to the main menu.");
                    clearConsole();
                    return;
                }
            } else {
                String studentName = studentNames[studentIndex];
                System.out.println("Current Student Name: " + studentName);

                System.out.print("Enter New Student Name: ");
                String newStudentName = scanner.nextLine();

                studentNames[studentIndex] = newStudentName;

                System.out.println("Student details updated successfully.");

                while (true) {
                    System.out.print("Do you want to update details for another student? (Y/N): ");
                    String choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase("Y")) {
                        clearConsole();
                        updateStudentDetails(scanner); 
                        return; 
                    } else if (choice.equalsIgnoreCase("N")) {
                        System.out.println("Returning to the main menu.");
                        clearConsole();
                        return; 
                    } else {
                        System.out.println("Invalid choice. Please enter Y or N.");
                    }
                }
            }
        }
    }


    // --------------------------- (5) Update Marks ---------------------------

    private static void updateMarks(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                               UPDATE MARKS                                 |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        String studentId;
        boolean continueSearch = true;

        while (continueSearch) {
            System.out.print("Enter Student ID: ");
            studentId = scanner.nextLine();

            int studentIndex = -1;

            for (int i = 0; i < studentCount; i++) {
                if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                    studentIndex = i;
                    break;
                }
            }

            if (studentIndex == -1) {
                System.out.println("Invalid Student ID.");
                System.out.print("Continue search? (Y/N): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Returning to the main menu.");
                    clearConsole();
                    return;
                }
            } else if (!hasAssignedMarks(studentIndex)) {
                System.out.println("Marks have not been assigned for this student yet.");
                System.out.println("Please add marks for this student before updating.");
                System.out.println();
                continue;
            } else {
                String studentName = studentNames[studentIndex];
                System.out.println("Student Name: " + studentName);
                System.out.println("Current Marks:");

                int[] marks = studentMarks[studentIndex];
                String[] subjects = { "PRF", "DBMS" };

                for (int i = 0; i < marks.length; i++) {
                    System.out.println(subjects[i] + ": " + marks[i]);
                }

                System.out.println();

                for (int i = 0; i < marks.length; i++) {
                    while (true) {
                        System.out.print("Enter New " + subjects[i] + " Marks: ");
                        int mark = scanner.nextInt();
                        scanner.nextLine(); 

                        if (mark < 0 || mark > 100) {
                            System.out.println(
                                    "Invalid " + subjects[i] + " marks. Marks should be within the range of 0-100.");
                        } else {
                            marks[i] = mark;
                            break;
                        }
                    }
                }

                studentMarks[studentIndex] = marks;

                System.out.println("Marks updated successfully.");

                while (true) {
                    System.out.print("Do you want to update marks for another student? (Y/N): ");
                    String choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase("Y")) {
                        clearConsole();
                        updateMarks(scanner); 
                        return; 
                    } else if (choice.equalsIgnoreCase("N")) {
                        System.out.println("Returning to the main menu.");
                        clearConsole();
                        return; 
                    } else {
                        System.out.println("Invalid choice. Please enter Y or N.");
                    }
                }
            }
        }
    }


    // --------------------------- (6) Delete Student ---------------------------

    private static void deleteStudent(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                              DELETE STUDENT                                |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        String studentId;
        boolean continueSearch = true;

        while (continueSearch) {
            System.out.print("Enter Student ID: ");
            studentId = scanner.nextLine();

            int studentIndex = -1;

            for (int i = 0; i < studentCount; i++) {
                if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                    studentIndex = i;
                    break;
                }
            }

            if (studentIndex == -1) {
                System.out.println("Invalid Student ID.");
                System.out.print("Continue search? (Y/N): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Returning to the main menu.");
                    clearConsole();
                    return;
                }
            } else {
                String studentName = studentNames[studentIndex];
                System.out.println("Student Name: " + studentName);
                System.out.print("Are you sure you want to delete this student? (Y/N): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("Y")) {
                    // Delete student (array)
                    for (int i = studentIndex; i < studentCount - 1; i++) {
                        studentIds[i] = studentIds[i + 1];
                        studentNames[i] = studentNames[i + 1];
                        studentMarks[i] = studentMarks[i + 1];
                    }

                    // Reset the last element to null
                    studentIds[studentCount - 1] = null;
                    studentNames[studentCount - 1] = null;
                    studentMarks[studentCount - 1] = null;

                    studentCount--;

                    System.out.println("Student deleted successfully.");
                }

                while (true) {
                    System.out.print("Do you want to delete another student? (Y/N): ");
                    choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase("Y")) {
                        clearConsole();
                        deleteStudent(scanner);
                        return;
                    } else if (choice.equalsIgnoreCase("N")) {
                        System.out.println("Returning to the main menu.");
                        clearConsole();
                        return; 
                    } else {
                        System.out.println("Invalid choice. Please enter Y or N.");
                    }
                }
            }
        }
    }


    // --------------------------- (7) Print Student Details ---------------------------

    private static void printStudentDetails(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                            PRINT STUDENT MARKS                             |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        String studentId;
        boolean continueSearch = true;

        while (continueSearch) {
            System.out.print("Enter Student ID: ");
            studentId = scanner.nextLine();

            int studentIndex = -1;

            for (int i = 0; i < studentCount; i++) {
                if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                    studentIndex = i;
                    break;
                }
            }

            if (studentIndex == -1) {
                System.out.println("Invalid Student ID.");
                System.out.print("Continue search? (Y/N): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Returning to the main menu.");
                    clearConsole();
                    return;
                }
            } else if (!hasAssignedMarks(studentIndex)) {
                System.out.println("Marks have not been assigned for this student yet.");
                System.out.println("Please add marks for this student before updating.");
                System.out.println();
                continue;
            } else {
                String studentName = studentNames[studentIndex];
                System.out.println("Student Name: " + studentName);
                System.out.println();
                System.out.println("---------------------------------------");
                System.out.println("------------ Current Marks ------------");

                int[] marks = studentMarks[studentIndex];
                String[] subjects = { "PRF", "DBMS" };

                for (int i = 0; i < marks.length; i++) {
                    System.out.println(subjects[i] + ": " + marks[i]);
                }

                int totalMarks = calculateTotalMarks(studentIndex);
                double averageMarks = calculateAverageMarks(studentIndex);
                int rank = calculateRank(studentIndex);

                System.out.println("Total Marks: " + totalMarks);
                System.out.println("Average Marks: " + averageMarks);
                System.out.print("Rank: " + rank);
                switch (rank) {
                    case 1:
                        System.out.print(" (First)");
                        break;
                    case 2:
                        System.out.print(" (Second)");
                        break;
                    case 3:
                        System.out.print(" (Third)");
                        break;
                    case MAX_STUDENTS:
                        System.out.print(" (Last)");
                        break;
                }
                System.out.println();
                System.out.println("---------------------------------------");
            }

            while (true) {
                System.out.print("Do you want to continue seeking student details? (Y/N): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("Y")) {
                    clearConsole();
                    printStudentDetails(scanner);
                    return;
                } else if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Returning to the main menu.");
                    clearConsole();
                    return;
                } else {
                    System.out.println("Invalid choice. Please enter Y or N.");
                }
            }
        }
    }

    private static int calculateTotalMarks(int studentIndex) {
        int totalMarks = 0;
        for (int i = 0; i < MAX_SUBJECTS; i++) {
            totalMarks += studentMarks[studentIndex][i];
        }
        return totalMarks;
    }

    private static double calculateAverageMarks(int studentIndex) {
        int totalMarks = calculateTotalMarks(studentIndex);
        return (double) totalMarks / MAX_SUBJECTS;
    }

    private static int calculateRank(int studentIndex) {
        int studentTotalMarks = calculateTotalMarks(studentIndex);
        int rank = 1;

        for (int i = 0; i < studentCount; i++) {
            if (i != studentIndex) {
                int otherStudentTotalMarks = calculateTotalMarks(i);
                if (otherStudentTotalMarks > studentTotalMarks) {
                    rank++;
                }
            }
        }
        return rank;
    }


    // --------------------------- (8) Print Student Ranks ---------------------------

    private static void printStudentRanks(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                            PRINT STUDENT RANKS                             |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        if (studentCount == 0) {
            System.out.println("No students found in the system.");
            System.out.println();
            return;
        }

        boolean[] visited = new boolean[studentCount]; // track visited students (array)
        int remainingStudents = studentCount; // Count of remaining unvisited students

        System.out.println("Rank\tStudent ID\tStudent Name\tTotal Marks\tAverage Marks");
        System.out.println();

        while (remainingStudents > 0) {
            int minRank = Integer.MAX_VALUE;
            int minRankIndex = -1;

            // Find the student with the lowest rank among unvisited students
            for (int i = 0; i < studentCount; i++) {
                if (!visited[i] && calculateRank(i) < minRank) {
                    minRank = calculateRank(i);
                    minRankIndex = i;
                }
            }

            // Print the details of the student with the lowest rank
            int totalMarks = calculateTotalMarks(minRankIndex);
            double averageMarks = calculateAverageMarks(minRankIndex);
            System.out.println(minRank + "\t" + studentIds[minRankIndex] + "\t\t" + studentNames[minRankIndex] + "\t\t" +
                            totalMarks + "\t\t" + averageMarks);

            // Mark the student as visited
            visited[minRankIndex] = true;
            remainingStudents--;
        }

        System.out.println();

        while (true) {
            System.out.print("Do you want to go back to main menu? (Y/N): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y")) {
                System.out.println("Returning to the main menu.");
                clearConsole();
                return;
            } else if (choice.equalsIgnoreCase("N")) {
                clearConsole();
                printStudentRanks(scanner);
                return;
            } else {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
        }
    }


    // --------------------------- (9) Best in PRF Module ---------------------------

    private static void bestInPRF(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                            BEST IN PRF MODULE                              |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        if (studentCount == 0) {
            System.out.println("No students found in the system.");
            System.out.println();
            return;
        }

        boolean[] visited = new boolean[studentCount]; // track visited students (array)
        int remainingStudents = studentCount; // Count of remaining unvisited students

        System.out.println("ID\tName\t\tPRF Marks\tDBMS Marks");
        System.out.println();

        while (remainingStudents > 0) {
            int maxPRF = -1;
            int maxPRFIndex = -1;

            // Find the student with the highest PRF marks among unvisited students
            for (int i = 0; i < studentCount; i++) {
                if (!visited[i] && studentMarks[i][0] > maxPRF) {
                    maxPRF = studentMarks[i][0];
                    maxPRFIndex = i;
                }
            }

            // Print the details of the student with the highest PRF marks
            System.out.println(studentIds[maxPRFIndex] + "\t" + studentNames[maxPRFIndex] + "\t\t" +
                    studentMarks[maxPRFIndex][0] + "\t\t" + studentMarks[maxPRFIndex][1]);

            // Mark the student as visited
            visited[maxPRFIndex] = true;
            remainingStudents--;
        }

        System.out.println();

        while (true) {
            System.out.print("Do you want to go back to main menu? (Y/N): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y")) {
                System.out.println("Returning to the main menu.");
                clearConsole();
                return;
            } else if (choice.equalsIgnoreCase("N")) {
                clearConsole();
                bestInPRF(scanner);
                return;
            } else {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
        }
    }


    // --------------------------- (10) Best in DBMS Module ---------------------------

    private static void bestInDBMS(Scanner scanner) {
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("|                            BEST IN DBMS MODULE                             |");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();

        if (studentCount == 0) {
            System.out.println("No students found in the system.");
            System.out.println();
            return;
        }

        boolean[] visited = new boolean[studentCount]; // track visited students (array)
        int remainingStudents = studentCount; // Count of remaining unvisited students

        System.out.println("ID\tName\t\tPRF Marks\tDBMS Marks");
        System.out.println();

        while (remainingStudents > 0) {
            int maxDBMS = -1;
            int maxDBMSIndex = -1;

            // Find the student with the highest DBMS marks among unvisited students
            for (int i = 0; i < studentCount; i++) {
                if (!visited[i] && studentMarks[i][1] > maxDBMS) {
                    maxDBMS = studentMarks[i][1];
                    maxDBMSIndex = i;
                }
            }

            // Print the details of the student with the highest DBMS marks
            System.out.println(studentIds[maxDBMSIndex] + "\t" + studentNames[maxDBMSIndex] + "\t\t" +
                    studentMarks[maxDBMSIndex][0] + "\t\t" + studentMarks[maxDBMSIndex][1]);

            // Mark the student as visited
            visited[maxDBMSIndex] = true;
            remainingStudents--;
        }

        System.out.println();

        while (true) {
            System.out.print("Do you want to go back to main menu? (Y/N): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("Y")) {
                System.out.println("Returning to the main menu.");
                clearConsole();
                return;
            } else if (choice.equalsIgnoreCase("N")) {
                clearConsole();
                bestInDBMS(scanner);
                return;
            } else {
                System.out.println("Invalid choice. Please enter Y or N.");
            }
        }
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }
}