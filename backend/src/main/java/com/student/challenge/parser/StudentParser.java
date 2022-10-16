package com.student.challenge.parser;

import com.student.challenge.exception.StudentParserException;
import com.student.challenge.model.StudentModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentParser {
    private static final String STUDENT_KEY = "Student";
    private static final String STUDENT_DELIMITER = ",";

    public StudentModel parse(String str) {

        try {
            if (StringUtils.isBlank(str)) {
                throw new StudentParserException("is blank");
            }

            final int delimiterIndex = str.indexOf(STUDENT_DELIMITER);
            final String name = str.substring(0, delimiterIndex);

            if (!StringUtils.startsWith(name, STUDENT_KEY)) {
                throw new StudentParserException("not starts with " + STUDENT_KEY);
            }

            final float performance = Float.parseFloat(str.substring(delimiterIndex + 1));

            return new StudentModel(name, performance);
        } catch (Throwable t) {
            throw new StudentParserException("Can't parse " + str + " error:" + t.getMessage());
        }
    }

    public List<StudentModel> parse(InputStream is) {
        try {
            final List<StudentModel> studentModelList = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .map(this::parse).collect(Collectors.toList());
            return studentModelList;
        } catch (Throwable t) {
            throw new StudentParserException("Can't parse file " + " error:" + t.getMessage());
        }
    }
}
