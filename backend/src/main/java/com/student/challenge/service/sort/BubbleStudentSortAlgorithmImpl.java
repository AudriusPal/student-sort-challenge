package com.student.challenge.service.sort.bubblesort;

import com.student.challenge.dto.StudentSortResultDTO;
import com.student.challenge.model.StudentModel;
import com.student.challenge.parser.StudentParser;
import com.student.challenge.service.sort.StudentSortAlgorithm;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service("bubbleStudentSort")
class BubbleStudentSortAlgorithmImpl implements StudentSortAlgorithm {

    private StudentParser studentParser;

    public BubbleStudentSortAlgorithmImpl(StudentParser studentParser) {
        this.studentParser = studentParser;
    }

    //TODO improve, no time to play with streams or collections or linked lists
    @Override
    public StudentSortResultDTO getStudentSortResult(InputStream is) {
        final List<StudentModel> studentModelList = studentParser.parse(is);

        boolean swapped = false;
        final StudentModel[] array = new StudentModel[studentModelList.size()];

        do {
            for(int i = 0 ; i < array.length - 1; i++) {
                if (array[i].compareTo(array[i + 1]) == -1) {
                    var temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = array[i];
                    swapped = true;
                }
            }
        } while (swapped);

        return null;
    }
}
