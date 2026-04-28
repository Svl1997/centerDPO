
package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> students = new ArrayList();
    private static ArrayList<Teacher> teachers = new ArrayList();
    private static ArrayList<Program> programs = new ArrayList();
    private static ArrayList<Course> courses = new ArrayList();
    private static Scanner scanner;
    private static final String STUDENTS_FILE = "students.txt";
    private static final String TEACHERS_FILE = "teachers.txt";
    private static final String PROGRAMS_FILE = "programs.txt";

    public Main() {
    }

    public static void main(String[] args) {
        importData();
        boolean exit = false;

        while(!exit) {
            printMenu();
            int choice = readInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addTeacher();
                    break;
                case 3:
                    addProgram();
                    break;
                case 4:
                    addCourse();
                    break;
                case 5:
                    exportData();
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный пункт. Повторите.");
            }
        }

        System.out.println("Программа завершена.");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Добавить студента");
        System.out.println("2. Добавить преподавателя");
        System.out.println("3. Добавить программу обучения");
        System.out.println("4. Добавить курс");
        System.out.println("5. Выход");
        System.out.print("Ваш выбор: ");
    }

    private static int readInt() {
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }

    private static void addStudent() {
        System.out.println("--- Добавление студента ---");
        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();
        System.out.print("Имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Отчество: ");
        String middleName = scanner.nextLine();
        System.out.print("Дата рождения (ГГГГ-ММ-ДД): ");
        LocalDate birth = LocalDate.parse(scanner.nextLine());
        System.out.print("Пол: ");
        String gender = scanner.nextLine();
        System.out.print("Телефон: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Дата регистрации (ГГГГ-ММ-ДД): ");
        LocalDate regDate = LocalDate.parse(scanner.nextLine());
        Student s = new Student(lastName, firstName, middleName, birth, gender, phone, email, studentId, regDate);
        students.add(s);
        System.out.println("Студент добавлен. Всего студентов: " + students.size());
    }

    private static void addTeacher() {
        System.out.println("--- Добавление преподавателя ---");
        System.out.print("Фамилия: ");
        String lastName = scanner.nextLine();
        System.out.print("Имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Отчество: ");
        String middleName = scanner.nextLine();
        System.out.print("Дата рождения (ГГГГ-ММ-ДД): ");
        LocalDate birth = LocalDate.parse(scanner.nextLine());
        System.out.print("Пол: ");
        String gender = scanner.nextLine();
        System.out.print("Телефон: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teacher ID: ");
        String teacherId = scanner.nextLine();
        System.out.print("Дата приема (ГГГГ-ММ-ДД): ");
        LocalDate hire = LocalDate.parse(scanner.nextLine());
        System.out.print("Специализация: ");
        String spec = scanner.nextLine();
        Teacher t = new Teacher(lastName, firstName, middleName, birth, gender, phone, email, teacherId, hire, spec);
        teachers.add(t);
        System.out.println("Преподаватель добавлен.");
    }

    private static void addProgram() {
        System.out.println("--- Добавление программы обучения ---");
        System.out.print("Program ID: ");
        String id = scanner.nextLine();
        System.out.print("Наименование: ");
        String name = scanner.nextLine();
        System.out.print("Описание: ");
        String desc = scanner.nextLine();
        System.out.print("Продолжительность (часов): ");
        int hours = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Категория: ");
        String cat = scanner.nextLine();
        Program p = new Program(id, name, desc, hours, cat);
        programs.add(p);
        System.out.println("Программа добавлена.");
    }

    private static void addCourse() {
        System.out.println("--- Добавление курса ---");
        System.out.print("Course ID: ");
        String courseId = scanner.nextLine();
        if (programs.isEmpty()) {
            System.out.println("Сначала добавьте хотя бы одну программу.");
        } else {
            System.out.println("Список программ:");

            int progIndex;
            for(progIndex = 0; progIndex < programs.size(); ++progIndex) {
                System.out.println("" + progIndex + ": " + ((Program)programs.get(progIndex)).getName());
            }

            System.out.print("Выберите номер программы: ");
            progIndex = scanner.nextInt();
            scanner.nextLine();
            Program selectedProgram = (Program)programs.get(progIndex);
            System.out.print("Дата начала (ГГГГ-ММ-ДД): ");
            LocalDate start = LocalDate.parse(scanner.nextLine());
            System.out.print("Дата окончания (ГГГГ-ММ-ДД): ");
            LocalDate end = LocalDate.parse(scanner.nextLine());
            if (teachers.isEmpty()) {
                System.out.println("Сначала добавьте преподавателя.");
            } else {
                System.out.println("Список преподавателей:");

                int teacherIndex;
                for(teacherIndex = 0; teacherIndex < teachers.size(); ++teacherIndex) {
                    System.out.println("" + teacherIndex + ": " + ((Teacher)teachers.get(teacherIndex)).getFullName());
                }

                System.out.print("Выберите номер преподавателя: ");
                teacherIndex = scanner.nextInt();
                scanner.nextLine();
                Teacher selectedTeacher = (Teacher)teachers.get(teacherIndex);
                System.out.print("Максимальное число студентов: ");
                int max = scanner.nextInt();
                scanner.nextLine();
                Course course = new Course(courseId, selectedProgram, start, end, selectedTeacher, max);
                courses.add(course);
                System.out.println("Курс добавлен.");
            }
        }
    }

    private static void importData() {
        System.out.println("Загрузка данных из файлов...");

        ArrayList programLines;
        try {
            programLines = FileInputOutput.textFileRead("students.txt");
            FileInputOutput.studentsImport(programLines, students);
            System.out.println("Загружено студентов: " + students.size());
        } catch (IOException var3) {
            System.out.println("Файл студентов не найден, будет создан при экспорте.");
        }

        try {
            programLines = FileInputOutput.textFileRead("teachers.txt");
            FileInputOutput.teachersImport(programLines, teachers);
            System.out.println("Загружено преподавателей: " + teachers.size());
        } catch (IOException var2) {
            System.out.println("Файл преподавателей не найден, будет создан при экспорте.");
        }

        try {
            programLines = FileInputOutput.textFileRead("programs.txt");
            FileInputOutput.programsImport(programLines, programs);
            System.out.println("Загружено программ: " + programs.size());
        } catch (IOException var1) {
            System.out.println("Файл программ не найден, будет создан при экспорте.");
        }

    }

    private static void exportData() {
        System.out.println("Сохранение данных в файлы...");
        ArrayList<String> studentOut = FileInputOutput.studentsExport(students);
        if (FileInputOutput.textFileWrite("students.txt", studentOut)) {
            System.out.println("Студенты сохранены (" + studentOut.size() + " записей)");
        } else {
            System.err.println("Ошибка сохранения студентов");
        }

        ArrayList<String> teacherOut = FileInputOutput.teachersExport(teachers);
        if (FileInputOutput.textFileWrite("teachers.txt", teacherOut)) {
            System.out.println("Преподаватели сохранены (" + teacherOut.size() + " записей)");
        } else {
            System.err.println("Ошибка сохранения преподавателей");
        }

        ArrayList<String> programOut = FileInputOutput.programsExport(programs);
        if (FileInputOutput.textFileWrite("programs.txt", programOut)) {
            System.out.println("Программы сохранены (" + programOut.size() + " записей)");
        } else {
            System.err.println("Ошибка сохранения программ");
        }

    }

    static {
        scanner = new Scanner(System.in);
    }
}
