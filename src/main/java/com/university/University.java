package com.university;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class University {
    
    public static Set<Student> allStudents = new TreeSet<>(Comparator.comparing(Student::getName));;
    public static Set<Course> allCourses = new TreeSet<>(Comparator.comparing(Course::getName));;
    
    // FOR STUDENTS
    public static Student getStudent(String stu_name) {
        
        for (Student stu : allStudents) {
            if(stu.getName().equals(stu_name)){ return stu; }
        }
        return null;
    }
    public static boolean hasStudent(String stu_name) {
        if(stu_name == "") { return false; }
        
        // We create a list of names from the courses.
        List<String> students_names = new ArrayList<>();  
        for(Student av_stu : allStudents) {
            students_names.add(av_stu.getName());
        }

        // Check if course_name is in the list.
        return students_names.contains(stu_name);
    }
    public static Course getCourse(String name) { 
        for (Course course : allCourses) {
            if(course.getName().equals(name)){ return course; }
        }
        return null;
    }
    public static boolean hasCourse(String course_name) {
        if(course_name == "") { return false; }
        
        // We create a list of names from the courses.
        List<String> courses_names = new ArrayList<>();  
        for(Course av_course : University.allCourses) {
            courses_names.add(av_course.getName());
        }
        
        // Check if course_name is in the list.
        return courses_names.contains(course_name);
    }
    public static void AddStudentTo(Student student, Course course) {    
        for(Course c : University.allCourses) {
            
            if(c.equals(course)) {
                if(!c.hasStudent(student)) {
                    c.AddStudent(student); 
                }
            }
        }
    }
    public static Set<Course> getStudentCourses(Student student) {
        Set<Course> stud_courses = new TreeSet<>(Comparator.comparing(Course::getName));;
        for(Course c : allCourses) {
            if(c.hasStudent(student)) {stud_courses.add(c);}
        }
        return stud_courses;
    }
    
   
}
