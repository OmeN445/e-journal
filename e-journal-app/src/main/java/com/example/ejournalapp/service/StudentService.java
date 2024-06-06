package com.example.ejournalapp.service;

import com.example.ejournalapp.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findByUsername(String username);

    Student save(Student student);
}
