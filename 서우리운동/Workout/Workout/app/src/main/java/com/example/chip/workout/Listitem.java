package com.example.chip.workout;

/**
 * Created by JIN on 2017-09-22.
 */

public class Listitem {

    String name;
    String stimulus;
    int resId;

    public Listitem(String name, String stimulus, int resId) {
        this.name = name;
        this.stimulus = stimulus;
        this.resId=resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStimulus() {
        return stimulus;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setStimulus(String stimulus) {
        this.stimulus = stimulus;
    }

    @Override
    public String toString() {
        return "Listitem{" +
                "name='" + name + '\'' +
                ", stimulus='" + stimulus + '\'' +
                '}';
    }
}
