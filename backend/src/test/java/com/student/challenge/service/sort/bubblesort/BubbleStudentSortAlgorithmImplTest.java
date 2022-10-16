package com.student.challenge.service.sort.bubblesort;

import com.student.challenge.StudentSortChallengeApplication;
import com.student.challenge.dto.StudentSortResultDTO;
import com.student.challenge.service.sort.StudentSort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = StudentSortChallengeApplication.class)
@ActiveProfiles({""})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BubbleStudentSortImplTest {

    private static final String FILE_NAME_00_INPUT = "00_input.txt";
    private static final String FILE_NAME_00_OUTPUT = "00_output.txt";

    @Autowired
    @Qualifier("bubbleStudentSort")
    private StudentSort studentSort;

    @Test
    void testGetStudentSortResult() {
    }

    private InputStream getInputStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}