package com.example.SpringBootTestDemo.service;

import com.example.SpringBootTestDemo.entity.Student;

import java.util.List;

public interface StudentServices {
    Student addStudent(Student student);

    List<Student> getAllStudent();

    Student getStudentById(Integer id);

    Student deleteStudentById(Integer id);

    List<Student> getstudentByName(String name);
}
