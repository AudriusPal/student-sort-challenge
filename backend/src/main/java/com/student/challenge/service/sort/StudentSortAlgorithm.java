package com.student.challenge.service.sort;

import com.student.challenge.dto.StudentSortResultDTO;

import java.io.InputStream;

public interface StudentSort {
    StudentSortResultDTO getStudentSortResult(InputStream is);
}
