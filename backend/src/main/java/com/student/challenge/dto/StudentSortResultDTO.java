package com.student.challenge.dto;

import java.util.List;
import java.util.ArrayList;

public class StudentSortResultDTO {
    private float sortDurationInMs;
    private int numberOfRecords;
    private List<StudentDTO> sortedList = new ArrayList();

    public StudentSortResultDTO() {
    }

    public StudentSortResultDTO(float sortDurationInMs, int numberOfRecords, List<StudentDTO> sortedList) {
        this.sortDurationInMs = sortDurationInMs;
        this.numberOfRecords = numberOfRecords;
        this.sortedList = sortedList;
    }

    public float getSortDurationInMs() {
        return sortDurationInMs;
    }

    public void setSortDurationInMs(float sortDurationInMs) {
        this.sortDurationInMs = sortDurationInMs;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public List<StudentDTO> getSortedList() {
        return sortedList;
    }

    public void setSortedList(List<StudentDTO> sortedList) {
        this.sortedList = sortedList;
    }
}
