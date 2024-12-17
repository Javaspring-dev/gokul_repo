package com.example.carbooking.controller;
import com.example.carbooking.entities.UserEntity;
import com.example.carbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<UserEntity>createUser( @RequestBody UserEntity userEntity) {
        UserEntity savedEntity=userService.create(userEntity);
        return ResponseEntity.ok(savedEntity);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable int id) {
        UserEntity userEntity = userService.getById(id);
        return ResponseEntity.ok(userEntity);
    }
}