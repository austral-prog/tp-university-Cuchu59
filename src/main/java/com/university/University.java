package com.university;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class University {
    
    String name;
    Set<Course> courses = new HashSet<Course>();   

    // =============== Constructor ==========================    
    public University(String name) {
        this.name = name;
        this.courses = courses;
    }
    // ======================================================

    // Settting up al the gets. !============================
    public String getName() { return name; };
    public Set<Course> getCourses() { return courses; };

    // ======================================================

    //---------------------------------------------------------------------

    // CLASS METHODS ========================================
    public void AddCourse(Course new_course) { courses.add(new_course); }
    
    public void AddStudentTo(Student student, Course course) {
        for(Course c: courses) {
            if(c.equals(course)) {
                if(!c.hasStudent(student)) {
                    c.AddStudent(student); 
                    student.AddCourse(c);
                }
            }
        }
    }

    public boolean hasCourse(Course course) {
        List<String> courses_names = new ArrayList<>();  
        for(Course av_course : courses) {
            courses_names.add(av_course.getName());
        }

        return courses_names.contains(course.getName());
    }

    public void printInfo() {
        for(Course c : courses) {
            List<Student> students = c.getStudents();
            System.out.print("||"+c.getName()+"||: ");
            
            for (Student s : students) {                
                System.out.print(s.getName() + ", ");
            }
            
            System.out.println("\n");
        }
    }

}
