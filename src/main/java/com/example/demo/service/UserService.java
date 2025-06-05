package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(String password, String username, String email){
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);

        userRepository.save(user);

        return user;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updatePassword(Long id, String newPassword) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    public User updateEmail(Long id, String newEmail) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(newEmail);
        return userRepository.save(user);
    }

    public User updateUsername(Long id, String newUsername) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(newUsername);
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}