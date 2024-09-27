package com.university;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String email;
    private List<Subject> subjects;
    private Course course;

    // =============== Constructor ========================
    public Student(String name, String email, Course course) {
        this.name = name;
        this.email = email;
        this.course = course;
        this.subjects = new ArrayList<>();
    }
    // ====================================================

    // Settting up al the gets. !============================
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Subject> getSubjects() { return subjects; }
    public Course getCourse() { return course; }
    // ======================================================

//---------------------------------------------------------------------

    // CLASS METHODS ========================================

    // ======================================================

    

}
