package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public class Course {
    private String courseId;
    private Program program;
    private LocalDate startDate;
    private LocalDate endDate;
    private Teacher teacher;
    private ArrayList<Student> students;
    private int maxStudents;

    public Course(String courseId, Program program, LocalDate startDate, LocalDate endDate, Teacher teacher, int maxStudents) {
        this.courseId = courseId;
        this.program = program;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
        this.students = new ArrayList();
        this.maxStudents = maxStudents;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Program getProgram() {
        return this.program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public int getMaxStudents() {
        return this.maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public boolean addStudent(Student student) {
        if (this.students.size() < this.maxStudents && !this.students.contains(student)) {
            this.students.add(student);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStudent(Student student) {
        return this.students.remove(student);
    }

    public boolean assignTeacher(Teacher newTeacher) {
        if (newTeacher != null) {
            this.teacher = newTeacher;
            return true;
        } else {
            return false;
        }
    }
}