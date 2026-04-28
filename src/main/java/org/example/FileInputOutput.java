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
    public FileInputOutput() {
    }

    public static ArrayList<String> textFileRead(String path) throws IOException {
        ArrayList<String> buffer = new ArrayList();
        BufferedReader fileIn = new BufferedReader(new FileReader(path));

        String line;
        try {
            while((line = fileIn.readLine()) != null) {
                buffer.add(line);
            }
        } catch (Throwable var6) {
            try {
                fileIn.close();
            } catch (Throwable var5) {
                var6.addSuppressed(var5);
            }

            throw var6;
        }

        fileIn.close();
        return buffer;
    }

    public static boolean textFileWrite(String path, ArrayList<String> data) {
        try {
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(path, false));

            boolean var9;
            try {
                Iterator var3 = data.iterator();

                while(true) {
                    if (!var3.hasNext()) {
                        var9 = true;
                        break;
                    }

                    String line = (String)var3.next();
                    fileOut.write(line);
                    fileOut.newLine();
                }
            } catch (Throwable var6) {
                try {
                    fileOut.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            fileOut.close();
            return var9;
        } catch (IOException var7) {
            IOException e = var7;
            System.err.println("Ошибка записи в файл " + path + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean studentsImport(ArrayList<String> source, ArrayList<Student> destination) {
        if (source != null && destination != null) {
            destination.clear();
            Iterator var2 = source.iterator();

            while(var2.hasNext()) {
                String line = (String)var2.next();
                if (line != null && !line.trim().isEmpty()) {
                    String[] parts = line.split(";");
                    if (parts.length != 9) {
                        System.err.println("Некорректная строка студента (пропущено): " + line);
                    } else {
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
                            Student s = new Student(lastName, firstName, middleName, birthDate, gender, phone, email, studentId, registrationDate);
                            destination.add(s);
                        } catch (Exception var15) {
                            Exception e = var15;
                            System.err.println("Ошибка парсинга строки студента: " + line + " -> " + e.getMessage());
                        }
                    }
                }
            }

            return !destination.isEmpty();
        } else {
            return false;
        }
    }

    public static ArrayList<String> studentsExport(ArrayList<Student> source) {
        ArrayList<String> result = new ArrayList();
        if (source == null) {
            return result;
        } else {
            Iterator var2 = source.iterator();

            while(var2.hasNext()) {
                Student s = (Student)var2.next();
                String line = String.join(";", s.getStudentId(), s.getLastName(), s.getFirstName(), s.getMiddleName(), s.getBirthDate().toString(), s.getGender(), s.getPhone(), s.getEmail(), s.getRegistrationDate().toString());
                result.add(line);
            }

            return result;
        }
    }

    public static boolean teachersImport(ArrayList<String> source, ArrayList<Teacher> destination) {
        if (source != null && destination != null) {
            destination.clear();
            Iterator var2 = source.iterator();

            while(var2.hasNext()) {
                String line = (String)var2.next();
                if (line != null && !line.trim().isEmpty()) {
                    String[] parts = line.split(";");
                    if (parts.length != 10) {
                        System.err.println("Некорректная строка преподавателя (пропущено): " + line);
                    } else {
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
                            Teacher t = new Teacher(lastName, firstName, middleName, birthDate, gender, phone, email, teacherId, hireDate, specialization);
                            destination.add(t);
                        } catch (Exception var16) {
                            Exception e = var16;
                            System.err.println("Ошибка парсинга строки преподавателя: " + line + " -> " + e.getMessage());
                        }
                    }
                }
            }

            return !destination.isEmpty();
        } else {
            return false;
        }
    }

    public static ArrayList<String> teachersExport(ArrayList<Teacher> source) {
        ArrayList<String> result = new ArrayList();
        if (source == null) {
            return result;
        } else {
            Iterator var2 = source.iterator();

            while(var2.hasNext()) {
                Teacher t = (Teacher)var2.next();
                String line = String.join(";", t.getTeacherId(), t.getLastName(), t.getFirstName(), t.getMiddleName(), t.getBirthDate().toString(), t.getGender(), t.getPhone(), t.getEmail(), t.getHireDate().toString(), t.getSpecialization());
                result.add(line);
            }

            return result;
        }
    }

    public static boolean programsImport(ArrayList<String> source, ArrayList<Program> destination) {
        if (source != null && destination != null) {
            destination.clear();
            Iterator var2 = source.iterator();

            while(var2.hasNext()) {
                String line = (String)var2.next();
                if (line != null && !line.trim().isEmpty()) {
                    String[] parts = line.split(";");
                    if (parts.length != 5) {
                        System.err.println("Некорректная строка программы (пропущено): " + line);
                    } else {
                        try {
                            String programId = parts[0];
                            String name = parts[1];
                            String description = parts[2];
                            int durationHours = Integer.parseInt(parts[3]);
                            String category = parts[4];
                            Program p = new Program(programId, name, description, durationHours, category);
                            destination.add(p);
                        } catch (Exception var11) {
                            Exception e = var11;
                            System.err.println("Ошибка парсинга строки программы: " + line + " -> " + e.getMessage());
                        }
                    }
                }
            }

            return !destination.isEmpty();
        } else {
            return false;
        }
    }

    public static ArrayList<String> programsExport(ArrayList<Program> source) {
        ArrayList<String> result = new ArrayList();
        if (source == null) {
            return result;
        } else {
            Iterator var2 = source.iterator();

            while(var2.hasNext()) {
                Program p = (Program)var2.next();
                String line = String.join(";", p.getProgramId(), p.getName(), p.getDescription(), String.valueOf(p.getDurationHours()), p.getCategory());
                result.add(line);
            }

            return result;
        }
    }
}