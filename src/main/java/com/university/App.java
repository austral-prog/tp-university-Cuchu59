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
        List<String[]> data = new ArrayList<>();
        data.add(a);
        
        CSV_Treat.ReadFile("tp-university-Cuchu59/src/main/resources/input.csv");
        Creator.create_Courses_Students();

        for(Student stud : University.allStudents) {
            data.add(stud.process_data().get(0));
        }
        CSV_Treat.createCSV("tp-university-Cuchu59/src/main/resources/solution.csv", data);


        // ======| TP 2 |===============================================

        String[] b = {"Subject_Name","Evaluation_Name", "Student_Name", "Grade"};
        List<String[]> data_2 = new ArrayList<>();
        data_2.add(b);
        
        CSV_Treat.ReadFile("tp-university-Cuchu59/src/main/resources/input_2.csv");
        Creator.create_evaluationsPerCourse();
        
        for(Course cour : University.allCourses) {
            data_2.addAll(cour.process_data());
        }
        
        CSV_Treat.createCSV("tp-university-Cuchu59/src/main/resources/solution2.csv", data_2);
        

        // ======| TP 3 |===============================================
        String[] c = {"","", "", ""};
        List<String[]> data_3 = new ArrayList<>();
        data_2.add(c);

        CSV_Treat.ReadFile("tp-university-Cuchu59/src/main/resources/input_3.csv");
    }
    

    

    
}
