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
        if(course != null) { courses.add(course); }
        
    }
    // ====================================================

    // Settting up al the gets. !============================
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Course> getCourses() { return courses;}
    // ======================================================

//---------------------------------------------------------------------

    // CLASS METHODS ========================================
    public boolean equals(Student stu) {
        return (stu.getName()).equals(this.getName());
    }

    public void AddCourse(Course c) {
        if(!hasCourse(c)) {courses.add(c);}
        
    }
    public boolean hasCourse(Course course) {
        List<String> courses_names = new ArrayList<>();  
        for(Course av_course : courses) {
            courses_names.add(av_course.getName());
        }

        return courses_names.contains(course.getName());
    }
    // ======================================================

    

}
