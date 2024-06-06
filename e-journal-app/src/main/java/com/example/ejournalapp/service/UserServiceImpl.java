package com.example.ejournalapp.service;

import com.example.ejournalapp.domain.User;
import com.example.ejournalapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
