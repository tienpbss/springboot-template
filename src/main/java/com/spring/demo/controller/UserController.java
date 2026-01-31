package com.spring.demo.controller;

import com.spring.demo.entity.User;
import com.spring.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public List<User> getAlls() {
        return userService.getAlls();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/me")
    public User getMe() {
        // For demonstration, returning a static user. In real scenarios, fetch from security context.
        return userService.getById(1L);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PatchMapping("/{id}/password" )
    public User setNewPassword(@PathVariable Long id, @RequestBody User user) {
        return userService.setNewPassword(id, user);
    }


}
