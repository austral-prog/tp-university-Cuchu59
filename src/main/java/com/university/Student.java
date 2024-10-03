package com.university;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String email;
    private List<Course> courses;

    // =============== Constructor ========================
    public Student(String name, String email, Course course) {
        this.name = name;
        this.email = email;
        this.courses = new ArrayList<>();
    }
    // ====================================================

    // Settting up al the gets. !============================
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Course> getCourses() { return courses;}
    // ======================================================

//---------------------------------------------------------------------

    // CLASS METHODS ========================================

    // ======================================================

    

}
