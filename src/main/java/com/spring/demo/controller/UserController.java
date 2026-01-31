package com.spring.demo.controller;

import com.spring.demo.dto.PasswordUserDto;
import com.spring.demo.dto.UserCreateDto;
import com.spring.demo.dto.UserResponseDto;
import com.spring.demo.dto.UserUpdateDto;
import com.spring.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponseDto create(@RequestBody @Valid UserCreateDto dto) {
        return userService.create(dto);
    }

    @GetMapping
    public List<UserResponseDto> getAlls() {
        return userService.getAlls();
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/me")
    public UserResponseDto getMe() {
        // For demonstration, returning a static user. In real scenarios, fetch from security context.
        return userService.getById(1L);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PatchMapping("/{id}/password" )
    public UserResponseDto setNewPassword(@PathVariable Long id, @RequestBody @Valid PasswordUserDto dto) {
        return userService.setNewPassword(id, dto);
    }


}
