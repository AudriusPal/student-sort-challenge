package com.student.challenge.dto;

public class StudentDTO {
    private String name;
    private float performance;

    public StudentDTO() {
    }

    public StudentDTO(String name, float performance) {
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
