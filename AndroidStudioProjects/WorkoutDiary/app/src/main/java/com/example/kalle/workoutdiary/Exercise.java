package com.example.kalle.workoutdiary;

import java.io.Serializable;

public class Exercise implements Serializable {
    private String name;
    private int reps;
    private int sets;
    private Double weight;
    private int rest;
    private int volume;


    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setReps(String r) {
        reps = Integer.parseInt(r);
    }

    public int getReps() {
        return reps;
    }

    public void setSets(String s) {
        sets = Integer.parseInt(s);
    }

    public int getSets() {
        return sets;
    }

    public void setWeight(String w) {
        weight = Double.parseDouble(w);
    }

    public Double getWeight() {
        return weight;
    }

    public void setRest(String rst) {
        rest = Integer.parseInt(rst);
    }

    public int getRest() {
        return rest;
    }

    public void setVolume(int r, int s) {
        volume = r * s;
    }

    public int getVolume() {
        return volume;
    }

}
