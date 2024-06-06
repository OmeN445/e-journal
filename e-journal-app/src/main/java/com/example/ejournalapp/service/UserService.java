package com.example.ejournalapp.service;

import com.example.ejournalapp.domain.User;

public interface UserService {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    User save(User user);
}
