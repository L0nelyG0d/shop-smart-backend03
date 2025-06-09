package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserRepository userRepository;

    private final UserService userService;
    @Autowired
    public UserController(UserService userService, UserRepository userRepository){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.createUser(
                user.getPassword(),
                user.getUsername(),
                user.getEmail()
        );
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestParam String password) {
        User updated = userService.updatePassword(id, password);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<User> updateEmail(@PathVariable Long id, @RequestParam String email) {
        User updated = userService.updateEmail(id, email);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/username")
    public ResponseEntity<User> updateUsername(@PathVariable Long id, @RequestParam String username) {
        User updated = userService.updateUsername(id, username);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return ResponseEntity.ok(user);
    }
}