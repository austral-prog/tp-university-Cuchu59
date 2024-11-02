package com.university;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Student extends Person implements Processor {
    private Set<Evaluation> evaluations;
    
    public Student(String name, String email) {
        super(name, email); // Inherits from abstract class "Person".
        evaluations = new HashSet<>(); 
        University.allStudents.add(this);
    }
    
    public Set<Evaluation> getEvaluations() { return evaluations; }
    public Evaluation getEvaluation(String eva_name) {
        for(Evaluation eva : evaluations) {
            if(eva.getName().equals(eva_name)) { return eva;}
        } 
        return null;
    }
    
    public void AddEvaluation(Evaluation eva) { if(!hasEvaluation(eva.getName())) { evaluations.add(eva); } }
    public boolean hasEvaluation(String eva_name) { 
        for(Evaluation eva : evaluations) {
            if(eva.getName().equals(eva_name)) {
                return true; 
            }
        }
        return false;
    }
    
    // public float EvaluationMethod(String type, Evaluation eva, Student stud) {
    //     List<Float> scores = eva.getScores().get(stud.getName());
    //     if(type.equals("WRITTEN_EXAM`")) {
    //         Float average = 0f; 
    //         for(Float n : scores) {
    //             average += n;
    //         }
    //         average /= scores.size();
    //         return average;
    //     } 
    //     if(type.equals("FINAL_PRACTICAL_WORK")) {
    //         Float sum = 0f;
    //         for(Float n : scores) {
    //             sum += n;
    //         } 
    //         return sum; 
    //     }
    //     if(type.equals("PRACTICAL_WORK")) {
    //         return scores.getLast();
    //     }
    //     if(type.equals("ORAL_EXAM")) {
    //         return scores.get(0);
    //     }
    //     return -0.1f;
    // }
    

    // CSV RELATED
    public List<String[]> process_data() {
        List<String[]> line = new ArrayList<>();
        String[] toAdd = {this.getName(), String.valueOf(University.getStudentCourses(this).size())};
        line.add(toAdd);
        
        return line;
    }
    
}
