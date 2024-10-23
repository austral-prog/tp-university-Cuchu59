package com.university;

import java.util.ArrayList;
import java.util.List;

public class App {
    
    public static void main(String[] args) {
        System.out.println("Corriendo");
        
        // LA CLASE UNIVERSITY YA NO EXISTE, TOCA REESCRIBIR EL SISTEMA
        //University new_university = CSV_Treat.CSVToUniv("tp-university-Cuchu59/src/main/resources/input.csv","Universidad Austral");
        
        // ======| TP 1 |===============================================
        String[] a = {"Student_Name","Course_Count"};
        CSV_Treat.Read_File("tp-university-Cuchu59/src/main/resources/input.csv");
        Course.create_Courses_Students();
        
        List<String[]> data = new ArrayList<>();
        data.add(a);
        for(Student stud : Student.getAllStudents()) {
            data.add(stud.process_data().get(0));
        }
        
        CSV_Treat.createCSV("tp-university-Cuchu59/src/main/resources/solution.csv", data);

        

        // ======| TP 2 |===============================================
        String[] b = {"Subject_Name","Evaluation_Name", "Student_Name", "Grade"};
        CSV_Treat.Read_File("tp-university-Cuchu59/src/main/resources/input_2.csv");
        Course.create_evaluationsPerCourse();
        
        List<String[]> data_2 = new ArrayList<>();
        data_2.add(b);
        for(Course cour : Course.getAllCourses()) {
            data_2.addAll(cour.process_data());
        }
        
        CSV_Treat.createCSV("tp-university-Cuchu59/src/main/resources/solution2.csv", data_2);
        



    }
    

    

    
}
