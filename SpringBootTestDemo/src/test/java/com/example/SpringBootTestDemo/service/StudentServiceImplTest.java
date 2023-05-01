package com.example.SpringBootTestDemo.service;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.exception.StudentNotFoundException;
import com.example.SpringBootTestDemo.repository.Studentrepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private Studentrepo mockStudentrepo;

    @InjectMocks
    private StudentServiceImpl studentServiceImplUnderTest;

    @Test
    void testAddStudent() {
        // Setup
        final Student student = new Student(0, "name", "address", "rollNo");
        final Student expectedResult = new Student(0, "name", "address", "rollNo");

        // Configure Studentrepo.save(...).
        final Student student1 = new Student(0, "name", "address", "rollNo");
        when(mockStudentrepo.save(new Student(0, "name", "address", "rollNo"))).thenReturn(student1);

        // Run the test
        final Student result = studentServiceImplUnderTest.addStudent(student);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllStudent() {
        // Setup
        final List<Student> expectedResult = List.of(new Student(0, "name", "address", "rollNo"));

        // Configure Studentrepo.findAll(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollNo"));
        when(mockStudentrepo.findAll()).thenReturn(students);

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getAllStudent();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllStudent_StudentrepoReturnsNoItems() {
        // Setup
        when(mockStudentrepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getAllStudent();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetStudentById() {
        // Setup
        final Student expectedResult = new Student(0, "name", "address", "rollNo");

        // Configure Studentrepo.findById(...).
        final Optional<Student> studentOptional = Optional.of(new Student(0, "name", "address", "rollNo"));
        when(mockStudentrepo.findById(0)).thenReturn(studentOptional);

        // Run the test
        final Student result = studentServiceImplUnderTest.getStudentById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetStudentById_StudentrepoReturnsAbsent() {
        // Setup
        when(mockStudentrepo.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.getStudentById(0))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void testDeleteStudentById() {
        // Setup
        // Configure Studentrepo.findById(...).
        final Optional<Student> studentOptional = Optional.of(new Student(0, "name", "address", "rollNo"));
        when(mockStudentrepo.findById(0)).thenReturn(studentOptional);

        // Run the test
        final Student result = studentServiceImplUnderTest.deleteStudentById(0);

        // Verify the results
        assertThat(result).isNull();
        verify(mockStudentrepo).delete(new Student(0, "name", "address", "rollNo"));
    }

    @Test
    void testDeleteStudentById_StudentrepoFindByIdReturnsAbsent() {
        // Setup
        when(mockStudentrepo.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.deleteStudentById(0))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void testGetstudentByName() {
        // Setup
        final List<Student> expectedResult = List.of(new Student(0, "name", "address", "rollNo"));

        // Configure Studentrepo.findByName(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollNo"));
        when(mockStudentrepo.findByName("name")).thenReturn(students);

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getstudentByName("name");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetstudentByName_StudentrepoReturnsNoItems() {
        // Setup
        when(mockStudentrepo.findByName("name")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getstudentByName("name");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
