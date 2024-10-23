package com.university;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Course implements Processor{
    private List<Student> students;
    private String name;
    private int students_amount;
    private Set<Evaluation> evaluations = new TreeSet<>(Comparator.comparing(Evaluation::getName));;
    
    private static Set<Course> courses_instances = new TreeSet<>(Comparator.comparing(Course::getName));;

    // =============== Constructor ==========================
    public Course(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.students_amount = 0;
        courses_instances.add(this);
    }
    // ======================================================
    

    // CLASS METHODS. !=====================================
    public static Set<Course> getAllCourses() { return courses_instances; }
    public static Course getCourse(String name) { 
        for (Course course : courses_instances) {
            if(course.getName().equals(name)){ return course; }
        }
        return null;
    }    
    public Set<Evaluation> getEvaluations() { return this.evaluations;}
    public Evaluation getEvaluation(String name) {
        for (Evaluation eva : evaluations) {
            if(eva.getName().equals(name)){ return eva; }
        }
        return null;
    }
    public static void AddStudentTo(Student student, Course course) {    
        for(Course c : courses_instances) {
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
        for(Course av_course : courses_instances) {
            courses_names.add(av_course.getName());
        }

        // Check if course_name is in the list.
        return courses_names.contains(course_name);
    }
    public static void printCourses() {
        for(Course c : courses_instances) {
            List<Student> studs = c.getStudents();
            System.out.print("||"+c.getName()+"||: ");
            
            for (Student s : studs) {                
                System.out.print(s.getName() + ", ");
            }
            
            System.out.println("\n");
        }
    }
    
        //---->> CREATING FROM CSV INPUTS ===================================
    public static void create_Courses_Students() {
        // revisar si existe el curso
        
        for (int i = 0; i < CSV_Treat.currentCSV_data.size(); i++) {
            // indice 1 = nombre de curso
            // indice 2 = nombre de estudiante
            // indice 3 = email

            if(!Course.hasCourse(CSV_Treat.currentCSV_data.get(i)[1])) {
                Course new_course = new Course(CSV_Treat.currentCSV_data.get(i)[1]);            
            }
            
            // revisar si existe el estudiante
            if(!Student.hasStudent(CSV_Treat.currentCSV_data.get(i)[2])) {
                Student new_student = new Student(CSV_Treat.currentCSV_data.get(i)[2], CSV_Treat.currentCSV_data.get(i)[3]);
            }
    
            Course.AddStudentTo(Student.getStudent(CSV_Treat.currentCSV_data.get(i)[2]), Course.getCourse(CSV_Treat.currentCSV_data.get(i)[1]));   
        }
    }
    
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
                if(!this.hasEvaluation(eva_name)) {
                    Evaluation new_evaluation = new Evaluation(eva_name, eva_type);            
                    evaluations.add(new_evaluation);
                }
                
                if(Student.hasStudent(stud_name)) {
                    getEvaluation(eva_name).addScores(Student.getStudent(stud_name), score);
                } else {
                    System.out.println("FALTA ESTUDIANTE\n");
                }
            
            
                
            
            }

        }
    }

    public static void create_evaluationsPerCourse() {
        for (Course course : courses_instances) {
            course.create_evaluations();
        }
    }
    
    
    // ======================================================
    
//---------------------------------------------------------------------

    // Settting up al the gets. !============================
    public List<Student> getStudents() { return students; };
    public String getName() { return name; };
    public int getStudents_amount() { return students_amount; };
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
            for (int n = 0; n < students.size(); n++) {
                Student stud = students.get(n);
                if(!evaluation.getScores().containsKey(stud.getName())) { continue; }
                
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
