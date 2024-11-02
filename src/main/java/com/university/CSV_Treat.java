package com.university;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSV_Treat {
    
    public static List<String[]> currentCSV_data = new ArrayList<>();
    public static void ReadFile(String PATH) {
        String linea = "";
        currentCSV_data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            br.readLine();
            while ((linea = br.readLine()) != null) {   
                // Dividimos la línea en columnas
                String[] columnas = linea.split(",");
                
                currentCSV_data.add(columnas);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    
    // Función para crear un archivo CSV y escribir la primera línea (encabezados)
    public static void createCSV(String fileName, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for(String[] line : data) {
                bw.write(String.join(",", line));
                bw.newLine();  // Añadir salto de línea después de los datos
            }
        } catch (IOException e) { e.printStackTrace(); }
        
    }
    
}
