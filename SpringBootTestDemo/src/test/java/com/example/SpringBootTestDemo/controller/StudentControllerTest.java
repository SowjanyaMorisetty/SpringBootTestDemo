package com.example.SpringBootTestDemo.controller;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.service.StudentServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServices mockStudentservice;

    @Test
    void testAddStudent() throws Exception {
        // Setup
        // Configure StudentServices.addStudent(...).
        final Student student = new Student(0, "name", "address", "rollNo");
        when(mockStudentservice.addStudent(new Student(0, "name", "address", "rollNo"))).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/student")
                        .content(asJsonString(student)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private String asJsonString(Student student) throws JsonProcessingException {
        try {
            return new ObjectMapper().writeValueAsString(student);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return null;

    }


    @Test
    void testGetAllstudent() throws Exception {
        // Setup
        // Configure StudentServices.getAllStudent(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollNo"));
        when(mockStudentservice.getAllStudent()).thenReturn(students);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllstudent_StudentServicesReturnsNoItems() throws Exception {
        // Setup
        when(mockStudentservice.getAllStudent()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetStudentById() throws Exception {
        // Setup
        // Configure StudentServices.getStudentById(...).
        final Student student = new Student(0, "name", "address", "rollNo");
        when(mockStudentservice.getStudentById(0)).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/{student-id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDeleteStudentById() throws Exception {
        // Setup
        // Configure StudentServices.deleteStudentById(...).
        final Student student = new Student(0, "name", "address", "rollNo");
        when(mockStudentservice.deleteStudentById(0)).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/student/{student-id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetstudentByName() throws Exception {
        // Setup
        // Configure StudentServices.getstudentByName(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollNo"));
        when(mockStudentservice.getstudentByName("name")).thenReturn(students);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/name/{student-name}", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetstudentByName_StudentServicesReturnsNoItems() throws Exception {
        // Setup
        when(mockStudentservice.getstudentByName("name")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/name/{student-name}", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
