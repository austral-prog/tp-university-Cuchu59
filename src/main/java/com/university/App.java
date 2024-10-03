package com.university;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        System.out.println("Corriendo");
        ReadCSV("tp-university-Cuchu59/src/main/resources/input.csv");
    }


    // ====================================================
    // *  HAY QUE IMPLEMENTAR EL METODO ReadCSV() que reciba la direccion
    // *  del archivo para recibir todos los datos de la base de datos
    // * --> input.csv
    
    public static void ReadCSV(String PATH) {
        String linea = "";

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            while ((linea = br.readLine()) != null) {   
                // Usamos el separador para dividir la línea en columnas
                String[] columnas = linea.split(",");

                // Mostrar cada columna (puedes hacer otras operaciones aquí)
                for (String columna : columnas) {
                    System.out.print(columna + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
