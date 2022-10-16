package com.student.challenge.model;

public class StudentModel {
    private String name;
    private float performance;

    public StudentModel() {
    }

    public StudentModel(String name, float performance) {
        this.name = name;
        this.performance = performance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPerformance() {
        return performance;
    }

    public void setPerformance(float performance) {
        this.performance = performance;
    }
}
