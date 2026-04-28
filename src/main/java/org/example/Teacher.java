package org.example;

import java.time.LocalDate;

public class Teacher extends Person {
    private String teacherId;
    private LocalDate hireDate;
    private String specialization;

    public Teacher(String lastName, String firstName, String middleName, LocalDate birthDate, String gender, String phone, String email, String teacherId, LocalDate hireDate, String specialization) {
        super(lastName, firstName, middleName, birthDate, gender, phone, email);
        this.teacherId = teacherId;
        this.hireDate = hireDate;
        this.specialization = specialization;
    }

    public String getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
