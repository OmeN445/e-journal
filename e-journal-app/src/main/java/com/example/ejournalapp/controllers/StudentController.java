package com.example.ejournalapp.controllers;

import com.example.ejournalapp.domain.Role;
import com.example.ejournalapp.domain.Student;
import com.example.ejournalapp.domain.User;
import com.example.ejournalapp.service.StudentService;
import com.example.ejournalapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final UserService userService;
    private final StudentService studentService;

    @GetMapping
    public String findAll(Model model, Principal principal) {
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "students";
    }

    @GetMapping("/self/marks")
    public String findAllSelfMarks(Model model, Principal principal) {
        model.addAttribute("student", studentService.findByUsername(principal.getName()));
        return "lessons-student";
    }

    @PostMapping
    public String create(@ModelAttribute Student student) {
        if (userService.existsByUsername(student.getUsername())) {
            return String.join("", "redirect:/students?error=", URLEncoder.encode("Введіть інший username", StandardCharsets.UTF_8));
        }
        student.setPassword("{noop}" + student.getPassword());
        student.setRole(Role.ROLE_STUDENT);
        studentService.save(student);
        return "redirect:/students";
    }
}
