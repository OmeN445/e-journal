package com.example.ejournalapp.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Teacher extends User {
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lesson> lessons = new LinkedHashSet<>();

    public void addLesson(Lesson lesson) {
        lesson.setTeacher(this);
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lesson.setTeacher(null);
        lessons.remove(lesson);
    }
}