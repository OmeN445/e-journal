package com.example.ejournalapp.controllers;

import com.example.ejournalapp.domain.User;
import com.example.ejournalapp.repository.UserRepository;
import com.example.ejournalapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
