package com.student.challenge.service;

import com.student.challenge.dto.StudentSortResultDTO;
import com.student.challenge.enums.SortType;

import java.io.InputStream;

public interface StudentService {
    StudentSortResultDTO getStudentSortResult(InputStream is, SortType sortType);
    byte[] exportToFile(InputStream is, SortType sortType);
}
