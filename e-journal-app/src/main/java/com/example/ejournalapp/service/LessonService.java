package com.example.ejournalapp.service;

import com.example.ejournalapp.domain.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> findAll();

    Lesson findById(Long id);

    Lesson create(Lesson lesson, String teacherUsername);

    void addMark(Long id, String studentUsername, Integer grade);

    void deleteById(Long id);
}
