package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/users")
public class userController {
    @Autowired
    private UserService userService;
    @PostMapping("/records")
	   public ResponseEntity<?> addData(@RequestBody User obj) {
	       if (userService.existsByEmail(obj.getEmail())) {
	           return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists!");
	       }
	       User createdUser = userService.createNewRecordService(obj);
	       return ResponseEntity.ok(createdUser);
	   }
   @GetMapping("/records")
   public List<User> getAllData() {
       return userService.getAllRecordsService();
   }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User userData) {
        System.out.println(userData);
        User user = userService.findByEmail(userData.getEmail());
        if (user != null && user.getPassword().equals(userData.getPassword())) {
            System.out.println(user);
            return ResponseEntity.ok(user);
        }
        return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }
}
