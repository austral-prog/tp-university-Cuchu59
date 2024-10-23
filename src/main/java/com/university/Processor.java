package com.university;

import java.util.List;

public interface Processor {
    // Cada una de las clases va a procesar de forma distinta un csv a otro.
    List<String[]> process_data();
    
}
