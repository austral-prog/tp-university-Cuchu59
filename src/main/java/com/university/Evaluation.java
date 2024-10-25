package com.university;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluation {
    private String name;
    private String type;
    private Map<String, List<Float>> scores;
    
    public Evaluation(String name, String type) {
        this.name = name;
        this.type = type;
        this.scores = new HashMap<>();
    }

    public String getName() { return name; }
    public Map<String, List<Float>> getScores() { return scores; }

    public void addScores(Student stu, Float score) {
        if(scores.get(stu.getName()) == null) {
            List<Float> new_l = new ArrayList<>(); 
            scores.put(stu.getName(), new_l);    
        }
        scores.get(stu.getName()).add(score);
    }

    


}
