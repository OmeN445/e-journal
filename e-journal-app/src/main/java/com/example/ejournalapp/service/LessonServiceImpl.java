package com.example.ejournalapp.service;

import com.example.ejournalapp.domain.Lesson;
import com.example.ejournalapp.domain.Mark;
import com.example.ejournalapp.repository.LessonRepository;
import com.example.ejournalapp.repository.MarkRepository;
import com.example.ejournalapp.repository.StudentRepository;
import com.example.ejournalapp.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService{

    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final MarkRepository markRepository;

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public Lesson create(Lesson lesson, String teacherUsername) {
        var teacher = teacherRepository.findByUsername(teacherUsername).orElseThrow(IllegalArgumentException::new);
        var students = studentRepository.findAll();
        var marks = Stream.generate(Mark::new).limit(students.size()).toList();
        teacher.addLesson(lesson);
        for (int i = 0; i < students.size(); i++) {
            students.get(i).addMark(marks.get(i));
        }
        lesson.addMarks(marks);
        return lessonRepository.save(lesson);
    }

    @Override
    @Transactional
    public void addMark(Long id, String studentUsername, Integer grade) {
        var student = studentRepository.findByUsername(studentUsername).orElseThrow(IllegalArgumentException::new);
        var lesson = lessonRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        var newMark = student.getMarks()
                .stream()
                .filter(mark -> mark.getLesson().equals(lesson))
                .findFirst()
                .orElse(new Mark());
        newMark.setGrade(grade);
        markRepository.save(newMark);
    }

    @Override
    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }


}
