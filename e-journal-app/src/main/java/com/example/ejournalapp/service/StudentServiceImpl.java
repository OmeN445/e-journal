package com.example.ejournalapp.service;

import com.example.ejournalapp.domain.Mark;
import com.example.ejournalapp.domain.Student;
import com.example.ejournalapp.repository.LessonRepository;
import com.example.ejournalapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public Student save(Student student) {
        var lessons = lessonRepository.findAll();
        if (!lessons.isEmpty()) {
            var mark = new Mark();
            student.addMark(mark);
            lessons.forEach(lesson -> lesson.addMark(mark));
        }
        return studentRepository.save(student);
    }
}
