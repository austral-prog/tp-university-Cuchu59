package com.university;

import java.util.List;

public class Course {
    private List<Student> students;
    private String name;
    private int max_students;
    private int students_amount;
    
    // =============== Constructor ==========================
    public Course(List<Student> students, String name, int max_students) {
        this.name = name;
        this.max_students = max_students;

        if(students.equals(List.of())) { this.students_amount = 0;}
        else {
            for(Student st : students) {
                this.AddStudent(st);
            }
        }
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
        if(max_students <= students_amount) { return; }
        if(new_student != null) {
            students.add(new_student);
            students_amount += 1;
        }
    }

    public void RmvStudent(Student new_student) {
        if(max_students <= students_amount) { return; }
        if(new_student != null) {
            students.remove(new_student);
            students_amount -= 1;
        }
    }
    

    // ======================================================
}
