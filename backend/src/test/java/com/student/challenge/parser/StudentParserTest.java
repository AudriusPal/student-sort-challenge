package com.student.challenge.parser;

import com.student.challenge.StudentSortChallengeApplication;
import com.student.challenge.exception.StudentParserException;
import com.student.challenge.model.StudentModel;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = StudentSortChallengeApplication.class)
@ActiveProfiles({""})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentParserTest {

    private static final String FILE_NAME_00_INPUT = "00_input.txt";

    @Autowired
    private StudentParser studentParser;

    @ParameterizedTest
    @ValueSource(strings = {" ", "Student10", "LaStudent7,8.5", "student,7", "Student7,7.8Stu", "  Student6,1", "Student78, "})
    void testParseStringExceptionCases(String str) {
        assertThrows(StudentParserException.class, () -> studentParser.parse(str));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Student0,8.7;      Student0;   8.7",
            "Student10,8.77;    Student10;  8.77",
            "Student124,3;      Student124; 3"}, delimiter = ';')
    void testParseStringAcceptCases(String str, String name, String performance) {

        assertThatCode(() -> {
            final StudentModel studentModel = studentParser.parse(str);
            assertNotNull(studentModel);
            assertEquals(studentModel.getName(), name);
            assertEquals(studentModel.getPerformance(), Float.parseFloat(performance));
        }).doesNotThrowAnyException();
    }

    @Test
    void testParseInputStream00InputStreamFromFile() {
        final InputStream is = getInputStream(FILE_NAME_00_INPUT);

        assertThatCode(() -> {
            final List<StudentModel> studentModelList = studentParser.parse(is);
            assertTrue(CollectionUtils.isNotEmpty(studentModelList));
        }).doesNotThrowAnyException();
    }

    @Test
    void testParseInputStream00InputStreamFromString() {
        final String name = "Student2";
        final float performance = 6.5f;

        final String str = name + "," + performance;
        final InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));

        assertThatCode(() -> {
            final List<StudentModel> studentModelList = studentParser.parse(is);

            assertTrue(CollectionUtils.isNotEmpty(studentModelList));
            assertTrue(studentModelList.size() == 1);

            StudentModel studentModel = studentModelList.get(0);
            assertNotNull(studentModel);
            assertEquals(studentModel.getName(), name);
            assertEquals(studentModel.getPerformance(), performance);

        }).doesNotThrowAnyException();
    }

    private InputStream getInputStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}