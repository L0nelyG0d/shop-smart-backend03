package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
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
}