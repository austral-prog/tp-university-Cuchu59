package com.university;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        System.out.println("Corriendo");
        DigestCSV("tp-university-Cuchu59/src/main/resources/input.csv");
    }


    // ====================================================
    // *  HAY QUE IMPLEMENTAR EL METODO ReadCSV() que reciba la direccion
    // *  del archivo para recibir todos los datos de la base de datos
    // * --> input.csv
    
    public static void DigestCSV(String PATH) {
        String linea = "";

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            University university = new University("Universidad Nueva");
            
            while ((linea = br.readLine()) != null) {   
                // Dividimos la línea en columnas
                String[] columnas = linea.split(",");
                
                Course new_course = new Course(columnas[1]); 
                Student new_student = new Student(columnas[2], columnas[3], new_course); 
                if(!(new_course.getName().equals("Subject")) || !(new_student.getName().equals("Student_Name"))) 
                { 
                    if(!university.hasCourse(new_course)) {
                        university.AddCourse(new_course);
                    }
                    university.AddStudentTo(new_student, new_course);
                }
            }
            university.printInfo();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
