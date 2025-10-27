package com.example.healthhub.model;

import java.time.Year;

public class Person {
    private String name;
    private int yob;
    private double weight, height;

    public Person(String name, int yob, double weight, double height){
        this.name = name;
        this.yob = yob;
        this.weight = weight;
        this.height = height;
    }

    public String getName(){
        return name;
    }

    public int getYob(){
        return yob;
    }

    public double getWeight(){
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getAge(){
        int CurrentYear = Year.now().getValue();
        return CurrentYear - yob;
    }

    public double calcBMI(){
        return height/(weight*weight);
    }

    public String getCategory(){
        double bmi = calcBMI();
        if (bmi < 18.5) return "Underweight";
        if (bmi < 20.0) return "Normal";
        if (bmi < 30.0) return "Overweight";
        return "Obese";
    }
    
    
}
