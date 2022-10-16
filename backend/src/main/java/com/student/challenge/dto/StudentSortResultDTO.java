package com.student.challenge.dto;

import com.student.challenge.enums.SortType;

import java.util.List;
import java.util.ArrayList;

//TODO need cover with tests
public class StudentSortResultDTO {
    private float sortDurationInMs;
    private int numberOfRecords;
    private SortType sortType;
    private List<StudentDTO> sortedList = new ArrayList();

    public StudentSortResultDTO() {
    }

    public StudentSortResultDTO(float sortDurationInMs, int numberOfRecords, SortType sortType, List<StudentDTO> sortedList) {
        this.sortDurationInMs = sortDurationInMs;
        this.numberOfRecords = numberOfRecords;
        this.sortType = sortType;
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

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
}
