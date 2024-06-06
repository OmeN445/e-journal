package com.example.ejournalapp.controllers;

import com.example.ejournalapp.domain.Lesson;
import com.example.ejournalapp.domain.Role;
import com.example.ejournalapp.domain.User;
import com.example.ejournalapp.service.LessonService;
import com.example.ejournalapp.service.StudentService;
import com.example.ejournalapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;
    private final StudentService studentService;
    private final UserService userService;

    @GetMapping
    public String findAll(Model model, Principal principal) {
        var user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("lessons", lessonService.findAll());
        if (user.getRole() == Role.ROLE_TEACHER) {
            return "lessons-teacher";
        } else {
            return "redirect:/students/self/marks";
        }
    }

    @GetMapping("/{id}")
    public String findById(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("lesson", lessonService.findById(id));
        return "lesson";
    }

    @PostMapping
    public String create(@ModelAttribute Lesson lesson, Principal principal) {
        var newLesson = lessonService.create(lesson, principal.getName());
        return "redirect:/lessons/" + newLesson.getId();
    }

    @PostMapping("/{id}/marks")
    public String addMark(@PathVariable Long id,
                          @ModelAttribute(name = "username") String username,
                          @ModelAttribute(name = "grade") String grade) {
        lessonService.addMark(id, username, Integer.parseInt(grade));
        return "redirect:/lessons/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        lessonService.deleteById(id);
        return "redirect:/lessons";
    }
}
