package com.spring.demo.utils;

import com.spring.demo.entity.User;
import com.spring.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Boolean adminExist = userRepository.existsByUsername("admin");
        if (adminExist) {
            System.out.println("Admin user already exists. Skipping data initialization.");
            return;
        }
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEmail("admin@gg.com");
        userRepository.save(user);
        System.out.println("Init admin account: admin/admin");
    }
}