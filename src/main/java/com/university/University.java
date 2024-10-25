package com.university;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class University {
    
    public static Set<Student> allStudents = new TreeSet<>(Comparator.comparing(Student::getName));;
    public  static Set<Course> allCourses = new TreeSet<>(Comparator.comparing(Course::getName));;

    // FOR STUDENTS
    public static Student getStudent(String stu_name) { 
        for (Student stu : allStudents) {
            if(stu.getName().equals(stu_name)){ return stu; }
        }
        return null;
    }
    public static void printStudents() {
        for(Student s : allStudents) {
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
        for(Student av_stu : allStudents) {
            students_names.add(av_stu.getName());
        }

        // Check if course_name is in the list.
        return students_names.contains(stu_name);
    }

    // FOR COURSES
    



    // CREATING
    public static void create_Courses_Students() {
        // revisar si existe el curso
        
        for (int i = 0; i < CSV_Treat.currentCSV_data.size(); i++) {
            // indice 1 = nombre de curso
            String course_name = CSV_Treat.currentCSV_data.get(i)[1];
            // indice 2 = nombre de estudiante
            String stud_name = CSV_Treat.currentCSV_data.get(i)[2];
            // indice 3 = email
            String stud_email = CSV_Treat.currentCSV_data.get(i)[3];

            if(!Course.hasCourse(course_name)) {
                Course new_course = new Course(course_name);            
            }
            
            // revisar si existe el estudiante
            if(!University.hasStudent(stud_name)) {
                Student new_student = new Student(stud_name, stud_email);
            }
            
            Course.AddStudentTo(University.getStudent(stud_name), Course.getCourse(course_name));   
        }
    }
    
    
    public static void create_evaluationsPerCourse() {
        for (Course course : allCourses) {
            course.create_evaluations();
        }
    }
}
