package com.student.challenge.model;

import org.apache.commons.lang3.StringUtils;

//TODO need cover with tests
public class StudentModel implements Comparable<StudentModel> {
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

    @Override
    public int compareTo(StudentModel o) {
        if (o == null) {
            return -1;
        }
        else if (getPerformance() > o.getPerformance()) {
            return 1;
        } else if (o.getPerformance() > getPerformance()) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof StudentModel)) {
            return false;
        }

        final StudentModel s = (StudentModel) o;
        return this.performance == s.performance && StringUtils.equals(this.name, s.name);
    }
}
