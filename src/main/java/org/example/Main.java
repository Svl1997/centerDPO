package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private static ArrayList<Program> programs = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String STUDENTS_FILE = "students.txt";
    private static final String TEACHERS_FILE = "teachers.txt";
    private static final String PROGRAMS_FILE = "programs.txt";

    public static void main(String[] args) {


        importData();

        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addTeacher();
                case 3 -> addProgram();
                case 4 -> addCourse();
                case 5 -> {
                    exportData();
                    exit = true;
                }
                default -> System.out.println("Неверный пункт. Повторите.");
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
        System.out.print("Фамилия: "); String lastName = scanner.nextLine();
        System.out.print("Имя: "); String firstName = scanner.nextLine();
        System.out.print("Отчество: "); String middleName = scanner.nextLine();
        System.out.print("Дата рождения (ГГГГ-ММ-ДД): "); LocalDate birth = LocalDate.parse(scanner.nextLine());
        System.out.print("Пол: "); String gender = scanner.nextLine();
        System.out.print("Телефон: "); String phone = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Student ID: "); String studentId = scanner.nextLine();
        System.out.print("Дата регистрации (ГГГГ-ММ-ДД): "); LocalDate regDate = LocalDate.parse(scanner.nextLine());

        Student s = new Student(lastName, firstName, middleName, birth, gender, phone, email, studentId, regDate);
        students.add(s);
        System.out.println("Студент добавлен. Всего студентов: " + students.size());
    }

    private static void addTeacher() {
        System.out.println("--- Добавление преподавателя ---");
        System.out.print("Фамилия: "); String lastName = scanner.nextLine();
        System.out.print("Имя: "); String firstName = scanner.nextLine();
        System.out.print("Отчество: "); String middleName = scanner.nextLine();
        System.out.print("Дата рождения (ГГГГ-ММ-ДД): "); LocalDate birth = LocalDate.parse(scanner.nextLine());
        System.out.print("Пол: "); String gender = scanner.nextLine();
        System.out.print("Телефон: "); String phone = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Teacher ID: "); String teacherId = scanner.nextLine();
        System.out.print("Дата приема (ГГГГ-ММ-ДД): "); LocalDate hire = LocalDate.parse(scanner.nextLine());
        System.out.print("Специализация: "); String spec = scanner.nextLine();

        Teacher t = new Teacher(lastName, firstName, middleName, birth, gender, phone, email, teacherId, hire, spec);
        teachers.add(t);
        System.out.println("Преподаватель добавлен.");
    }

    private static void addProgram() {
        System.out.println("--- Добавление программы обучения ---");
        System.out.print("Program ID: "); String id = scanner.nextLine();
        System.out.print("Наименование: "); String name = scanner.nextLine();
        System.out.print("Описание: "); String desc = scanner.nextLine();
        System.out.print("Продолжительность (часов): "); int hours = scanner.nextInt(); scanner.nextLine();
        System.out.print("Категория: "); String cat = scanner.nextLine();

        Program p = new Program(id, name, desc, hours, cat);
        programs.add(p);
        System.out.println("Программа добавлена.");
    }

    private static void addCourse() {
        System.out.println("--- Добавление курса ---");
        System.out.print("Course ID: "); String courseId = scanner.nextLine();

        // Выбор программы
        if (programs.isEmpty()) {
            System.out.println("Сначала добавьте хотя бы одну программу.");
            return;
        }
        System.out.println("Список программ:");
        for (int i = 0; i < programs.size(); i++) {
            System.out.println(i + ": " + programs.get(i).getName());
        }
        System.out.print("Выберите номер программы: ");
        int progIndex = scanner.nextInt(); scanner.nextLine();
        Program selectedProgram = programs.get(progIndex);

        System.out.print("Дата начала (ГГГГ-ММ-ДД): "); LocalDate start = LocalDate.parse(scanner.nextLine());
        System.out.print("Дата окончания (ГГГГ-ММ-ДД): "); LocalDate end = LocalDate.parse(scanner.nextLine());

        // Выбор преподавателя
        if (teachers.isEmpty()) {
            System.out.println("Сначала добавьте преподавателя.");
            return;
        }
        System.out.println("Список преподавателей:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println(i + ": " + teachers.get(i).getFullName());
        }
        System.out.print("Выберите номер преподавателя: ");
        int teacherIndex = scanner.nextInt(); scanner.nextLine();
        Teacher selectedTeacher = teachers.get(teacherIndex);

        System.out.print("Максимальное число студентов: ");
        int max = scanner.nextInt(); scanner.nextLine();

        Course course = new Course(courseId, selectedProgram, start, end, selectedTeacher, max);
        courses.add(course);
        System.out.println("Курс добавлен.");
    }

    // Импорт данных из файлов
    private static void importData() {
        System.out.println("Загрузка данных из файлов...");
        try {
            ArrayList<String> studentLines = FileInputOutput.textFileRead(STUDENTS_FILE);
            FileInputOutput.studentsImport(studentLines, students);
            System.out.println("Загружено студентов: " + students.size());
        } catch (IOException e) {
            System.out.println("Файл студентов не найден, будет создан при экспорте.");
        }

        try {
            ArrayList<String> teacherLines = FileInputOutput.textFileRead(TEACHERS_FILE);
            FileInputOutput.teachersImport(teacherLines, teachers);
            System.out.println("Загружено преподавателей: " + teachers.size());
        } catch (IOException e) {
            System.out.println("Файл преподавателей не найден, будет создан при экспорте.");
        }

        try {
            ArrayList<String> programLines = FileInputOutput.textFileRead(PROGRAMS_FILE);
            FileInputOutput.programsImport(programLines, programs);
            System.out.println("Загружено программ: " + programs.size());
        } catch (IOException e) {
            System.out.println("Файл программ не найден, будет создан при экспорте.");
        }
    }

    // Экспорт данных в файлы
    private static void exportData() {
        System.out.println("Сохранение данных в файлы...");
        ArrayList<String> studentOut = FileInputOutput.studentsExport(students);
        if (FileInputOutput.textFileWrite(STUDENTS_FILE, studentOut)) {
            System.out.println("Студенты сохранены (" + studentOut.size() + " записей)");
        } else {
            System.err.println("Ошибка сохранения студентов");
        }

        ArrayList<String> teacherOut = FileInputOutput.teachersExport(teachers);
        if (FileInputOutput.textFileWrite(TEACHERS_FILE, teacherOut)) {
            System.out.println("Преподаватели сохранены (" + teacherOut.size() + " записей)");
        } else {
            System.err.println("Ошибка сохранения преподавателей");
        }

        ArrayList<String> programOut = FileInputOutput.programsExport(programs);
        if (FileInputOutput.textFileWrite(PROGRAMS_FILE, programOut)) {
            System.out.println("Программы сохранены (" + programOut.size() + " записей)");
        } else {
            System.err.println("Ошибка сохранения программ");
        }
    }
}