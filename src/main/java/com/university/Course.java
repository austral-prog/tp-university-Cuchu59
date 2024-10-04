package com.university;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private List<Student> students;
    private String name;
    private int students_amount;
    
    // =============== Constructor ==========================
    public Course(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.students_amount = 0;
    }
    // ======================================================

    // Settting up al the gets. !============================
    public List<Student> getStudents() { return students; };
    public String getName() { return name; };
    public int getStudents_amount() { return students_amount; };
    // ======================================================

//---------------------------------------------------------------------

    // CLASS METHODS ========================================
    public void AddStudent(Student new_student) {
        if(new_student != null) {
            students.add(new_student);
            students_amount += 1;
        }
    }

    public void RmvStudent(Student new_student) {
        if(new_student != null) {
            students.remove(new_student);
            students_amount -= 1;
        }
    }
    
    public boolean hasStudent(Student stu) { 
        
        for(Student s : students) {
            if(stu.equals(s)) {
                return true; 
            }
        }
        return false;
    }
    public boolean equals(Course c) {
        return (c.getName()).equals(this.getName());
    }

    // ======================================================
}
