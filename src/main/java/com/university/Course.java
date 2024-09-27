package com.university;

import java.util.List;

public class Course {
    private List<Student> students;
    private String name;
    private int max_students;
    private int students_amount;
    
    // =============== Constructor ========================
    public Course(List<Student> students, String name, int max_students) {
        this.students = students;
        this.name = name;
        this.max_students = max_students;

        if(students.equals(List.of())) { this.students_amount = 0;}
        else {
            for(Student st : students) {
                students_amount += 1;
            }
        }
    }
    // ====================================================

    public List<Student> getStudents() { return students; };
    public String getName() { return name; };
    public int getStudents_amount() { return students_amount; };
    
}
