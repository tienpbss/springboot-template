package com.spring.demo.service;

import com.spring.demo.entity.User;
import com.spring.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> getAlls() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setAge(user.getAge());
                    existingUser.setAddress(user.getAddress());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User setNewPassword(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setPassword(user.getPassword());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }
}
