package com.university;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Student extends Person implements Processor {
    private List<Course> courses;
    
    private static Set<Student> students_instances = new TreeSet<>(Comparator.comparing(Student::getName));;
    
    public Student(String name, String email) {
        super(name, email); // Inherits from abstract class "Person".
        this.courses = new ArrayList<>();
        students_instances.add(this);
    }

    
    // CLASS METHODS
    public static Set<Student> getAllStudents() { return students_instances; };
    public static Student getStudent(String stu_name) { 
        for (Student stu : students_instances) {
            if(stu.getName().equals(stu_name)){ return stu; }
        }
        return null;
    }
    public static void printStudents() {
        for(Student s : students_instances) {
            System.out.print("||"+s.getName()+"||: ");
            
            for (Course c : s.getCourses()) {                
                System.out.print(c.getName() + ", ");
            }

            System.out.println("\n");
        }
    }
    public static boolean hasStudent(String stu_name) {
        if(stu_name == "") { return false; }
        
        // We create a list of names from the courses.
        List<String> students_names = new ArrayList<>();  
        for(Student av_stu : students_instances) {
            students_names.add(av_stu.getName());
        }

        // Check if course_name is in the list.
        return students_names.contains(stu_name);
    }

    // Instance METHODS
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


    // CSV RELATED
    public List<String[]> process_data() {
        List<String[]> line = new ArrayList<>();
        String[] toAdd = {this.getName(), String.valueOf(this.getCourses().size())};    
        line.add(toAdd);
        
        return line;
    }
    
}
