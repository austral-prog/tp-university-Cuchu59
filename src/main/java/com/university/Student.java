package com.university;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Student extends Person implements Processor {
    private List<Course> courses;
    private Set<Evaluation> evaluations;
    

    public Student(String name, String email) {
        super(name, email); // Inherits from abstract class "Person".
        this.courses = new ArrayList<>();
        University.allStudents.add(this);
        evaluations = new HashSet<>(); 
    }

    public List<Course> getCourses() { return courses;}
    public void AddCourse(Course c) { if(!hasCourse(c)) { courses.add(c); } }
    public boolean hasCourse(Course check_course) {
        for(Course course : this.courses) {
            if(course.getName().equals(check_course.getName())) { 
                return true; 
            }
        }
        return false;
    }
    public void AddEvaluation(Evaluation eva) {
        if(!hasEvaluation(eva)) {
            evaluations.add(eva);
        }
    }
    public boolean hasEvaluation(Evaluation in_eva) {
        for(Evaluation eva : evaluations) {
            if(eva.equals(in_eva)) { return true; }
        }
        return false;
    }
    
    // CSV RELATED
    public List<String[]> process_data() {
        List<String[]> line = new ArrayList<>();
        String[] toAdd = {this.getName(), String.valueOf(this.getCourses().size())};    
        line.add(toAdd);
        
        return line;
    }
    
}
