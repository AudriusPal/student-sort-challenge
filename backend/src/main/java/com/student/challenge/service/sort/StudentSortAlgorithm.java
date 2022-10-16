package com.student.challenge.service.sort;

import com.student.challenge.model.StudentModel;

import java.io.InputStream;
import java.util.List;

public interface StudentSortAlgorithm {
    List<StudentModel> getStudentSortResult(InputStream is);
}
