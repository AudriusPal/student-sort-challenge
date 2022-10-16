package com.student.challenge.service.sort.bubblesort;

import com.student.challenge.StudentSortChallengeApplication;
import com.student.challenge.model.StudentModel;
import com.student.challenge.parser.StudentParser;
import com.student.challenge.service.sort.StudentSortAlgorithm;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = StudentSortChallengeApplication.class)
@ActiveProfiles({""})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BubbleStudentSortAlgorithmImplTest {

    private static final String FILE_NAME_00_INPUT = "00_input.txt";
    private static final String FILE_NAME_00_OUTPUT = "00_output.txt";

    @Autowired
    @Qualifier("bubbleStudentSort")
    private StudentSortAlgorithm bubbleSortAlgorithm;

    @Autowired
    private StudentParser studentParser;

    @Test
    void testGetStudentSortResult() {
        final InputStream inputIS = getInputStream(FILE_NAME_00_INPUT);
        final InputStream outputIS = getInputStream(FILE_NAME_00_OUTPUT);

        final List<StudentModel> sorted = bubbleSortAlgorithm.getStudentSortResult(inputIS);
        final List<StudentModel> result = studentParser.parse(outputIS);

        assertTrue(CollectionUtils.isNotEmpty(sorted));
        assertTrue(CollectionUtils.isNotEmpty(result));

        assertTrue(sorted.size() == result.size());

        for(int i = 0; i < sorted.size(); i++) {
            assertTrue(sorted.get(i).equals(result.get(i)));
        }
    }

    private InputStream getInputStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}