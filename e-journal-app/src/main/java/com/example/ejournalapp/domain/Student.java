package com.example.ejournalapp.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Student extends User {
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mark> marks = new LinkedHashSet<>();

    public void addMark(Mark mark) {
        marks.add(mark);
        mark.setStudent(this);
    }
    public void addMarks(List<Mark> marks) {
        marks.forEach(this::addMark);
    }

    public void removeMark(Mark mark) {
        marks.remove(mark);
        mark.setStudent(null);
    }

    public Mark getMarkByLesson(Lesson lesson) {
        var tempMark = new Mark();
        tempMark.setGrade(0);
        return marks.stream()
                .filter(mark -> mark.getLesson().equals(lesson))
                .findFirst()
                .orElse(tempMark);
    }
}