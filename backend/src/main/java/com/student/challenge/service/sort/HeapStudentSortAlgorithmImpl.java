package com.student.challenge.service.sort;

import com.student.challenge.exception.AlgorithmNotImplementedException;
import com.student.challenge.model.StudentModel;
import com.student.challenge.parser.StudentParser;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service("heapStudentSort")
class HeapStudentSortAlgorithmImpl implements StudentSortAlgorithm {

    private StudentParser studentParser;

    public HeapStudentSortAlgorithmImpl(StudentParser studentParser) {
        this.studentParser = studentParser;
    }

    @Override
    public  List<StudentModel> getStudentSortResult(InputStream is) {
        //TODO need implement
      throw new AlgorithmNotImplementedException("Not implemented!");
    }
}
