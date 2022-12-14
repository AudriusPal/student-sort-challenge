package com.student.challenge.web;

import com.student.challenge.dto.StudentSortResultDTO;
import com.student.challenge.enums.SortType;
import com.student.challenge.service.StudentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

//TODO no time for tests
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "student/")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("sort/{sortType}")
    public ResponseEntity<StudentSortResultDTO> getStudentSortResult(@RequestParam("file") MultipartFile file, @PathVariable SortType sortType) throws IOException {
        final StudentSortResultDTO studentSortResultDTO = studentService.getStudentSortResult(file.getInputStream(), sortType);
        return ResponseEntity.ok(studentSortResultDTO);
    }

    @PostMapping("export-to-file/{sortType}")
    public ResponseEntity<ByteArrayResource> exportSortResult(@RequestParam("file") MultipartFile file, @PathVariable SortType sortType) throws IOException {
        final byte[] bytes = studentService.exportToFile(file.getInputStream(), sortType);

        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO  sortType + LocalDateTime.now().toString() + ".txt to service
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ sortType + LocalDateTime.now() + ".txt");

        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(httpHeaders)
                .body(new ByteArrayResource(bytes));
    }
}
