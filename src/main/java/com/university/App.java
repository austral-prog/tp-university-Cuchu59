package com.university;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }


    public static List<String[]> ReadCSV(String path) {
        List<String[]> registros = new ArrayList<>();
        String linea = "";
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en un arreglo de Strings por el separador
                String[] datos = linea.split(separador);
                registros.add(datos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return registros;
    }
}
