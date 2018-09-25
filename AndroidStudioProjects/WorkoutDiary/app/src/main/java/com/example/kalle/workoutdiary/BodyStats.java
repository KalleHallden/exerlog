package com.example.kalle.workoutdiary;

import java.io.Serializable;
import java.util.ArrayList;

public class BodyStats implements Serializable {

    static ArrayList<Double> bodyweight = new ArrayList<Double>();

    private Double bodyWeight;
    private Double bicepsSize;
    private Double neckSize;
    private Double wristSize;
    private Double chestSize;
    private Double waistSize;
    private Double thighSize;
    private Double calfSize;

    public void setBodyWeight(String bw) {
        bodyWeight = Double.parseDouble(bw);
    }

    public Double getBodyWeight() {
        return bodyWeight;
    }

    public void setBicepsSize(String bis) {
        bicepsSize = Double.parseDouble(bis);
    }

    public Double getBicepsSize() {
        return bicepsSize;
    }

    public void setNeckSize(String ns) {
        neckSize = Double.parseDouble(ns);
    }

    public Double getNeckSize() {
        return neckSize;
    }

    public void setWristSize(String wrists) {
        wristSize = Double.parseDouble(wrists);
    }

    public Double getWristSize() {
        return wristSize;
    }

    public void setChestSize(String cs) {
        chestSize = Double.parseDouble(cs);
    }

    public Double getChestSize() {
        return chestSize;
    }

    public void setWaistSize(String waists) {
        waistSize = Double.parseDouble(waists);
    }

    public Double getWaistSize() {
        return waistSize;
    }

    public void setThighSize(String ts) {
        thighSize = Double.parseDouble(ts);
    }

    public Double getThighSize() {
        return thighSize;
    }

    public void setCalfSize(String calfs) {
        calfSize = Double.parseDouble(calfs);
    }

    public Double getCalfSize() {
        return calfSize;
    }


}
