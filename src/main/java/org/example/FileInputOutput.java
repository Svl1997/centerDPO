package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class FileInputOutput {

    // Чтение текстового файла, возвращает список строк
    public static ArrayList<String> textFileRead(String path) throws IOException {
        ArrayList<String> buffer = new ArrayList<>();
        try (BufferedReader fileIn = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = fileIn.readLine()) != null) {
                buffer.add(line);
            }
        }
        return buffer;
    }

    // Запись списка строк в текстовый файл
    public static boolean textFileWrite(String path, ArrayList<String> data) {
        try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(path, false))) {
            for (String line : data) {
                fileOut.write(line);
                fileOut.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + path + ": " + e.getMessage());
            return false;
        }
    }

    // Импорт студентов из строк в список
    public static boolean studentsImport(ArrayList<String> source, ArrayList<Student> destination) {
        if (source == null || destination == null) return false;
        destination.clear();
        for (String line : source) {
            if (line == null || line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length != 9) {
                System.err.println("Некорректная строка студента (пропущено): " + line);
                continue;
            }
            try {
                String studentId = parts[0];
                String lastName = parts[1];
                String firstName = parts[2];
                String middleName = parts[3];
                LocalDate birthDate = LocalDate.parse(parts[4]);
                String gender = parts[5];
                String phone = parts[6];
                String email = parts[7];
                LocalDate registrationDate = LocalDate.parse(parts[8]);
                Student s = new Student(lastName, firstName, middleName, birthDate,
                        gender, phone, email, studentId, registrationDate);
                destination.add(s);
            } catch (Exception e) {
                System.err.println("Ошибка парсинга строки студента: " + line + " -> " + e.getMessage());
            }
        }
        return !destination.isEmpty();
    }

    // Экспорт студентов в список строк
    public static ArrayList<String> studentsExport(ArrayList<Student> source) {
        ArrayList<String> result = new ArrayList<>();
        if (source == null) return result;
        for (Student s : source) {
            String line = String.join(";",
                    s.getStudentId(),
                    s.getLastName(),
                    s.getFirstName(),
                    s.getMiddleName(),
                    s.getBirthDate().toString(),
                    s.getGender(),
                    s.getPhone(),
                    s.getEmail(),
                    s.getRegistrationDate().toString()
            );
            result.add(line);
        }
        return result;
    }

    // Импорт преподавателей из строк в список
    public static boolean teachersImport(ArrayList<String> source, ArrayList<Teacher> destination) {
        if (source == null || destination == null) return false;
        destination.clear();
        for (String line : source) {
            if (line == null || line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length != 10) {
                System.err.println("Некорректная строка преподавателя (пропущено): " + line);
                continue;
            }
            try {
                String teacherId = parts[0];
                String lastName = parts[1];
                String firstName = parts[2];
                String middleName = parts[3];
                LocalDate birthDate = LocalDate.parse(parts[4]);
                String gender = parts[5];
                String phone = parts[6];
                String email = parts[7];
                LocalDate hireDate = LocalDate.parse(parts[8]);
                String specialization = parts[9];
                Teacher t = new Teacher(lastName, firstName, middleName, birthDate,
                        gender, phone, email, teacherId, hireDate, specialization);
                destination.add(t);
            } catch (Exception e) {
                System.err.println("Ошибка парсинга строки преподавателя: " + line + " -> " + e.getMessage());
            }
        }
        return !destination.isEmpty();
    }

    // Экспорт преподавателей в список строк
    public static ArrayList<String> teachersExport(ArrayList<Teacher> source) {
        ArrayList<String> result = new ArrayList<>();
        if (source == null) return result;
        for (Teacher t : source) {
            String line = String.join(";",
                    t.getTeacherId(),
                    t.getLastName(),
                    t.getFirstName(),
                    t.getMiddleName(),
                    t.getBirthDate().toString(),
                    t.getGender(),
                    t.getPhone(),
                    t.getEmail(),
                    t.getHireDate().toString(),
                    t.getSpecialization()
            );
            result.add(line);
        }
        return result;
    }

    // Импорт программ из строк в список
    public static boolean programsImport(ArrayList<String> source, ArrayList<Program> destination) {
        if (source == null || destination == null) return false;
        destination.clear();
        for (String line : source) {
            if (line == null || line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length != 5) {
                System.err.println("Некорректная строка программы (пропущено): " + line);
                continue;
            }
            try {
                String programId = parts[0];
                String name = parts[1];
                String description = parts[2];
                int durationHours = Integer.parseInt(parts[3]);
                String category = parts[4];
                Program p = new Program(programId, name, description, durationHours, category);
                destination.add(p);
            } catch (Exception e) {
                System.err.println("Ошибка парсинга строки программы: " + line + " -> " + e.getMessage());
            }
        }
        return !destination.isEmpty();
    }

    // Экспорт программ в список строк
    public static ArrayList<String> programsExport(ArrayList<Program> source) {
        ArrayList<String> result = new ArrayList<>();
        if (source == null) return result;
        for (Program p : source) {
            String line = String.join(";",
                    p.getProgramId(),
                    p.getName(),
                    p.getDescription(),
                    String.valueOf(p.getDurationHours()),
                    p.getCategory()
            );
            result.add(line);
        }
        return result;
    }
}