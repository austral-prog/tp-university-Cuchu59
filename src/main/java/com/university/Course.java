package com.university;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Course implements Processor{
    private Set<Student> students;
    private String name;
    

    // =============== Constructor ==========================
    public Course(String name) {
        this.name = name;
        this.students = new TreeSet<>(Comparator.comparing(Student::getName));;
        University.allCourses.add(this);
    }
    // ======================================================
    
    //---------------------------------------------------------------------
    
    // Settting up al the gets. !============================
    public String getName() { return name; };
    public Set<Student> getStudents() { return students; };
    
    // ======================================================

    // CLASS METHODS ========================================
    public void AddStudent(Student new_student) {
        if(new_student != null) {
            students.add(new_student);
        }
    }
    public boolean hasStudent(Student stu) {
        if(stu == null) { return false; }
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
    // CSV RELATED
    public List<String[]> process_data() {
        
        List<String[]> lines = new ArrayList<>();
        
        // Para cada estudiante de la universidad
        for(Student stud : students) {
            for (Evaluation evaluation : stud.getEvaluations()) {
                
                if(!stud.hasEvaluation(evaluation.getName())) { continue; }
                Float note = 2f;
                String[] toAdd = {this.getName(), evaluation.getName(), stud.getName(), note.toString()};            
                lines.add(toAdd);
            }
        }
        
        return lines;
    }

    public void create_evaluations(){ Creator.create_evaluations_for(this); }

    
}
