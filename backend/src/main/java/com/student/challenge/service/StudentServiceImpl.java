package com.student.challenge.service;

import com.student.challenge.dto.StudentDTO;
import com.student.challenge.dto.StudentSortResultDTO;
import com.student.challenge.enums.SortType;
import com.student.challenge.exception.AlgorithmNotImplementedException;
import com.student.challenge.model.StudentModel;
import com.student.challenge.parser.StudentParser;
import com.student.challenge.profiler.TimeProfiler;
import com.student.challenge.service.sort.StudentSortAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentSortAlgorithm bubbleStudentSortAlgorithm;
    private final StudentSortAlgorithm mergeStudentSortAlgorithm;
    private final StudentSortAlgorithm heapStudentSortAlgorithm;

    private final StudentParser studentParser;

    public StudentServiceImpl(@Qualifier("bubbleStudentSort") StudentSortAlgorithm bubbleStudentSortAlgorithm,
                              @Qualifier("mergeStudentSort") StudentSortAlgorithm mergeStudentSortAlgorithm,
                              @Qualifier("heapStudentSort") StudentSortAlgorithm heapStudentSortAlgorithm,
                              StudentParser studentParser) {
        this.bubbleStudentSortAlgorithm = bubbleStudentSortAlgorithm;
        this.mergeStudentSortAlgorithm = mergeStudentSortAlgorithm;
        this.heapStudentSortAlgorithm = heapStudentSortAlgorithm;
        this.studentParser = studentParser;
    }

    @Override
    public StudentSortResultDTO getStudentSortResult(InputStream is, SortType sortType) {
        switch (sortType) {
            case NONE: {
                final List<StudentModel> studentModelList = studentParser.parse(is);
                return createStudentSortResultDTO(0l, studentModelList, sortType);
            }
            case BUBBLE_SORT: {
                final TimeProfiler timeProfiler = new TimeProfiler();

                timeProfiler.start();
                final List<StudentModel> studentModelList = bubbleStudentSortAlgorithm.getStudentSortResult(is);
                timeProfiler.end();

                return createStudentSortResultDTO(timeProfiler.durationInMs(), studentModelList, sortType);
            }
            default:
                throw new AlgorithmNotImplementedException("Not implemented " + sortType.name());
        }
    }

    public byte[] exportToFile(InputStream is, SortType sortType) {
        final StringBuilder sb = new StringBuilder();

        bubbleStudentSortAlgorithm.getStudentSortResult(is).stream().forEach(s -> {
            sb.append(s.getName() + "," + s.getPerformance() +  System.lineSeparator());
        });
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private StudentSortResultDTO createStudentSortResultDTO(long durationInMs, List<StudentModel> studentModelList, SortType sortType) {
        final StudentSortResultDTO studentSortResultDTO = new StudentSortResultDTO();
        studentSortResultDTO.setSortDurationInMs(durationInMs);
        studentSortResultDTO.setNumberOfRecords(studentModelList.size());
        studentSortResultDTO.setSortType(sortType);
        studentSortResultDTO.setSortedList(studentModelList.stream().map(m -> new StudentDTO(m.getName(),
                m.getPerformance())).collect(Collectors.toList()));
        return studentSortResultDTO;
    }
}
