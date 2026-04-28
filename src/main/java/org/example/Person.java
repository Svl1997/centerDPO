package org.example;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    protected String lastName;
    protected String firstName;
    protected String middleName;
    protected LocalDate birthDate;
    protected String gender;
    protected String phone;
    protected String email;

    public Person(String lastName, String firstName, String middleName, LocalDate birthDate, String gender, String phone, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return this.lastName + " " + this.firstName + " " + this.middleName;
    }

    public int getAge() {
        return this.birthDate == null ? 0 : Period.between(this.birthDate, LocalDate.now()).getYears();
    }
}
