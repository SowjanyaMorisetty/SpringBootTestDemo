package com.example.SpringBootTestDemo.exception;

public class StudentNotFoundException extends RuntimeException{
    private static final long serialversionUID=1L;
    public StudentNotFoundException (String message){
        super(message);
    }
}
