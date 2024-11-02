package com.university;

public class Creator {
    
    public static void create_Courses_Students() {
        // revisar si existe el curso
        for (int i = 0; i < CSV_Treat.currentCSV_data.size(); i++) {
            
            // indice 1 = nombre de curso
            String course_name = CSV_Treat.currentCSV_data.get(i)[1];
            // indice 2 = nombre de estudiante
            String stud_name = CSV_Treat.currentCSV_data.get(i)[2];
            // indice 3 = email
            String stud_email = CSV_Treat.currentCSV_data.get(i)[3];

            if(!University.hasCourse(course_name)) {
                Course new_course = new Course(course_name);            
            }
            
            // revisar si existe el estudiante
            if(!University.hasStudent(stud_name)) {
                Student new_student = new Student(stud_name, stud_email);
            }
            
            University.AddStudentTo(University.getStudent(stud_name), University.getCourse(course_name));   
        }
    }

    public static void create_evaluationsPerCourse() {
        for (Course course : University.allCourses) {
            course.create_evaluations();
        }
    }

    
    public static void create_evaluations_for(Course course) {
        

        for (int i = 0; i < CSV_Treat.currentCSV_data.size(); i++) {
            // indice 0 = student_name
            String stud_name = CSV_Treat.currentCSV_data.get(i)[0];
            Student stud_ref = University.getStudent(stud_name);
            if(stud_ref == null) { continue; }
            
            // indice 1 = course_name
            String course_name = CSV_Treat.currentCSV_data.get(i)[1];
            if(University.getCourse(course_name) != course) { continue; }
            
            // indice 2 = eva_type
            String eva_type = CSV_Treat.currentCSV_data.get(i)[2];
            
            // indice 3 = eva_name
            String eva_name = CSV_Treat.currentCSV_data.get(i)[3];
            
            // indice 4 = Ej(n)
            String ejN = CSV_Treat.currentCSV_data.get(i)[4];
            
            // indice 5 = score
            Float score = Float.valueOf(CSV_Treat.currentCSV_data.get(i)[5]);
            
            if(!stud_ref.hasEvaluation(eva_name)) {
                Evaluation new_evaluation = new Evaluation(eva_name, eva_type);            
                stud_ref.AddEvaluation(new_evaluation);
            }
            // AGREGAMOS EL PUNTAJE
            stud_ref.getEvaluation(eva_name).addScores(stud_ref, score);
            
            
        }
    }
}
