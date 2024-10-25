package com.university;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Course implements Processor{
    private Set<Student> students;
    private String name;
    private int students_amount;
    private Set<Evaluation> evaluations = new TreeSet<>(Comparator.comparing(Evaluation::getName));;
    

    // =============== Constructor ==========================
    public Course(String name) {
        this.name = name;
        this.students = new TreeSet<>(Comparator.comparing(Student::getName));;
        this.students_amount = 0;
        University.allCourses.add(this);
    }
    // ======================================================
    

    // CLASS METHODS. !=====================================
    public static Set<Course> getAllCourses() { return University.allCourses; }
    public static Course getCourse(String name) { 
        for (Course course : University.allCourses) {
            if(course.getName().equals(name)){ return course; }
        }
        return null;
    }    
    public static void AddStudentTo(Student student, Course course) {    
        for(Course c : University.allCourses) {
            if(c.equals(course)) {
                if(!c.hasStudent(student)) {
                    c.AddStudent(student); 
                    student.AddCourse(c);
                }
            }
        }
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
    public static void printCourses() {
        for(Course c : University.allCourses) {
            Set<Student> studs = c.getStudents();
            System.out.print("||"+c.getName()+"||: ");
            
            for (Student s : studs) {                
                System.out.print(s.getName() + ", ");
            }
            
            System.out.println("\n");
        }
    }
    
    //---->> CREATING FROM CSV INPUTS ===================================
    public void create_evaluations() {
    
        for (int i = 0; i < CSV_Treat.currentCSV_data.size(); i++) {
            // indice 0 = student_name
            String stud_name = CSV_Treat.currentCSV_data.get(i)[0];
            // indice 1 = course_name
            String course_name = CSV_Treat.currentCSV_data.get(i)[1];
            // indice 2 = eva_type
            String eva_type = CSV_Treat.currentCSV_data.get(i)[2];
            // indice 3 = eva_name
            String eva_name = CSV_Treat.currentCSV_data.get(i)[3];
            // indice 4 = Ej(n)
            String ejN = CSV_Treat.currentCSV_data.get(i)[4];
            // indice 5 = score
            Float score = Float.valueOf(CSV_Treat.currentCSV_data.get(i)[5]);
            
            
            if(course_name.equals(this.name)) {
                if(!hasEvaluation(eva_name)) {
                    Evaluation new_evaluation = new Evaluation(eva_name, eva_type);            
                    evaluations.add(new_evaluation);
                    
                }
                
                if(University.hasStudent(stud_name)) {
                    Evaluation evaToADD = getEvaluation(eva_name);
                    evaToADD.addScores(University.getStudent(stud_name), score);
                    University.getStudent(stud_name).AddEvaluation(evaToADD);
                } else {
                    System.out.println("FALTA ESTUDIANTE\n");
                }
            }
            
        }
    }
    
    //---------------------------------------------------------------------
    
    // Settting up al the gets. !============================
    public String getName() { return name; };
    public Set<Student> getStudents() { return students; };
    public int getStudents_amount() { return students_amount; };
    public Set<Evaluation> getEvaluations() { return this.evaluations;}
    public Evaluation getEvaluation(String name) {
        for (Evaluation eva : evaluations) {
            if(eva.getName().equals(name)){ return eva; }
        }
        return null;
    }
    // ======================================================

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
    public boolean hasEvaluation(String eva_name) { 
        for(Evaluation eva : evaluations) {
            if(eva.getName().equals(eva_name)) {
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
        for (Evaluation evaluation : evaluations) {
            for(Student stud : students) {
                // revisamos si el estudiante tiene la evaluacion.
                if(!stud.hasEvaluation(evaluation)) { continue; }
                
                // sacamos el promedio de sus notas.
                Float average = 0f; 
                for(Float a : evaluation.getScores().get(stud.getName())) {
                    average += a;
                }
                average /= evaluation.getScores().get(stud.getName()).size();


                String[] toAdd = {this.getName(), evaluation.getName(), stud.getName(), average.toString()};            
                lines.add(toAdd);
            }
        }
        
        return lines;
    }
    
}
