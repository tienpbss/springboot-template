package com.spring.demo.service;

import com.spring.demo.dto.PasswordUserDto;
import com.spring.demo.dto.UserCreateDto;
import com.spring.demo.dto.UserResponseDto;
import com.spring.demo.dto.UserUpdateDto;
import com.spring.demo.entity.User;
import com.spring.demo.mapper.UserMapper;
import com.spring.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto create(UserCreateDto dto) {
        User userEntity = userMapper.toEntity(dto);
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        User newUser = userRepository.save(userEntity);
        return userMapper.toDto(newUser);
    }

    public List<UserResponseDto> getAlls() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtos(users);
    }

    public UserResponseDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public UserResponseDto update(Long id, UserUpdateDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateEntityFromDto(dto, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);

    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponseDto setNewPassword(Long id, PasswordUserDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }
}
