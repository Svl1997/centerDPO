package org.example;

import java.time.LocalDate;

public class Student extends Person {
    private String studentId;
    private LocalDate registrationDate;

    public Student(String lastName, String firstName, String middleName, LocalDate birthDate, String gender, String phone, String email, String studentId, LocalDate registrationDate) {
        super(lastName, firstName, middleName, birthDate, gender, phone, email);
        this.studentId = studentId;
        this.registrationDate = registrationDate;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDate getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
